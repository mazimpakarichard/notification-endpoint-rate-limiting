# notification-endpoint-rate-limiting
the api rate limit for designed in spring boot

This project is a solution for rate limiting the SMS and Email notification service provided by Corporation X,Y,Z. The main objective of this project is to implement a rate limiting system for the API that will prevent the system from being overloaded and ensure that each client is using the service according to their allotted limits.

# Architecture:

The rate limiter has been designed to work for a distributed setup, as the APIs are accessible through a cluster of servers. The system consists of three main components:

Request Counting: This component is responsible for counting the number of requests being made by each client. It maintains a record of the number of requests made by each client within a specific time window (e.g., 1 minute, 1 hour, or 1 day).

Throttling: This component is responsible for enforcing the rate limits set for each client. It checks the request count for each client and enforces throttling if the limit is exceeded.

Distributed Data Store: This component is responsible for storing the request count data for each client. A distributed data store such as Redis can be used for this purpose.

Throttling:

Two types of throttling have been implemented:

Soft Throttling: In this case, the system will not reject any requests. Instead, it will delay the requests and process them at a slower rate to ensure that the total number of requests from all clients stays within the limit.

Hard Throttling: In this case, the system will reject any requests that exceed the limit set for each client. The client will receive an error message and will not be able to send any more requests until the limit resets.

### PREEQUISITES

  1. JAVA 8
  2. MAVEN 3.8.1
  3. redis server
  4. postgres 13 or later
  5. prometheus 2.28.1
  6. grafana 9.5.1


Running the Solution:

The following steps can be followed to run the solution:

Clone the Github repository: 

```
git clone https://github.com/mazimpakarichard/notification-endpoint-rate-limiting.git
```

Install the dependencies:

```
mvn clean install -DskipTests
```


# queries to run 
```
you should run the queries found under src/main/resource in the sql folder before running the application
```

# Start the server:
```
mvn spring-boot:run
```

Send requests to the API: Use any HTTP client (e.g., Postman) to send requests to the API. The API endpoint for sending SMS notifications is 

```
[/notifications/sms](http://localhost:9000/notifications/email)

```
and for sending email notifications is

```
["/notifications/email".](http://localhost:9000/notifications/sms)

```

Testing:

The solution has been tested using unit tests and integration tests. The unit tests ensure that each component of the system is functioning correctly, while the integration tests ensure that the system as a whole is working correctly.

Conclusion:

This solution provides a scalable and distributed rate limiting system for the SMS and Email notification service provided by Corporation X,Y,Z. The system ensures that each client is using the service according to their allotted limits, and prevents the system from being overloaded. The solution can be further improved by adding support for dynamic rate limits and by using machine learning algorithms to optimize the rate limits for each client.





