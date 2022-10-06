package tests.firebase.shopping;

import org.testng.annotations.Test;

import firebase.core.web.FireBaseWebTestBase;

public class Shopping extends FireBaseWebTestBase {

    @Test(priority = 1)
    public void homePageContent() throws Exception {
    	firebase().pages().home().isLoadedHomePage();
    }

    @Test(priority = 2)
    public void openAndCloseCart() throws Exception {
        firebase().pages().home().isLoadedHomePage()
    			.isLoadedAndClosedCart();
    }

    @Test(priority = 3)
    public void singleItemPurchase() throws Exception {
        firebase().pages().home().isLoadedHomePage()
    			.addToCartSingleItem()
                .checkout();
    }

    @Test(priority = 4)
    public void multipleItemsAddedAndDiscarded() throws Exception {
        firebase().pages().home().isLoadedHomePage()
                .addToCartMultipleItems()
                .removeProduct();

    }

}


