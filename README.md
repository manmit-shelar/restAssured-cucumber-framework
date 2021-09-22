# About the Framework
  1. This is an customized restAssured-Cucumber framework.
  2. At the base this framework uses Cinamon(Cucumber-BDD) Framework along with restAssured APIs.

  Built with
  1. Java
  2. JUnit
  3. Gherkins
  4. restAssured
  5. log4j

# Running the test
01. From Eclipce :  
  Run test by setting correct tags in AllTest.java and by setting correct environment in Run Configuration.

01. From Command Prompt :  
  mvn clean install -Denv=envName -Dcucumber.options="--tags @tagName"  
  -Denv = Name of environment profile defined in env.conf.  
  -Dcucumber.options = tags to be executed. Need to select respective tags from feature file.
  

# Reporting
01. Donut Report
    - Cinnamon provides out the box pretty, summarized Donut report.
    - After every sucessful execution report will get generated at target/donut.

02. API log summary
    - runtime.log provides details of API calls at scenario level.

# Automating Practice APIs
 - Best Buy API Playground provides free E-Commerce Rest APIs to practice.
 -  Best Buy API Playground was locally deployed for practice.
 - In the sample project, 'Store' APIs are automated.
 
 API Automated
 ![image](https://user-images.githubusercontent.com/19619344/134340865-99eb754a-083a-40ed-94b3-017b65781efe.png)

