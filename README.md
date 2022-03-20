
Project: Forex Assignment

Technical Spec:
Java 8, Spring boot, Apache Camel, cfx 
Build tool: Maven

How to run:
Checkout the code from github repository-> Details shared by url
Open the project in eclipse as existing maven project
Right click on ForexApplication.java class -> Run as -> Java Application

**Endpoint: **
Service: 1 
Details: Service to get the forex details. 
 
Method:
Post


http://localhost:9080/getforexDetails

Field description:
 amount: Number of multiples 
 from: Currency from which the conversion is required
 to: Currency to which conversion is required 			 

Sample Body:
{"amount": 1,"from":"ZAR","to":"EUR"} 

Field description:
totalAmount: conversion amount
date: current date

Sample Response:
{
"statusCode":0,
"status":"Success",
"totalAmount":0.06,
"date":"03/21/2022"
}

Service: 2
Description: To get total service request for forex
Method:Get
 
Endpoint: 
http://localhost:9080/getTotalRequests 

Field Description:
forextCombination: field from and to currency
totalNumbers: total attempt made
Sample Response:
{"statusCode":200,"status":"Success","forex":[{"forextCombination":"INR-ZAR","totalNumbers":1},{"forextCombination":"EUR-ZAR","totalNumbers":2}]} 
 
  

Remaining Tasks:
Code analysis tool implementation
Some test cases


Docker build commands: 

docker build -t springboot-forex .jar . 
docker run -p 9080:8080 springboot-forex.jar
