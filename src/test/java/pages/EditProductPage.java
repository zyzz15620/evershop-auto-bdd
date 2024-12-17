package pages;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Page;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static common.ConstantUtils.*;

public class EditProductPage extends CommonPage{
    public EditProductPage(Page page) {
        super(page);
    }

    public void deleteProductByApi(String productName){
        //Verify để đợi trang đã load xong sau khi tạo product
        //Hoặc Edit Name thì phải refresh lại để title cập nhật tên (trong verifyPageWithHeader có tự động refresh)
        verifyPageWithHeader(String.format("Editing %s", productName));

        String pageUrl = page.url();
        List<String> urlElements = List.of(pageUrl.split("/"));
        String id = urlElements.get(urlElements.size()-1);
        APIResponse response = page.request().delete(COMMON_URL + String.format(DELETED_PRODUCT_API, id));
        String responseBody = new String(response.body(), StandardCharsets.UTF_8);
        System.out.println(responseBody);
    }

    public void verifyInputFieldValue(String label, String value){
        String inputXpath = String.format("//div[./label[normalize-space(.//text())='%s']]//input", label);
        assertThat(page.locator(inputXpath)).hasAttribute("value", value);
    }

}
