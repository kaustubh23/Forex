FROM openjdk:11
EXPOSE 8080
ADD target/springboot-forex.jar springboot-forex.jar
ENTRYPOINT ["java","-jar","/springboot-forex.jar"]