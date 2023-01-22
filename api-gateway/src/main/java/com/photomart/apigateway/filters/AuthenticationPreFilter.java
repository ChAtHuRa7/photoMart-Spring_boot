package com.photomart.apigateway.filters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.photomart.apigateway.Exceptions.ExceptionResponseModel;
import com.photomart.apigateway.models.Authority;
import com.photomart.apigateway.models.respons.JwtValidationResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class AuthenticationPreFilter extends AbstractGatewayFilterFactory<AuthenticationPreFilter.Config> {

    @Autowired
    @Qualifier("excludedUrls")
    private List<String> excludedUrls;

    @Autowired
    private ObjectMapper objectMapper;


    private final WebClient.Builder webClientBuilder;

    public AuthenticationPreFilter(WebClient.Builder webClientBuilder){
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange,chain)->{

            ServerHttpRequest request = exchange.getRequest();
            String bearerToken =request.getHeaders().getFirst("Authorization");

            if(isSecured.test(request)){
                return webClientBuilder.build().get()
                        .uri("lb://authentication-service/api/v1/auth/")
                        .header("Authorization" , bearerToken)
                        .retrieve().bodyToMono(JwtValidationResponse.class)
                        .map(response -> {
                            exchange.getRequest().mutate().header("UserName",response.getUserName());
                            exchange.getRequest().mutate().header("JwtToken",response.getJwt());
                            exchange.getRequest().mutate().header("Authorities" , response.getAuthorities().stream()
                                    .map(Authority::getAuthority).reduce("",(a,b)-> a+","+b));

                            return exchange;
                        }).flatMap(chain::filter).onErrorResume( error ->{
                            HttpStatus errorCode = null;
                            String errorMsg = "";

                            if(error instanceof WebClientResponseException){
                                WebClientResponseException webClientResponseException = (WebClientResponseException) error;

                                errorCode = (HttpStatus) webClientResponseException.getStatusCode();
                                errorMsg = webClientResponseException.getStatusText();
                            }else {
                                errorCode = HttpStatus.BAD_GATEWAY;
                                errorMsg = HttpStatus.BAD_GATEWAY.getReasonPhrase();
                            }
                            return onError(exchange,String.valueOf(errorCode.value()),errorMsg,"JWT Authentication Failed",errorCode);
                        });


            }
            return chain.filter(exchange);
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, String errCode, String err,String errDetails ,HttpStatus httpStatus) {
        DataBufferFactory dataBufferFactory = exchange.getResponse().bufferFactory();

        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);

        try {
            response.getHeaders().add("Content-Type","application/json");
            ExceptionResponseModel data = new ExceptionResponseModel(errCode,err,errDetails,new Date());
            byte[] byteData = objectMapper.writeValueAsBytes(data);
            response.writeWith(Mono.just(byteData).map(dataBufferFactory::wrap));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return response.setComplete();
    }

    public Predicate<ServerHttpRequest> isSecured = request -> excludedUrls.stream().noneMatch(uri -> request.getURI().getPath().contains(uri));

    @NoArgsConstructor
    public static class Config{

    }
}
