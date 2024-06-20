package it.univaq.webmarket.data.model;

import freemarker.template.SimpleNumber;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import it.univaq.webmarket.framework.data.DataItem;

import java.util.List;

public interface Proposta extends DataItem<Integer> {

    class StatoProposta implements TemplateMethodModelEx {

        public static final Integer IN_ATTESA = 1;
        public static final Integer ACCETTATO = 2;
        public static final Integer RIFIUTATO = 3;

        public String valueOf(Integer value) {
            switch (value) {
                case 1:
                    return "In attesa";
                case 2:
                    return "Accettato";
                case 3:
                    return "Rifiutato";
                default:
                    return "Unknown";
            }
        }

        @Override
        public Object exec(List list) throws TemplateModelException {
            return new SimpleScalar(valueOf(Integer.valueOf(list.get(0).toString())));
        }
    }

    String getCodiceProdotto();

    void setCodiceProdotto(String codiceProdotto);

    String getProduttore();

    void setProduttore(String produttore);

    String getNote();

    void setNote(String note);

    String getURL();

    void setURL(String URL);

    Float getPrezzo();

    void setPrezzo(Float prezzo);

    String getNomeProdotto();

    void setNomeProdotto(String nomeProdotto);

    Integer getStatoProposta();

    void setStatoProposta(Integer statoProposta);

    String getMotivazione();

    void setMotivazione(String motivazione);

    RichiestaPresaInCarico getRichiestaPresaInCarico();

    void setRichiestaPresaInCarico(RichiestaPresaInCarico richiestaPresaInCarico);
}
