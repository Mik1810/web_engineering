package it.univaq.webmarket.data.model.impl.proxy;

import it.univaq.webmarket.data.model.RichiestaConCaratteristiche;
import it.univaq.webmarket.data.model.TecnicoPreventivi;
import it.univaq.webmarket.data.model.impl.RichiestaPresaInCaricoImpl;
import it.univaq.webmarket.framework.data.DataItemProxy;
import it.univaq.webmarket.framework.data.DataLayer;

public class RichiestaPresaInCaricoProxy extends RichiestaPresaInCaricoImpl implements DataItemProxy {

    protected boolean modified;
    protected DataLayer dataLayer;
    protected Integer tecnicoPreventivi_key;
    protected Integer richiestaConCaratteristica_key;

    public RichiestaPresaInCaricoProxy(DataLayer d) {
        super();
        this.dataLayer = d;
        this.modified = false;
        this.tecnicoPreventivi_key = 0;
        this.richiestaConCaratteristica_key = 0;
    }

    @Override
    public void setKey(Integer key) {
        super.setKey(key);
        this.modified = true;
    }

    @Override
    public void setRichiestaConCaratteristiche(RichiestaConCaratteristiche richiestaConCaratteristiche) {
        super.setRichiestaConCaratteristiche(richiestaConCaratteristiche);
        this.richiestaConCaratteristica_key = richiestaConCaratteristiche.getKey();
        this.modified = true;
    }


    @Override
    public void setTecnicoPreventivi(TecnicoPreventivi tecnicoPreventivi) {
        super.setTecnicoPreventivi(tecnicoPreventivi);
        this.tecnicoPreventivi_key = tecnicoPreventivi.getKey();
        this.modified = true;
    }

    @Override
    public RichiestaConCaratteristiche getRichiestaConCaratteristiche() {
        //TODO: implementare lazy loading
        return super.getRichiestaConCaratteristiche();
    }

    @Override
    public TecnicoPreventivi getTecnicoPreventivi() {
        //TODO: implementare lazy loading
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

    public void setTecnicoPreventiviKey(Integer idTecnicoPreventivi) {
        this.tecnicoPreventivi_key = idTecnicoPreventivi;
        super.setTecnicoPreventivi(null);
    }

    public void setRichiestaConCaratteristicaKey(Integer idRichiestaConCaratteristica) {
        this.richiestaConCaratteristica_key = idRichiestaConCaratteristica;
        super.setRichiestaConCaratteristiche(null);
    }
}
