package common.core;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class MTFProperties {

    public static Properties dynamic = new Properties();
    public static Properties testng = new Properties();
    public static Properties timeouts = new Properties();

    public static void init() {

    	File propertiesDir = new File(System.getProperty("user.dir"), "src/test/properties");

        initFromFile(dynamic, propertiesDir + "/dynamic.properties");
        initFromFile(testng, propertiesDir + "/testng.properties");
        initFromFile(timeouts, propertiesDir + "/timeouts.properties");
    }

    public static void initFromFile(Properties props, String filepath) {
        try {
            FileInputStream stream = new FileInputStream(filepath);
            props.load(stream);
            stream.close();
        } catch(Exception e) {
            System.out.println("Error initializing props: " + e.getMessage());
        }
    }

    public static String getBrowser() {
        return dynamic.getProperty("browser").toLowerCase();
    }

    public static String getEnvironment() {
        String runEnvironment = System.getProperty("run_environment");
        if(StringUtils.isEmpty(runEnvironment)) {
            return dynamic.getProperty("environment").toLowerCase();
        } else {
            return runEnvironment.toLowerCase();
        }
    }

    public static String getServer() {
        return dynamic.getProperty("server").toLowerCase();
    }
    
    public static String getExecutionEnvironment() {
        return dynamic.getProperty("environment").toLowerCase();
    }

}
