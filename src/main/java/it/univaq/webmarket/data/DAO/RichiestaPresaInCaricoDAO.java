package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.RichiestaPresaInCarico;
import it.univaq.webmarket.data.model.TecnicoPreventivi;
import it.univaq.webmarket.framework.data.DataException;

import java.util.List;

public interface RichiestaPresaInCaricoDAO {

    RichiestaPresaInCarico createRichiestaPresaInCarico();

    RichiestaPresaInCarico getRichiestaPresaInCarico(Integer key) throws DataException;

    List<RichiestaPresaInCarico> getAllRichiestePresaInCaricoByTecnicoPreventivi(TecnicoPreventivi tecnicoPreventivi, Integer page) throws DataException;

    void storeRichiestaPresaInCarico(RichiestaPresaInCarico richiestaPresaInCarico) throws DataException;

    void deleteRichiestaPresaInCarico(RichiestaPresaInCarico richiestaPresaInCarico) throws DataException;

}
