FROM openjdk:19

COPY target/*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java","-jar","/app.jar"]