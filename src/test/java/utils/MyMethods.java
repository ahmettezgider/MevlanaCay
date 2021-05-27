package utils;


import com.codeborne.selenide.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MyMethods implements Locators {


    /**
     * scroll to element until it is visible
     * @param locator of element
     */
    public void scrollToBeVisible(By locator){
        while (!$(locator).isDisplayed())
            executeJavaScript("scrollBy(0, 10);");
        $(locator).scrollIntoView(false).shouldBe(appear);

    }


    /**
     * accept the cookies
     */
    public void acceptCookies(){
        //$(cookieDialog1).should(appear).$("button", 0).click();
        sleep(500);
        if ($(cookieDialog1).is(visible)) {
            $(cookieDialog1).$("button", 0).click();
            $(cookieDialog1).should(disappear);
        }
    }


    /**
     * accept the cookies
     */
    public void waitAndAcceptCookies(){
        //$(cookieDialog1).should(appear).$("button", 0).click();
        $(cookieDialog1).shouldBe(visible);
        $(cookieDialog1).$("button", 0).click();
        $(cookieDialog1).should(disappear);
    }
    /**
     * controls if the browser at the mobile mode
     * @return true is the browser is mobile mode
     */
    public boolean isMobileMode(){
        return $(responsiveMenuToggle).isDisplayed();
    }


    /**
     * controls if the mobile menu is on
     * @return true if the mobile menu overlay is visible
     */
    public boolean isMobileMenuOn(){
        return $(menuOverlay).is(appear);
    }


    /**
     * open the mobile menu
     */
    public void openMenu(){
        if ($(menuOverlay).is(disappear))
            $(responsiveMenuToggle).click();
    }


    /**
     * close the mobile menu
     */
    public void closeMenu(){
        if ($(menuOverlay).is(appear))
            $(mobileMenuClose).click();
    }


    /**
     *  finds the language of the current link
     * @return the language of the current site : DE, EN, TR
     */
    public String getSiteLanguage(){
        String currentUrl = WebDriverRunner.url();
        String[] langArr = currentUrl.substring(currentUrl.indexOf(".de")+3).split("/");

        if (langArr.length < 2){
            return "DE";
        }else{
            String lang = langArr[1].trim().toUpperCase();
            if (!lang.equals("EN") && !lang.equals("TR"))
                lang = "DE";
            return lang;
        }
    }


    /**
     * returns the xPath of the wanted menu link
     * @param text menu text
     * @return String
     */
    public String getMenuXPath(String text){
        return "//ul[@id='menu-ana-menu']//span[text()='" + text + "']";
    }


    /**
     * returns the xPath of the wanted submenu opener locator in mobile mode (+)
     * @param text main menu text
     * @return String
     */
    public String getSubMenuOpener(String text){
        return getMenuXPath(text) + "//ancestor::li//span[@class='menu-toggle']";
    }


    /**
     * navigates on the menu
     * @param menuLinks menu link as enum (MenuLinks)
     */
    public void navigateOnMenuTo(MenuLinks menuLinks){
        Selenide.executeJavaScript("window.scrollBy(0, 300);");
        String langStr = getSiteLanguage();
        int lang = langStr.equals("EN") ? 1 : langStr.equals("TR") ? 2 : 0 ;
        if (isMobileMode())
            openMenu();

        if (menuLinks.isSubMenu()){
            SelenideElement mainLink;
            if (menuLinks.isLanguage())
                mainLink = $$(mainMenu).last();
            else
                mainLink = $x(getMenuXPath(menuLinks.getBaseMenu().getLang(lang)));

            if (isMobileMode()) {
                if (menuLinks.isLanguage())
                    $x(getSubMenuOpener(" " + langStr)).click();
                else
                    $x(getSubMenuOpener(menuLinks.getBaseMenu().getLang(lang))).click();
            }else
                mainLink.hover();
        }
        $x(getMenuXPath(menuLinks.getLang(lang))).click();
        acceptCookies();
    }


    /**
     * check whether the alert is opened
     * @return boolean
     */
    public boolean isAlertPresent() {
        try {
            Selenide.switchTo().alert();
            return true;
        }catch (Exception Ex) {
            return false;
        }
    }


    public void verifyNotificationAs(Notification notification){

        switch (notification){
            case added:
                $(notificationAlertCart)
                        .shouldBe(visible)
                        .shouldHave(or("Added", text("hinzugefügt"), text("added"), text("eklendi")));
                break;
            case updated:
                $(notificationAlertCart)
                        .shouldBe(visible)
                        .shouldHave(or("Updated", text("aktualisiert"), text("updated"), text("güncellendi")));
                break;
            case deleted:
                $(notificationAlertCart)
                        .shouldBe(visible)
                        .shouldHave(or("Deleted", text("entfernt"), text("removed"), text("silindi")));
                break;

        }
    }

    /**
     * verify the given link whether it is brocken
     * @param element as selenide element of the link "<a ...>"
     * @return boolean
     */
    public boolean verifyLink(SelenideElement element) {

        String urlLink = element.getAttribute("href") + "";

        if(urlLink.isEmpty()){
            Selenide.screenshot(getFileName(element.getText()));
            return false;
        }else {
            try {
                URL link = new URL(urlLink);
                HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();
                httpConn.setConnectTimeout(2000);
                httpConn.connect();
                if (httpConn.getResponseCode() >= 400) {
                    Selenide.screenshot(getFileName(element.getText()));
                    return false;
                }
            } catch (Exception e) {
                Selenide.screenshot(getFileName(element.getText()));
            }
        }
        return true;
    }

    /**
     * verify if the IMG is properly defined
     * @param element of the image
     * @return boolean
     */
    public boolean verifyImage(SelenideElement element) {

        String imgSrc = element.getAttribute("src");

        if(imgSrc.isEmpty() || imgSrc.contains("placeholder.png")){
            Selenide.screenshot(getFileName(element.getText()));
            return false;
        }else {
            try {
                URL link = new URL(imgSrc);
                HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();
                httpConn.setConnectTimeout(2000);
                httpConn.connect();
                if (httpConn.getResponseCode() != 200) {
                    Selenide.screenshot(getFileName(element.getText()));
                    return false;
                }
            } catch (Exception e) {
                Selenide.screenshot(getFileName(element.getText()));
                return false;
            }
        }
        return true;
    }

    /**
     * retuns the file name of brocken link or img
     * @param text of the element
     * @return file name
     */
    public String getFileName(String text){
        String dateTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String fileEnd = text.substring(Math.min(10, text.length()));
        return dateTime + "_brockenLinkOrImg" + fileEnd;
    }


    /**
     * tests whether videos are played
     */
    public void verifyVideos() {
        for (int i = 0; i < $$("iframe").size(); i++) {
            $("iframe",i).scrollTo();
            switchTo().frame(i);
            String videoFrameVal = $("div.focus-target").getAttribute("aria-valuenow");
            $("div.player").hover().click();
            sleep(1000);
            $("div.focus-target").shouldNot(Condition.attribute("aria-valuenow", videoFrameVal));
            $("div.player").hover().click();
            switchTo().parentFrame();
        }
    }

    /**
     *
     * @param menuLinks
     * @return
     */
    public String getSubTitleOf(MenuLinks menuLinks){
        return SubMenuLinks.valueOf(menuLinks.toString()).getsubTitle(getSiteLanguage());
    }

    /**
     *
     */
    Map<String, Integer> mapOfLinks;
    public Map<String, Integer> getAlllinksOfDomain(String domainUrl){
        String url = domainUrl;
        mapOfLinks = new LinkedHashMap<>();
        mapOfLinks.put(url, 0);

        while (true){
            url = getUnUsedLink();
            System.out.println(url);
            if (url.isEmpty())
                break;

            open(url);
            int numOfLinks = $$(By.tagName("a")).size();
            for (int i = 0; i < numOfLinks; i++) {
                String lnk = $("a", i).scrollTo().getAttribute("href")+"";
                if(!mapOfLinks.containsKey(lnk) &&
                        lnk.replace("www.","").contains("https://mevlanacay.de") &&
                        !lnk.endsWith(".img"))  {
                    mapOfLinks.put(lnk, 0);
                }
            }
        }
        return mapOfLinks;

    }

    public String getUnUsedLink(){
        for (Map.Entry<String, Integer> entry : mapOfLinks.entrySet()){
            if (entry.getValue()==0){
                entry.setValue(1);
                return entry.getKey();
            }
        }
        return "";
    }


    public static void writeToExcel(String fileName, String sheetName, Map<String, String> values) {
        String path = "test output/" + fileName + ".xlsx";

        File f = new File(path);

        if (!f.exists()) {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet(sheetName);

            Row row = sheet.createRow(0);
            Cell cell;
            int i = 0;
            for (Map.Entry<String, String> entry : values.entrySet()) {
                cell = row.createCell(i++);
                cell.setCellValue(entry.getKey());
            }
            FileOutputStream outputStream= null;
            try {
                outputStream = new FileOutputStream(path);
                workbook.write(outputStream);
                workbook.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileInputStream inputStream = null;
        Workbook workbook = null;
        Sheet sheet = null;
        try {
            inputStream = new FileInputStream(path);
            workbook = WorkbookFactory.create(inputStream);
            sheet = workbook.getSheet(sheetName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Cell cell;
        int numberOfRows = sheet.getPhysicalNumberOfRows();
        Row row = sheet.createRow(numberOfRows++);
        int i = 0;
        for (Map.Entry<String, String> entry : values.entrySet()) {
            cell = row.createCell(i++);
            cell.setCellValue(entry.getValue());
        }

        FileOutputStream outputStream = null;
        try {
            inputStream.close();
            outputStream = new FileOutputStream(path);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public float changeToFloat(String text){
        String ret = text.replaceAll("[^0-9.]+", "");
        return Float.parseFloat(ret);
    }

    public int changeToInt(String text){
        String ret = text.replaceAll("[^0-9.]+", "");
        return Integer.parseInt(ret);
    }

    public float getPriceInCartTable(){
        //String xpathStr = "//table[contains(@class, 'cart')]//tr[.//a[text()='" + text + "']]";
        String xpathStr = "//table[contains(@class, 'cart')]";
        xpathStr += "//td[@class='product-price']";
        String ret = $(By.xpath(xpathStr)).getText();
        return changeToFloat(ret);
    }

    public int getQuantityInCartTable(){
        String xpathStr = "//table[contains(@class, 'cart')]";
        xpathStr += "//div[@class='quantity']/input";
        String ret = $(By.xpath(xpathStr)).getValue()+"";
        return changeToInt(ret);
    }

    public float getTotalInCartTable(){
        String xpathStr = "//table[contains(@class, 'cart')]";
        xpathStr += "//td[@class='product-subtotal']";
        String ret = $(By.xpath(xpathStr)).getText();
        return changeToFloat(ret);
    }

    public SelenideElement getRemoveButtonInCartTable(){
        String xpathStr = "//table[contains(@class, 'cart')]";
        xpathStr += "//td[@class='product-remove']/a";
        return $(By.xpath(xpathStr));

    }

}
