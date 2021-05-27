package _DENEME._otherpages;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import java.util.Arrays;

import static com.codeborne.selenide.Selenide.*;

public class P1 {

    public static void main(String[] args) {


        String baseUrl = "https://www.mevlanacay.de/";

        String underConsTitle = "Under Construction: Mercury Tours";

        open(baseUrl);
        //List<SelenideElement> linkElements = $$(By.tagName("a"));
        int linkNumbers = $$(By.tagName("a")).size();
        String[] linkTexts = new String[linkNumbers];

        //extract the link texts of each link element
        for (int i = 0; i < linkNumbers; i++) {
            linkTexts[i] = $(By.tagName("a"), i).getText();
        }

        System.out.println(Arrays.toString(linkTexts));

        for (String t : linkTexts) {
            $(By.linkText(t)).scrollIntoView(true).click();
            if ($("title").getText().equals(underConsTitle)) {
                System.out.println("\"" + t + "\""
                        + " is under construction.");
            } else {
                System.out.println("\"" + t + "\""
                        + " is working.");
            }
            Selenide.back();
        }
    }


}