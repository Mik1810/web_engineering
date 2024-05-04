package it.univaq.webmarket.model.Utenti;

import it.univaq.webmarket.model.Ufficio;

public class Ordinante extends Utente{

    private Ufficio ufficio;

    public Ordinante(Integer id, String email, String password, Ufficio ufficio) {

        super(id, email, password);
        this.ufficio = ufficio;
    }

    public Ufficio getUfficio() { return ufficio; }

    public void setUfficio(Ufficio ufficio) {
        this.ufficio = ufficio;
    }
}
