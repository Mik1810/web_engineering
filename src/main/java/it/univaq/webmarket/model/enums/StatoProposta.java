package it.univaq.webmarket.model.enums;

/*
public class StatoProposta {

    public static final String IN_ATTESA = "In Attesa";
    public static final String ACCETTATA = "Accettata";
    public static final String RIFIUTATA = "RIFIUTATA";

}
 */

public enum StatoProposta {
    IN_ATTESA("In Attesa"),
    ACCETTATA("Accettata"),
    RIFIUTATA("RIFIUTATA");

    private final String value;

    StatoProposta(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}


