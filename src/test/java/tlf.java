import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class tlf {


    @Test
    public void test01(){
        open("https://telefonvertrag.de/blog/");
        int i = 0;
        int arrSize = 0;
        while (true) {
            arrSize = $$(By.xpath("//div/span[text()='Blog']")).size();

            if (i == arrSize)
                break;
            else {
                if ($(By.xpath("//div/span[text()='Blog']"), i).exists()) {
                    $(By.xpath("//div/span[text()='Blog']"), i).scrollTo();
                    i++;
                    System.out.println(i);
                    sleep(200);
                }
            }
        }
    }

}
