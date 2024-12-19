package steps;

import com.microsoft.playwright.*;
import common.BrowserManagement;
import io.cucumber.java.*;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;

import static common.ConstantUtils.*;
import static data.ProductsData.STUB_RESPONSE_CREATE_PRODUCT;

public class Hooks {
    public static Map<String, Playwright> playwrightMap = new HashMap<>();
    public static Map<String, Browser> browserMap = new HashMap<>();
    public static Map<String, BrowserContext> contextMap = new HashMap<>();
    public static Map<String, Page> pageMap = new HashMap<>();

//    public static Playwright playwright;
//    public static Browser browser;
//    public static BrowserContext context;
//    public static Page page;
    static Set<String> ids = new HashSet<>();

    @BeforeAll
    public static void beforeAll(){
//        playwright = Playwright.create();
//        browser = getBrowserInstance();
//        playwrightMap.put(Thread.currentThread().getName(), Playwright.create());
//        browserMap.put(Thread.currentThread().getName(), BrowserManagement.getBrowserInstance());

    }
    private Thread currentThread = Thread.currentThread();
    @AfterAll
    public static void afterAll() {
//        playwright.close();
        playwrightMap.get(Thread.currentThread().getName()).close();
    }

    @Before(order = 999) //default is 1000
    public void beforeEach() throws InterruptedException {
//        context = browser.newContext();
//        context = browserMap.get(Thread.currentThreadName().getName()).newContext();
        String currentThreadName = Thread.currentThread().getName();
        if(playwrightMap.get(currentThreadName)==null){
            Playwright currentPlayWright = Playwright.create();
            currentThread.sleep(4000); // Playwright.create() take time to create
            playwrightMap.put(currentThreadName, currentPlayWright);
        }
        if(browserMap.get(currentThreadName)==null){
            Browser currentBrowser = BrowserManagement.getBrowserInstance();
            currentThread.sleep(4000);
            browserMap.put(currentThreadName, currentBrowser);
             // getBrowserInstance() also take time to launches browser
        }
        if(contextMap.get(currentThreadName)==null){
            BrowserContext currentContext = browserMap.get(currentThreadName).newContext();
            contextMap.put(currentThreadName, currentContext);
        }
        if(pageMap.get(currentThreadName)==null){
            Page currentPage = contextMap.get(currentThreadName).newPage();
            pageMap.put(currentThreadName, currentPage);
        }
        pageMap.get(currentThreadName).route("**", handler -> {
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
        pageMap.get(Thread.currentThread().getName()).route("**", handler -> {
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
        pageMap.get(Thread.currentThread().getName()).route("**", handler -> {
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
    public void afterEach() throws InterruptedException {
        String currentThreadName = Thread.currentThread().getName();

        ids.forEach(id ->{
            try {
                Thread.sleep(2000);
                APIResponse response = pageMap.get(currentThreadName).request().delete( COMMON_URL + String.format(DELETED_PRODUCT_API, id));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        contextMap.get(currentThreadName).close();
        contextMap.remove(currentThreadName);
        //remove ra khỏi map để đc tạo lại cho cái thread đó ở mỗi test
    }

    @After(order = 999)
    public void afterFail(Scenario scenario){
        if(scenario.isFailed()){
            byte[] image = pageMap.get(Thread.currentThread().getName()).screenshot(new Page.ScreenshotOptions().
                    setPath(Path.of("build/screenshots/" + scenario.getName().replaceAll("\\s", "_") + ".png")).
                    setFullPage(true));
            Allure.addAttachment("Screenshot", new ByteArrayInputStream(image)); //This is to add picture to allure report
        }
    }
    //Save in build so that every time we run test, the old image will be cleared
}
