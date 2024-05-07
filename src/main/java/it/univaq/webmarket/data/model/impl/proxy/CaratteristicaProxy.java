package it.univaq.webmarket.data.model.impl.proxy;

import it.univaq.webmarket.data.model.CategoriaNipote;
import it.univaq.webmarket.data.model.impl.CaratteristicaImpl;
import it.univaq.webmarket.framework.data.DataItemProxy;
import it.univaq.webmarket.framework.data.DataLayer;

public class CaratteristicaProxy extends CaratteristicaImpl implements DataItemProxy {

    protected boolean modified;
    protected DataLayer dataLayer;
    protected Integer categoriaNipoteKey = 0;

    public CaratteristicaProxy(DataLayer d) {
        super();
        this.dataLayer = d;
        this.modified = false;
        this.categoriaNipoteKey = 0;
    }

    @Override
    public void setNome(String nome) {
        super.setNome(nome);
        this.modified = true;
    }

    @Override
    public void setUnitaMisura(String unitaMisura) {
        super.setUnitaMisura(unitaMisura);
        this.modified = true;
    }

    @Override
    public void setCategoriaNipote(CategoriaNipote categoriaNipote) {
        super.setCategoriaNipote(categoriaNipote);
        this.modified = true;
    }

    @Override
    public void setKey(Integer key) {
        super.setKey(key);
        this.modified = true;
    }

    @Override
    public CategoriaNipote getCategoriaNipote() {
        //TODO: implementare il caricamento lazy di categoriaNipote
        return null;
    }

    @Override
    public boolean isModified() {
        return modified;
    }

    @Override
    public void setModified(boolean dirty) { this.modified = dirty; }
}
