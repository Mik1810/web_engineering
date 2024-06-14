package it.univaq.webmarket.data.model.impl.proxy;

import it.univaq.webmarket.data.DAO.CaratteristicaDAO;
import it.univaq.webmarket.data.DAO.UfficioDAO;
import it.univaq.webmarket.data.model.Caratteristica;
import it.univaq.webmarket.data.model.Ufficio;
import it.univaq.webmarket.data.model.impl.CaratteristicaConValoreImpl;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.data.DataItemProxy;
import it.univaq.webmarket.framework.data.DataLayer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CaratteristicaConValoreProxy extends CaratteristicaConValoreImpl implements DataItemProxy {

    protected boolean modified;
    protected DataLayer dataLayer;
    protected Integer caratteristica_key;

    public CaratteristicaConValoreProxy(DataLayer dataLayer) {
        super();
        this.dataLayer = dataLayer;
        this.modified = false;
        this.caratteristica_key = 0;
    }

    @Override
    public void setKey(Integer key) {
        super.setKey(key);
        this.modified = true;
    }

    @Override
    public void setCaratteristica(Caratteristica caratteristica) {
        super.setCaratteristica(caratteristica);
        this.caratteristica_key = caratteristica.getKey();
        this.modified = true;
    }

    @Override
    public void setValore(String valore) {
        super.setValore(valore);
        this.modified = true;
    }

    @Override
    public Caratteristica getCaratteristica() {
        if (super.getCaratteristica() == null && caratteristica_key > 0) {
            try {
                super.setCaratteristica(((CaratteristicaDAO) dataLayer.getDAO(Caratteristica.class)).getCaratteristica(caratteristica_key));
            } catch (DataException ex) {
                Logger.getLogger(CaratteristicaConValoreProxy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return super.getCaratteristica();
    }

    @Override
    public boolean isModified() {
        return modified;
    }

    @Override
    public void setModified(boolean dirty) {
        this.modified = dirty;
    }

    public void setCaratteristica_key(Integer caratteristica_key) {
        this.caratteristica_key = caratteristica_key;
        super.setCaratteristica(null);
    }
}
