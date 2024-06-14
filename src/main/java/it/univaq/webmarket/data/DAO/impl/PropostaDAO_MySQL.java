package it.univaq.webmarket.data.DAO.impl;

import it.univaq.webmarket.data.DAO.PropostaDAO;
import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.Proposta;
import it.univaq.webmarket.data.model.impl.proxy.PropostaProxy;
import it.univaq.webmarket.framework.data.DAO;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.data.DataLayer;
import it.univaq.webmarket.framework.data.OptimisticLockException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PropostaDAO_MySQL extends DAO implements PropostaDAO {

    private Integer offset = 5;

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
            sProposte = connection.prepareStatement("SELECT ID FROM proposta LIMIT ?, ?");
            iProposta = connection.prepareStatement("INSERT INTO proposta(codice_prodotto, produttore, note, prezzo,nome_prodotto,URL,stato,motivazione, ID_richiesta_presa_in_carico) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            dProposta = connection.prepareStatement("DELETE FROM proposta WHERE ID=?");
            uProposta = connection.prepareStatement("UPDATE proposta SET codice_prodotto=?, produttore=?, note=?, prezzo=?, nome_prodotto=?, URL=?, stato=?, motivazione=?, ID_richiesta_presa_in_carico=?, version=? WHERE ID=? AND version=?");
        } catch (SQLException ex) {
            throw new DataException("Error initializing webmarket data layer", ex);
        }
    }

    @Override
    public void destroy() throws DataException {
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
            proposta.setStatoProposta_key(rs.getInt("ID_stato_proposta"));



            proposta.setMotivazione(rs.getString("motivazione"));
            proposta.setVersion(rs.getLong("version"));
            proposta.setRichiestaPresaInCarico_key(rs.getInt("ID_richiesta_presa_in_carico"));
            proposta.setVersion(rs.getLong("version"));
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
    public List<Proposta> getAllProposta(Integer page) throws DataException {

        List<Proposta> result = new ArrayList<>();
        try {
            sProposte.setInt(1, page*offset);
            sProposte.setInt(2, offset);
            try (ResultSet rs = sProposte.executeQuery()){
                while (rs.next()){
                    result.add(getProposta(rs.getInt("ID")));
                }
            }
            return result;
        }catch (SQLException ex){
            throw new DataException("Unable to get all Proposta", ex);
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
    public void storeProposta(Proposta proposta) throws DataException {
        try{
            if(proposta.getKey()!= null && proposta.getKey()>0){
               if(proposta instanceof PropostaProxy && !((PropostaProxy) proposta).isModified()){
                   return;
               }
                uProposta.setString(1, proposta.getCodiceProdotto());
                uProposta.setString(2, proposta.getProduttore());
                uProposta.setString(3, proposta.getNote());
                uProposta.setFloat(4, proposta.getPrezzo());
                uProposta.setString(5, proposta.getNomeProdotto());
                uProposta.setString(6, proposta.getURL());
                uProposta.setInt(7, proposta.getStatoProposta().getKey());

                long current_version = proposta.getVersion();
                long next_version = current_version + 1;

                uProposta.setLong(11, next_version);
                uProposta.setInt(12, proposta.getKey());
                uProposta.setLong(13, current_version);

                if(uProposta.executeUpdate() == 0){
                    throw new OptimisticLockException(proposta);
                }else {
                    proposta.setVersion(next_version);
                }
            }else {
                iProposta.setString(1, proposta.getCodiceProdotto());
                iProposta.setString(2, proposta.getProduttore());
                iProposta.setString(3, proposta.getNote());
                iProposta.setFloat(4, proposta.getPrezzo());
                iProposta.setString(5, proposta.getNomeProdotto());
                iProposta.setString(6, proposta.getURL());
                iProposta.setString(7, proposta.getStatoProposta().getNome());
                iProposta.setString(8, proposta.getMotivazione());
                iProposta.setInt(9, proposta.getRichiestaPresaInCarico().getKey());

                if(iProposta.executeUpdate() == 1){
                    try(ResultSet keys = iProposta.getGeneratedKeys()){
                        if(keys.next()){
                            int key = keys.getInt(1);
                            proposta.setKey(key);
                            dataLayer.getCache().add(Proposta.class, proposta);
                        }
                    }
                }
            }
        }catch (SQLException ex){
            throw new DataException("Unable to store Proposta", ex);
        }
    }
}
