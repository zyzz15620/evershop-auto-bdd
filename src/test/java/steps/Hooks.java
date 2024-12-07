package steps;

import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

public class Hooks {
    public static Playwright playwright;
    public static Browser browser;
    public static BrowserContext context;
    public static Page page;

    @BeforeAll
    public static void beforeAll(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @AfterAll
    public static void afterAll() {
        playwright.close();
    }

    @Before //before each scenario, similar to @BeforeEach on playwright
    public void beforeEach(){
        context = browser.newContext();
        page = context.newPage();

    }

    @After
    public void afterEach(){
        context.close();
    }
}
