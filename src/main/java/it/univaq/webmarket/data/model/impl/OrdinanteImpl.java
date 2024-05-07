package it.univaq.webmarket.data.model.impl;

import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.Ufficio;
import it.univaq.webmarket.framework.data.DataItemImpl;

public class OrdinanteImpl extends DataItemImpl<Integer> implements Ordinante {

    private Ufficio ufficio;
    private String email;
    private String password;

    public OrdinanteImpl() {
        super();
        this.ufficio = null;
        this.email = "";
        this.password = "";
    }

    @Override
    public Ufficio getUfficio() { return ufficio; }

    @Override
    public void setUfficio(Ufficio ufficio) {
        this.ufficio = ufficio;
    }

    @Override
    public String getEmail() { return this.email; }

    @Override
    public String getPassword() { return this.password; }

    @Override
    public void setEmail(String email) { this.email = email;}

    @Override
    public void setPassword(String password) { this.password = password; }
}
