package it.univaq.webmarket.data.model.impl.proxy;

import it.univaq.webmarket.data.DAO.RichiestaPresaInCaricoDAO;
import it.univaq.webmarket.data.DAO.StatiEnumDAO;
import it.univaq.webmarket.data.model.RichiestaPresaInCarico;
import it.univaq.webmarket.data.model.enums.StatoEnum;
import it.univaq.webmarket.data.model.enums.StatoProposta;
import it.univaq.webmarket.data.model.impl.PropostaImpl;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.data.DataItemProxy;
import it.univaq.webmarket.framework.data.DataLayer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PropostaProxy extends PropostaImpl implements DataItemProxy {

    protected boolean modified;
    protected DataLayer dataLayer;
    protected Integer richiestaPresaInCarico_key;
    protected Integer statoProposta_key;

    public PropostaProxy(DataLayer d) {
        super();
        this.dataLayer = d;
        this.modified = false;
        this.richiestaPresaInCarico_key = 0;
        this.statoProposta_key = 0;
    }

    @Override
    public void setKey(Integer key) {
        super.setKey(key);
        this.modified = true;
    }

    @Override
    public void setRichiestaPresaInCarico(RichiestaPresaInCarico richiestaPresaInCarico) {
        super.setRichiestaPresaInCarico(richiestaPresaInCarico);
        this.richiestaPresaInCarico_key = richiestaPresaInCarico.getKey();
        this.modified = true;
    }

    @Override
    public RichiestaPresaInCarico getRichiestaPresaInCarico() {
        if (super.getRichiestaPresaInCarico() == null && richiestaPresaInCarico_key > 0) {
            try {
                super.setRichiestaPresaInCarico(((RichiestaPresaInCaricoDAO) dataLayer.getDAO(RichiestaPresaInCarico.class)).getRichiestaPresaInCarico(richiestaPresaInCarico_key));
            } catch (DataException ex) {
                Logger.getLogger(PropostaProxy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return super.getRichiestaPresaInCarico();
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
    public void setURL(String URL) {
        super.setURL(URL);
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
        this.statoProposta_key = statoProposta.getKey();
        this.modified = true;
    }

    @Override
    public void setMotivazione(String motivazione) {
        super.setMotivazione(motivazione);
        this.modified = true;
    }

    @Override
    public StatoProposta getStatoProposta() {
        if (super.getStatoProposta() == null && statoProposta_key > 0) {
            try {
                super.setStatoProposta(((StatiEnumDAO) dataLayer.getDAO(StatoEnum.class)).getStatoProposta(statoProposta_key));
            } catch (DataException ex) {
                Logger.getLogger(PropostaProxy.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return super.getStatoProposta();

    }

    @Override
    public boolean isModified() {
        return modified;
    }

    @Override
    public void setModified(boolean dirty) {
        this.modified = dirty;
    }

    public void setRichiestaPresaInCarico_key(Integer richiestaPresaInCarico_key) {
        this.richiestaPresaInCarico_key = richiestaPresaInCarico_key;
        super.setRichiestaPresaInCarico(null);
    }

    public void setStatoProposta_key(Integer statoProposta_key) {
        this.statoProposta_key = statoProposta_key;
        super.setStatoProposta(null);
    }
}
