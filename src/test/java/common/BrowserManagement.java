package common;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

//import static steps.Hooks.playwright;
import static steps.Hooks.playwrightMap;

public class BrowserManagement {
    public static Browser getBrowserInstance() throws InterruptedException {
        String browserType = ConfigUtils.getDotenv().get("browser");
        Playwright playwright = playwrightMap.get(Thread.currentThread().getName());
        return switch (browserType){
            case "chrome" -> playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            case "firefox" -> playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(true));
            case "webkit" -> playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(true));
            default -> throw new IllegalStateException("Unexpected value: " + browserType);
        };
    }
}
