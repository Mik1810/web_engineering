package it.univaq.webmarket.data.model.impl.proxy;

import it.univaq.webmarket.data.model.impl.UfficioImpl;
import it.univaq.webmarket.framework.data.DataItemProxy;
import it.univaq.webmarket.framework.data.DataLayer;

public class UfficioProxy extends UfficioImpl implements DataItemProxy {

    protected boolean modified;

    protected DataLayer dataLayer;

    public UfficioProxy(DataLayer d) {
        super();
        this.dataLayer = d;
        this.modified = false;
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void setModified(boolean dirty) {
        this.modified = dirty;
    }

    public void setSede(String sede) {
        super.setSede(sede);
        this.modified = true;
    }

    public void setNumeroTelefono(String telefono) {
        super.setNumeroTelefono(telefono);
        this.modified = true;
    }

    public void setNumeroUfficio(Integer numeroUfficio) {
        super.setNumeroUfficio(numeroUfficio);
        this.modified = true;
    }

    public void setPiano(Integer piano) {
        super.setPiano(piano);
        this.modified = true;
    }


    public String getSede() {
        return super.getSede();
    }


    public String getNumeroTelefono() {
        return super.getNumeroTelefono();
    }


    public Integer getPiano() {
        return super.getPiano();
    }


    public Integer getNumeroUfficio() {
        return super.getNumeroUfficio();
    }

}
