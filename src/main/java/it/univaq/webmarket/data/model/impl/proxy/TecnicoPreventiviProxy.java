package it.univaq.webmarket.data.model.impl.proxy;

import it.univaq.webmarket.data.model.impl.TecnicoPreventiviImpl;
import it.univaq.webmarket.framework.data.DataItemProxy;
import it.univaq.webmarket.framework.data.DataLayer;

public class TecnicoPreventiviProxy extends TecnicoPreventiviImpl implements DataItemProxy {

    protected boolean modified;
    protected DataLayer dataLayer;

    public TecnicoPreventiviProxy(DataLayer dataLayer) {
        super();
        this.dataLayer = dataLayer;
        this.modified = false;
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void setModified(boolean dirty) {
        this.modified = dirty;
    }

    public String getEmail() {
        return super.getEmail();
    }

    public String getPassword() {
        return super.getPassword();
    }

    public void setEmail(String email) {
        super.setEmail(email);
        this.modified = true;
    }


    public void setPassword(String password) {
        super.setPassword(password);
        this.modified = true;
    }


}
