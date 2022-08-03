package links.helpers;

import common.core.MTFProperties;
import common.core.TestBase;
import links.core.web.EtherscanWebManager;


public class SettingsHelper extends TestBase {

    public EtherscanWebManager etherscanWebManager;

    public SettingsHelper(EtherscanWebManager etherscanWebManager) {
        this.etherscanWebManager = etherscanWebManager;
    }

    public void setEtherscanTestServer() throws Exception {
      if(MTFProperties.getServer().equals("etherscan")) {
            driver.get("https://etherscan.io/");
        }
    }

}
