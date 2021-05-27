package runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        //tags = "@Menu",
        features = {"src/test/java/features/Produkte_LinksImagesVideos.feature"},
        glue = {"stepdefs"},
        plugin = { "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "summary" }
)

public class Runner_ProdukteLinksImagesVideos extends AbstractTestNGCucumberTests {
    /*
    @BeforeTest
    @Parameters("browser")
    public static void beforeTest(String browser){
        Configuration.browser = browser;
    }


    @AfterClass
    public static void afterClass() {
        Reporter.loadXMLConfig("src/test/java/XMLFiles/extentReport.xml");
        Reporter.setSystemInfo("User Name", "Grup-QA");
        Reporter.setSystemInfo("Application Name", "Mevlana Cay");
        Reporter.setSystemInfo("Operating System Info", System.getProperty("os.name"));
        Reporter.setSystemInfo("Department", "QA");
        Reporter.setSystemInfo("Project Name", "Mevlana Cay");
        Reporter.setTestRunnerOutput("Test excetuion Cucumber report");


    }

    @AfterTest
    public void tearDown(){
        //WebDriverRunner.closeWebDriver();
    }
     */

}
