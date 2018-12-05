# JMeter test suite for Adidas Technical Test

To be able to perform integration or performance testing for our solution we
will user <a href="http://jmeter.apache.org/">Apache JMeter</a> software.<br>
JMeter is highly customisable test tool and perfectly suits microservices test needs.<br>

### Running JMeter Project

1) Start the solution locally or using Docker containers as described <a href="https://github.com/psavinov/adidas-services/blob/master/README.md">here</a>
2) Start JMeter and open AdidasMicroservicesTest.jmx project file
3) Choose a thread group you would like to test and press Start button on the control bar
4) Wait until the tests passed and observe the results in the Summary Report(latency, response time, etc.) or
in the Results Tree(particular execution details).
5) (Optional) Change a thread group parameters - number of threads, loop count, etc. and repeat steps 3 and 4.<br>
<br>

Proposed test suite performs the following tests:
* Login using basic auth
* Create a Review using random ID, reviews number and average score
* Update a Review with an existing ID
* Get list of reviews
* Delete a review
* Get valid product information from the product service
