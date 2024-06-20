package it.univaq.webmarket.data.model.impl;

import it.univaq.webmarket.data.model.Proposta;
import it.univaq.webmarket.data.model.RichiestaPresaInCarico;
import it.univaq.webmarket.framework.data.DataItemImpl;

public class PropostaImpl extends DataItemImpl<Integer> implements Proposta {

    private String nomeProdotto, codiceProdotto, produttore, note, motivazione, URL;
    private Float prezzo;
    private Integer statoProposta;
    private RichiestaPresaInCarico richiestaPresaInCarico;

    public PropostaImpl() {
        this.codiceProdotto = "";
        this.produttore = "";
        this.note = "";
        this.prezzo = 0F;
        this.nomeProdotto = "";
        this.URL = "";
        this.statoProposta = 0;
        this.motivazione = "";
        this.richiestaPresaInCarico = null;
    }

    @Override
    public String getCodiceProdotto() {
        return codiceProdotto;
    }

    @Override
    public void setCodiceProdotto(String codiceProdotto) {
        this.codiceProdotto = codiceProdotto;
    }

    @Override
    public String getProduttore() {
        return produttore;
    }

    @Override
    public void setProduttore(String produttore) {
        this.produttore = produttore;
    }

    @Override
    public String getNote() {
        return note;
    }

    @Override
    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public Float getPrezzo() {
        return prezzo;
    }

    @Override
    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    @Override
    public String getNomeProdotto() {
        return nomeProdotto;
    }

    @Override
    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    @Override
    public Integer getStatoProposta() { return statoProposta;}

    @Override
    public void setStatoProposta(Integer statoProposta) {
        this.statoProposta = statoProposta;
    }

    @Override
    public String getMotivazione() {
        return motivazione;
    }

    @Override
    public void setMotivazione(String motivazione) {
        this.motivazione = motivazione;
    }

    @Override
    public RichiestaPresaInCarico getRichiestaPresaInCarico() {
        return this.richiestaPresaInCarico;
    }

    @Override
    public void setRichiestaPresaInCarico(RichiestaPresaInCarico richiestaPresaInCarico) {
        this.richiestaPresaInCarico = richiestaPresaInCarico;
    }

    @Override
    public String getURL() {
        return this.URL;
    }

    @Override
    public void setURL(String URL) {
        this.URL = URL;
    }

    @Override
    public String toString() {
        return "PropostaImpl{" +
                "nomeProdotto='" + nomeProdotto + '\'' +
                ", codiceProdotto='" + codiceProdotto + '\'' +
                ", produttore='" + produttore + '\'' +
                ", note='" + note + '\'' +
                ", motivazione='" + motivazione + '\'' +
                ", URL='" + URL + '\'' +
                ", prezzo=" + prezzo +
                ", statoProposta=" + statoProposta +
                ", richiestaPresaInCarico=" + richiestaPresaInCarico +
                '}';
    }
}
