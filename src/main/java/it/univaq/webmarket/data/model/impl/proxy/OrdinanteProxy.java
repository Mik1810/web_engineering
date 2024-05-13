package it.univaq.webmarket.data.model.impl.proxy;

import it.univaq.webmarket.data.model.Ufficio;
import it.univaq.webmarket.data.model.impl.OrdinanteImpl;
import it.univaq.webmarket.framework.data.DataItemProxy;
import it.univaq.webmarket.framework.data.DataLayer;

//Fix giacomo
public class OrdinanteProxy extends OrdinanteImpl implements DataItemProxy {

    protected boolean modified;
    protected DataLayer dataLayer;
    protected Integer ufficio_key;


    public OrdinanteProxy(DataLayer dataLayer) {
        super();
        this.dataLayer = dataLayer;
        this.modified = false;
        this.ufficio_key = 0;
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
    public void setUfficio(Ufficio ufficio) {
        super.setUfficio(ufficio);
        this.ufficio_key = ufficio.getKey();
        this.modified = true;
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
        this.modified = true;
    }

    @Override
    public Ufficio getUfficio() {
        //TODO: Implementare lazy loading
        return super.getUfficio();
    }

    @Override
    public boolean isModified() {
        return modified;
    }

    @Override
    public void setModified(boolean dirty) {
        this.modified = dirty;
    }

    public void setUfficio_key(Integer ufficio_key) {
        this.ufficio_key = ufficio_key;
        super.setUfficio(null);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
