package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import utils.Locators;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import utils.MyMethods;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;


public class InnerLinksTest extends MyMethods implements Locators {

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
            //Selenide.screenshot("Page_" + pageScrName);
            //System.out.println("############################################################");
            System.out.println(Selenide.title() + " - " + innerURL);
            int numOfLinks = $$(By.tagName("a")).size();
            for (int i = 0; i < numOfLinks; i++) {
                String lnk = $("a", i).scrollTo().getAttribute("href") + "";
                lnk = lnk.replace("#","");
                if(lnk.startsWith(urlDe) && innerSites.get(lnk)==null && !lnk.contains("announcements")){
                    innerSites.put(lnk, 0);
                    verifyLink1($("a", i));
                    num+=1;
                }
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

    public void verifyLink1(SelenideElement element) {

        String urlLink = element.getAttribute("href") + "";
        String screenshotImageName = "";
        Map<String, String> vals = new HashMap<>();

        if(urlLink.isEmpty()){
            screenshotImageName = getFileName(element.getText());
            vals.put("link", urlLink);
            vals.put("responceCode", "empty");
            vals.put("element text", "");
            vals.put("aciklama", "URL is either not configured for anchor tag or it is empty");
            vals.put("screenshotImage", screenshotImageName);
            writeToExcel("MevlanaTee", "linkler", vals);
        }else {
            try {
                URL link = new URL(urlLink);
                HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();
                httpConn.setConnectTimeout(2000);
                httpConn.connect();
                if (httpConn.getResponseCode() > 400) {
                    vals.put("link", urlLink);
                    vals.put("responceCode", httpConn.getResponseCode()+"");
                    vals.put("element text", element.parent().getText());
                    vals.put("aciklama", httpConn.getResponseMessage());
                    vals.put("screenshotImage", "");
                    writeToExcel("MevlanaTee", "linkler", vals);
                }
            } catch (Exception e) {
                screenshotImageName = getFileName(element.getText());
                vals.put("link", urlLink);
                vals.put("responceCode", "");
                vals.put("element text", element.parent().getText());
                vals.put("aciklama", "anchor Or NonLink");
                vals.put("screenshotImage", screenshotImageName);
                writeToExcel("MevlanaTee", "linkler", vals);
                Selenide.screenshot(screenshotImageName);
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