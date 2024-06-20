package it.univaq.webmarket.data.model.impl.proxy;

import it.univaq.webmarket.data.DAO.PropostaDAO;
import it.univaq.webmarket.data.DAO.StatiFeedbackDAO;
import it.univaq.webmarket.data.DAO.TecnicoOrdiniDAO;
import it.univaq.webmarket.data.model.Proposta;
import it.univaq.webmarket.data.model.TecnicoOrdini;
import it.univaq.webmarket.data.model.impl.OrdineImpl;
import it.univaq.webmarket.data.model.enums.Feedback;
import it.univaq.webmarket.data.model.enums.StatoConsegna;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.data.DataItemProxy;
import it.univaq.webmarket.framework.data.DataLayer;

import java.time.LocalDate;
import java.util.logging.Logger;

public class OrdineProxy extends OrdineImpl implements DataItemProxy {

    protected boolean modified;
    protected DataLayer dataLayer;

    protected Integer statoConsegna_key;
    protected Integer feedback_key;
    protected Integer tecnicoOrdini_key;
    protected Integer proposta_key;

    public OrdineProxy(DataLayer d) {
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
    public void setModified(boolean dirty) {
        this.modified = dirty;
    }

    @Override
    public void setFeedback(Feedback feedback) {
        super.setFeedback(feedback);
        this.feedback_key = feedback.getKey();
        this.modified = true;
    }

    @Override
    public void setStatoConsegna(StatoConsegna statoConsegna) {
        super.setStatoConsegna(statoConsegna);
        this.statoConsegna_key = statoConsegna.getKey();
        this.modified = true;
    }

    @Override
    public void setDataConsegna(LocalDate dataConsegna) {
        super.setDataConsegna(dataConsegna);
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
        if (super.getStatoConsegna() == null && statoConsegna_key > 0) {
            try {
                super.setStatoConsegna(((StatiFeedbackDAO) dataLayer.getDAO(StatoConsegna.class)).getStatoConsegna(statoConsegna_key));
            } catch (DataException ex) {
                Logger.getLogger(OrdineProxy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }

        return super.getStatoConsegna();
    }

    @Override
    public Feedback getFeedback() {
        if (super.getFeedback() == null && feedback_key > 0) {
            try {
                super.setFeedback(((StatiFeedbackDAO) dataLayer.getDAO(Feedback.class)).getFeedback(feedback_key));
            } catch (DataException ex) {
                Logger.getLogger(OrdineProxy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }

        return super.getFeedback();
    }

    @Override
    public TecnicoOrdini getTecnicoOrdini() {
        if (super.getTecnicoOrdini() == null && tecnicoOrdini_key > 0) {
            try {
                super.setTecnicoOrdini(((TecnicoOrdiniDAO) dataLayer.getDAO(TecnicoOrdini.class)).getTecnicoOrdini(tecnicoOrdini_key));
            } catch (DataException ex) {
                Logger.getLogger(OrdineProxy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }

        return super.getTecnicoOrdini();
    }

    @Override
    public Proposta getProposta() {
        if (super.getProposta() == null && proposta_key > 0) {
            try {
                super.setProposta(((PropostaDAO) dataLayer.getDAO(Proposta.class)).getProposta(proposta_key));
            } catch (DataException ex) {
                Logger.getLogger(OrdineProxy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }

        return super.getProposta();
    }

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
