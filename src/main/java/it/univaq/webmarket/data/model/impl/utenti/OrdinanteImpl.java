package it.univaq.webmarket.data.model.impl.utenti;

import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.impl.UfficioImpl;

public class OrdinanteImpl extends Utente implements Ordinante {

    private UfficioImpl ufficioImpl;

    public OrdinanteImpl() {

        super();
        this.ufficioImpl = null;
    }

    public UfficioImpl getUfficio() { return ufficioImpl; }

    public void setUfficio(UfficioImpl ufficioImpl) {
        this.ufficioImpl = ufficioImpl;
    }
}
