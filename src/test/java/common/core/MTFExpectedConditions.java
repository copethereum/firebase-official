package common.core;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;

public class MTFExpectedConditions {

    public static ExpectedCondition<Boolean> invisibilityOf(final WebElement element) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return !element.isDisplayed();
                } catch(Exception e) {
                    return true;
                }
            }
            @Override
            public String toString() {
                return "invisibility of " + element;
            }
        };
    }

    public static ExpectedCondition<List<WebElement>> presenceOfElements(final List<WebElement> elements) {
        return new ExpectedCondition<List<WebElement>>() {
            @Override
            public List<WebElement> apply(WebDriver driver) {
                try {
                    if(elements.size() > 0)
                        return elements;
                    else
                        return null;
                } catch(Exception e) {
                    return null;
                }
            }
            @Override
            public String toString() {
                return "presense of elements";
            }
        };
    }

    public static void selectByVisibleText(WebElement webElement, String text) throws InterruptedException {
        Select select = new Select(webElement);

        // wait until options have been loaded
        while(!(select.getOptions().size() > 0)) {
            Thread.sleep(10);
        }

        select.selectByVisibleText(text);
    }

    public static boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch(NoSuchElementException e) {
            return false;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
