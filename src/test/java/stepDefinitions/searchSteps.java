package stepDefinitions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import static utils.ExtentTestManager.getTest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.BaseTest;


public class searchSteps extends BaseTest{

	static WebDriver driver;
	static String srchitem;
	@AfterAll
	public static void closeall()
	{
		driver.close();
		
	}
	@BeforeAll
	public static void beforeall()
	{
		driver=getDriver();

	}
	

	@Given("^Search box is displayed$")
	public void search_box_is_displayed() {
    // Write code here that turns the phrase above into concrete actions
	
	try {
		driver.findElement(By.id("autoComplete")).isDisplayed();
		getTest().log(Status.INFO, "Found the search box!");
	}
	catch(NoSuchElementException e)
	{
		System.out.println("Search box cannot be found.");
		getTest().log(Status.INFO, "Cannot find Search box");
	}
	
	}


@Given("the advantageonlineshopping.com website is opened")
public void the_advantageonlineshopping_com_website_to_open_here() {
    // Write code here that turns the phrase above into concrete actions
	driver.get("https://advantageonlineshopping.com");
}

@When("I enter a search keyword {string}")
public void i_enter_a_search_keyword(String string) {
	driver.findElement(By.id("autoComplete")).sendKeys(string);
	srchitem=string;
	getTest().log(Status.INFO, "User entered a search keyword "+ string);
	// ✅ store for later use
    
 }

@And("press enter key")
public void press_enter_key() {
	
	driver.findElement(By.id("autoComplete")).sendKeys(Keys.ENTER);
    	
}

@Then("validate search results")
public void validate_search_results() {
    // Write code here that turns the phrase above into concrete actions

	boolean success;
	try {

		WebElement noOfItems = ((WebDriver) driver).findElement(By.xpath("//a[@class=\"titleItemsCount ng-binding\"]"));
		success=true;
		System.out.println(noOfItems.getText());
		//Add the first item listed to cart
	    WebElement firstImage = ((WebDriver) driver).findElement(By.xpath("//*[@class=\"product ng-scope\"][1]/img//parent::a"));
		firstImage.click();
		driver.findElement(By.name("save_to_cart")).click();
		getTest().log(Status.PASS, "Search successful for: "+ srchitem);
		
	}catch (NoSuchElementException e) {

		System.out.println("Search term did not fetch results");
		success=false;
		getTest().log(Status.FAIL, "Search did not fetch results for: "+ srchitem);
		ScenarioContext.set("Keyword", srchitem);
		//driver.close();
	}
	
	Assert.assertEquals(success,true);
	}




}
