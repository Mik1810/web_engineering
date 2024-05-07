package it.univaq.webmarket.data.model.impl.proxy;

import it.univaq.webmarket.data.model.Proposta;
import it.univaq.webmarket.data.model.TecnicoOrdini;
import it.univaq.webmarket.data.model.impl.OrdineAcquistoimpl;
import it.univaq.webmarket.data.model.impl.enums.Feedback;
import it.univaq.webmarket.data.model.impl.enums.StatoConsegna;
import it.univaq.webmarket.framework.data.DataItemProxy;
import it.univaq.webmarket.framework.data.DataLayer;

public class OrdineAcquistoProxy extends OrdineAcquistoimpl implements DataItemProxy {

    protected boolean modified;
    protected DataLayer dataLayer;

    protected Integer statoConsegna_key;
    protected Integer feedback_key;
    protected Integer tecnicoOrdini_key;
    protected Integer proposta_key;

    public OrdineAcquistoProxy(DataLayer d) {
        super();
        this.dataLayer = d;
        this.modified = false;
        this.statoConsegna_key = 0;
        this.feedback_key = 0;
        this.tecnicoOrdini_key = 0;
        this.proposta_key = 0;
    }

    @Override
    public void setKey(Integer key) {
        super.setKey(key);
        this.modified = true;
    }

    @Override
    public boolean isModified() {
        return modified;
    }

    @Override
    public void setModified(boolean dirty) { this.modified = dirty; }

    @Override
    public void setFeedback(Feedback feedback) {
        super.setFeedback(feedback);
        // Se lo StatoConsegna è diverso da "Consegnato" (3), allora
        // il feedback è null
        if (feedback != null) {
            this.feedback_key = feedback.getValue();
        } else {
            this.feedback_key = 0;
        }
        this.modified = true;
    }

    @Override
    public void setStatoConsegna(StatoConsegna statoConsegna) {
        super.setStatoConsegna(statoConsegna);
        // StatoConsegna è una ENUM, pertanto la sua chiave è il suo valore
        this.statoConsegna_key = statoConsegna.getValue();
        this.modified = true;
    }

    @Override
    public void setTecnicoOrdini(TecnicoOrdini tecnicoOrdini) {
        super.setTecnicoOrdini(tecnicoOrdini);
        this.tecnicoOrdini_key = tecnicoOrdini.getKey();
        this.modified = true;
    }

    @Override
    public void setProposta(Proposta proposta) {
        super.setProposta(proposta);
        this.proposta_key = proposta.getKey();
        this.modified = true;
    }

    @Override
    public StatoConsegna getStatoConsegna() {
        // Credo che per le ENUM basti semplicemente ritornare il valore
        // Difatti, non rappresentano un oggetto in sè e per sè, ma solo un valore
        return super.getStatoConsegna();
    }

    @Override
    public Feedback getFeedback() {
        return super.getFeedback();
    }

    @Override
    public TecnicoOrdini getTecnicoOrdini() {
        //TODO: Implementare il lazy loading
        return super.getTecnicoOrdini();
    }

    @Override
    public Proposta getProposta() {
        //TODO: Implementare il lazy loading
        return super.getProposta();
    }

    // Per resettare la cache
    public void setStatoConsegnaKey(Integer key) {
        this.statoConsegna_key = key;
        super.setStatoConsegna(null);
    }

    public void setFeedbackKey(Integer key) {
        this.feedback_key = key;
        super.setFeedback(null);
    }

    public void setTecnicoOrdiniKey(Integer key) {
        this.tecnicoOrdini_key = key;
        super.setTecnicoOrdini(null);
    }

    public void setPropostaKey(Integer key) {
        this.proposta_key = key;
        super.setProposta(null);
    }
}
