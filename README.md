# SeleniumWBL
 Automate Wallmart.com- search flow

Used Data driven approach

The TestNg.xml file contains test suites to be run 

The TestNg.xml file is added as a configuration in pom.xml so that it can be run using mvn command


The Java test files(SearchTest.java and HomePageTest.java) reads data from TestData/WalmartTestData.xlsx file .

The testdata is converted into key value pairs and then fed to WBLAssingment\src\test\java\com\wbl\test\*.java  file


Test can be run using following

  eg mvn test


