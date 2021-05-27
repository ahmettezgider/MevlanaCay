package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.Test;
import utils.Locators;
import utils.MenuLinks;
import utils.MyMethods;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class BrockenVideos extends MyMethods implements Locators {

    @Test
    public void gotoPage(){
        Configuration.startMaximized = true;
        open(urlDe);
        Selenide.clearBrowserCookies();
        acceptCookies();
        navigateOnMenuTo(MenuLinks.TVADS);
    }


    @Test(dependsOnMethods = "gotoPage")
    public void videoTest(){
        for (int i=0; i< $$("iframe").size(); i++){
            switchTo().frame(i);
            String videoFrameVal = $("div.focus-target").getAttribute("aria-valuenow");
            $("div.player").hover().click();
            sleep(2000);
            $("div.focus-target").shouldNot(Condition.attribute("aria-valuenow", videoFrameVal));
            $("div.player").hover().click();
            switchTo().parentFrame();
        }

    }


}
