package it.univaq.webmarket.model;

public class RichiestaConCaratteristica {

    private RichiestaAcquisto richiestaAcquisto;
    private Caratteristica caratteristica;
    private String valore;

    public RichiestaConCaratteristica(RichiestaAcquisto richiestaAcquisto, Caratteristica caratteristica, String valore) {
        this.richiestaAcquisto = richiestaAcquisto;
        this.caratteristica = caratteristica;
        this.valore = valore;
    }

    public RichiestaAcquisto getRichiestaAcquisto() {
        return richiestaAcquisto;
    }

    public void setRichiestaAcquisto(RichiestaAcquisto richiestaAcquisto) {
        this.richiestaAcquisto = richiestaAcquisto;
    }

    public Caratteristica getCaratteristica() {
        return caratteristica;
    }

    public void setCaratteristica(Caratteristica caratteristica) {
        this.caratteristica = caratteristica;
    }

    public String getValore() {
        return valore;
    }

    public void setValore(String valore) {
        this.valore = valore;
    }
}
