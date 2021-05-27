package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import utils.Locators;
import utils.MenuLinks;
import utils.MyMethods;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class Produkte extends MyMethods implements Locators {

    By products = By.cssSelector("ul.products li");
    By productName = By.cssSelector("h4");
    By productPrice = By.cssSelector("bdi");


    @Test
    public void xx(){

        open(urlDe);
        acceptCookies();
        navigateOnMenuTo(MenuLinks.SHOP);
        int i=1;

        for (SelenideElement e : $$(products).filter(Condition.text("stück"))) {
            e.scrollTo();
            System.out.println("------" + i++);
            System.out.println(e.find(productName).text());
            System.out.println(e.find(productPrice).text());

            verifyImage(e.find("img"));
        }
    }


}
