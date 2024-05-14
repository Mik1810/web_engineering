package it.univaq.webmarket.data.model.impl.proxy;

import it.univaq.webmarket.data.model.TecnicoPreventivi;
import it.univaq.webmarket.data.model.impl.RichiestaPresaInCaricoImpl;
import it.univaq.webmarket.framework.data.DataItemProxy;
import it.univaq.webmarket.framework.data.DataLayer;

public class RichiestaPresaInCaricoProxy extends RichiestaPresaInCaricoImpl implements DataItemProxy {

    protected boolean modified;

    protected DataLayer dataLayer;
    protected Integer tecnico_preventivi_key;
    protected Integer richiesta_con_caratteristica_key;

    public RichiestaPresaInCaricoProxy(DataLayer d) {
        super();
        this.dataLayer = d;
        this.modified = false;
        this.tecnico_preventivi_key = 0;
        this.richiesta_con_caratteristica_key = 0;
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void setModified(boolean dirty) {
        this.modified = dirty;
    }

    public void setTecnicoPreventiviKey(int idTecnicoPreventivi) {
        this.tecnico_preventivi_key = idTecnicoPreventivi;
        super.setTecnicoPreventivi(null);
    }

    public void setRichiestaConCaratteristicaKey(int idRichiestaConCaratteristica) {
        //TODO setRichiestaConCaratteristicaKey nel proxy
    }

    @Override
    public TecnicoPreventivi getTecnicoPreventivi() {
        //TODO getTecnicoPreventivi nel proxy implementare con DAO
        return super.getTecnicoPreventivi();
    }
}
