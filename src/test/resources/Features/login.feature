Feature:Free CRM features

 Background:
  	Given the browser has to open here
  	
@Sanity

Scenario Outline: Free CRM Successful Login Test Scenario
Given user is on Login Page
When title of login page is Free CRM
Then user enters "<username>" and "<password>"
Then user clicks on login button
Then user is on Landing page

Examples:
|username | password |
|bala.murthy@gmail.com | Password@123|
|xyz.d@gmail.com | abd@293 |


