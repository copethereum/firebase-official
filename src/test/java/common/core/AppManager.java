package common.core;

import org.openqa.selenium.WebDriver;

import firebase.core.FireBaseManager;
import firebase.core.web.FireBaseWebManager;

public class AppManager {

    private WebDriver driver;
    private FireBaseManager fireBaseManager;
    private FireBaseWebManager fireBaseWebManager;

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() throws Exception {
        return driver;
    }

    public FireBaseManager getFireBaseManager() {
        if(fireBaseManager == null) {
        	fireBaseManager = new FireBaseManager(this);
        }

        return fireBaseManager;
    }

    public FireBaseWebManager getFireBaseWebManager() {
        if(fireBaseWebManager == null) {
        	fireBaseWebManager = new FireBaseWebManager(this);
        }

        return fireBaseWebManager;
    }

}
