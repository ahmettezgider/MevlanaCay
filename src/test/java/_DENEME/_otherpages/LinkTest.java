package _DENEME._otherpages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;


public class LinkTest {

    @Test
    public void main(){
        String baseUrl = "https://www.mevlanacay.de/";
        open(baseUrl);

        List<SelenideElement> links = $$(By.tagName("a"));

        System.out.println("Total links are " + links.size());

        for (int i = 0; i < links.size(); i++) {
            WebElement element = links.get(i);
            String url = element.getAttribute("href");
            verifyLink(url);
        }
    }

    public void verifyLink(String urlLink) {

        try {
            URL link = new URL(urlLink);
            HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();
            httpConn.setConnectTimeout(2000);
            httpConn.connect();
            System.out.println(httpConn.getResponseCode() + " - " + urlLink + " - " + httpConn.getResponseMessage());
        } catch (Exception e) {
            System.out.println(urlLink + " - " + e.getMessage());
        }
    }
}