package it.univaq.webmarket.data.model.impl.proxy;

import it.univaq.webmarket.data.model.CategoriaFiglio;
import it.univaq.webmarket.data.model.impl.CategoriaNipoteImpl;
import it.univaq.webmarket.framework.data.DataItemProxy;
import it.univaq.webmarket.framework.data.DataLayer;

public class CategoriaNipoteProxy extends CategoriaNipoteImpl implements DataItemProxy {

    protected boolean modified;
    protected DataLayer dataLayer;
    protected Integer categoriaFiglio_key;

    public CategoriaNipoteProxy(DataLayer d) {
        super();
        this.dataLayer = d;
        this.modified = false;
        this.categoriaFiglio_key = 0;
    }

    @Override
    public void setKey(Integer key) {
        super.setKey(key);
        this.modified = true;
    }

    @Override
    public void setNome(String nome) {
        super.setNome(nome);
        this.modified = true;
    }

    @Override
    public void setCategoriaGenitore(CategoriaFiglio categoriaFiglio) {
        super.setCategoriaGenitore(categoriaFiglio);
        this.categoriaFiglio_key = categoriaFiglio.getKey();
        this.modified = true;
    }

    @Override
    public CategoriaFiglio getCategoriaGenitore() {
        //TODO: implementare il caricamento lazy
        return super.getCategoriaGenitore();
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void setModified(boolean dirty) {
        this.modified = dirty;
    }

    public void setCategoriaFiglio_key(Integer idCategoriaFiglio) {
        this.categoriaFiglio_key = idCategoriaFiglio;
        super.setCategoriaGenitore(null);
    }



}
