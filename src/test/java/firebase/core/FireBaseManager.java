package firebase.core;

import org.openqa.selenium.WebDriver;

import common.core.AppManager;

public class FireBaseManager {

    protected AppManager app;;
    

    public FireBaseManager(AppManager app) {
        this.app = app;
    }

    public AppManager appManager() {
        return app;
    }

    public WebDriver driver() throws Exception {
        return app.getDriver();
    }

}
