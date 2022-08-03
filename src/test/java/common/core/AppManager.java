package common.core;

import org.openqa.selenium.WebDriver;

import etherscan.core.EtherscanManager;
import etherscan.core.web.EtherscanWebManager;

public class AppManager {

    private WebDriver driver;
    private EtherscanManager etherscanManager;
    private EtherscanWebManager etherscanWebManager;

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() throws Exception {
        return driver;
    }

    public EtherscanManager getEtherscanManager() {
        if(etherscanManager == null) {
        	etherscanManager = new EtherscanManager(this);
        }

        return etherscanManager;
    }

    public EtherscanWebManager getEtherscanWebManager() {
        if(etherscanWebManager == null) {
        	etherscanWebManager = new EtherscanWebManager(this);
        }

        return etherscanWebManager;
    }

}
