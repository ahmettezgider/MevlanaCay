package _DENEME.mtd.dataTable;

import org.testng.annotations.Test;
import utils.MenuLinks;
import utils.MyMethods;

import static com.codeborne.selenide.Selenide.open;

public class Test2 extends MyMethods {

    @Test
    public void testShop1(){
        open(urlDe);
        acceptCookies();
        navigateOnMenuTo(MenuLinks.SHOP);
        navigateOnMenuTo(MenuLinks.TVADS);
    }
}
