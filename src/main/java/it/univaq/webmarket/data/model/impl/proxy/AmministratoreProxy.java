package it.univaq.webmarket.data.model.impl.proxy;

import it.univaq.webmarket.data.model.impl.AmministratoreImpl;
import it.univaq.webmarket.framework.data.DataItemProxy;
import it.univaq.webmarket.framework.data.DataLayer;

public class AmministratoreProxy extends AmministratoreImpl implements DataItemProxy {

    protected boolean modified;
    protected DataLayer dataLayer;

    public AmministratoreProxy(DataLayer dataLayer) {
        super();
        this.dataLayer = dataLayer;
        this.modified = false;
    }

    @Override
    public void setKey(Integer key) {
        super.setKey(key);
        this.modified = true;
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
        this.modified = true;
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
        this.modified = true;
    }

    @Override
    public boolean isModified() { return modified; }

    @Override
    public void setModified(boolean dirty) { this.modified = dirty; }
}
