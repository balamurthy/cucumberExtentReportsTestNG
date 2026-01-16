package utils;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    protected static WebDriver driver;

    // Private constructor prevents object creation
    public BaseTest() {}


    public static WebDriver getDriver() {

        if (driver == null) {
            synchronized (BaseTest.class) {
                if (driver == null) {
                	
                	//--
                    ChromeOptions options = new ChromeOptions();
                    
                    // Create a HashMap to store the preferences
                    Map<String, Object> prefs = new HashMap<String, Object>();
                    
                    // Disable "Offer to save passwords"
                    prefs.put("credentials_enable_service", false);
                    
                    // Disable "profile.password_manager_enabled"
                    prefs.put("profile.password_manager_enabled", false);
                    
                    // Disable the "Change your password" security pop-up (for newer Chrome versions)
                    prefs.put("profile.password_manager_leak_detection", false);

                    // Set the experimental options
                    options.setExperimentalOption("prefs", prefs);

                    // Initialize the ChromeDriver with the configured options
                    driver = new ChromeDriver(options);
                	driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                }
            }
        }
        return driver;
    }

    public static void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
            "arguments[0].style.border='3px solid red'",
            element
        );
    }
    
    public String takeScreenshot(String testName) {
        String path;
    	try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            String dir = System.getProperty("user.dir") + "/screenshots/";
            FileUtils.copyFile(
                src,
                new File("./screenshots/" + testName + ".png")
            );
            path= dir   + testName + ".png";
            System.out.println("Screenshot available at " + path);
    	} catch (Exception e) {
            e.printStackTrace();
            path="Exception occured";
            
        }
        return path;
    }
   
    public static String linkValidation(String hyperLinkUrl) {
        String result = "";
        try {
            URL url = new URL(hyperLinkUrl);

            HttpURLConnection openConnection = (HttpURLConnection) url.openConnection();
            openConnection.setConnectTimeout(5000);
            openConnection.connect();

            if (HttpURLConnection.HTTP_OK == openConnection.getResponseCode()) {
                result = hyperLinkUrl + " : OK " + openConnection.getResponseCode();
            
            } else {
                result = hyperLinkUrl + " : " + openConnection.getResponseMessage();
            }
        } catch (Exception e) {
            result = "Exception caught: " + e.getMessage();
        }
        return result;
    }
    
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
