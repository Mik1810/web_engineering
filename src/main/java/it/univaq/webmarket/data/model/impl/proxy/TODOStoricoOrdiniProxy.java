package it.univaq.webmarket.data.model.impl.proxy;

import it.univaq.webmarket.framework.data.DataItemProxy;
import it.univaq.webmarket.framework.data.DataLayer;

public class TODOStoricoOrdiniProxy extends StoricoOrdiniImpl implements DataItemProxy {
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
