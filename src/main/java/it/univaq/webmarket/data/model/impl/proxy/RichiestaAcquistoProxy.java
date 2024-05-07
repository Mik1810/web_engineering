package it.univaq.webmarket.data.model.impl.proxy;

import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.impl.RichiestaAcquistoImpl;
import it.univaq.webmarket.framework.data.DataItemProxy;
import it.univaq.webmarket.framework.data.DataLayer;

import java.time.LocalDateTime;

public class RichiestaAcquistoProxy extends RichiestaAcquistoImpl implements DataItemProxy {

    protected boolean modified;
    protected DataLayer dataLayer;
    protected Integer ordinante_key;

    public RichiestaAcquistoProxy(DataLayer d) {
        super();
        this.dataLayer = d;
        this.modified = false;
        this.ordinante_key = 0;
    }

    @Override
    public void setKey(Integer key) {
        super.setKey(key);
        this.modified = true;
    }

    @Override
    public Ordinante getOrdinante() {
        //TODO: implementare il caricamento lazy
        return null;
    }

    @Override
    public void setOrdinante(Ordinante ordinante) {
        super.setOrdinante(ordinante);
        this.ordinante_key = ordinante.getKey();
        this.modified = true;
    }

    @Override
    public void setNote(String note) {
        super.setNote(note);
        this.modified = true;
    }

    @Override
    public void setCodiceRichiesta(String codiceRichiesta) {
        super.setCodiceRichiesta(codiceRichiesta);
        this.modified = true;
    }

    @Override
    public void setDataEOra(LocalDateTime dataEOra) {
        super.setDataEOra(dataEOra);
        this.modified = true;
    }

    @Override
    public boolean isModified() { return modified; }

    @Override
    public void setModified(boolean dirty) {
        this.modified = dirty;
    }

    public void setOrdinante_key(Integer ordinante_key) {
        this.ordinante_key = ordinante_key;
        super.setOrdinante(null);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
