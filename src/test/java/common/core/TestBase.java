package common.core;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.core.TestBase;
import firebase.core.web.FireBaseWebManager;
import firebase.core.web.FireBaseWebTestBase;

import static common.core.Constants.ENV_LOCAL;
import static common.core.Constants.CHROME_BROWSER;
import static common.core.Constants.EDGE_BROWSER;
import static common.core.Constants.FIREFOX_BROWSER;
import static common.core.Constants.IE_BROWSER;
import static common.core.Constants.OPERA_BROWSER;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.openqa.selenium.WebDriver;

@Listeners({
    common.core.TestsRunner.class
})

public class TestBase {

    public static FireBaseWebManager etherscan = new FireBaseWebManager(TestBase.appManager);

    public static WebDriver driver;
    public static AppManager appManager;

    // Extent report variables
    static ExtentTest logger;
    String date;

    // Jsoup variables
    protected Response res = null;
    protected int retryCount = 0;

    // flag for skipped tests
    protected boolean isSkip = false;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite
    public void initialization() throws Exception {
        try {
            initProperties();
        } catch(Exception e) {
            isSkip = true;
            System.out.println(e.getMessage());
        }
    }

    private void initProperties() throws Exception {
        System.out.println("=========================================================");
        System.out.println("=== INIT PROPERTIES");
        System.out.println("=========================================================");
        TestProperties.init();
    }

    @BeforeClass
    public void initAppManager(ITestContext testContext) throws Exception {
        System.out.println("=========================================================");
        System.out.println("=== INIT APP MANAGER");
        long startTimer = System.currentTimeMillis();

        System.out.println("=== App Manager: start....");
        appManager = new AppManager();
        driver = initWebDriver(testContext);
        appManager.setDriver(driver);
        System.out.println("=== App Manager: started ");

        long seconds = TimeUnit.SECONDS.convert(System.currentTimeMillis() - startTimer, TimeUnit.MILLISECONDS);
        System.out.println("=== " + seconds + "seconds");
        System.out.println("=========================================================");

        FireBaseWebTestBase webTestBase = new FireBaseWebTestBase();
        System.out.println("=========================================================");
        System.out.println("=== Wait for web application to load");
        webTestBase.firebase().settings().setTestServer();
        System.out.println("=== Done!");
        System.out.println("=========================================================");
    }

    public WebDriver initWebDriver(ITestContext testContext) throws Exception {
        WebDriver driver = null;
        String browser = TestProperties.getBrowser();
        if(TestProperties.getEnvironment().equals(ENV_LOCAL)) {
            System.out.println("=== WEBDRIVER: local initialization started...");
            driver = initDriver(browser);
            System.out.println("=== WEBDRIVER: local initialization finished");
        } else {
            System.out.println("=== WEBDRIVER: remote initialization started...");
            driver = initDriver(browser);
            System.out.println("=== WEBDRIVER: remote initialization finished");
        }

        return driver;
    }

    @AfterClass
    public void stopAppManager() {
        System.out.println("=========================================================");
        System.out.println("=== STOP WEBDRIVER");
        long startTimer = System.currentTimeMillis();
        driver.quit();
        long seconds = TimeUnit.SECONDS.convert(System.currentTimeMillis() - startTimer, TimeUnit.MILLISECONDS);
        System.out.println("=== " + seconds + "seconds");
        System.out.println("=== done! ");
        System.out.println("=========================================================");
    }


    /**
     * Initialization methods
     */

    private WebDriver initDriver(String browser) throws Exception {
        WebDriver newDriver = null;
        if(osDetector().equals("windows")) {
            String path = System.getProperty("user.dir") + "\\windows-drivers";
            if(browser.equals(FIREFOX_BROWSER)) {
                String exePath = path + "\\geckodriver.exe";
                System.setProperty("webdriver.gecko.driver", exePath);
                FirefoxOptions firefoxOptions = new FirefoxOptions();

                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("browser.download.folderList",2);
                profile.setPreference("browser.download.dir", Constants.PATH_BROWSER_DOWNLOADS);
                profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf, application/octet-stream");
                profile.setPreference("pdfjs.disabled", true);

                if(TestProperties.getEnvironment().equals(Constants.ENV_CLOUD)) {
                    // Firefox options for headless Firefox browser
                    firefoxOptions.addArguments("--headless");
                    firefoxOptions.addArguments("--width=1920");
                    firefoxOptions.addArguments("--height=1080");
                    firefoxOptions.addArguments("--allow-running-insecure-content");
                }
                firefoxOptions.setProfile(profile);
                newDriver = new FirefoxDriver(firefoxOptions);
            } else if(browser.equals(CHROME_BROWSER)) {
                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", Constants.PATH_BROWSER_DOWNLOADS);
                chromePrefs.put("safebrowsing.enabled", "true");

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setExperimentalOption("prefs", chromePrefs);

                DesiredCapabilities cap = DesiredCapabilities.chrome();
                cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                String exePath = path + "\\chromedriver.exe";
                System.setProperty("webdriver.chrome.driver", exePath);

                if(TestProperties.getEnvironment().equals(Constants.ENV_CLOUD)) {
                    // Chrome options for headless Chrome browser
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--allow-running-insecure-content");
                    chromeOptions.addArguments("--ignore-certificate-errors");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--window-size=1920,1080");
                    ChromeDriverService driverService = ChromeDriverService.createDefaultService();

                    newDriver = new ChromeDriver(driverService, chromeOptions);

                    Map<String, Object> commandParams = new HashMap<>();
                    commandParams.put("cmd", "Page.setDownloadBehavior");

                    Map<String, String> params = new HashMap<>();
                    params.put("behavior", "allow");
                    params.put("downloadPath", Constants.PATH_BROWSER_DOWNLOADS);
                    commandParams.put("params", params);

                    ObjectMapper objectMapper = new ObjectMapper();
                    HttpClient httpClient = HttpClientBuilder.create().build();

                    String command = objectMapper.writeValueAsString(commandParams);

                    String u = driverService.getUrl().toString() + "/session/" + ((RemoteWebDriver) newDriver).getSessionId() + "/chromium/send_command";

                    HttpPost request = new HttpPost(u);
                    request.addHeader("content-type", "application/json");
                    request.setEntity(new StringEntity(command));
                    httpClient.execute(request);

                    /** Added desired capabilities due to issue for loading
                     * insecure URL content in headless Chrome browser
                     */

                    cap.setCapability("acceptInsecureCerts", true);

                    /** this two options bellow could be used
                     * due to ChromeDriver Timed out receiving
                     * message from renderer exception
                     */
                    //options.addArguments("--disable-gpu");
                    //options.addArguments("--disable-browser-side-navigation");
                } else {
                    cap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                    newDriver = new ChromeDriver(cap);
                }
            } else if(browser.equals(IE_BROWSER)) {
                String exePath = path + "\\IEDriverServer.exe";
                System.setProperty("webdriver.ie.driver", exePath);
                newDriver = new InternetExplorerDriver();
            } else if(browser.equals(OPERA_BROWSER)) {
                String exePath = path + "\\operadriver.exe";
                OperaOptions options = new OperaOptions();
                options.setBinary("C:\\Program Files (x86)\\Jenkins\\workspace\\Opera\\opera.exe");
                System.setProperty("webdriver.opera.driver", exePath);
                newDriver = new OperaDriver(options);
            } else if(browser.equals(EDGE_BROWSER)) {
                String exePath = path + "\\msedgedriver.exe";
                System.setProperty("webdriver.edge.driver", exePath);
                EdgeOptions options = new EdgeOptions();
                options.setCapability("ms:inPrivate", true);
                newDriver = new EdgeDriver(options);
            }
        } else if(osDetector().equals("linux")) {
            String path = System.getProperty("user.dir") + "/linux-drivers/linux64";
            if(browser.equals(FIREFOX_BROWSER)) {
                String exePath = path + "/geckodriver";
                File file = new File(exePath);
                setExecutable(file);
                System.setProperty("webdriver.gecko.driver", exePath);
                newDriver = new FirefoxDriver();
            } else if(browser.equals(CHROME_BROWSER)) {
                String exePath = path + "/chromedriver";
                File file = new File(exePath);
                setExecutable(file);
                System.setProperty("webdriver.chrome.driver", exePath);
                newDriver = new ChromeDriver();
            } else if(browser.equals(OPERA_BROWSER)) {
                String exePath = path + "/operadriver";
                File file = new File(exePath);
                setExecutable(file);
                System.setProperty("webdriver.opera.driver", exePath);
                newDriver = new OperaDriver();
            }
        }
        if(TestProperties.getBrowser().equals(Constants.FIREFOX_BROWSER) && TestProperties.getEnvironment().equals(Constants.ENV_CLOUD)) {
            System.out.println("Skip window maximizing!");
        } else {
            newDriver.manage().window().maximize();
        }
        System.out.println("Window size: " + newDriver.manage().window().getSize().toString());

        return newDriver;
    }

    private String osDetector() {
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("win")) {
            return "windows";
        } else if(os.contains("nux") || os.contains("nix")) {
            return "linux";
        } else if(os.contains("mac")) {
            return "mac";
        } else if(os.contains("sunos")) {
            return "solaris";
        } else {
            return "other";
        }
    }

    private void setExecutable(File file) {
        if(file.exists() && !file.canExecute()) {
            file.setExecutable(true);
        }
    }

    protected org.jsoup.Connection jsoupConnection(String url, Method method) {
        org.jsoup.Connection conn = Jsoup.connect(url).timeout(TestProperties.getTimeout()).method(method)
                .ignoreContentType(true).validateTLSCertificates(false)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0");
        if(res != null) {
            conn = conn.cookies(res.cookies());
        }

        return conn;
    }

    public static void writeReportLog(String log) {
        logger.log(Status.INFO, MarkupHelper.createLabel(log, ExtentColor.BLUE));
    }
}
