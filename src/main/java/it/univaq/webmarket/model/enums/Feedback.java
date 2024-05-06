package it.univaq.webmarket.model.enums;

/*
public class Feedback {
    public static final String ACCETTATO = "Accettato";
    public static final String NON_CONFORME = "Respinto perché non conforme";
    public static final String NON_FUNZIONANTE = "Respinto perché non funzionante";
}*/


public enum Feedback {

    ACCETTATO(1),
    NON_CONFORME(2),
    NON_FUNZIONANTE(3);

    private final Integer value;

    Feedback(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}