package common;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;

import static steps.Hooks.playwright;

public class BrowserManagement {
    public static Browser getBrowserInstance(){
        String browserType = ConfigUtils.getDotenv().get("browser");
        return switch (browserType){
            case "chrome" -> playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            case "firefox" -> playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(true));
            case "webkit" -> playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(true));
            default -> throw new IllegalStateException("Unexpected value: " + browserType);
        };
    }
}
