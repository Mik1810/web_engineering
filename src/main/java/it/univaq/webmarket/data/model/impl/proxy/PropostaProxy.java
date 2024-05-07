package it.univaq.webmarket.data.model.impl.proxy;

import it.univaq.webmarket.data.model.TecnicoPreventivi;
import it.univaq.webmarket.data.model.impl.PropostaImpl;
import it.univaq.webmarket.data.model.impl.enums.StatoProposta;
import it.univaq.webmarket.framework.data.DataItemProxy;
import it.univaq.webmarket.framework.data.DataLayer;

public class PropostaProxy extends PropostaImpl implements DataItemProxy {
    protected boolean modified;
    protected DataLayer dataLayer;
    protected Integer tecnicoPreventivi_key;

    public PropostaProxy(DataLayer d) {
        super();
        this.dataLayer = d;
        this.modified = false;
        this.tecnicoPreventivi_key = 0;
    }

    @Override
    public void setKey(Integer key) {
        super.setKey(key);
        this.modified = true;
    }

    @Override
    public TecnicoPreventivi getTecnicoPreventivi() {
        //TODO: implementare il caricamento lazy di tecnicoPreventivi
        return super.getTecnicoPreventivi();
    }

    @Override
    public void setTecnicoPreventivi(TecnicoPreventivi tecnicoPreventivi) {
        super.setTecnicoPreventivi(tecnicoPreventivi);
        this.tecnicoPreventivi_key = tecnicoPreventivi.getKey();
        this.modified = true;
    }

    @Override
    public void setCodiceProdotto(String codiceProdotto) {
        super.setCodiceProdotto(codiceProdotto);
        this.modified = true;
    }

    @Override
    public void setProduttore(String produttore) {
        super.setProduttore(produttore);
        this.modified = true;
    }

    @Override
    public void setNote(String note) {
        super.setNote(note);
        this.modified = true;
    }

    @Override
    public void setPrezzo(Float prezzo) {
        super.setPrezzo(prezzo);
        this.modified = true;
    }

    @Override
    public void setNomeProdotto(String nomeProdotto) {
        super.setNomeProdotto(nomeProdotto);
        this.modified = true;
    }

    @Override
    public void setStatoProposta(StatoProposta statoProposta) {
        super.setStatoProposta(statoProposta);
        this.modified = true;
    }

    @Override
    public void setMotivazione(String motivazione) {
        super.setMotivazione(motivazione);
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

    public void setTecnicoPreventivi_key(Integer tecnicoPreventivi_key) {
        this.tecnicoPreventivi_key = tecnicoPreventivi_key;
        super.setTecnicoPreventivi(null);
    }
}
