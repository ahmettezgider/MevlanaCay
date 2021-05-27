package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import utils.Locators;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;


public class BrockenLinks implements Locators {

    Map<String, Integer> innerSites = new LinkedHashMap<String, Integer>(){
        {
            put(urlDe,0);
        }
    };


    @Test
    public void brockenLinksTest() {

        Configuration.headless = true;
        int num = 0;
        while (true){
            String innerURL = getLink();
            if (innerURL.isEmpty())
                    break;

            open(innerURL);
            zoom(0.7);
            String pageScrName = innerURL.replace(urlDe,"").replace("/","_");
            Selenide.screenshot("Page_" + pageScrName);
            System.out.println("############################################################");
            System.out.println(Selenide.title() + " - " + innerURL);
            int numOfLinks = $$(By.tagName("img")).size();
            for (int i = 0; i < numOfLinks; i++) {
                String lnk = $("a", i).scrollTo().getAttribute("href") + "";
                innerSites.put(lnk, 0);
                verifyLink($("img", i));
                num+=1;
            }

        }
        System.out.println("----------------------------------");
        System.out.println(Arrays.toString(innerSites.entrySet().toArray()));

    }

    public String getLink(){
        for (Map.Entry<String, Integer> entry : innerSites.entrySet()){
            if (entry.getValue()==0){
                entry.setValue(1);
                return entry.getKey();
            }
        }
        return "";
    }

    // verification of a link
    public void verifyLink(SelenideElement element) {

        String urlLink = element.getAttribute("href") + "";

        if(urlLink.isEmpty()){
            System.out.println(" - URL is either not configured for anchor tag or it is empty");
        }else {
            try {
                URL link = new URL(urlLink);
                HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();
                httpConn.setConnectTimeout(2000);
                httpConn.connect();
                if (httpConn.getResponseCode() != 200) {
                    System.out.print(element.parent().getText() + "\n" + urlLink);
                    System.out.println(" - " + httpConn.getResponseCode() + " - " + httpConn.getResponseMessage());
                    System.out.println();
                }
            } catch (Exception e) {
                System.out.print(element.parent().getText() + "\n" + urlLink);
                System.out.println(" - anchor Or NonLink");
                System.out.println();
                Selenide.screenshot(getFileName(element.getText()));
            }
        }
    }

    public String getFileName(String text){
        String dateTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String fileEnd = text.substring(Math.min(10, text.length()));
        return dateTime + "_anchorOrNonLink" + fileEnd;
    }

}