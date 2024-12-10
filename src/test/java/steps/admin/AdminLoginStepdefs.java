package steps.admin;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.Assert.assertNull;
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

    @Then("User should see Dashboard page")
    public void iShouldSeeDashboardPage() {
        assertThat(page.locator("//h1[normalize-space(text())='Dashboard']")).isVisible();
    }

    @Then("User should see error message for {string} is {string}")
    public void verifyValidationMessage(String label, String errorMessage) {
        String xpath = String.format("//div[./label[normalize-space(.//text())='%s']]//div[contains(concat(' ',normalize-space(@class),' '),' field-error ')]", label);
        if ("".equals(errorMessage)){
            Locator messageLocator = page.locator(xpath);
            try {
                messageLocator.waitFor(new Locator.WaitForOptions().setTimeout(1000));
            } catch (Exception e){
                messageLocator = null;
            }
            assertNull(messageLocator);
        } else {
            assertThat(page.locator(xpath)).hasText(errorMessage);
        }
    }
}
