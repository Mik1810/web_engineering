package it.univaq.webmarket.data.DAO;


import it.univaq.webmarket.data.model.enums.Feedback;
import it.univaq.webmarket.data.model.enums.StatoConsegna;
import it.univaq.webmarket.data.model.enums.StatoProposta;
import it.univaq.webmarket.framework.data.DataException;

import java.util.List;

public interface StatiFeedbackDAO {

    StatoProposta createStatoProposta();

    StatoConsegna createStatoConsegna();

    Feedback createFeedback();

    StatoProposta getStatoProposta(int statoProposta_key) throws DataException;

    StatoConsegna getStatoConsegna(int statoConsegna_key) throws DataException;;

    Feedback getFeedback(int feedback_key) throws DataException;

    // Qui posso anche evitare di passare il parametro page, dal momento che saranno sempre di una
    // quantità limitata
    List<StatoProposta> getAllStatiProposta() throws DataException;

    List<StatoConsegna> getAllStatiConsegna() throws DataException;;

    List<Feedback> getAllFeedback() throws DataException;
}
