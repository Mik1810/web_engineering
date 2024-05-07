package it.univaq.webmarket.data.model.impl;

import it.univaq.webmarket.data.model.TecnicoOrdini;
import it.univaq.webmarket.framework.data.DataItemImpl;

public class TecnicoOrdiniImpl extends DataItemImpl<Integer> implements TecnicoOrdini {

    private String email;
    private String password;

    public TecnicoOrdiniImpl() {
        super();
        this.email = "";
        this.password = "";
    }

    @Override
    public String getEmail() { return this.email; }

    @Override
    public String getPassword() { return this.password; }

    @Override
    public void setEmail(String email) { this.email = email; }

    @Override
    public void setPassword(String password) { this.password = password; }
}
