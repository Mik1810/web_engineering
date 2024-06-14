package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.Proposta;
import it.univaq.webmarket.framework.data.DataException;

import java.util.List;

public interface PropostaDAO {
    Proposta createProposta();

    Proposta getProposta(int key) throws DataException;

    List<Proposta> getAllProposta(Integer page) throws DataException;

    void deleteProposta(Proposta proposta) throws DataException;

    void storeProposta(Proposta proposta) throws DataException;

}
