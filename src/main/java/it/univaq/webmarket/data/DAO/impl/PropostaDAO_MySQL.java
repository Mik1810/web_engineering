package it.univaq.webmarket.data.DAO.impl;

import it.univaq.webmarket.data.DAO.PropostaDAO;
import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.Proposta;
import it.univaq.webmarket.data.model.impl.proxy.PropostaProxy;
import it.univaq.webmarket.framework.data.DAO;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.data.DataLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PropostaDAO_MySQL extends DAO implements PropostaDAO {

    private PreparedStatement sPropostaByID;
    private PreparedStatement sProposte;
    private PreparedStatement iProposta;
    private PreparedStatement dProposta;
    private PreparedStatement uProposta;

    public PropostaDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public void init() throws DataException {
        try {
            super.init();
            sPropostaByID = connection.prepareStatement("SELECT * FROM proposta WHERE ID=?");
            sProposte = connection.prepareStatement("SELECT ID FROM proposta");
            iProposta = connection.prepareStatement("INSERT INTO proposta(codice_prodotto, produttore, note, prezzo,nome_prodotto,URL,stato,motivazione, ID_richiesta_presa_in_carico, ID_tecnico_preventivi) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            dProposta = connection.prepareStatement("DELETE FROM proposta WHERE ID=?");
            uProposta = connection.prepareStatement("UPDATE proposta SET codice_prodotto=?, produttore=?, note=?, prezzo=?, nome_prodotto=?, URL=?, stato=?, motivazione=?, ID_richiesta_presa_in_carico=?, ID_tecnico_preventivi=?, version=? WHERE ID=? AND version=?");
        } catch (SQLException ex) {
            throw new DataException("Error initializing webmarket data layer", ex);
        }
    }

    @Override
    public void destroy() throws DataException {
        //anche chiudere i PreparedStamenent è una buona pratica...
        //also closing PreparedStamenents is a good practice...
        try {
            sPropostaByID.close();
            sProposte.close();
            iProposta.close();
            dProposta.close();
            uProposta.close();
        } catch (SQLException ex) {
            throw new DataException("Can't destroy connections", ex);
        }
        super.destroy();
    }


    @Override
    public Proposta createProposta() {
        return new PropostaProxy(getDataLayer());
    }

    private PropostaProxy createProposta (ResultSet rs) throws DataException {
        try {
            PropostaProxy proposta = new PropostaProxy(getDataLayer());
            proposta.setKey(rs.getInt("ID"));
            proposta.setCodiceProdotto(rs.getString("codice_prodotto"));
            proposta.setProduttore(rs.getString("produttore"));
            proposta.setNote(rs.getString("note"));
            proposta.setPrezzo(rs.getFloat("prezzo"));
            proposta.setNomeProdotto(rs.getString("nome_prodotto"));
            proposta.setURL(rs.getString("URL"));

            //usare il DAO per ottenere lo stato proposta
            //proposta.setStatoProposta(stato);

            proposta.setMotivazione(rs.getString("motivazione"));
            proposta.setVersion(rs.getLong("version"));
            proposta.setRichiestaPresaInCarico_key(rs.getInt("ID_richiesta_presa_in_carico"));
            return proposta;

        } catch (SQLException ex) {
            throw new DataException("Unable to create author object form ResultSet", ex);
        }
    }

    @Override
    public Proposta getProposta(int key) throws DataException{
        Proposta proposta = null;
        if(dataLayer.getCache().has(Proposta.class, key)){
            proposta = dataLayer.getCache().get(Proposta.class, key);
        }else{
            try {
                sPropostaByID.setInt(1,key);
                try (ResultSet rs = sPropostaByID.executeQuery()){
                    if(rs.next()){
                        proposta = createProposta(rs);
                        dataLayer.getCache().add(Proposta.class, proposta);
                    }
                }
            }catch (SQLException ex){
                throw new DataException("Unable to get proposta by ID", ex);
            }
        }
        return proposta;
    }

    @Override
    public List<Proposta> getAllProposta() throws DataException {
        List<Proposta> result = new ArrayList<>();
        try {
            try (ResultSet rs = sProposte.executeQuery()){
                while (rs.next()){
                    result.add(getProposta(rs.getInt("ID")));
                }
            }
            return result;

        }catch (SQLException ex){
            throw new DataException("Unable to get all proposta", ex);
        }
    }

    @Override
    public void deleteProposta(Proposta proposta) throws DataException {
        try {
            dataLayer.getCache().delete(Proposta.class, proposta);
            dProposta.setInt(1,proposta.getKey());
            dProposta.executeUpdate();
        }catch (SQLException ex){
            throw new DataException("Unable to delete Proposta", ex);
        }

    }

    @Override
    public void storeProposta(Proposta proposta) {

    }
    @Override
    public void updateProposta(Proposta proposta) {

    }
}
