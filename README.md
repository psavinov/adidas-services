# Adidas Services (Technical Test)
Adidas Product and Review services.

## How to run
There are two possible options to run the solution:
1) Running locally, using Springboot Maven plugin:
	1) Check <b>application.properties</b> in both modules (review-service and product-service) and set 
	appropriate properties: Review writing credentials, ports and API endpoints. <b>Services ports must be different.</b>
	2) Start review-service first, in order to do that, change the directory to review-service and execute 
	a command: <code>./mvnw spring-boot:run</code> (or <code>mvnw spring-boot:run</code> on Windows).
	3) Change the directory to product-service and execute the same Maven command - <code>spring-boot:run</code>
	4) Assert that both services are running and there are no errors in the standard output.<br><br>
2) Running using Docker Maven plugin:
	1) Go to the repository root directory and run the following command:<br>
	<code>mvn --projects review-service,product-service package -DskipTests && mvn --projects review-service,product-service dockerfile:build</code><br><br>
	Assert that both modules were built successfully.
	2) In any directory execute the following Docker commands:<br><b>Change environment variables if needed, but don't forget to change port mapping and use correct Review service host if changed.</b>

		* <code>docker network create service-net</code>
		* <code>docker run -p 8082:8082
		--net service-net
		--name review-host
		-e REVIEW_SERVICE_PORT=8081
		-e REVIEW_ADMIN_USER=admin
		-e REVIEW_ADMIN_PASS=admin
		-t adidas-psavinov/review-service</code>
		* <code>docker run -p 8082:8082
		--net service-net
		--name product-host
		-e PRODUCT_SERVICE_PORT=8082
		-e REVIEW_SERVICE_HOST=review-host:8081
		-t adidas-psavinov/product-service</code>
		
		Assert that both services are running and there are no errors in the standard output.
		
## How to use
When started, services become available on the specified ports, some preloaded data is available:<br><br>
<b>Review Service:</b> <a href="http://localhost:8081/review/BB5476">http://localhost:8081/review/BB5476</a><br>
<b>Product Service: </b> <a href="http://localhost:8082/product/BB5476">http://localhost:8082/product/BB5476</a>
	
## How to test
There are tests for both endpoints, to run the tests execute the following command in the corresponding service module directory:<br>
<code>./mvnw test</code> (or <code>mvnw test</code> on Windows)<br><br>
Assert that all tests passed successfully. <b><i>There are exceptions stack traces during the tests execution, it is an expected 
behaviour caused by corresponding logger.error() calls in the DataProvider implementations</i></b>.
		
	

	

