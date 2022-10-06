package firebase.core.web;

import common.core.AppManager;
import firebase.core.FireBaseManager;
import firebase.core.web.FireBaseWebManager;
import firebase.helpers.SettingsHelper;
import firebase.helpers.web.FireBaseWebPagesHelper;

public class FireBaseWebManager extends FireBaseManager {

    private FireBaseWebPagesHelper pages;
    private SettingsHelper settings;

    public FireBaseWebManager(AppManager app) {
        super(app);
    }

    public FireBaseWebPagesHelper pages() throws Exception {
        if(pages == null) {
            pages = new FireBaseWebPagesHelper(this);
        }

        return pages;
    }
    
    public SettingsHelper settings() throws Exception {
        if(settings == null) {
            settings = new SettingsHelper(this);
        }

        return settings;
    }

}
