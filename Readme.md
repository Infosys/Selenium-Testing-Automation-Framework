Selenium OSTAF
===============
Selenium Open Source Testing Automation Framework(OSTAF) is a framework which helps the tester in automating test execution of web applications without 
recording or writing the selenium scripts.

How to build
-------------
Unzip the selenium OSTAF zip to a folder on local machine
Open Command Prompt as administrator
Change directory to the folder containing the OSTAF code base
run the command "maven install" and click enter Key in keyboard

---User need to navigate the command prompt to the project path and then need to type mvn install and enter the build will happen.


How to use
-----------
The SeleniumOSTAF-0.0.1-SNAPSHOT jar generated from the build can be placed anywhere in the system and config.properties file
need to be in the same directory or folder where the jar is placed.

Tester needs to be fill the configuration details like 
1. application URL
2. Browser name
3. Path of supporting driverserver.exe file of browser
4. Path of the excel sheet which has the list of test cases to be executed
5. Path of the excel sheet which has the test step deatils for each test case.

 Tester needs to fill two excel sheets,

1. The TestSuite.xslx in which tester needs to specify the testcases names and execution status with either YES or NO. 
 
2. The TestCase.xslx in which first sheet is "TestCase" will have columns which describe each test cases with details as
	i.  test case name
	ii. TestStepId (which user can give any name to define the particular step in the testcase)
	iii.TestSteps which user has to give unique step ids like TC1,TC2 etc
	iv. Page where tester need to give the defined name for each page of web application corresponding to the web element in the step
	v.  ObjectLocators is the one defines the property of the web element
	vi. ActionType is the operation which needs to be performed on the specified web element (which has to be one of the defined keywords)
	vii.Data in which user needs to specify the sheetname followed by "." and the coloumn name whose next row is the data to be given to the web element
 
Next Sheet is the "CapturedObjectProperties" sheet where we define the properties of the webelement. The sheet has the columns with details as 
	i.   First coloumn is "Page" in which user can give any name to the page in which required web element is present
	ii.  Second one is the "NAME" which is also user given name to webelement but this name and the ObjectLocator in the first sheet should be same
	iii. Third coloumn is "Property" which define how user is identifying the element like ID, XPATH,CSS and NAME
	iv.  Fourth one is "Value" which is corresponding to the property. 
After defining all the webelements user has to specify	NA in all coloumns to identify the end of the page. User can give any number of sheets to provide data in the first page. 

This framework is licensed under Apache License, Version 2.0 


