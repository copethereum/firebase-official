package firebase.core.web;

import firebase.core.FireBaseTestBase;

public class FireBaseWebTestBase extends FireBaseTestBase {

    @Override
    public FireBaseWebManager firebase() {
        return appManager.getFireBaseWebManager();
    }

}
