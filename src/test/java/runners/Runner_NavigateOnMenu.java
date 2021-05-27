package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



@CucumberOptions(
        tags = "@MainMenu or @FooterMenu",
        features = {"src/test/java/features"},
        glue = {"stepdefs"},
        plugin = { "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "summary" }
)

public class Runner_NavigateOnMenu extends AbstractTestNGCucumberTests {

}
