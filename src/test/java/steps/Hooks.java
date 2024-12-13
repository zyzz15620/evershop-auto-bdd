package steps;

import com.microsoft.playwright.*;
import common.ConfigUtils;
import io.cucumber.java.*;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static common.BrowserManagement.getBrowserInstance;
import static common.ConstantUtils.*;
import static data.ProductsData.STUB_RESPONSE_CREATE_PRODUCT;

public class Hooks {
    public static Playwright playwright;
    public static Browser browser;
    public static BrowserContext context;
    public static Page page;
    static List<String> ids = new ArrayList<>();

    @BeforeAll
    public static void beforeAll(){
        playwright = Playwright.create();
        browser = getBrowserInstance();
    }

    @AfterAll
    public static void afterAll() {
        playwright.close();
    }

    @Before(order = 999) //default is 1000
    public void beforeEach(){
        context = browser.newContext();
        page = context.newPage();
        page.route("**", handler -> {
            String pageUrl = handler.request().url();
            String method = handler.request().method();
            System.out.println("URL: " + pageUrl);
            System.out.println("METHOD: " + method);
            if(pageUrl.contains("/admin/products/edit")){
                collectProductId(pageUrl, handler);
            } else {
                handler.resume();
            }
        });
    }

    @Before("@FrontEnd_Verify_Login")
    public void beforeEachLogin(){
        page.route("**", handler -> {
            String pageUrl = handler.request().url();
            String method = handler.request().method();
            System.out.println("URL: " + pageUrl);
            System.out.println("METHOD: " + method);
            if("POST".equals(method) && pageUrl.endsWith("/admin/user/login")){
                handler.fulfill(new Route.FulfillOptions()
                        .setStatus(500)
                        .setContentType("text/plain")
                        .setBody("Server Lỗi!"));
            } else {
                handler.resume();
            }
        });
    }



    @Before("@FrontEnd_Verify_CreateProduct")
    public void beforeEachProductCreate(){
        page.route("**", handler -> {
            String pageUrl = handler.request().url();
            String method = handler.request().method();
            System.out.println("URL: " + pageUrl);
            System.out.println("METHOD: " + method);
            if("POST".equals(method) && pageUrl.endsWith("/api/products")){
                handler.fulfill(new Route.FulfillOptions()
                        .setStatus(500)
                        .setContentType("application/json")
                        .setBody(STUB_RESPONSE_CREATE_PRODUCT));
            } else {
                handler.resume();
            }
        });
    }

    private static void collectProductId(String pageUrl, Route handler){
        List<String> urlElements = List.of(pageUrl.split("/"));
        String id = urlElements.get(urlElements.size()-1);
        ids.add(id);
        handler.resume();
    }
    //config order 1 because beforeEach() need to run before beforeCreateProduct(). By default order is 1000

//    @Before("@Delete_product_after")
//    public void beforeCreateProduct(){
//        page.route("**/admin/products/edit/**", handler -> {
//            ids.add(handler.request().method());
//            handler.resume();
//        });
//    }

//    @After("@Delete_product_after")
//    public void afterCreateProduct(){
//
//    }

    @After
    public void afterEach(){
        ids.forEach(id ->{
            APIResponse response = page.request().delete( COMMON_URL + String.format(DELETED_PRODUCT_API, id));
            String responseBody = new String(response.body(), StandardCharsets.UTF_8);
            System.out.println(responseBody);
        });
        context.close();
    }

    @After
    public void afterFail(Scenario scenario){
        if(scenario.isFailed()){
            byte[] image = page.screenshot(new Page.ScreenshotOptions().
                    setPath(Path.of("build/screenshots/" + scenario.getName().replaceAll("\\s", "_") + ".png")).
                    setFullPage(true));
            Allure.addAttachment("Screenshot", new ByteArrayInputStream(image)); //This is to add picture to allure report
        }
    }
    //Save in build so that every time we run test, the old image will be cleared
}
