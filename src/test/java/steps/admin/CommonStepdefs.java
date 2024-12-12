package steps.admin;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CommonPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static steps.Hooks.page;

public class CommonStepdefs {
    private CommonPage commonPage = new CommonPage(page);
    @When("User select menu item {string}")
    public void selectMenuItem(String label) {
        commonPage.selectMenuItem(label);
    }

    @Then("User should see {string} page with title {string}")
    public void verifyPageWithHeader(String header, String value) {
        String headerXpath = String.format("//h1[contains(concat(' ',normalize-space(@class),' '),' page-heading-title ') and ./text()[normalize-space()='%s']]", value);
        assertThat(page.locator(headerXpath)).isVisible();
    }

    @When("User input {string} field with value {string}")
    public void inputFieldByLabel(String label, String value) {
        String inputXpath = String.format("//div[./label[normalize-space(.//text())='%s']]//input", label);
        page.locator(inputXpath).fill(value);
    }

    @Then("User should see {string} page")
    public void verifyPageTitle(String title) {
        assertThat(page.locator(String.format("//h1[normalize-space(text())='%s']", title))).isVisible();
    }
}
