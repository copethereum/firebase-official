package common.core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebPageBase {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ExtendedWebDriver extendedDriver;
    protected AppManager app;

    public WebPageBase(AppManager app) throws Exception {
        this.driver = app.getDriver();
        this.wait = new WebDriverWait(this.driver,
                MTFTimeouts.getTimeout(MTFTimeouts.DRIVER_WAIT_DEFAULT, TimeUnit.SECONDS));

        this.extendedDriver = new ExtendedWebDriver(this.driver, this.wait);
        this.app = app;

        PageFactory.initElements(this.driver, this);
    }

}
