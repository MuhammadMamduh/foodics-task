This framework contains the required UI test cases to test the functionality of a web application.
This framework contains the required API test cases.
I've applied the Single Responsibility Principle in the components package.
You can easily spot the composition relationship between the components and the pages.
I've applied the Page Object Model design pattern.
I've applied the Factory Design Pattern to abstract the generation of the webdriver.
I've used TestNG to run the tests.
I've created 2 independent Test Suites to run the tests, one for the APIs and the other for the UI Tests.
I've used the properties file to store the API base URL.
I've used the log4j2 library to generate readable logs (src/test/execution-results/logs).
I've used allure reports to generate clean test reports (src/test/execution-results/test-report).
I've created a simple bash script as the entrypoint to run the framework.