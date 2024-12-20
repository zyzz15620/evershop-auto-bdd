package steps.admin;

import com.microsoft.playwright.Page;
import common.ConfigUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CommonPage;

import static steps.Hooks.pageMap;

//import static steps.Hooks.page;

public class CommonStepdefs {

    private final Page page = pageMap.get(Thread.currentThread().getName());

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
        if("$ADMIN_EMAIL$".equals(value)){
            value = ConfigUtils.getDotenv().get("adminEmail");
        } else if ("$ADMIN_PASSWORD$".equals(value)) {
            value = ConfigUtils.getDotenv().get("adminPassword");
        }
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
