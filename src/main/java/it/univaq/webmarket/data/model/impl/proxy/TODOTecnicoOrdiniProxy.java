package it.univaq.webmarket.data.model.impl.proxy;

import it.univaq.webmarket.data.model.impl.TecnicoOrdiniImpl;
import it.univaq.webmarket.framework.data.DataItemProxy;
import it.univaq.webmarket.framework.data.DataLayer;

public class TODOTecnicoOrdiniProxy  extends TecnicoOrdiniImpl implements DataItemProxy {

    protected boolean modified;
    protected DataLayer dataLayer;


    public TODOTecnicoOrdiniProxy(DataLayer dataLayer) {
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

    }
}
