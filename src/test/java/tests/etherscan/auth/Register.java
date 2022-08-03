package tests.etherscan.auth;

import org.testng.annotations.Test;

import etherscan.core.web.EtherscanWebTestBase;

public class Register extends EtherscanWebTestBase {

    @Test(priority = 1)
    public void homePageContent() throws Exception {
    	etherscan().pages().home().isLoadedHomePage();              
    }
    
    @Test(priority = 2)
    public void submitRegisterWithEmptyFields() throws Exception {
    	etherscan().pages().register().isLoadedRegisterPage()
    			.submitRegisterEmptyFields();
    }
    
    @Test(priority = 3)
    public void submitRegisterWithoutPassword() throws Exception {
    	etherscan().pages().register().isLoadedRegisterPage()
    			.submitRegisterWithoutPassword();
    }
    
    @Test(priority = 4)
    public void successfulSubmitRegister() throws Exception {
    	etherscan().pages().register().isLoadedRegisterPage()
    			.successfulSubmitRegister();
    }

}


