package runners;

import com.codeborne.selenide.Configuration;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import utils.ListenerTestNG;


@CucumberOptions(
        tags = "@Smoke",
        features = {"src/test/java/features"},
        glue = {"stepdefs"},
        plugin = { "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "summary" }
)

@Listeners(utils.ListenerTestNG.class)
public class Runner_Smoke extends AbstractTestNGCucumberTests {

    @BeforeSuite
    public void beforeSuite(){

    }
}
