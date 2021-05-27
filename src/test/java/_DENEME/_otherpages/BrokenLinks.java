package _DENEME._otherpages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class BrokenLinks {

    private static WebDriver driver = null;

    public static void main(String[] args) {
        String homePage = "https://www.mevlanacay.de/";
        String url = "";
        HttpURLConnection huc = null;
        int respCode = 200;


        open(homePage);

        List<SelenideElement> links = $$(By.tagName("a"));
        Iterator<SelenideElement> it = links.iterator();
        SelenideElement element = null;

        while(it.hasNext()){
            element = it.next();
            url = element.getAttribute("href");
            String urlText = element.getText();

            //System.out.println(url);

            if(url == null || url.isEmpty()){
                System.out.println(urlText + ":" + url + ", URL is either not configured for anchor tag or it is empty");
                continue;
            }

            if(!url.startsWith(homePage)){
                System.out.println(urlText + ":" + url + ",  URL belongs to another domain, skipping it.");
                continue;
            }

            try {
                huc = (HttpURLConnection)(new URL(url).openConnection());

                huc.setRequestMethod("HEAD");

                huc.connect();

                respCode = huc.getResponseCode();

                if(respCode >= 400){
                    System.out.println(urlText + ":" + url + " is a broken link");
                }
                else{
                    //System.out.println(url + " is a valid link");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}