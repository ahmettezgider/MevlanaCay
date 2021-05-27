package stepdefs;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import utils.MyMethods;

import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class ShoppingBuyProduct extends MyMethods {
    @And("user click to Checkout")
    public void userClickToCheckout() {
        $(proceedButton).click();
        Configuration.startMaximized = true;
        $(orderFormFirstName).shouldBe(Condition.visible);
    }

    @When("user fill the order form as follows")
    public void userFillTheOrderFormAsFollows(DataTable dataTable) {
        Selenide.refresh();
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : data ){
            $(orderFormFirstName).scrollIntoView(false).setValue(map.get("FirstName"));
            $(orderFormLastName).setValue(map.get("LastName"));
            $(orderFormCompany).scrollIntoView(false).setValue(map.get("Company"));
            if (!$(orderFormCountrySelectList).isDisplayed())
                $(orderFormCountrySelect).click();
            int countryNum = map.get("Country") == null ? 0 : Integer.parseInt(map.get("Country"));
            if (countryNum<0)
                countryNum = 0;
            if (countryNum >= $$(orderFormCountrySelectList).size())
                countryNum = $$(orderFormCountrySelectList).size()-1;
            $$(orderFormCountrySelectList)
                    .shouldHave(CollectionCondition.sizeGreaterThan(0))
                    .get(countryNum)
                    .click();
            $(orderFormAddress1).scrollIntoView(false).setValue(map.get("Address1"));
            $(orderFormAddress2).setValue(map.get("Address2"));
            $(orderFormPostcode).scrollIntoView(false).setValue(map.get("Postcode"));
            $(orderFormCity).setValue(map.get("City"));
            $(orderFormPhone).scrollIntoView(false).setValue(map.get("Phone"));
            $(orderFormEmail).setValue(map.get("Email"));
            $(orderFormOrderComments).scrollIntoView(false).setValue(map.get("OrderComments"));
            if (map.get("AboneCheckBox").equals("true"))
                $(orderFormAboneCheckBox).scrollIntoView(false).click();
            /*
            if (!map.get("Payment").equalsIgnoreCase("transfer")) {
                $(orderFormPaymentPlus).scrollIntoView(false).click();
                switchTo().frame(0);
                switch (map.get("Payment")) {
                    case "bank":
                        $(orderFormPaymentBank).click();
                        break;
                    case "credit":
                        $(orderFormPaymentVisa).click();
                        break;
                }
                switchTo().parentFrame();
            }else*/

            $(orderFormPaymentTransfer).scrollIntoView(false).click();

            if (map.get("Terms").equals("true"))
                $(orderFormTerms).scrollIntoView(false).click();
            $(orderFormOrderButton).scrollIntoView(false).click();

            if (map.get("Result").equalsIgnoreCase("false"))
                $$(getOrderFormAlert).shouldHave(CollectionCondition.sizeGreaterThan(0));
            else
                $("li.iban").shouldBe(Condition.visible);
        }
    }
}
