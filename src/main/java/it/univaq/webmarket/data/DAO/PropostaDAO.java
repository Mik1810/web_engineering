package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.Proposta;
import it.univaq.webmarket.data.model.RichiestaPresaInCarico;
import it.univaq.webmarket.data.model.TecnicoPreventivi;
import it.univaq.webmarket.framework.data.DataException;

import java.util.List;

public interface PropostaDAO {

    Proposta createProposta();

    Proposta getProposta(Integer key) throws DataException;

    // Questo metodo ritorna tutte le proposte legate ad una richiesta presa in carico
    List<Proposta> getAllProposteByRichiestaPresaInCarico(RichiestaPresaInCarico richiestaPresaInCarico, Integer page) throws DataException;

    // Questo metodo ritorna tutte le proposte rivolte ad un ordinante
    List<Proposta> getAllProposteDaDecidereByOrdinante(Ordinante ordinante, Integer page) throws DataException;

    //Questo metodo ritorna tutte le proposte effettuate da un tecnico dei preventivi
    List<Proposta> getAllProposteByTecnicoPreventivi(TecnicoPreventivi tecnicoPreventivi, Integer page) throws DataException;

    // Questo metodo ritorna tutte le proposte accettate che possono diventare ordini
    List<Proposta> getAllProposteAccettate(Integer page) throws DataException;

    void deleteProposta(Proposta proposta) throws DataException;

    void storeProposta(Proposta proposta) throws DataException;

}
