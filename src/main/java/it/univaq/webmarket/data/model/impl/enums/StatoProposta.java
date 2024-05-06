package it.univaq.webmarket.data.model.impl.enums;

/*
public class StatoProposta {

    public static final String IN_ATTESA = "In Attesa";
    public static final String ACCETTATA = "Accettata";
    public static final String RIFIUTATA = "RIFIUTATA";

}
 */

public enum StatoProposta {
    IN_ATTESA(1),
    ACCETTATA(2),
    RIFIUTATA(3);

    private final Integer value;

    StatoProposta(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}


