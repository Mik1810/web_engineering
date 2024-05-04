package it.univaq.webmarket.model;

import it.univaq.webmarket.model.enums.StatoProposta;

public class Proposta {

    private Integer id;
    private String codiceProdotto;
    private String produttore;
    private String note;
    private Float prezzo;
    private String nomeProdotto;
    private StatoProposta statoProposta;
    private String motivazione;

    public Proposta(Integer id, String codiceProdotto, String produttore, String note, Float prezzo, String nomeProdotto, StatoProposta statoProposta, String motivazione) {
        this.id = id;
        this.codiceProdotto = codiceProdotto;
        this.produttore = produttore;
        this.note = note;
        this.prezzo = prezzo;
        this.nomeProdotto = nomeProdotto;
        this.statoProposta = statoProposta;
        this.motivazione = motivazione;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodiceProdotto() {
        return codiceProdotto;
    }

    public void setCodiceProdotto(String codiceProdotto) {
        this.codiceProdotto = codiceProdotto;
    }

    public String getProduttore() {
        return produttore;
    }

    public void setProduttore(String produttore) {
        this.produttore = produttore;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    public StatoProposta getStatoProposta() {
        return statoProposta;
    }

    public void setStatoProposta(StatoProposta statoProposta) {
        this.statoProposta = statoProposta;
    }

    public String getMotivazione() {
        return motivazione;
    }

    public void setMotivazione(String motivazione) {
        this.motivazione = motivazione;
    }
}
