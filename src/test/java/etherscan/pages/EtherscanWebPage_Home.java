package etherscan.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import etherscan.core.web.EtherscanWebManager;
import etherscan.pages.EtherscanWebPage_Home;

public class EtherscanWebPage_Home extends EtherscanWebPage_Base {
	
	   public EtherscanWebPage_Home(EtherscanWebManager etherscan) throws Exception {
	        super(etherscan);
	    }
	
	@FindBy(id = "txtSearchInput")
	private WebElement input_search_field;
	
	 public EtherscanWebPage_Home isLoadedHomePage() throws Exception {
	        wait.until(ExpectedConditions.visibilityOf(input_search_field));
	        
	        System.out.println("Home Page Is Loaded");
	        
	        return this;
	    }
	
}