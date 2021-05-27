package _DENEME.mtd;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Locators;
import utils.MenuLinks;
import utils.MyMethods;

import static com.codeborne.selenide.Selenide.*;

public class denemeStock extends MyMethods implements Locators {


    @Test
    public void test4(){
        open(urlEn);
        navigateOnMenuTo(MenuLinks.SHOP);
        String[] arr = {"500", "1000", "Bag"};

        for (int i = 0; i < 3; i++) {
            ElementsCollection list = $("ul.products", i).findAll("li");
            Assert.assertEquals(list.filter(Condition.text(arr[i])).size(), list.size());
        }




    }


    @Test
    public void test2(){
        open(urlEn);
        navigateOnMenuTo(MenuLinks.SHOP);
        SelenideElement e = $(productListShop, 1);
        System.out.println(e.getOwnText() + ", " + e.getText());
        System.out.println(e.find("div.desc h4 a").getAttribute("href"));

        int pn = 0;
        for (SelenideElement ee : $$("input[id^='quantity']")){
            pn += Integer.parseInt(ee.getValue());
        }


    }



    @Test
    public void test1(){
        open(urlEn);
        navigateOnMenuTo(MenuLinks.SHOP);


        int productSize = $$(productListShop).size();

        for (int i = 6; i < productSize; i++) {
            SelenideElement product = $(productListShop, i);
            String name = product.find(productNameShop).getText();
            String price = product.find(productPriceShop).getText();
            boolean soldout = false;
            if (product.find("span.soldout").is(Condition.exist))
                soldout = true;

            product.scrollTo().hover();
            product.find("a[class='link']").shouldBe(Condition.visible).click();

            $(productNameDetail).shouldBe(Condition.visible, Condition.text(name));
            $(productPriceDetail).shouldBe(Condition.text(price));
            if (!soldout) {
                $(inputQuantityDetail).shouldBe(Condition.exist);
                $(buttonAddToCart).shouldBe(Condition.enabled);
            }
            Selenide.back();
        }





    }

    @Test
    public void test3(){
        open(urlEn);
        navigateOnMenuTo(MenuLinks.SHOP);
        $(productListShop, 1).click();
        $(inputQuantityDetail).shouldBe(Condition.visible).setValue(2+"");
        $(buttonAddToCart).click();

        $(nameInCartTable).shouldBe(Condition.visible);
        double price = priceConvert($(priceInCartTable).getText());
        int quantity = Integer.parseInt($(inputQuantityInCartTable).getValue());
        double total = priceConvert($(subtotalInCartTable).getText());

        Assert.assertEquals(price*quantity, total);
    }

    public static void main(String[] args) {
        System.out.println(priceConvert("12,45€ "));
    }

    public static double priceConvert(String val){
        val = val.replace("€", "");
        val = val.trim();
        val = val.replace(",", ".");
        return Double.parseDouble(val);
    }
}
