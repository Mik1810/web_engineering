package it.univaq.webmarket.data.DAO.impl;

import it.univaq.webmarket.data.DAO.PropostaDAO;
import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.Proposta;
import it.univaq.webmarket.data.model.RichiestaPresaInCarico;
import it.univaq.webmarket.data.model.TecnicoPreventivi;
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
    private PreparedStatement sProposteByRichiestaPresaInCarico;
    private PreparedStatement sProposteByTecnicoPreventivi;
    private PreparedStatement sProposteDaDecidereByOrdinante;
    private PreparedStatement sProposteAccettate;
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
            sProposteByRichiestaPresaInCarico = connection.prepareStatement("SELECT ID FROM proposta WHERE ID_richiesta_presa_in_carico = ? LIMIT ?, ?");
            sProposteByTecnicoPreventivi = connection.prepareStatement("SELECT p.ID FROM proposta p JOIN richiestapresaincarico r ON p.ID_richiesta_presa_in_carico = r.ID WHERE r.ID_tecnico_preventivi = ? LIMIT ?, ?");
            sProposteAccettate = connection.prepareStatement("SELECT ID FROM proposta WHERE stato_proposta = 'Accettato' LIMIT ?, ?");
            sProposteDaDecidereByOrdinante = connection.prepareStatement(
                    "SELECT p.ID FROM proposta p " +
                    "JOIN richiestapresaincarico rp ON p.ID_richiesta_presa_in_carico = rp.ID " +
                    "JOIN richiesta r ON rp.ID_richiesta=r.ID " +
                    "WHERE ID_ordinante = ? AND p.stato_proposta = 'In attesa' LIMIT ?, ?");
            iProposta = connection.prepareStatement("INSERT INTO proposta(" +
                    "codice_prodotto, " +
                    "produttore, " +
                    "note, " +
                    "prezzo, " +
                    "nome_prodotto," +
                    "URL, " +
                    "stato_proposta," +
                    "motivazione," +
                    "ID_richiesta_presa_in_carico)" +
                    " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            dProposta = connection.prepareStatement("DELETE FROM proposta WHERE ID=?");
            uProposta = connection.prepareStatement("UPDATE proposta SET codice_prodotto=?, produttore=?, note=?, prezzo=?, nome_prodotto=?, URL=?, stato_proposta=?, motivazione=?, ID_richiesta_presa_in_carico=?, version=? WHERE ID=? AND version=?");
        } catch (SQLException ex) {
            throw new DataException("Error initializing webmarket data layer", ex);
        }
    }

    @Override
    public void destroy() throws DataException {
        try {
            sPropostaByID.close();
            sProposteByRichiestaPresaInCarico.close();
            sProposteByTecnicoPreventivi.close();
            sProposteAccettate.close();
            sProposteDaDecidereByOrdinante.close();
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
            proposta.setStatoProposta(rs.getString("stato_proposta"));
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
    public Proposta getProposta(Integer key) throws DataException{
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
    public List<Proposta> getAllProposteByRichiestaPresaInCarico(RichiestaPresaInCarico richiestaPresaInCarico, Integer page) throws DataException {
        List<Proposta> result = new ArrayList<>();
        try {
            sProposteByRichiestaPresaInCarico.setInt(1, richiestaPresaInCarico.getKey());
            sProposteByRichiestaPresaInCarico.setInt(2, page * offset);
            sProposteByRichiestaPresaInCarico.setInt(3, offset);
            try (ResultSet rs = sProposteByRichiestaPresaInCarico.executeQuery()) {
                while (rs.next()) {
                    result.add(getProposta(rs.getInt("ID")));
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to get all Proposte by RichiestaPresaInCarico", ex);
        }
        return result;
    }

    @Override
    public List<Proposta> getAllProposteDaDecidereByOrdinante(Ordinante ordinante, Integer page) throws DataException {
        List<Proposta> result = new ArrayList<>();
        try {
            sProposteDaDecidereByOrdinante.setInt(1, ordinante.getKey());
            sProposteDaDecidereByOrdinante.setInt(2, page * offset);
            sProposteDaDecidereByOrdinante.setInt(3, offset);
            try (ResultSet rs = sProposteDaDecidereByOrdinante.executeQuery()) {
                while (rs.next()) {
                    result.add(getProposta(rs.getInt("ID")));
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to get all Proposte by Ordinante", ex);
        }
        return result;
    }

    @Override
    public List<Proposta> getAllProposteByTecnicoPreventivi(TecnicoPreventivi tecnicoPreventivi, Integer page) throws DataException {
        List<Proposta> result = new ArrayList<>();
        try {
            sProposteByTecnicoPreventivi.setInt(1, tecnicoPreventivi.getKey());
            sProposteByTecnicoPreventivi.setInt(2, page * offset);
            sProposteByTecnicoPreventivi.setInt(3, offset);
            try (ResultSet rs = sProposteByTecnicoPreventivi.executeQuery()) {
                while (rs.next()) {
                    result.add(getProposta(rs.getInt("ID")));
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to get all Proposte by TecnicoPreventivi", ex);
        }
        return result;
    }

    @Override
    public List<Proposta> getAllProposteAccettate(Integer page) throws DataException {
        List<Proposta> result = new ArrayList<>();
        try {
            sProposteAccettate.setInt(1, page * offset);
            sProposteAccettate.setInt(2, offset);
            try (ResultSet rs = sProposteAccettate.executeQuery()) {
                while (rs.next()) {
                    result.add(getProposta(rs.getInt("ID")));
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to get all Proposte accettate", ex);
        }
        return result;
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
               /*
               * codice_prodotto=1,
               * produttore=2,
               * note=3,
               * prezzo=4,
               * nome_prodotto=5,
               * URL=6,
               * stato_proposta=7,
               * motivazione=8,
               * ID_richiesta_presa_in_carico=9,
               * version=10
               * WHERE ID=11
               * AND version=12
               */
                uProposta.setString(1, proposta.getCodiceProdotto());
                uProposta.setString(2, proposta.getProduttore());
                uProposta.setString(3, proposta.getNote());
                uProposta.setFloat(4, proposta.getPrezzo());
                uProposta.setString(5, proposta.getNomeProdotto());
                uProposta.setString(6, proposta.getURL());
                uProposta.setString(7, proposta.getStatoProposta());
                uProposta.setString(8, proposta.getMotivazione());
                uProposta.setInt(9, proposta.getRichiestaPresaInCarico().getKey());

                long current_version = proposta.getVersion();
                long next_version = current_version + 1;

                uProposta.setLong(10, next_version);
                uProposta.setInt(11, proposta.getKey());
                uProposta.setLong(12, current_version);

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
                iProposta.setString(7, proposta.getStatoProposta());
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
