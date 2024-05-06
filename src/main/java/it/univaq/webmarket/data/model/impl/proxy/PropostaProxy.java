package it.univaq.webmarket.data.model.impl.proxy;

import it.univaq.webmarket.data.model.impl.PropostaImpl;
import it.univaq.webmarket.framework.data.DataItemProxy;
import it.univaq.webmarket.framework.data.DataLayer;

public class PropostaProxy extends PropostaImpl implements DataItemProxy {
    protected boolean modified;

    protected DataLayer dataLayer;

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void setModified(boolean dirty) {

    }
}
