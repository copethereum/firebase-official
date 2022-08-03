package etherscan.core.web;

import common.core.AppManager;
import etherscan.core.EtherscanManager;
import etherscan.core.web.EtherscanWebManager;
import etherscan.helpers.SettingsHelper;
import etherscan.helpers.web.EtherscanWebPagesHelper;

public class EtherscanWebManager extends EtherscanManager {

    private EtherscanWebPagesHelper pages;
    private SettingsHelper settings;

    public EtherscanWebManager(AppManager app) {
        super(app);
    }

    public EtherscanWebPagesHelper pages() throws Exception {
        if(pages == null) {
            pages = new EtherscanWebPagesHelper(this);
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
