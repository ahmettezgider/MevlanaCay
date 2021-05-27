package stepdefs;

import static com.codeborne.selenide.Condition.*;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.Locators;
import utils.MenuLinks;
import utils.MyMethods;

import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

public class ContactUsSteps extends MyMethods implements Locators {

    @And("user navigate to contact page")
    public void userNavigateToContactPage() {
        navigateOnMenuTo(MenuLinks.CONTACTUS);
    }

    @When("user select the following languages as {string}")
    public void userSelectTheFollowingLanguagesAs(String language) {
        navigateOnMenuTo(MenuLinks.valueOf(language));

    }

    @Then("the success of submission of the contact form should be as follows")
    public void theSuccessOfSubmissionOfTheContactFormShouldBeAsFollows(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            $(inputNameContact).scrollIntoView(false).setValue(columns.get("name"));
            $(inputEmailContact).scrollIntoView(false).setValue(columns.get("email"));
            $(inputSubjectContact).scrollIntoView(false).setValue(columns.get("subject"));
            $(textareaMessajeContact).scrollIntoView(false).setValue(columns.get("message"));
            $(buttonSubmitContact).click();
            String success = columns.get("success");
            if (success.equalsIgnoreCase("true"))
                $(notificationFormContact)
                        .shouldBe(visible)
                        .shouldNotHave(or("Contact success", text("fehlerhaft"), text("error"), text("hata ")));
            else
                $(notificationFormContact)
                        .shouldBe(visible)
                        .shouldHave(or("Contact failed", text("fehlerhaft"), text("error"), text("hata ")));


        }
    }

    @When("the submissions of the following eMails should be as follows")
    public void theSubmissionsOfTheFollowingEMailsShouldBeAsFollows(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            String buttonText = $(submitRegistration).getValue()+"";
            $(inputMailRegistration).shouldBe(visible).setValue(columns.get("email"));
            $(submitRegistration).shouldBe(enabled).click();
            if (columns.get("result").equalsIgnoreCase("not")){
                $(submitRegistration).shouldNotHave(Condition.value("."));
            } else if (columns.get("result").equalsIgnoreCase("fake")){
                if (getSiteLanguage().equals("DE"))
                    $(registrationBar).shouldHave(Condition.text("schief"));
                if (getSiteLanguage().equals("EN"))
                    $(registrationBar).shouldHave(Condition.text("wrong"));
                if (getSiteLanguage().equals("TR"))
                    $(registrationBar).shouldHave(Condition.text("hata"));
            } else if (columns.get("result").equalsIgnoreCase("real")){
                $(submitRegistration).shouldNotHave(Condition.value(buttonText));
            }
        }

    }
}
