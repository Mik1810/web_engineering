package it.univaq.webmarket.data.model.impl.utenti;

import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.Ufficio;
import it.univaq.webmarket.data.model.impl.UfficioImpl;

public class OrdinanteImpl extends Utente implements Ordinante {

    private Ufficio ufficio;

    public OrdinanteImpl() {

        super();
        this.ufficio = null;
    }

    public Ufficio getUfficio() { return ufficio; }

    public void setUfficio(Ufficio ufficio) {
        this.ufficio = ufficio;
    }
}
