package firebase.helpers.web;

import firebase.core.web.FireBaseWebManager;
import firebase.pages.FireBaseWebPage_Home;


public class FireBaseWebPagesHelper {

    private final FireBaseWebManager firebase;

    private FireBaseWebPage_Home home;

    public FireBaseWebPagesHelper(FireBaseWebManager firebase) {
        this.firebase = firebase;

    }

    public FireBaseWebPage_Home home() throws Exception {
        if(home == null) {
            home = new FireBaseWebPage_Home(firebase);
        }

        return home;
    }

}
