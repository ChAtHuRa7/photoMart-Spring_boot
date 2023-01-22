package com.photomart.apigateway.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponseModel{

    private String errCode;
    private String err;
    private  String errDetails;
    private Date date;
}
