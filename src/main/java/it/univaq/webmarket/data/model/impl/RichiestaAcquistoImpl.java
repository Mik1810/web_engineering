package it.univaq.webmarket.data.model.impl;

import it.univaq.webmarket.data.model.RichiestaAcquisto;
import it.univaq.webmarket.framework.data.DataItemImpl;

import java.time.LocalDateTime;

public class RichiestaAcquistoImpl extends DataItemImpl<Integer> implements RichiestaAcquisto {

    private Integer id;
    private String note;
    private String codiceRichietsa;
    private LocalDateTime dataEOra;

    public RichiestaAcquistoImpl() {
        super();
        this.note = "";
        this.codiceRichietsa = "";
        this.dataEOra = null;
    }

    @Override
    public String getNote() {
        return "";
    }

    @Override
    public void setNote(String note) {

    }

    @Override
    public String getCodiceRichiesta() {
        return "";
    }

    @Override
    public void setCodiceRichiesta(String codiceRichiesta) {

    }

    @Override
    public LocalDateTime getDataEOra() {
        return null;
    }

    @Override
    public void setDataEOra(LocalDateTime dataEOra) {

    }
}
