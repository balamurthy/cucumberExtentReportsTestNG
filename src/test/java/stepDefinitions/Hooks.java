package stepDefinitions;

import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.BaseTest;
import utils.ExtentReportManager;
import utils.ExtentTestManager;


public class Hooks extends BaseTest{

    private static ExtentReports extent = ExtentReportManager.getExtent();

    @Before
    public void beforeScenario(Scenario scenario) {
        ExtentTestManager.setTest(
            extent.createTest(scenario.getName())
        );
    }

    @After
    public void afterScenario(Scenario scenario) {

        if (scenario.isFailed()) {
            ExtentTestManager.getTest()
                .log(Status.FAIL, "Search did not fetch results");
            String name =
            	    "Search Failed for "+ ScenarioContext.get("Keyword");
            		
            String path = takeScreenshot(name);
            ExtentTestManager.getTest()
            .addScreenCaptureFromPath(name);
        } else {
            ExtentTestManager.getTest()
                .log(Status.PASS, "Scenario Passed");
        }

        extent.flush();
        ExtentTestManager.removeTest();
    }
}
