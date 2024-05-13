package it.univaq.webmarket.data.model.impl.proxy;

import it.univaq.webmarket.data.model.impl.TecnicoPreventiviImpl;
import it.univaq.webmarket.framework.data.DataItemProxy;
import it.univaq.webmarket.framework.data.DataLayer;


//Fix giacomo
public class TODOTecnicoPreventiviProxy extends TecnicoPreventiviImpl implements DataItemProxy {

    protected boolean modified;
    protected DataLayer dataLayer;

    public TODOTecnicoPreventiviProxy(DataLayer dataLayer) {
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

    @Override
    public String toString() {
        return super.toString();
    }
}
