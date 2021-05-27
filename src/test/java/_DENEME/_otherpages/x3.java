package _DENEME._otherpages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class x3 {
    @Test
    public void test1(){

        open("https://www.seleniumeasy.com/test/bootstrap-download-progress-demo.html");
        $("#cricle-btn").shouldBe(Condition.enabled).click();
        System.out.println( $(".percenttext").getText());
        $(".percenttext").shouldHave(Condition.text("60"), Duration.ofSeconds(30));

    }
    @Test
    public void test2(){

        open("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html");
        SelenideElement e = $$(".panel.panel-primary").filter(Condition.text("Java Script Alert Box")).first();
        e.scrollTo().find("button").click();

        switchTo().alert().accept();
        sleep(2000);
    }

    @Test
    public void test3(){

        open("https://www.seleniumeasy.com/test/jquery-dropdown-search-demo.html");
        $("select#country+span").click();
        $("select#country").selectOption(3);
        sleep(1000);
    }
}
