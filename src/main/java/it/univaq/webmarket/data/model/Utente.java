package it.univaq.webmarket.data.model;

import it.univaq.webmarket.framework.data.DataItem;

public interface Utente extends DataItem<Integer> {

    String getEmail();

    String getPassword();

    void setEmail(String email);

    void setPassword(String password);
}
