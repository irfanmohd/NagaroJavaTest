# NagaroJavaTest
Bank Account Statment REST API
What is this project for?
The goal of this mini project is to write a simple Spring Rest Services for fetching bank statement based on Date ranges, Amount ranges etc. Through this Rest service, Admin can can access all the services and User only access with out parameters service.More details about the services are described below.
The request can specify from date and to date (the date range).

Added Security for ADMIN and USER Role
-> Admin can perform all the operations
-> User can perform only limited

How do I run the project?
mvn spring-boot:run

Rest Endpoints

URL	                                                                                                          Http Verb
http://localhost:8080/api/account/get/statement/by/dates/{accountId}/{fromDate}/{toDate}	                      GET
http://localhost:8080/api/account/get/statement/by/amounts/{accountId}/{fromAmount}/{toAmount}	                GET
http://localhost:8080/api/account/get/last/three/month/statement	                                              GET


How to test API from postman 

![Nagaro](https://user-images.githubusercontent.com/39884239/133926121-e2ca2175-5a1f-41c8-aff4-0d3be4e6f153.PNG)


Account Number sendingto user using Bcryt hashing technique.

Note: Unable to start SonarQube server in my laptop due to java version mismatch. It is expecting java 11 but i am using java 8 even i installed java 11 still giving error while generating sonar qube report and jacoco report.
