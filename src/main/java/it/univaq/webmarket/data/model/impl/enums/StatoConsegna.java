package it.univaq.webmarket.data.model.impl.enums;

/*
public class StatoConsegna {

    public static final String PRESA_IN_CARICO = "Presa in carico";
    public static final String IN_CONSEGNA = "In consegna";
    public static final String CONSEGNATO = "Consegnato";
}*/

public enum StatoConsegna {
    PRESA_IN_CARICO(1),
    IN_CONSEGNA(2),
    CONSEGNATO(3);

    private final Integer value;

    StatoConsegna(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
