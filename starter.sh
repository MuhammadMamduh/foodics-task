mvn clean test -DsuiteXmlFile=smoke-suite.xml
allure generate
mv allure-report src/test/execution-results/test-report/
allure serve src/test/execution-results/test-report