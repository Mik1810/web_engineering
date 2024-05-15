package it.univaq.webmarket.data.model.impl.enums;

/*
public class Feedback {
    public static final String ACCETTATO = "Accettato";
    public static final String NON_CONFORME = "Respinto perché non conforme";
    public static final String NON_FUNZIONANTE = "Respinto perché non funzionante";
}*/


import it.univaq.webmarket.data.model.enums.Feedback;

public class FeedbackImpl extends StatoEnumImpl implements Feedback {

    @Override
    public String toString() {
        return "FeedbackImpl{" +
                "id=" + getKey() + ", " +
                "nome='" + getNome() + '\'' +
                '}';
    }
}