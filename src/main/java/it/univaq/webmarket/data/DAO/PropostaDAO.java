package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.Proposta;
import it.univaq.webmarket.data.model.RichiestaPresaInCarico;
import it.univaq.webmarket.data.model.TecnicoPreventivi;
import it.univaq.webmarket.framework.data.DataException;

import java.util.List;

public interface PropostaDAO {

    /**
     * Crea un oggetto di tipo Proposta.
     *
     * @return un oggetto di tipo Proposta.
     */
    Proposta createProposta();

    /**
     * Restituisce l'oggetto Propostya con l'id passato come parametro
     *
     * @param key id della Proposta
     * @return l'oggetto Proposta con l'id passato come parametro
     */
    Proposta getProposta(Integer key) throws DataException;

    /**
     * Ritorna una lista di Proposte paginate data una RichiestaPresaInCarico
     *
     * @param richiestaPresaInCarico il TecnicoOrdini di cui si vogliono conoscere gli Ordini gestiti
     * @return una lista di Proposte associate ad una RichiestaPresaInCarico
     */
    List<Proposta> getAllProposteByRichiestaPresaInCarico(RichiestaPresaInCarico richiestaPresaInCarico) throws DataException;

    /**
     * Ritorna una lista di Proposte paginate suggerite ad un Ordinante e ancora da decidere,
     * cioè l'Ordinante deve ancora decidere se accettare o meno la proposta. Infatti, lo stato
     * di queste proposte è "In attesa"
     *
     * @param ordinante l'Ordinante di cui si vuole ottenere le Proposte
     * @param page indica il valore della paginazione degli oggetti, questo significa che gli oggetti
     *             verranno restituiti da page*offset fino a page*offset+offset
     * @return una lista di Proposte da far decidere all'Ordinante
     */
    List<Proposta> getAllProposteDaDecidereByOrdinante(Ordinante ordinante, Integer page) throws DataException;

    /**
     * Ritorna una lista di Proposte paginate create da uno specifico TecnicoPreventivi
     *
     * @param tecnicoPreventivi il TecnicoPreventivi che ha creato le Proposte
     * @param page indica il valore della paginazione degli oggetti, questo significa che gli oggetti
     *             verranno restituiti da page*offset fino a page*offset+offset
     * @return una lista di Proposte create da un TecnicoPreventivi
     */
    List<Proposta> getAllProposteByTecnicoPreventivi(TecnicoPreventivi tecnicoPreventivi, Integer page) throws DataException;

    /**
     * Ritorna una lista di Proposte paginate accettate da un Ordinante
     *
     * @param page indica il valore della paginazione degli oggetti, questo significa che gli oggetti
     *             verranno restituiti da page*offset fino a page*offset+offset
     * @return una lista di Proposte accettate da un Ordinante
     */
    List<Proposta> getAllProposteAccettate(Integer page) throws DataException;

    /**
     * Cancella dal database un oggetto di tipo Proposta
     *
     * @param proposta l'oggetto di tipo Proposta da cancellare
     */
    void deleteProposta(Proposta proposta) throws DataException;

    /**
     * Salva nel database una nuova Proposta o aggiorna quella esistente
     *
     * @param proposta l'oggetto di tipo Proposta da salvare
     */
    void storeProposta(Proposta proposta) throws DataException;

}
