package it.univaq.webmarket.data.model.impl.proxy;

import it.univaq.webmarket.data.DAO.UfficioDAO;
import it.univaq.webmarket.data.model.Ufficio;
import it.univaq.webmarket.data.model.impl.OrdinanteImpl;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.data.DataItemProxy;
import it.univaq.webmarket.framework.data.DataLayer;

import java.util.logging.Level;
import java.util.logging.Logger;

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
        if (super.getUfficio() == null && ufficio_key > 0) {
            try {
                super.setUfficio(((UfficioDAO) dataLayer.getDAO(Ufficio.class)).getUfficio(ufficio_key));
            } catch (DataException ex) {
                Logger.getLogger(OrdinanteProxy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
}
