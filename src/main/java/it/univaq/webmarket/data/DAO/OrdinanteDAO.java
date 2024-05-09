package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.OrdineAcquisto;
import it.univaq.webmarket.framework.data.DataException;

import javax.xml.crypto.Data;
import java.util.List;

public interface OrdinanteDAO {

    Ordinante createOrdinante();

    Ordinante getOrdinante(int ordinante_key) throws DataException;

    List<Ordinante> getAllOrdinanti() throws DataException;

    List<OrdineAcquisto> getOrderHistory(Ordinante ordinante) throws DataException;

    // email è dichiarato UNIQUE, abbiamo al certezza che ne esista solo uno
    Ordinante getOrdinanteByEmail(String email) throws DataException;

    void storeOrdinante(Ordinante ordinante) throws DataException;
}
