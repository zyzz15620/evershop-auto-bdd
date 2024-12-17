package steps.admin;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CommonPage;

import static steps.Hooks.page;

public class CommonStepdefs {
    private CommonPage commonPage = new CommonPage(page);
    @When("User select menu item {string}")
    public void selectMenuItem(String label) {
        commonPage.selectMenuItem(label);
    }

    @Then("User now in page with title {string}")
    public void verifyPageWithHeader(String value) {
        commonPage.verifyPageWithHeader(value);
    }

    @When("User input {string} field with value {string}")
    public void inputFieldByLabel(String label, String value) {
        commonPage.inputFieldByLabel(label, value);
    }

    @Then("User should see {string} page")
    public void verifyPageTitle(String title) {
        commonPage.verifyPageTitleVisible(title);
    }

    @Then("User shouldn't see {string} page")
    public void verifyPageTitleNotVisible(String title) {
        commonPage.verifyPageTitleNotVisible(title);
    }
}
