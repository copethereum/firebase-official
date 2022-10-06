package firebase.core;

import common.core.TestBase;

public class FireBaseTestBase extends TestBase {


    public FireBaseManager firebase() {
        return appManager.getFireBaseManager();
    }

}
