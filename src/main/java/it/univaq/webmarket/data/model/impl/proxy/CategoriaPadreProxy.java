package it.univaq.webmarket.data.model.impl.proxy;

import it.univaq.webmarket.data.model.impl.CategoriaPadreImpl;
import it.univaq.webmarket.framework.data.DataItemProxy;
import it.univaq.webmarket.framework.data.DataLayer;

public class CategoriaPadreProxy extends CategoriaPadreImpl implements DataItemProxy {

    protected boolean modified;
    protected DataLayer dataLayer;

    public CategoriaPadreProxy(DataLayer dataLayer) {
        super();
        this.dataLayer = dataLayer;
        this.modified = false;
    }

    @Override
    public void setKey(Integer key) {
        super.setKey(key);
        this.modified = true;
    }

    @Override
    public void setNome(String nome) {
        super.setNome(nome);
        this.modified = true;
    }

    @Override
    public boolean isModified() { return modified; }

    @Override
    public void setModified(boolean dirty) {
        this.modified = dirty;
    }
}
