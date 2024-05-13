package it.univaq.webmarket.data.model.impl.proxy;

import it.univaq.webmarket.data.model.impl.CategoriaNipoteImpl;
import it.univaq.webmarket.framework.data.DataItemProxy;
import it.univaq.webmarket.framework.data.DataLayer;

public class TODOCategoriaNipoteProxy extends CategoriaNipoteImpl implements DataItemProxy {

    protected boolean modified;
    protected DataLayer dataLayer;
    protected Integer categoria_figlio_key;

    public TODOCategoriaNipoteProxy(DataLayer d) {
        super();
        this.dataLayer = d;
        this.modified = false;
        this.categoria_figlio_key = 0;
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void setModified(boolean dirty) {
        this.modified = dirty;
    }
}
