package it.univaq.webmarket.data.model;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import it.univaq.webmarket.framework.data.DataItem;

import java.time.LocalDate;
import java.util.List;

public interface Ordine extends DataItem<Integer> {

    class StatoConsegna {

        public static final String PRESA_IN_CARICO = "Presa in carico";
        public static final String IN_CONSEGNA = "In consegna";
        public static final String CONSEGNATO = "Consegnato";

    }

    class Feedback  {

        public static final String ACCETTATO = "Accettato";
        public static final String RESPINTO_NON_CONFORME = "Respinto perchè non conforme";
        public static final String RIFIUTATO_NON_FUNZIONANTE = "Rifiutato perchè non conforme";

    }

    String getStatoConsegna();

    void setStatoConsegna(String statoConsegna);

    String getFeedback();

    void setFeedback(String feedback);

    TecnicoOrdini getTecnicoOrdini();

    void setTecnicoOrdini(TecnicoOrdini tecnicoOrdini);

    Proposta getProposta();

    void setProposta(Proposta proposta);

    LocalDate getDataConsegna();

    void setDataConsegna(LocalDate dataConsegna);


}
