package firebase.pages;


import common.core.WebPageBase;
import firebase.core.web.FireBaseWebManager;

public class FireBaseWebPage_Base extends WebPageBase {

	   public FireBaseWebPage_Base(FireBaseWebManager firebase) throws Exception {
	        super(firebase.appManager());
	        this.firebase = firebase;
	    }

	   public FireBaseWebManager firebase;

}
