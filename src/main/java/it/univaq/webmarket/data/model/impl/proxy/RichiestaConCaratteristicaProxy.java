package it.univaq.webmarket.data.model.impl.proxy;

import it.univaq.webmarket.data.model.Caratteristica;
import it.univaq.webmarket.data.model.Richiesta;
import it.univaq.webmarket.data.model.impl.RichiestaConCaratteristicheImpl;
import it.univaq.webmarket.framework.data.DataItemProxy;
import it.univaq.webmarket.framework.data.DataLayer;

import java.util.Map;

public class RichiestaConCaratteristicaProxy extends RichiestaConCaratteristicheImpl implements DataItemProxy {

    protected boolean modified;
    protected DataLayer dataLayer;
    protected Integer richiesta_key;

    public RichiestaConCaratteristicaProxy(DataLayer dataLayer) {
        super();
        this.dataLayer = dataLayer;
        this.modified = false;
        this.richiesta_key = 0;
    }

    @Override
    public void setKey(Integer key) {
        super.setKey(key);
        this.modified = true;
    }

    @Override
    public void setCaratteristiche(Map<Caratteristica, String> caratteristiche) {
        super.setCaratteristiche(caratteristiche);
        this.modified = true;
    }

    @Override
    public void setRichiesta(Richiesta richiesta) {
        super.setRichiesta(richiesta);
        this.richiesta_key = richiesta.getKey();
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

    public void setRichiesta_key(Integer richiesta_key) {
        this.richiesta_key = richiesta_key;
        super.setRichiesta(null);
    }
}
