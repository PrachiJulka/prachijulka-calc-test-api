<p align="center">
    <img width= "240" src="https://github.com/PrachiJulka/prachijulka-calc-test-api/blob/main/src/main/resources/static/image/calculator.png"/>
</div>

<h1 align="center">prachijulka-calc-test-api</h1>
<h5 align="center">Calculator API is for performing the calculations with following operators: Addition(+),Subtraction(-),Division(/),Multiplication(*,x,X).</h5>

## :monocle_face: Introduction
This API is build on Java framework. It can be used to calculate any size of integer or decimal value, it contains constraints such as:
- There can only be two operands and one operator.
- If there are more than two operands or more than one operator then it will give an error.
- It also displays the type of number the expression result produces.
- Expression's result number type can be identified as Positive, Negative, Whole numbers, Natural numbers and Prime numbers.

## :wrench: Build Status 
[![Test Status](https://circleci.com/gh/PrachiJulka/prachijulka-calc-test-api.svg?style=shield)](https://circleci.com/gh/PrachiJulka/prachijulka-calc-test-api?style=shield)

## :rocket: Tech/framework used
- Locust 1.4.1 : For load testing
- CircleCI: For CI/CD pipeline
- Java 8
- SpringBoot 2.3.5.RELEASE
- Gradle 5.6.4
- Junit-5 : For Integration and Unit testing
- Javascript
- Jquery
- Bootstrap

## :file_folder: Important file,folder and their usage
- **locust-master.py** file inside root directory contains the task to run the locust master.
- **.circleci** inside root directory contains **config.yml** which has configuration to implement CI/CD using circleci.
- **docker** folder inside main directory contains **DockerFile** which is used to create the docker image.
- **integrationTest** folder inside src directory has integration testing files.
- **main** folder contains **APIS** which are developed using java.
- **test** folder inside **src** directory contains unit tests.

## :gear: Installation
To use this application in developer mode. There is a need of an IDE and for developing this project IntelliJIDEA Ultimate 2018.4 is used.
- You can use Eclipse,IntelliJIDEA or netbeans.
- You need to have Java 8 and Locust 1.4.1 installed on your system.
- You need to have SpringBoot 2.3.5.RELEASE, Gradle 5.6.4 installed in your IDE.

## :test_tube: Tests

**Note:** _The individual test reports can be accessible by clicking badge that is adjacent to their respective heading heading._ 

### Unit Testing [![UnitTest: Report](https://img.shields.io/badge/Unit%20Test-Report-green.svg)](https://prachijulka.github.io/prachijulka-calc-test-api/src/main/resources/templates/test-report/test/index.html)

Unit Testing is implemented using Junit. To run the unit tests one can run the following command:<br/>
`./gradlew test` <br/>
After running the above command you will see the following result.<br/>

![picture](https://github.com/PrachiJulka/prachijulka-calc-test-api/blob/main/src/main/resources/static/image/UnitTest.png)

### Integration Testing [![IntegrationTest: Report](https://img.shields.io/badge/Integration%20Test-Report-green.svg)](https://prachijulka.github.io/prachijulka-calc-test-api/src/main/resources/templates/test-report/integrationTest/index.html)

Integration Testing is implemented using Junit. To run the integration tests you can run the following command: <br/>
`./gradlew integrationTest` <br/>
After running the above command you will see the following result.<br/>

![picture](https://github.com/PrachiJulka/prachijulka-calc-test-api/blob/main/src/main/resources/static/image/IntegrationTest.png)

### Load Testing [![LoadTest: Report](https://img.shields.io/badge/Load%20Test-Report-green.svg)](https://prachijulka.github.io/prachijulka-calc-test-api/src/main/resources/templates/locustReport/report_1607175264.055484.html)
Load Testing is implemented using Locust. To run the Locust you need to install locust using command <br/>

`pip3 install locust` </br>

After installing locust run master, to run master you need to run the following command <br/>

`locust -f locust-master.py --master --master-bind-host=127.0.0.1 --master-bind-port=5557` <br/>

After running the above command execute LoustMain file <br/>

![picture](https://github.com/PrachiJulka/prachijulka-calc-test-api/blob/main/src/main/resources/static/image/LocustTest.png)

After that, you can run the Locust Java slave using this command <br/>

`locust -f locust-master.py --master --master-bind-host=127.0.0.1 --master-bind-port=5557` <br/>

There are 2 tasks for which load testing is implemented.<br/>
1. First task(Open application task) is to access the appplication.(http://localhost:8080/)
2. Second task(Calculate Expression Task) is to access the calculate api and get result.(http://localhost:8080/calculate=2/2)

## :page_with_curl: API Reference
There is no authentication require to access this API.

You can access API by following url <br/>

`GET http://localhost:8080/calculate?expression=2/2`

| Parameter | Type | Description |
| --- | --- | --- |
| expression | String | **Required**. Your expression |

### Responses
You will get the response in JSON format if its a success then

<pre> {
    "status": true,
    "recordsList": [
        {
            "expression": "2*2",
            "result": "4",
            "naturalNumber": true,
            "wholeNumber": true,
            "positiveNumber": true,
            "negativeNumber": false,
            "primeNumber": false
        }
    ]
}</pre>

If it's failure then

<pre>
{
    "msg": [
        "Wrong expression syntax"
    ],
    "status": false
}
</pre>

### Status Codes
Following status codes are returned in this api.

| Status Code | Description |
| --- | --- |
| 200 | `ok` |
| 400 | `BAD REQUEST` |
| 500 | `INTERNAL SERVER ERROR` |
| 404 | `NOT FOUND` |

## :memo: How to use ?
To use this application via UI one can run the application and go on http://localhost:8080/.

![picture](https://github.com/PrachiJulka/prachijulka-calc-test-api/blob/main/src/main/resources/static/image/UI.png)

Write expression on the input box and click on submit button.You will see the following result.

![picture](https://github.com/PrachiJulka/prachijulka-calc-test-api/blob/main/src/main/resources/static/image/UIresult.png)

To access rest api call use following url

`http://localhost:8080/calculate?expression=2/2`
 
**Note:-** 2/2 are the expression value that is taken by the user.


## :mailbox: Contact
prachijulka31@gmail.com
