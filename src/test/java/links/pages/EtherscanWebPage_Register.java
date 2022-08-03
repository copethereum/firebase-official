package links.pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import common.core.functions.CoreFunctions;
import common.core.MTFExpectedConditions;
import links.core.web.EtherscanWebManager;
import links.pages.EtherscanWebPage_Register;


public class EtherscanWebPage_Register extends EtherscanWebPage_Base {
	
	   public EtherscanWebPage_Register(EtherscanWebManager etherscan) throws Exception {
	        super(etherscan);
	    }
	   
	   private String email;
	   private String username;
	   private String password;

	@FindBy(xpath = "//a[@class = 'u-header__nav-link']//i")
	private WebElement link_sign_in;

	@FindBy(id = "ContentPlaceHolder1_txtUserName")
	private WebElement input_user_name;
	
	@FindBy(id = "ContentPlaceHolder1_txtConfirmEmail")
	private WebElement confirm_email;
	
	@FindBy(xpath = "//a[contains(text(), 'Click to sign up')]")
	private WebElement link_click_to_sign_up;
	
	@FindBy(xpath = "//h1[contains(text(), 'Register a ')]")
	private WebElement lbl_register_a_new_account;

	@FindBy(id = "ContentPlaceHolder1_txtEmail")
	private WebElement input_email;
	
	@FindBy(id = "ContentPlaceHolder1_txtPassword")
	private WebElement input_password;
	
	@FindBy(id = "ContentPlaceHolder1_txtPassword2")
	private WebElement input_confirm_password;
	
	@FindBy(name = "ctl00$ContentPlaceHolder1$btnRegister")
	private WebElement btn_create_account;
	
	@FindBy(xpath = "//a[@class = 'header-small-menu user-register']")
	private WebElement btn_register_page;
	
	@FindBy(xpath = "//input[@id = 'ContentPlaceHolder1_MyCheckBox']")
	private WebElement cbox_agree_to_terms;
	
	@FindBy(name = "ctl00$ContentPlaceHolder1$txtUserName")
	private WebElement input_username;
	
	@FindBy(id = "ContentPlaceHolder1_txtUserName-error")
	private WebElement error_msg_user_name;
	
	@FindBy(id = "ContentPlaceHolder1_txtEmail-error")
	private WebElement error_msg_email;
	
	@FindBy(id = "ContentPlaceHolder1_txtConfirmEmail-error")
	private WebElement error_msg_confirm_email;
	
	@FindBy(id = "ContentPlaceHolder1_txtPassword-error")
	private WebElement error_msg_password;
	
	@FindBy(id = "ContentPlaceHolder1_txtPassword2-error")
	private WebElement error_msg_confirm_password;
	
	@FindBy(id = "ctl00$ContentPlaceHolder1$MyCheckBox-error")
	private WebElement error_msg_accept_terms;
	
	public EtherscanWebPage_Register isLoadedRegisterPage() throws Exception {
		
		wait.until(ExpectedConditions.visibilityOf(link_sign_in)).click();	
		wait.until(ExpectedConditions.visibilityOf(input_username));
		wait.until(ExpectedConditions.visibilityOf(link_click_to_sign_up)).click();
		
		String register = lbl_register_a_new_account.getText();
		
		Assert.assertEquals("Register a New Account", register);
		
		System.out.println("Register page is properly loaded");

		return this;
	}

	public EtherscanWebPage_Register submitRegisterEmptyFields() throws Exception {
		
		 wait.until(ExpectedConditions.visibilityOf(btn_create_account)).click();
		 
		 String username = error_msg_user_name.getText();
		 String email = error_msg_email.getText();
		 String confirm_email = error_msg_confirm_email.getText();
		 String password = error_msg_password.getText();
		 String confirm_password = error_msg_confirm_password.getText();
		 String accept_terms = error_msg_accept_terms.getText();
		 
		 Assert.assertEquals("Username is invalid.", username);
		 Assert.assertEquals("Please enter a valid email address.", email);
		 Assert.assertEquals("Please re-enter your email address.", confirm_email);
		 Assert.assertEquals("Your password must be at least 5 characters long.", password);
		 Assert.assertEquals("Your password must be at least 5 characters long.", confirm_password);
		 Assert.assertEquals("Please accept our Terms and Conditions.", accept_terms);
		 
	     System.out.println("Submit With Empty Fields is successfully executed");
		
		return this;
	}
	
	public EtherscanWebPage_Register submitRegisterWithoutPassword() throws Exception {
		
		input_user_name.sendKeys("Test");
		input_email.sendKeys("testerotesting@test.com");
		confirm_email.sendKeys("testerotesting@test.com");
		
		btn_create_account.click();

		 String password = error_msg_password.getText();
		 String confirm_password = error_msg_confirm_password.getText();
		 String accept_terms = error_msg_accept_terms.getText();
		 
		 Assert.assertEquals("Your password must be at least 5 characters long.", password);
		 Assert.assertEquals("Your password must be at least 5 characters long.", confirm_password);
		 Assert.assertEquals("Please accept our Terms and Conditions.", accept_terms);
		 
	     System.out.println("Submit Without Password successfully executed");
	
		return this;
	}
	
	public EtherscanWebPage_Register successfulSubmitRegister() throws Exception {
		
		email = (CoreFunctions.generateString("mix", 8));
		username = (CoreFunctions.generateString("mix", 8));
		password = (CoreFunctions.generateString("mix", 8));
		
		wait.until(ExpectedConditions.visibilityOf(input_user_name)).sendKeys(username);
		wait.until(ExpectedConditions.visibilityOf(input_email)).sendKeys("testing123@yopmail.com");
		wait.until(ExpectedConditions.visibilityOf(confirm_email)).sendKeys("testing123@yopmail.com");
		wait.until(ExpectedConditions.visibilityOf(input_password)).sendKeys("Testing1!");
		wait.until(ExpectedConditions.visibilityOf(input_confirm_password)).sendKeys("Testing1!");
		wait.until(ExpectedConditions.visibilityOf(cbox_agree_to_terms)).click();
		
		btn_create_account.click();
		
		System.out.println("Account successfully created");
		
		return this;
	}
 
}
