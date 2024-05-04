package it.univaq.webmarket.model;

import java.time.LocalDateTime;

public class RichiestaAcquisto {

    private Integer id;
    private String note;
    private String codiceRichietsa;
    private LocalDateTime dataEOra;

    public RichiestaAcquisto(Integer id, String note, String codiceRichietsa, LocalDateTime dataEOra) {
        this.id = id;
        this.note = note;
        this.codiceRichietsa = codiceRichietsa;
        this.dataEOra = dataEOra;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCodiceRichietsa() {
        return codiceRichietsa;
    }

    public void setCodiceRichietsa(String codiceRichietsa) {
        this.codiceRichietsa = codiceRichietsa;
    }

    public LocalDateTime getDataEOra() {
        return dataEOra;
    }

    public void setDataEOra(LocalDateTime dataEOra) {
        this.dataEOra = dataEOra;
    }
}
