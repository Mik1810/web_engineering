package it.univaq.webmarket.data.DAO;


import it.univaq.webmarket.data.model.enums.Feedback;
import it.univaq.webmarket.data.model.enums.StatoConsegna;
import it.univaq.webmarket.data.model.enums.StatoProposta;
import it.univaq.webmarket.framework.data.DataException;

import java.util.List;

public interface StatiEnumDAO {

    StatoProposta createStatoProposta();

    StatoConsegna createStatoConsegna();

    Feedback createFeedback();

    StatoProposta getStatoProposta(int statoProposta_key) throws DataException;

    StatoConsegna getStatoConsegna(int statoConsegna_key) throws DataException;;

    Feedback getFeedback(int feedback_key) throws DataException;

    List<StatoProposta> getAllStatiProposta() throws DataException;

    List<StatoConsegna> getAllStatiConsegna() throws DataException;;

    List<Feedback> getAllFeedback() throws DataException;;
}
