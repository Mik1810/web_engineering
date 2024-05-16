package it.univaq.webmarket.data.model.impl.proxy.enums;

import it.univaq.webmarket.data.model.impl.enums.StatoConsegnaImpl;
import it.univaq.webmarket.framework.data.DataItemProxy;
import it.univaq.webmarket.framework.data.DataLayer;

public class StatoConsegnaProxy extends StatoConsegnaImpl implements DataItemProxy {

    protected boolean modified;
    protected DataLayer dataLayer;

    public StatoConsegnaProxy(DataLayer dataLayer) {
        super();
        this.dataLayer = dataLayer;
        this.modified = false;
    }

    @Override
    public void setNome(String nome) {
        super.setNome(nome);
        this.modified = true;
    }

    @Override
    public void setKey(Integer key) {
        super.setKey(key);
        this.modified = true;
    }

    @Override
    public boolean isModified() {
        return modified;
    }

    @Override
    public void setModified(boolean dirty) {
        this.modified = dirty;
    }
}