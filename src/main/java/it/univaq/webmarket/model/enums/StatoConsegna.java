package it.univaq.webmarket.model.enums;

/*
public class StatoConsegna {

    public static final String PRESA_IN_CARICO = "Presa in carico";
    public static final String IN_CONSEGNA = "In consegna";
    public static final String CONSEGNATO = "Consegnato";
}*/

public enum StatoConsegna {
    PRESA_IN_CARICO("Presa in carico"),
    IN_CONSEGNA("In consegna"),
    CONSEGNATO("Consegnato");

    private final String value;

    StatoConsegna(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
