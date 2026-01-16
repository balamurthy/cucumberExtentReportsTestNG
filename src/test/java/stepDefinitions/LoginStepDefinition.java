package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.BaseTest;

public class LoginStepDefinition extends BaseTest{

	 static WebDriver driver;
	
	 //caution-This is a hook. Runs only once  
	
	 /*@Before
	 public void init()
	 {
		 driver=getDriver();
	 }
	 */

		
	 
	 //Background runs once per scenario! 
	 @Given("the browser has to open here")
	 public void the_browser_has_to_open_here() {
	     // Write code here that turns the phrase above into concrete actions
		 driver.get("https://ui.freecrm.com/");
	 
	 }
	
	 @Given("^user is on Login Page$")
	 public void user_is_on_login_page() {
	    
		 boolean isDisplayed= driver.findElement(By.name("email")).isDisplayed();
		 
		 Assert.assertEquals(isDisplayed,true);
		 
	 }
	
	
	 @When("^title of login page is Free CRM$")
	 public void title_of_login_page_is_free_CRM(){
	 String actualtitle = driver.getTitle();
	 System.out.println(actualtitle);
	 Assert.assertEquals("Free CRM", actualtitle);
	 }
	
	 
	 @Then("^user enters \"(.*)\" and \"(.*)\"$")
	 public void user_enters_username_and_password(String username, String password){
	 driver.findElement(By.name("email")).sendKeys(username);
	 driver.findElement(By.name("password")).sendKeys(password);
	 }
	
	 @Then("^user clicks on login button$")
	 public void user_clicks_on_login_button() {
	 WebElement loginBtn =
	 driver.findElement(By.cssSelector("#ui > div > div > form > div > div.ui.fluid.large.blue.submit.button"));
	 JavascriptExecutor js = (JavascriptExecutor)driver;
	 js.executeScript("arguments[0].click();", loginBtn);
	 }
	
	
	 @Then("^user is on Landing page$")
	 public void user_is_on_Landing_page(){
	 boolean result;
	//span[@class="user-display"]
	 try {
		 driver.findElement(By.xpath("//span[@class='user-display']"));
		 result=true;
		 
		 //Log Out if logged in successfully.
		//*[@id="top-header-menu"]/div[2]/div[2]/div
		 driver.findElement(By.xpath("//div[@role=\"listbox\"]")).click();
		 driver.findElement(By.partialLinkText("Out")).click();
	 }
	 catch(NoSuchElementException e) {
		 result=false;
	 }
	 Assert.assertEquals(result, true);
	 }
	 
		

}
