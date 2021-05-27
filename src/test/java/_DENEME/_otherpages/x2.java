package _DENEME._otherpages;

;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import utils.Locators;
import org.testng.annotations.Test;
import utils.MenuLinks;
import utils.MyMethods;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;

//@Listeners({ SoftAsserts.class})
@Listeners(utils.ListenerTestNG.class)
public class x2 extends MyMethods implements Locators {

    @Test
    public void test() {
        //Configuration.browserSize = "500x700";
        Configuration.startMaximized = true;
        open(urlDe);
        Selenide.clearBrowserCookies();
        acceptCookies();
        navigateOnMenuTo(MenuLinks.SHOP);
        navigateOnMenuTo(MenuLinks.FAQ);
    }


    @Test
    public void test1() {

        Assert.assertEquals(1, 1);

    }

    @Test
    public void test2() {

        Assert.assertEquals(0, 1);

    }

    @Test(dependsOnMethods = {"test2"})
    public void test3() {

        Assert.assertEquals(0, 1);

    }

}
