# Home-work #
--------------

Question) Please create a Spring Boot application with a "Hello World" REST endpoint. This project will contain all the following REST endpoints also.
Answer) API Endpoint to test this : curl -v http://localhost:8088/helloWorld/

Question) Please add a REST endpoint that accepts a JSON object containing a paragraph of text and returns a JSON array of objects. The returned objects represent the unique words from the supplied paragraph along with a count of the number of occurrences. The array of objects must be sorted alphabetically.
Answer) API Endpoint to test this : curl -v -H "Accept: application/json" -H "Content-Type:application/json" --data '{"text": "<paragraph>"}' "http://localhost:8088/helloWorld/paragraph"
Example : curl -v -H "Accept: application/json" -H "Content-Type:application/json" --data '{"text": "This is a test paragraph that tests the occurrence of word test"}' "http://localhost:8088/helloWorld/paragraph"

Question) Please add a REST endpoint that accepts a number, N, and returns a JSON array with the first N Fibonacci numbers. Please use the recursive form of Fibonacci. Be prepared to answer O() complexity questions when we review the submission by phone.
Answer) API Endpoint to test this : curl -v http://localhost:8088/helloWorld/fibonacci/<number>
Example : curl -v http://localhost:8088/helloWorld/fibonacci/10

Question) Please add a REST endpoint that creates two threads that become deadlocked with each other. Use the REST function to monitor the two threads and detect the deadlock, i.e. neither thread exits after a timeout of your choosing. Hint: We want to see you use the synchronized keyword.
Answer) API Endpoint to test this : curl -v http://localhost:8088/helloWorld/deadlock

Question) Please add three REST endpoints that add, query, and delete rows in a database. Please use the HyperSQL (hsqldb.org) database dependency for an in-process database. Hint: Initialize the database when your Spring Boot application starts. Hint:http://www.programmingforfuture.com/2011/02/in-process-mode-of-hsqldb-in-web.html
I have created a table named employee for transactional purposes
Answer) API Endpoint to test this : curl -v http://localhost:8088/helloWorld/employees
List all employees : curl -v http://localhost:8088/helloWorld/employees

List specific employee : curl -v http://localhost:8088/helloWorld/employees/getEmployee/<employeeId>
Example : curl -v http://localhost:8088/helloWorld/employees/getEmployee/1

Add new employee : curl -v -H "Accept: application/json" -H "Content-Type:application/json" --data '{"name": "<name>", "address": "<address>", "salary": <salary>}' "http://localhost:8088/helloWorld/employees/addEmployee"
Example : curl -v -H "Accept: application/json" -H "Content-Type:application/json" --data '{"name": "Johnny Walker", "address": "1099 Stevenson Blvd, Fremont, California, USA", "salary": 72542.1}' "http://localhost:8088/helloWorld/employees/addEmployee"

Delete an employee : curl -v -X DELETE http://localhost:8088/helloWorld/employees/deleteEmployee/<employeeId>
Example : curl -v -X DELETE http://localhost:8088/helloWorld/employees/deleteEmployee/1

Question) Finally, please create a REST endpoint that queries an external REST service using Spring RestTemplate. The response should be returned to the caller. We suggest using this external service: https://jsonplaceholder.typicode.com/posts
Answer) API Endpoint to test this : curl -v http://localhost:8088/helloWorld/externalService


-- Test classes :
HelloWorldControllerTest.java
WordControllerTest.java
FibonacciControllerTest.java
DeadlockThreadControllerTest.java
EmployeeControllerTest.java
ExternalServiceControllerTest

-- hsqldb configuration :
src/main/resources/application.yml

-- Command to compile and build :
mvn clean install 