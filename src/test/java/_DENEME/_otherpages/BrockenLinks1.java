package _DENEME._otherpages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.HttpURLConnection;
import java.net.URL;

import static com.codeborne.selenide.Selenide.*;


public class BrockenLinks1 {


    public static String isLinkBroken(URL url) throws Exception {
        String response = "";
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            connection.connect();
            response = connection.getResponseMessage();
            connection.disconnect();
            return response;
        }
        catch(Exception exp) {
            return exp.getMessage();
        }
    }

    public static void main(String[] args) throws Exception {

        open("https://www.mevlanacay.de/");

        ElementsCollection elementList;
        ElementsCollection finalList = null;

        elementList = $$(By.tagName("a"));
        elementList = $$(By.tagName("img"));

        for (SelenideElement element : elementList) {
            if(element.getAttribute("href") != null) {
                finalList.add(element);
            }
        }

        System.out.println("Total number of elements found " + finalList.size());

        for( WebElement element : finalList){

            try {
                System.out.println("URL: " + element.getAttribute("href")+ " returned " + isLinkBroken(new URL(element.getAttribute("href"))));
                //System.out.println("URL: " + element.getAttribute("outerhtml")+ " returned " + isLinkBroken(new URL(element.getAttribute("href"))));
            }
            catch(Exception exp) {
                System.out.println("At " + element.getAttribute("innerHTML") + " Exception occured -&gt; " + exp.getMessage());
            }

        }

    }

}
