package stepdefs;

import com.codeborne.selenide.*;
import com.codeborne.selenide.testng.SoftAsserts;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import utils.Locators;
import utils.MenuLinks;
import utils.MyMethods;


import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

@Listeners({SoftAsserts.class})
public class MenuSteps extends MyMethods implements Locators {

    @Given("user use {string} with the size {string}")
    public void userUseWithTheSize(String browser, String browserSize) {
        Configuration.browser = browser;
        Configuration.browserSize = browserSize;
    }


    @Given("user on home page")
    public void userOnHomePage() {
        open(urlDe);
        $(logo).shouldBe(appear);
        acceptCookies();
    }

    @Given("user on {string}")
    public void userOn(String url) {
        open(url);
        $(logo).shouldBe(appear);
        acceptCookies();
    }

    @Then("main menu should work properly")
    public void mainMenuShouldWorkProperly() {
        boolean mobileMode = $(responsiveMenuToggle).isDisplayed();
        if (mobileMode) {
            $(responsiveMenuToggle).click();
        }

        for (SelenideElement e: $$(mainMenu)){
            e.hover();
            if (e.find("ul").exists()){
                if (mobileMode)
                    e.find(".menu-toggle").click();
                else
                    e.hover();
                e.find("ul").shouldBe(appear);
                for(SelenideElement eSub : e.find("ul").findAll("li")){
                    eSub.hover();
                }
            }
        }
        if (mobileMode) $(mobileMenuClose).click();
    }


    @And("close the browser")
    public void closeTheBrowser() {
        WebDriverRunner.closeWebDriver();

    }

    @Then("main menu links should work properly")
    public void mainMenuLinksShouldWorkProperly() {
        Configuration.assertionMode = AssertionMode.SOFT;
        for (SelenideElement e : $$(linksMainMenu)){
            e.scrollIntoView(false);
            Assert.assertTrue(verifyLink(e));
        }
        Configuration.assertionMode = AssertionMode.STRICT;
    }

    @And("footer menu links should work properly")
    public void footerMenuLinksShouldWorkProperly() {
        Configuration.assertionMode = AssertionMode.SOFT;
        for (SelenideElement e : $$(linksFooterMenu)){
            e.scrollIntoView(false);
            Assert.assertTrue(verifyLink(e));
        }
        Configuration.assertionMode = AssertionMode.STRICT;
    }

    @When("user select the following languages")
    public void userSelectTheFollowingLanguages(DataTable dataTable) {
        List<String> langList = dataTable.asList(String.class);
        for (String language : langList) {
            navigateOnMenuTo(MenuLinks.valueOf(language));
        }
    }

    @Then("footer menu should work properly")
    public void footerMenuShouldWorkProperly() {
        for(SelenideElement e : $$(linksFooterMenu)){
            e.scrollTo().shouldBe(visible, enabled);
        }

    }

    @When("user navigate to tv spot page")
    public void userNavigateToTvSpotPage() {
        navigateOnMenuTo(MenuLinks.TVADS);
    }
}
