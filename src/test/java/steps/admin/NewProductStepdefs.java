package steps.admin;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.SelectOption;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.net.URL;
import java.nio.file.Path;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.Assert.assertEquals;
import static steps.Hooks.page;

public class NewProductStepdefs {
    @When("User select category {string} on New Product page")
    public void selectCategory(String label) {
        String openCategoryXpath = "//a[normalize-space()='Select category']";
        page.locator(openCategoryXpath).click();
        String itemXpath = String.format("//div[normalize-space()='Category']//following-sibling::div[1]//a[normalize-space()='%s']", label);
        page.locator(itemXpath).click();
    }

    @And("User input product description on New Product page with layout {string}")
    public void inputDesc(String layout, String desc) throws InterruptedException {
        //Step 1: Split String for each column
        String[] listDescArray = desc.split( "<-->");

        //Step 2: Select layout type with index
        String baseXpath = "//label[normalize-space()='Description']//following-sibling::div";
        String layoutXpath = String.format("%s//div[contains(concat(' ',normalize-space(@class),' '),' row-templates ')]//a[%s]",baseXpath, layout);
        page.locator(layoutXpath).click();

        //Step 3: Input data to each column on the last row
        String descRowXpath = String.format("(%s//div[contains(concat(' ',normalize-space(@class),' '),' row ')])[last()]", baseXpath);
        for(int i = 0; i < listDescArray.length; i++){
            String descColumn = String.format("(%s//div[@contenteditable='true'])[%s]", descRowXpath, i+1);
            page.locator(descColumn).fill(listDescArray[i]);
        }
        Thread.sleep(1000);
    }

    @And("User push image {string} on New Product page")
    public void pushImage(String images) throws InterruptedException {
        String uploadButtonXpath = "//div[contains(concat(' ',normalize-space(@class),' '),' uploader-icon ')]/label";
        String removeButtonXpath = "//div[contains(concat(' ',normalize-space(@class),' '),' image ')]" +
                "//span[contains(concat(' ',normalize-space(@class),' '),' remove ')]";
        ClassLoader classLoader = getClass().getClassLoader();
        String[] inputUploadListLabel = images.split(", ");
        for(int i = 0; i < inputUploadListLabel.length; i++){
            URL inputStream = classLoader.getResource(String.format("upload/%s", inputUploadListLabel[i]));
            page.locator(uploadButtonXpath).setInputFiles(Path.of(inputStream.getPath()));
            Thread.sleep(2000);
            assertEquals(i+1, page.locator(removeButtonXpath).count());
        }
    }

    @And("User select option {string} of radio {string}")
    public void selectRadio(String option, String group) {
        String optionXpath = String.format("//label[normalize-space()='%s']//following-sibling" +
                "::div[contains(concat(' ',normalize-space(@class),' '),' radio-field ')]" +
                "//input[../span[normalize-space()='%s']]", group, option);
        page.locator(optionXpath).check();
    }

    @And("User select {string} on dropdown Tax class")
    public void selectTaxClass(String itemLabel) {
        String selectClass = "//select[@id='tax_class']";
        String itemXpath = String.format("%s//option[normalize-space()='%s']", selectClass, itemLabel);
        String defaultValue = page.locator(selectClass).getAttribute("placeholder");
        if( !defaultValue.equals(itemLabel) ){
            page.locator(selectClass).click();
            page.locator(itemXpath).click();
        }
    }

    @And("User input {string} textarea with value {string}")
    public void inputTextareaByLabel(String label, String value) {
        String textareaXpath = String.format("//div[./label[normalize-space(.//text())='%s']]//textarea", label);
        page.locator(textareaXpath).fill(value);
    }

    @And("User click button {string}")
    public void clickButton(String text) {
        String buttonXpath = String.format("//button[.//text()[normalize-space()='%s']]", text);
        page.locator(buttonXpath).click();
    }

    @And("User select {string} on dropdown Attribute Group")
    public void selectAttributeGroup(String option) {
        String dropdownXpath = String.format("//div[h3[normalize-space()='Attribute group']]//following-sibling::div//select");
        page.locator(dropdownXpath).selectOption(new SelectOption().setLabel(option));
    }

    @And("User select {string} on dropdown Attributes {string}")
    public void selectAttributes(String option, String attribute) {
        String dropdownXpath = String.format("//div[h3[normalize-space()='Attributes']]//following-sibling::div//tr[td[normalize-space()='%s']]//select", attribute);
        page.locator(dropdownXpath).selectOption(new SelectOption().setLabel(option));
    }

    @Then("User see alert notification {string}")
    public void userSeeAlertNotification(String text) {
        String alertXpath = String.format("//div[contains(concat(' ',normalize-space(@class),' '),' Toastify ')]//div[@role='alert']");
        Locator alertLocator = page.locator(alertXpath);
        assertThat(alertLocator).hasText(text);
    }
}
