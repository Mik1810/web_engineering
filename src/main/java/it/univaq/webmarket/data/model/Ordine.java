package it.univaq.webmarket.data.model;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import it.univaq.webmarket.framework.data.DataItem;

import java.time.LocalDate;
import java.util.List;

public interface Ordine extends DataItem<Integer> {

    class StatoConsegna implements TemplateMethodModelEx {

        public static final Integer PRESA_IN_CARICO = 1;
        public static final Integer IN_CONSEGNA = 2;
        public static final Integer CONSEGNATO = 3;

        public String valueOf(Integer value) {
            switch (value) {
                case 1:
                    return "Presa in carico";
                case 2:
                    return "In consegna";
                case 3:
                    return "Consegnato";
                default:
                    return "Unknown";
            }
        }

        @Override
        public Object exec(List list) throws TemplateModelException {
            return new SimpleScalar(valueOf(Integer.valueOf(list.get(0).toString())));
        }

    }

    class Feedback implements TemplateMethodModelEx {

        public static final Integer ACCETTATO = 1;
        public static final Integer RESPINTO_NON_CONFORME = 2;
        public static final Integer RIFIUTATO_NON_FUNZIONANTE = 3;

        public  String valueOf(Integer value) {
            switch (value) {
                case 1:
                    return "Accettato";
                case 2:
                    return "Respinto perchè non conforme";
                case 3:
                    return "Respinto perchè non funzionante";
                default:
                    return "Unknown";
            }
        }

        @Override
        public Object exec(List list) throws TemplateModelException {
            return new SimpleScalar(valueOf(Integer.valueOf(list.get(0).toString())));
        }

    }

    Integer getStatoConsegna();

    void setStatoConsegna(Integer statoConsegna);

    Integer getFeedback();

    void setFeedback(Integer feedback);

    TecnicoOrdini getTecnicoOrdini();

    void setTecnicoOrdini(TecnicoOrdini tecnicoOrdini);

    Proposta getProposta();

    void setProposta(Proposta proposta);

    LocalDate getDataConsegna();

    void setDataConsegna(LocalDate dataConsegna);


}
