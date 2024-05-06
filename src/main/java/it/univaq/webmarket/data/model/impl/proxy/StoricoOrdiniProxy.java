package it.univaq.webmarket.data.model.impl.proxy;

import it.univaq.webmarket.data.model.impl.StoricoOrdiniImpl;
import it.univaq.webmarket.framework.data.DataItemProxy;
import it.univaq.webmarket.framework.data.DataLayer;

public class StoricoOrdiniProxy extends StoricoOrdiniImpl implements DataItemProxy {
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
