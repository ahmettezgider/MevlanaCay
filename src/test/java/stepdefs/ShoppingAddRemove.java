package stepdefs;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import utils.MyMethods;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ShoppingAddRemove extends MyMethods {
    int quantity = 0;
    String nameOfProduct;
    float price;

    @When("user change quantity to {int}")
    public void userChangeQuantityTo(int quantity) {
        $(inputQuantityDetail).shouldBe(Condition.enabled).setValue(quantity+"");
        nameOfProduct = $(productNameDetail).getText();
        price = changeToFloat($(productPriceDetail).getText());
    }

    @And("user goes to Cart")
    public void userGotoCart() {
        quantity = Integer.parseInt($(inputQuantityDetail).getValue()+"");
        $(buttonAddToCart).click();
    }

    @Then("quantity should be changed")
    public void quantityShouldBeChanged() {
        Assert.assertEquals(quantity, getQuantityInCartTable());
    }

    @And("the price in cart should be correct")
    public void thePriceInCartShouldBeCorrect() {
        Assert.assertEquals(price, getPriceInCartTable());
    }

    @And("the total price in cart should be correct")
    public void theTotalPriceInCartShouldBeCorrect() {
        Assert.assertEquals(quantity*price, getTotalInCartTable());
    }


    @And("the product should be deleted")
    public void theProductShouldBeDeleted() {
        getRemoveButtonInCartTable().click();
        $(tableCartRemove).shouldBe(Condition.disappear);
    }
}
