package it.univaq.webmarket.data.model.impl;

import it.univaq.webmarket.data.model.TecnicoPreventivi;
import it.univaq.webmarket.framework.data.DataItemImpl;

//Fix giacomo
public class TecnicoPreventiviImpl extends DataItemImpl<Integer> implements TecnicoPreventivi {

    private String email;
    private String password;

    public TecnicoPreventiviImpl() {
        super();
        this.password = "";
        this.email = "";
    }

    @Override
    public String getEmail() { return this.email; }

    @Override
    public String getPassword() { return this.password; }

    @Override
    public void setEmail(String email) { this.email = email; }

    @Override
    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return "TecnicoPreventiviImpl{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
