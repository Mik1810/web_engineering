package it.univaq.webmarket.data.model.impl.proxy;

import it.univaq.webmarket.data.DAO.CaratteristicaDAO;
import it.univaq.webmarket.data.DAO.OrdinanteDAO;
import it.univaq.webmarket.data.DAO.RichiestaDAO;
import it.univaq.webmarket.data.DAO.UfficioDAO;
import it.univaq.webmarket.data.model.*;
import it.univaq.webmarket.data.model.impl.RichiestaImpl;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.data.DataItemProxy;
import it.univaq.webmarket.framework.data.DataLayer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RichiestaProxy extends RichiestaImpl implements DataItemProxy {

    protected boolean modified;
    protected DataLayer dataLayer;
    protected Integer ordinante_key;

    public RichiestaProxy(DataLayer d) {
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
        if (super.getOrdinante() == null && ordinante_key > 0) {
            try {
                Ordinante ordinante = ((OrdinanteDAO) dataLayer.getDAO(Ordinante.class)).getOrdinante(ordinante_key);
                super.setOrdinante(ordinante);
            } catch (DataException e) {
                Logger.getLogger(RichiestaProxy.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
            }
        }

        return super.getOrdinante();
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
    public void setData(LocalDate data) {
        super.setData(data);
        this.modified = true;
    }

    @Override
    public void setCaratteristicheConValore(List<CaratteristicaConValore> caratteristicheConValore) {
        super.setCaratteristicheConValore(caratteristicheConValore);
    }

    @Override
    public List<CaratteristicaConValore> getCaratteristicheConValore() {
        if (super.getCaratteristicheConValore() == null) {
            try {
                List<CaratteristicaConValore> caratteristicheConValore = ((CaratteristicaDAO) dataLayer.getDAO(Caratteristica.class))
                        .getCaratteristicheConValore(this);
                super.setCaratteristicheConValore(caratteristicheConValore);
            } catch (DataException ex) {
                Logger.getLogger(RichiestaProxy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return super.getCaratteristicheConValore();
    }

    @Override
    public boolean isModified() {
        return modified;
    }

    @Override
    public void setModified(boolean dirty) {
        this.modified = dirty;
    }

    public void setOrdinante_key(Integer ordinante_key) {
        this.ordinante_key = ordinante_key;
        super.setOrdinante(null);
    }
}
