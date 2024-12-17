package common;

public class ConstantUtils {
    public static final String BASE_URL = ConfigUtils.getDotenv().get("url");
    public static final String PORT = ConfigUtils.getDotenv().get("port");
    public static final String COMMON_URL = String.format("%s:%s", BASE_URL, PORT);
    public static final String ADMIN_LOGIN_PAGE = "/admin/login";
    public static final String DELETED_PRODUCT_API = "/api/products/%s";
    public static final String CREATE_PRODUCT_API = "/api/products";



}
