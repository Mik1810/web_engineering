package it.univaq.webmarket.data.model.impl.proxy;

import it.univaq.webmarket.data.DAO.RichiestaDAO;
import it.univaq.webmarket.data.DAO.TecnicoPreventiviDAO;
import it.univaq.webmarket.data.model.Richiesta;
import it.univaq.webmarket.data.model.RichiestaPresaInCarico;
import it.univaq.webmarket.data.model.TecnicoPreventivi;
import it.univaq.webmarket.data.model.impl.RichiestaPresaInCaricoImpl;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.data.DataItemProxy;
import it.univaq.webmarket.framework.data.DataLayer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RichiestaPresaInCaricoProxy extends RichiestaPresaInCaricoImpl implements DataItemProxy {

    protected boolean modified;
    protected DataLayer dataLayer;
    protected Integer tecnicoPreventivi_key;
    protected Integer richiesta_key;

    public RichiestaPresaInCaricoProxy(DataLayer d) {
        super();
        this.dataLayer = d;
        this.modified = false;
        this.tecnicoPreventivi_key = 0;
        this.richiesta_key = 0;
    }

    @Override
    public void setKey(Integer key) {
        super.setKey(key);
        this.modified = true;
    }

    @Override
    public void setRichiesta(Richiesta richiesta) {
        super.setRichiesta(richiesta);
        this.richiesta_key = richiesta.getKey();
        this.modified = true;
    }


    @Override
    public void setTecnicoPreventivi(TecnicoPreventivi tecnicoPreventivi) {
        super.setTecnicoPreventivi(tecnicoPreventivi);
        this.tecnicoPreventivi_key = tecnicoPreventivi.getKey();
        this.modified = true;
    }

    @Override
    public Richiesta getRichiesta() {
        if (super.getRichiesta() == null && richiesta_key > 0) {
            try {
                super.setRichiesta(((RichiestaDAO) dataLayer.getDAO(Richiesta.class)).getRichiesta(richiesta_key));
            } catch (DataException ex) {
                Logger.getLogger(RichiestaPresaInCaricoProxy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return super.getRichiesta();
    }

    @Override
    public TecnicoPreventivi getTecnicoPreventivi() {
        if (super.getTecnicoPreventivi() == null && tecnicoPreventivi_key > 0) {
            try {
                super.setTecnicoPreventivi(((TecnicoPreventiviDAO) dataLayer.getDAO(TecnicoPreventivi.class)).getTecnicoPreventivi(tecnicoPreventivi_key));
            } catch (DataException ex) {
                Logger.getLogger(RichiestaPresaInCaricoProxy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return super.getTecnicoPreventivi();
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

    public void setRichiesta_key(Integer caratteristica_key) {
        this.richiesta_key = caratteristica_key;
        super.setRichiesta(null);
    }
}
