package it.univaq.webmarket.data.model.impl.enums;

/*
public class StatoConsegna {

    public static final String PRESA_IN_CARICO = "Presa in carico";
    public static final String IN_CONSEGNA = "In consegna";
    public static final String CONSEGNATO = "Consegnato";
}*/

import it.univaq.webmarket.data.model.enums.StatoConsegna;

public class StatoConsegnaImpl extends StatoEnumImpl implements StatoConsegna {

    @Override
    public String toString() {
        return "StatoConsegnaImpl{" +
                "id=" + getKey() + ", " +
                "nome='" + getNome() + '\'' +
                '}';
    }
}
