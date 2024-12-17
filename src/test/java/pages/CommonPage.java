package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.Assert.assertTrue;

public class CommonPage {
    public Page page;

    public CommonPage(Page page) {
        this.page = page;
    }

    public void selectMenuItem(String label) {
        String buttonXpath = String.format("//li[normalize-space(.//text())='%s' and contains(concat(' ',normalize-space(@class),' '),' nav-item ')]", label);
        page.locator(buttonXpath).click();
    }

    public void verifyPageWithHeader( String value) {
        String headerXpath = String.format("//h1[contains(concat(' ',normalize-space(@class),' '),' page-heading-title ') and ./text()[normalize-space()='%s']]", value);
        assertThat(page.locator(headerXpath)).isVisible();
    }

    public void inputFieldByLabel(String label, String value) {
        String inputXpath = String.format("//div[./label[normalize-space(.//text())='%s']]//input", label);
        page.locator(inputXpath).fill(value);
    }

    public void verifyPageTitleVisible(String title) {
        assertThat(page.locator(String.format("//h1[normalize-space(text())='%s']", title))).isVisible();
    }

    public void verifyPageTitleNotVisible(String title) {
        Locator locator = page.locator(String.format("//h1[normalize-space(text())='%s']", title));
        assertTrue(!locator.isVisible());
    }
}
