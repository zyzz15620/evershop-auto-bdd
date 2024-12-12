package pages;

import com.microsoft.playwright.Page;

import static steps.Hooks.page;

public class CommonPage {
    private Page page;

    public CommonPage(Page page) {
        this.page = page;
    }

    public void selectMenuItem(String label) {
        String buttonXpath = String.format("//li[normalize-space(.//text())='%s' and contains(concat(' ',normalize-space(@class),' '),' nav-item ')]", label);
        page.locator(buttonXpath).click();
    }
}
