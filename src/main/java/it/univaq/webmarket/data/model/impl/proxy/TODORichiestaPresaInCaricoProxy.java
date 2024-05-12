package it.univaq.webmarket.data.model.impl.proxy;

import it.univaq.webmarket.data.model.TecnicoPreventivi;
import it.univaq.webmarket.data.model.impl.RichiestaPresaInCaricoImpl;
import it.univaq.webmarket.framework.data.DataItemProxy;
import it.univaq.webmarket.framework.data.DataLayer;

public class TODORichiestaPresaInCaricoProxy extends RichiestaPresaInCaricoImpl implements DataItemProxy {

    protected boolean modified;

    protected DataLayer dataLayer;
    protected Integer tecnico_preventivi_key;

    public TODORichiestaPresaInCaricoProxy(DataLayer d) {
        super();
        this.dataLayer = d;
        this.modified = false;
        this.tecnico_preventivi_key = 0;
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void setModified(boolean dirty) {

    }

    @Override
    public void setTecnicoPreventivi(TecnicoPreventivi tecnicoPreventivi) {
        //TODO setTecnicoPreventivi nel proxy
    }
}
