package it.univaq.webmarket.data.DAO.impl;

import it.univaq.webmarket.data.DAO.OrdinanteDAO;
import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.OrdineAcquisto;
import it.univaq.webmarket.data.model.impl.proxy.OrdinanteProxy;
import it.univaq.webmarket.framework.data.DAO;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.data.DataLayer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrdinanteDAO_MySql extends DAO implements OrdinanteDAO {

    private PreparedStatement sOrdinanteByID;
    private PreparedStatement sOrdinanteByEmail;
    private PreparedStatement sOrderHistoryByOrdinanteID;
    private PreparedStatement iOrdinante;

    public OrdinanteDAO_MySql(DataLayer d) {
        super(d);
    }

    @Override
    public void init() throws DataException, SQLException {
        super.init();
        sOrdinanteByID = connection.prepareStatement("SELECT * FROM ordinante WHERE ID=?");
        //sOrderHistoryByOrdinanteID = connection.prepareStatement();
        sOrdinanteByEmail = connection.prepareStatement("SELECT * FROM ordinante WHERE email=?");
        iOrdinante = connection.prepareStatement("INSERT INTO ordinante(email, password) VALUES (?, ?)");
        

    }

    @Override
    public void destroy() throws DataException {
        super.destroy();
    }

    @Override
    public Ordinante createOrdinante() {
        return new OrdinanteProxy(getDataLayer());
    }

    @Override
    public Ordinante getOrdinante(int ordinante_key) {
        return null;
    }

    @Override
    public List<Ordinante> getAllOrdinanti() {
        return List.of();
    }

    @Override
    public List<OrdineAcquisto> getOrderHistory(int ordinante_key) {
        return List.of();
    }

    @Override
    public Ordinante getOrdinanteByEmail(String Email) {
        return null;
    }

    @Override
    public void storeOrdinante(Ordinante ordinante) {

    }
}
