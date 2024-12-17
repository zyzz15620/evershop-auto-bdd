package steps.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.EditProductPage;
import pages.NewProductPage;
import pages.ProductsPage;

import static steps.Hooks.page;

public class NewProductStepdefs {
    NewProductPage newProductPage = new NewProductPage(page);
    EditProductPage editProductPage = new EditProductPage(page);
    ProductsPage productsPage = new ProductsPage(page);
    @When("User select category {string} on New Product page")
    public void selectCategory(String label) {
        newProductPage.selectCategory(label);
    }

    @And("User input product description on New Product page with layout {string}")
    public void inputDesc(String layout, String desc) throws InterruptedException {
        newProductPage.inputDesc(layout, desc);
    }

    @And("User push image {string} on New Product page")
    public void pushImage(String images) throws InterruptedException {
        newProductPage.pushImage(images);
    }

    @And("User select option {string} of radio {string}")
    public void selectRadio(String option, String group) {
        newProductPage.selectRadio(option, group);
    }

    @And("User select {string} on dropdown Tax class")
    public void selectTaxClass(String itemLabel) {
        newProductPage.selectTaxClass(itemLabel);
    }

    @And("User input {string} textarea with value {string}")
    public void inputTextareaByLabel(String label, String value) {
        newProductPage.inputTextareaByLabel(label, value);
    }

    @And("User click button {string}")
    public void clickButton(String text) {
        newProductPage.clickButton(text);
    }

    @And("User select {string} on dropdown Attribute Group")
    public void selectAttributeGroup(String option) {
        newProductPage.selectAttributeGroup(option);
    }

    @And("User select {string} on dropdown Attributes {string}")
    public void selectAttributes(String option, String attribute) {
        newProductPage.selectAttributes(option, attribute);
    }

    @Then("User see alert notification {string}")
    public void userSeeAlertNotification(String text) {
        newProductPage.userSeeAlertNotification(text);
    }

    @And("User delete product {string}")
    public void deleteProductByApi(String productName) {
        editProductPage.deleteProductByApi(productName);
    }

    @And("User input a random SKU")
    public void userInputARandomSKU() {
        newProductPage.userInputARandomSKU();
    }

    @And("User create a product")
    public void createProductByApi() throws JsonProcessingException {
        productsPage.createProductByApi();
    }

    @And("User search for product that just created")
    public void userSearchForProductThatJustCreated() {
        productsPage.searchCreatedProduct();
    }

    @And("User click on the product that just created")
    public void userClickOnTheProductThatJustCreated() throws InterruptedException {
        productsPage.clickOnCreatedProduct();
    }

    @Then("User see should see input field {string} is {string}")
    public void verifyInputField(String label, String value) {
        editProductPage.verifyInputFieldValue(label, value);
    }

    @Then("Stub Api will verify the client's request")
    public void stubApiWillVerifyTheClientSRequest() {

    }
}
