package steps.admin;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static steps.Hooks.page;

public class AdminLoginStepdefs {

    @Given("User navigate to admin login page")
    public void navigateAdminLoginPage(){
        page.navigate("http://localhost:3000/admin/login");
        String buttonSignInXpath = "//button[normalize-space(.//text())='SIGN IN']";
        assertThat(page.locator(buttonSignInXpath)).isVisible();
    }

    @When("User input {string} field with value {string}")
    public void inputEmail(String label, String value) {
        String inputXpath = String.format("//div[./label[normalize-space(.//text())='%s']]//input", label);
        page.locator(inputXpath).fill(value);
    }

    @And("User click on button {string}")
    public void clickButton(String label) {
        String buttonXpath = String.format("//button[normalize-space(.//text())='%s']", label);
        page.locator(buttonXpath).click();
    }

    @Then("I should see Dashboard page")
    public void iShouldSeeDashboardPage() {
        assertThat(page.locator("//h1[normalize-space(text())='Dashboard']")).isVisible();
    }
}
