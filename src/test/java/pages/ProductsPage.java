package pages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.RequestOptions;
import model.Product;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static common.ConstantUtils.*;
import static data.ProductsData.PRODUCT_WITH_IMAGE;

public class ProductsPage extends CommonPage {
    private String productCreatedName;
    private String productSku;

    public ProductsPage(Page page) {
        super(page);
    }

    public void createProductByApi() throws JsonProcessingException {
        APIResponse response = page.request().post(COMMON_URL + CREATE_PRODUCT_API, RequestOptions.create().setHeader("Content-Type", "application/json").setData(PRODUCT_WITH_IMAGE));

        //Save name for search
        ObjectMapper mapper = new ObjectMapper();
        Product product = mapper.readValue(PRODUCT_WITH_IMAGE, Product.class);
        productCreatedName = product.getName();
        productSku = product.getSku();
        //Debug
        String responseBody = new String(response.body(), StandardCharsets.UTF_8);
        System.out.println(responseBody);

        //Chỗ này có thể lấy luôn cái ID từ response để lát nữa xoá
    }

    //search with name
    public void searchCreatedProduct(){
        String searchFieldXpath = "//form[@method='POST']//input[@placeholder='Search']";
        page.locator(searchFieldXpath).fill(productCreatedName);
        page.locator(searchFieldXpath).press("Enter");
    }

    //click based on SKU
    public void clickOnCreatedProduct() throws InterruptedException {
        String tableHeaderXpath = "//table[contains(concat(' ',normalize-space(@class),' '),' listing ')]//th";
        List<String> headers = Arrays.asList(page.locator(tableHeaderXpath).allTextContents().toArray(new String[0]));
        int skuIndex = headers.indexOf("SKU") + 1;

        String productNameXpath = String.format("//tbody[./tr[td]/td[%s]]//a[contains(text(),'%s')]", skuIndex, productCreatedName);
        page.locator(productNameXpath).click();
        Thread.sleep(5000);
    }

    public void verifyCreateProductRequest(){

    }
}
