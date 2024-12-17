package common;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfigUtils {
    private static Dotenv dotenv;

    public static Dotenv getDotenv(){
        String env = System.getenv("test.env");
        if(env==null){
            env = "local.env";
        }
        dotenv = Dotenv
                .configure()
                .directory("env")
                .filename(env)
                .load();
        return dotenv;
    }
}
