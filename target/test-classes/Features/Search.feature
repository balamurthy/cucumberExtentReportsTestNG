Feature: search
  
  Searching for a product should give results
    Background:
  	Given the advantageonlineshopping.com website is opened
  	
  @Smoketest	
  Scenario Outline: Successful search results found
    Given Search box is displayed
    When I enter a search keyword "<Key>"
    And press enter key 
    Then validate search results  
    
    Examples:
    |Key|
    |Mouse|
    |Headphones|
    |abcdefg|
    |hijklmno|