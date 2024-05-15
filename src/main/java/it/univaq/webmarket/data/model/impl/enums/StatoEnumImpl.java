package it.univaq.webmarket.data.model.impl.enums;

import it.univaq.webmarket.data.model.enums.StatoEnum;
import it.univaq.webmarket.framework.data.DataItemImpl;

public class StatoEnumImpl extends DataItemImpl<Integer> implements StatoEnum {

    private String nome;

    public StatoEnumImpl() {
        super();
        this.nome = "";
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "StatoEnumImpl{" +
                        "id=" + getKey() + ", " +
                        "nome='" + nome + '\'' +
                        '}';
    }
}
