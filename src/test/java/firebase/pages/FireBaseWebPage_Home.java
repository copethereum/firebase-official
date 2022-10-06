package firebase.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import firebase.core.web.FireBaseWebManager;
import firebase.pages.FireBaseWebPage_Home;

public class FireBaseWebPage_Home extends FireBaseWebPage_Base {

	   public FireBaseWebPage_Home(FireBaseWebManager firebase) throws Exception {
	        super(firebase);
	    }

	@FindBy(xpath = "//h4[contains(text(), 'Sizes')]")
	private WebElement lbl_sizes;

	@FindBy(xpath = "//div[@title = 'Products in cart quantity']")
	private WebElement cart_menu;

	@FindBy(xpath = "//span[contains(text(), 'Cart')]")
	private WebElement cart_lbl;

	@FindBy(xpath = "//button/span[contains(text(), 'X')]")
	private WebElement cart_close_button;

	@FindBy(xpath = "//button[contains(text(), 'Checkout')]")
	private WebElement cart_checkout_button;

	@FindBy(xpath = "(//p[contains(text(), 'Basic Cactus White T-shirt')])[2]")
	private WebElement added_to_cart_cactus_shirts;

	@FindBy(xpath = "//p[contains(text(), 'Basic Cactus White T-shirt')]/../button")
	private WebElement add_to_cart_cactus_shirts;

	@FindBy(xpath = "//p[contains(text(), 'Skater Black Sweatshirt')]/../button")
	private WebElement add_to_cart_skater_sweatshirt;

	@FindBy(xpath = "(//p[contains(text(), 'Skater Black Sweatshirt')])[2]")
	private WebElement added_to_cart_skater_sweatshirt;

	@FindBy(xpath = "(//button[contains(text(), '+')])[2]")
	private WebElement add_quantity_of_skater_sweatshirt;

	@FindBy(xpath = "//button[@title = 'remove product from cart']")
	private WebElement remove_product;

	@FindBy(xpath = "//p[contains(text(), 'Add some products in the cart ')]")
	private WebElement cart_is_empty;

	 public FireBaseWebPage_Home isLoadedHomePage() throws Exception {
	        wait.until(ExpectedConditions.visibilityOf(lbl_sizes));

	        System.out.println("Home Page Is Loaded");

	        return this;
	    }

	 public FireBaseWebPage_Home isLoadedAndClosedCart() throws Exception {
	     wait.until(ExpectedConditions.visibilityOf(cart_menu));
	     cart_menu.click();
	     wait.until(ExpectedConditions.visibilityOf(cart_lbl));
	     wait.until(ExpectedConditions.visibilityOf(cart_close_button));
	     wait.until(ExpectedConditions.visibilityOf(cart_checkout_button));
	     cart_close_button.click();

         System.out.println("Cart is properly functioning!!!!");

         return this;
     }

	   public FireBaseWebPage_Home addToCartSingleItem() throws Exception {
           wait.until(ExpectedConditions.visibilityOf(add_to_cart_cactus_shirts));
           add_to_cart_cactus_shirts.click();
           wait.until(ExpectedConditions.visibilityOf(cart_lbl));
           wait.until(ExpectedConditions.visibilityOf(added_to_cart_cactus_shirts));

           System.out.println("Single Item Successfully Added");

           return this;
       }

       public FireBaseWebPage_Home addToCartMultipleItems() throws Exception {
           wait.until(ExpectedConditions.visibilityOf(add_to_cart_cactus_shirts));
           add_to_cart_cactus_shirts.click();

           wait.until(ExpectedConditions.visibilityOf(cart_lbl));
           wait.until(ExpectedConditions.visibilityOf(added_to_cart_cactus_shirts));
           wait.until(ExpectedConditions.visibilityOf(cart_close_button));
           cart_close_button.click();

           wait.until(ExpectedConditions.visibilityOf(add_to_cart_skater_sweatshirt));
           add_to_cart_skater_sweatshirt.click();

           wait.until(ExpectedConditions.visibilityOf(added_to_cart_skater_sweatshirt));
           wait.until(ExpectedConditions.visibilityOf(add_quantity_of_skater_sweatshirt));

           System.out.println("Multiple Items Successfully Added!!!!");

           return this;
       }

	   public FireBaseWebPage_Home checkout() throws Exception {
           wait.until(ExpectedConditions.visibilityOf(cart_checkout_button));
           cart_checkout_button.click();

           wait.until(ExpectedConditions.alertIsPresent());
           Alert alert = driver.switchTo().alert();
           String text = alert.getText();
           System.out.println(text);
           alert.accept();

           return this;
       }

	   public FireBaseWebPage_Home removeProduct() throws Exception {
           wait.until(ExpectedConditions.visibilityOf(remove_product));

         List<WebElement> allProducts = driver.findElements(By.xpath("//button[@title = 'remove product from cart']"));

         for(WebElement all : allProducts) {
             all.click();
         }

         wait.until(ExpectedConditions.visibilityOf(cart_is_empty));

          System.out.println("Products successfully removed from cart!!!");

           return this;
       }

}
