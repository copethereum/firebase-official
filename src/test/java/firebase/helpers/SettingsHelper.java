package firebase.helpers;

import common.core.TestProperties;
import common.core.TestBase;
import firebase.core.web.FireBaseWebManager;


public class SettingsHelper extends TestBase {

    public FireBaseWebManager fireBaseWebManager;

    public SettingsHelper(FireBaseWebManager fireBaseWebManager) {
        this.fireBaseWebManager = fireBaseWebManager;
    }

    public void setTestServer() throws Exception {
      if(TestProperties.getServer().equals("coindesk_api")) {
            driver.get("https://api.coindesk.com/v1/bpi/currentprice.json");
        } else if (TestProperties.getServer().equals("firebase")) {
            driver.get("https://react-shopping-cart-67954.firebaseapp.com/");
        }
    }

}
