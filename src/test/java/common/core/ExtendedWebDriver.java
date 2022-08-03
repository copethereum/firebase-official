package common.core;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExtendedWebDriver {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ExtendedWebDriver(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch(NoSuchElementException e) {
            return false;
        }
    }

}
