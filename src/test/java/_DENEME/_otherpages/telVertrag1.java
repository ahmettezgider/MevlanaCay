package _DENEME._otherpages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import utils.MyMethods;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class telVertrag1 extends MyMethods {
    @Test
    public void test1(){
        open("https://telefonvertrag.de/");
        waitAndAcceptCookies();
        $("footer input[type='email']")
                .scrollTo()
                .shouldBe(Condition.visible)
                .setValue("atezgider@gmail.com");
        $(By.xpath("//button[contains(text(), 'anmelden')]")).click();
        $("footer div.alert")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("400"))
                .shouldHave(Condition.attributeMatching("class", ".*danger.*"));
    }
}
