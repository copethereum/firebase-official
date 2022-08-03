package links.helpers.web;

import links.core.web.EtherscanWebManager;
import links.pages.EtherscanWebPage_Home;
import links.pages.EtherscanWebPage_Register;


public class EtherscanWebPagesHelper {

    private final EtherscanWebManager etherscan;

    private EtherscanWebPage_Home home;
    private EtherscanWebPage_Register register;
    

    public EtherscanWebPagesHelper(EtherscanWebManager links) {
        this.etherscan = links;

    }

    public EtherscanWebPage_Home home() throws Exception {
        if(home == null) {
            home = new EtherscanWebPage_Home(etherscan);
        }

        return home;
    }
    
    public EtherscanWebPage_Register register() throws Exception {
        if(register == null) {
        	register = new EtherscanWebPage_Register(etherscan);
        }

        return register;
    }

}
