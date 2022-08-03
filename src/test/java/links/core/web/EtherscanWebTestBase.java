package links.core.web;

import links.core.EtherscanTestBase;


public class EtherscanWebTestBase extends EtherscanTestBase {

    @Override
    public EtherscanWebManager etherscan() {
        return appManager.getEtherscanWebManager();
    }

}
