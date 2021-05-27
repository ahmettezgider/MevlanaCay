package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.DataProvider;
import utils.Locators;
import org.testng.annotations.Test;
import utils.MenuLinks;
import utils.MyMethods;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.*;

public class MainMenuTest extends MyMethods implements Locators {

    @Test
    public void fireFoxTest(){
        Configuration.browser = "firefox";
        open(urlDe);
        acceptCookies();
        //navigateOnMenuTo(MenuLinks.TR);
        //acceptCookies();
        verifyVideos();
    }


    @Test(dataProvider = "sizeOfBrowser")
    public void mainMenuTest(String[] sizeOfBrowser){
        Configuration.browser = sizeOfBrowser[0];
        Configuration.browserSize = sizeOfBrowser[1];
        open(urlDe);
        acceptCookies();
        Selenide.clearBrowserCookies();

        boolean mobileMode = $(responsiveMenuToggle).isDisplayed();
        if (mobileMode)
            $(responsiveMenuToggle).click();

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

        navigateOnMenuTo(MenuLinks.SHOP);
        navigateOnMenuTo(MenuLinks.TR);
        acceptCookies();
        navigateOnMenuTo(MenuLinks.SHOP);
        navigateOnMenuTo(MenuLinks.EN);
        acceptCookies();
        navigateOnMenuTo(MenuLinks.SHOP);

        WebDriverRunner.closeWebDriver();
    }

    @DataProvider(name = "sizeOfBrowser1")
    public Object[] browserSize1(){
        return new Object[]{
                "500x760", "1366x760"
        };
    }

    @DataProvider(name = "sizeOfBrowser")
    public Object[][] browserSize(){
        return new Object[][]{
                {"chrome", "550x740"},
                {"chrome", "1360x740"},
                {"firefox", "550x740"},
                {"firefox", "1360x740"}
        };
    }
}
