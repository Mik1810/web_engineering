package it.univaq.webmarket.data.model.impl.proxy;

import it.univaq.webmarket.data.model.impl.RichiestaAcquistoImpl;
import it.univaq.webmarket.framework.data.DataItemProxy;
import it.univaq.webmarket.framework.data.DataLayer;

import java.time.LocalDateTime;

public class RichiestaAcquistoProxy extends RichiestaAcquistoImpl implements DataItemProxy {

    protected boolean modified;

    protected DataLayer dataLayer;

    public RichiestaAcquistoProxy(DataLayer d) {
        super();
        //dependency injection
        this.dataLayer = d;
        this.modified = false;
    }

    @Override
    public void setKey(Integer key) {
        super.setKey(key);
        this.modified = true;
    }

    public RichiestaAcquistoProxy() {
        super();
    }

    @Override
    public String getNote() {
        return super.getNote();
    }

    @Override
    public void setNote(String note) {
        super.setNote(note);
    }

    @Override
    public String getCodiceRichiesta() {
        return super.getCodiceRichiesta();
    }

    @Override
    public void setCodiceRichiesta(String codiceRichiesta) {
        super.setCodiceRichiesta(codiceRichiesta);
    }

    @Override
    public LocalDateTime getDataEOra() {
        return super.getDataEOra();
    }

    @Override
    public void setDataEOra(LocalDateTime dataEOra) {
        super.setDataEOra(dataEOra);
    }

    @Override
    public boolean isModified() {
        return modified;
    }

    @Override
    public void setModified(boolean dirty) {
        this.modified = dirty;
    }

}
