FROM openjdk:19

COPY target/*.jar app.jar

EXPOSE 8086

ENTRYPOINT ["java","-jar","/app.jar"]