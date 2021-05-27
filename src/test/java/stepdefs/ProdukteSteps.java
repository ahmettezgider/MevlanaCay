package stepdefs;

import com.codeborne.selenide.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import utils.Locators;
import utils.MenuLinks;
import utils.MyMethods;

import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ProdukteSteps extends MyMethods implements Locators {

    @And("user choose the language {string}")
    public void userChooseTheLanguage(String language) {
        navigateOnMenuTo(MenuLinks.valueOf(language));
    }

    @When("user navigate to shop page")
    public void userNavigateToShopPage() {
        zoom(0.7);
        navigateOnMenuTo(MenuLinks.SHOP);
    }

    @Then("all links should be properly defined")
    public void allLinksShouldBeProperlyDefined() {
        for (SelenideElement e : $$("div#Content a")){
            System.out.println(e.getText());
            e.scrollIntoView(false);
            Assert.assertTrue(verifyLink(e));
        }
    }

    @Then("all images should be properly defined")
    public void allImagesShouldBeProperlyDefined() {
        for (SelenideElement e : $$("div#Content img")){
            System.out.println(e.getText());
            e.scrollIntoView(false);
            Assert.assertTrue(verifyLink(e));
        }
    }


    String productName_old;
    String productPrice_old;

    @When("user select the {int}.th product")
    public void userSelectTheProductNumberProduct(int productNumber) {
        ElementsCollection products = $$(productListShop);

        if (productNumber<1)
            productNumber = 0;
        else if (productNumber>products.size())
            productNumber = products.size()-1;
        else
            productNumber--;

        SelenideElement e = products.get(productNumber);
        productName_old = e.find(productNameShop).getText();
        productPrice_old = e.find(productPriceShop).getText();
        e.click();
    }

    @Then("the name and the price should be correct in product detail page")
    public void theNameAndThePriceShouldBeCorrectInProductDetailPage() {
        $(productNameDetail).shouldHave(Condition.text(productName_old));
        $(productPriceDetail).shouldHave(Condition.text(productPrice_old));
    }

    @Then("all videos should be properly defined")
    public void allVideosShouldBeProperlyDefined() {
        verifyVideos();
    }

    @When("user click to the review tab in product detail")
    public void userClickToTheReviewTabInProductDetail() {
        $(tabReview).shouldBe(Condition.enabled).scrollIntoView(false).click();
    }

    @Then("the success of submission of the review form should be as follows")
    public void theSuccessOfSubmissionOfTheReviewFormShouldBeAsFollows(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        String stars = "";
        String url = WebDriverRunner.url();
        $$(starsProductReviewDetail).first().scrollIntoView(false);
        for (Map<String, String> columns : rows) {

            if (columns.get("stars")!=null) {
                stars = columns.get("stars");
                if (stars.equals("1") || stars.equals("2") || stars.equals("3") || stars.equals("4"))
                    stars = columns.get("stars");
                else
                    stars = "5";

                $$(starsProductReviewDetail).filter(text(stars)).first().scrollIntoView(false).click();
            }
            $(commentProductReviewDetail).scrollIntoView(false).setValue(columns.get("comment"));
            $(nameProductReviewDetail).scrollIntoView(false).setValue(columns.get("name"));
            $(emailproductReviewDetail).scrollIntoView(false).setValue(columns.get("email"));
            $(buttonSubmitReview).click();
            String success = columns.get("success");
            if (columns.get("stars")==null){
                Assert.assertTrue(isAlertPresent());
                switchTo().alert().accept();
            }
            if (success.equalsIgnoreCase("false"))
                Assert.assertEquals(url, WebDriverRunner.url());
            else
                Assert.assertNotEquals(url, WebDriverRunner.url());


        }
    }

}
