package links.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.core.WebPageBase;
import links.core.web.EtherscanWebManager;

public class EtherscanWebPage_Base extends WebPageBase {

	   public EtherscanWebPage_Base(EtherscanWebManager etherscan) throws Exception {
	        super(etherscan.appManager());
	        this.etherscan = etherscan;
	    }
	   
	   public EtherscanWebManager etherscan;
	   
	   @FindBy(id = "eu-cookie-ok")
	protected WebElement cookie_pop_up;
	   
}
