package it.univaq.webmarket.data.model.impl.utenti;

import it.univaq.webmarket.framework.data.DataItemImpl;

public abstract class Utente extends DataItemImpl<Integer> {
    private String email;
    private String password;
    private Integer id;

    public Utente() {
        this.email = "";
        this.password = "";
        this.id = null;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Integer getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
