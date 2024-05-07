package it.univaq.webmarket.data.model;

// Utente estende già DataItem<Integer>
public interface Ordinante extends Utente {

    Ufficio getUfficio() ;

    void setUfficio(Ufficio ufficio) ;
}
