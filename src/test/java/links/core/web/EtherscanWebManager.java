package links.core.web;

import links.helpers.SettingsHelper;
import common.core.AppManager;
import links.core.EtherscanManager;
import links.core.web.EtherscanWebManager;
import links.helpers.web.EtherscanWebPagesHelper;

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
