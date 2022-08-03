package etherscan.core.web;

import etherscan.core.EtherscanTestBase;


public class EtherscanWebTestBase extends EtherscanTestBase {

    @Override
    public EtherscanWebManager etherscan() {
        return appManager.getEtherscanWebManager();
    }

}
