package runners;

import com.codeborne.selenide.Configuration;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import utils.ListenerTestNG;


@CucumberOptions(
        tags = "@ContactUs",
        features = {"src/test/java/features/Contact.feature"},
        glue = {"stepdefs"},
        plugin = { "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "summary" }
)

@Listeners(ListenerTestNG.class)
public class Runner_ContactUs extends AbstractTestNGCucumberTests {
    @BeforeSuite
    public void beforeClass(){
        Configuration.timeout = 10000;
    }
}
