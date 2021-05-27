package _DENEME._otherpages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Locators;
import utils.MenuLinks;
import utils.MyMethods;
import utils.SubMenuLinks;

import java.util.Map;
import java.util.Set;

import static com.codeborne.selenide.Selenide.*;

public class x extends MyMethods implements Locators {

    @Test
    public void test1(){
        By addToCart = By.cssSelector("button[name='add-to-cart']");
        By quantity = By.cssSelector("div.quantity input");
        open(urlDe);
        acceptCookies();
        navigateOnMenuTo(MenuLinks.SHOP);
        $$("ul.products li")
                .filter(Condition.text("3 Stück"))
                .filter(Condition.not(Condition.text("DOSE")))
                .first()
                .click();

        $(addToCart).should(Condition.appear).click();
        $(quantity).shouldBe(Condition.enabled).setValue("5");
        $("button[name='update_cart']").shouldBe(Condition.enabled).click();
        $("div.woocommerce-notices-wrapper").shouldBe(Condition.visible, Condition.text("aktualisiert"));


    }
    @Test
    public void test2(){
        open(urlDe);
        acceptCookies();
        navigateOnMenuTo(MenuLinks.SHOP);
        for(SelenideElement e : $$ ("ul.products li")){
            String val = e.find("h4").text() + " : " + e.find("bdi").text();
            System.out.println(val);
        }

    }

    @Test
    public void test3(){
        open(urlDe);
        acceptCookies();
        navigateOnMenuTo(MenuLinks.SHOP);
        SelenideElement div = $$("div.the_content_wrapper>div").filter(Condition.text("Mevlana Tee 1000gr")).first();
        System.out.println("-------getOwnText---------");
        System.out.println(div.getOwnText());
        System.out.println("-------innerText---------");
        System.out.println(div.innerText());
        System.out.println("-------innerHtml---------");
        System.out.println(div.innerHtml());
        System.out.println("------text----------");
        System.out.println(div.text());
        System.out.println("-------with loop---------");
        for(SelenideElement e : div.findAll("li")){
            String val = e.find("h4").text() + " : " + e.find("bdi").text();
            System.out.println(val);

        }


    }

    @Test
    public void test4(){
        open(urlDe);
        acceptCookies();
        navigateOnMenuTo(MenuLinks.SHOP);
        $$(subHeaders).last().shouldHave(Condition.text(getSubTitleOf(MenuLinks.SHOP)));
        navigateOnMenuTo(MenuLinks.FAQ);
        $$(subHeaders).last().shouldHave(Condition.text(getSubTitleOf(MenuLinks.FAQ)));

        navigateOnMenuTo(MenuLinks.TR);
        acceptCookies();
        navigateOnMenuTo(MenuLinks.SHOP);
        $$(subHeaders).last().shouldHave(Condition.text(getSubTitleOf(MenuLinks.SHOP)));
        navigateOnMenuTo(MenuLinks.FAQ);
        $$(subHeaders).last().shouldHave(Condition.text(getSubTitleOf(MenuLinks.FAQ)));

        navigateOnMenuTo(MenuLinks.EN);
        acceptCookies();
        navigateOnMenuTo(MenuLinks.SHOP);
        $$(subHeaders).last().shouldHave(Condition.text(getSubTitleOf(MenuLinks.SHOP)));
        navigateOnMenuTo(MenuLinks.FAQ);
        $$(subHeaders).last().shouldHave(Condition.text(getSubTitleOf(MenuLinks.FAQ)));

    }
    @Test
    public void test5(){
        open(urlDe);
        acceptCookies();
        navigateOnMenuTo(MenuLinks.SHOP);
        $$(productListShop).first().hover();
        $(iconBasket).shouldBe(Condition.visible);
        $(iconLink).shouldBe(Condition.visible);
        sleep(2000);
    }

    @Test
    public void test6(){
        open(urlDe);
        acceptCookies();
        navigateOnMenuTo(MenuLinks.SHOP);
        $$(productListShop).first().click();
        String styleBefore = $(productBigImage).getAttribute("style");
        $(productImageContainer).hover();
        String styleAfter = $(productBigImage).getAttribute("style");
        Assert.assertNotEquals(styleBefore, styleAfter);
    }


    @Test
    public void test7(){
        open(urlDe);
        acceptCookies();
        navigateOnMenuTo(MenuLinks.SHOP);
        $$(productListShop).first().click();

        $(productImageLens).click();
    }


    @Test
    public void domainLinks(){
        Configuration.headless = true;
        open(urlDe);
        zoom(0.5);
        acceptCookies();
        Map<String, Integer> links = getAlllinksOfDomain(urlDe);
        System.out.println(links.size());
        System.out.println(links);
    }

    public static void main(String[] args) {
        int x = 100;
        int y = 100;

        for (int i = 0; i<360; i+=30){
            int X = x + (int)(x*Math.cos(Math.toRadians(i)));
            int Y = y + (int)(y*Math.sin(Math.toRadians(i)));
            System.out.println(X + ", " + Y);
        }


    }
}
