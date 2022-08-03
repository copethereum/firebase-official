package links.core;

import org.openqa.selenium.WebDriver;

import common.core.AppManager;

public class EtherscanManager {

    protected AppManager app;;
    

    public EtherscanManager(AppManager app) {
        this.app = app;
    }

    public AppManager appManager() {
        return app;
    }

    public WebDriver driver() throws Exception {
        return app.getDriver();
    }

}
