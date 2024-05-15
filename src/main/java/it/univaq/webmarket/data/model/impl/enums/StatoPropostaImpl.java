package it.univaq.webmarket.data.model.impl.enums;

/*
public class StatoProposta {

    public static final String IN_ATTESA = "In Attesa";
    public static final String ACCETTATA = "Accettata";
    public static final String RIFIUTATA = "RIFIUTATA";

}
 */

import it.univaq.webmarket.data.model.enums.StatoProposta;

public class StatoPropostaImpl extends StatoEnumImpl implements StatoProposta {

    @Override
    public String toString() {
        return "StatoPropostaImpl{" +
                "id=" + getKey() + ", " +
                "nome='" + getNome() + '\'' +
                '}';
    }
}


