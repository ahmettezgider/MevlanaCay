package _DENEME.mtd.deneme;

import com.codeborne.selenide.Configuration;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import utils.BrowserSize;
import utils.MenuLinks;
import utils.MyMethods;

import java.util.List;

public class denemeSteps extends MyMethods {
    @When("the browser size is as {string}")
    public void theBrowserSizeIsAs(String browserSize) {
        arrangeWinize(BrowserSize.valueOf(browserSize));
    }

    @When("user navigate to page the following pages")
    public void userNavigateToPageTheFollowingPages(DataTable dataTable) {
        List<String> links = dataTable.asList(String.class);
        for (String link : links) {
            navigateOnMenuTo(MenuLinks.valueOf(link));
        }

    }



    public void arrangeWinize(BrowserSize bs){
        String browserSize = bs.getWinSize();
        if (browserSize.equals("max"))
            Configuration.startMaximized = true;
        else
            Configuration.browserSize = browserSize;
    }

}
