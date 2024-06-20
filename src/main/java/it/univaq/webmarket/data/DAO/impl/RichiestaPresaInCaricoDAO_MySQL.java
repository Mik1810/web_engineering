package it.univaq.webmarket.data.DAO.impl;

import it.univaq.webmarket.data.DAO.RichiestaPresaInCaricoDAO;
import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.RichiestaPresaInCarico;
import it.univaq.webmarket.data.model.TecnicoPreventivi;
import it.univaq.webmarket.data.model.impl.proxy.RichiestaPresaInCaricoProxy;
import it.univaq.webmarket.framework.data.*;
import it.univaq.webmarket.framework.security.SecurityHelpers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RichiestaPresaInCaricoDAO_MySQL extends DAO implements RichiestaPresaInCaricoDAO {

    private final Integer offset = 5;

    private PreparedStatement sRichiestaPresaInCaricoByID;
    private PreparedStatement sRichiestePresaInCaricoByTecnicoPreventivi;
    private PreparedStatement iRichiestaPresaInCarico;
    private PreparedStatement uRichiestaPresaInCarico;
    private PreparedStatement dRichiestaPresaInCarico;

    public RichiestaPresaInCaricoDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public void init() throws DataException {
        try {
            super.init();
            sRichiestaPresaInCaricoByID = connection.prepareStatement("SELECT * FROM richiestapresaincarico WHERE ID=?");
            sRichiestePresaInCaricoByTecnicoPreventivi = connection.prepareStatement("SELECT ID FROM richiestapresaincarico WHERE ID_tecnico_preventivi=? LIMIT ?, ?");
            iRichiestaPresaInCarico = connection.prepareStatement("INSERT INTO richiestapresaincarico(ID_tecnico_preventivi, ID_richiesta) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            uRichiestaPresaInCarico = connection.prepareStatement("UPDATE richiestapresaincarico SET ID_tecnico_preventivi=?, ID_richiesta=?, version=? WHERE ID=? AND version=?");
            dRichiestaPresaInCarico = connection.prepareStatement("DELETE FROM richiestapresaincarico WHERE ID=?");
        } catch (SQLException ex) {
            throw new DataException("Error initializing webmarket data layer", ex);
        }
    }

    @Override
    public void destroy() throws DataException {
        try {
            sRichiestaPresaInCaricoByID.close();
            iRichiestaPresaInCarico.close();
            sRichiestePresaInCaricoByTecnicoPreventivi.close();
            uRichiestaPresaInCarico.close();
            dRichiestaPresaInCarico.close();
        } catch (SQLException ex) {
            //
        }
        super.destroy();
    }

    @Override
    public RichiestaPresaInCarico createRichiestaPresaInCarico() {
        return new RichiestaPresaInCaricoProxy(getDataLayer());
    }

    private RichiestaPresaInCaricoProxy createRichiestaPresaInCarico(ResultSet rs) throws DataException{
        try {
            RichiestaPresaInCaricoProxy rpic = (RichiestaPresaInCaricoProxy) createRichiestaPresaInCarico();
            rpic.setKey(rs.getInt("ID"));
            rpic.setRichiesta_key(rs.getInt("ID_richiesta"));
            rpic.setTecnicoPreventivi_key(rs.getInt("ID_tecnico_preventivi"));
            rpic.setVersion(rs.getLong("version"));
            return rpic;
        } catch (SQLException ex) {
            throw new DataException("Unable to create RichiestaPresaInCarico object form ResultSet", ex);
        }
    }

    @Override
    public RichiestaPresaInCarico getRichiestaPresaInCarico(Integer key) throws DataException {
        RichiestaPresaInCarico rpic = null;
        if (dataLayer.getCache().has(RichiestaPresaInCarico.class, key)) {
            rpic = dataLayer.getCache().get(RichiestaPresaInCarico.class, key);
        } else {
            try {
                sRichiestaPresaInCaricoByID.setInt(1, key);
                try (ResultSet rs = sRichiestaPresaInCaricoByID.executeQuery()) {
                    if (rs.next()) {
                        rpic = createRichiestaPresaInCarico(rs);
                        dataLayer.getCache().add(RichiestaPresaInCarico.class, rpic);
                    }
                }
            } catch (SQLException ex) {
                throw new DataException("Unable to load RichiestaPresaInCarico by ID", ex);
            }
        }
        return rpic;
    }

    @Override
    public List<RichiestaPresaInCarico> getAllRichiestePresaInCaricoByTecnicoPreventivi(TecnicoPreventivi tecnicoPreventivi, Integer  page) throws DataException {
        List<RichiestaPresaInCarico> result = new ArrayList<>();
        try {
            //SELECT ID FROM richiestapresaincarico WHERE ID_tecnico_preventivi=? LIMIT ?, ?
            sRichiestePresaInCaricoByTecnicoPreventivi.setInt(1,tecnicoPreventivi.getKey());
            sRichiestePresaInCaricoByTecnicoPreventivi.setInt(2, page * offset);
            sRichiestePresaInCaricoByTecnicoPreventivi.setInt(3, offset);
            try (ResultSet rs = sRichiestePresaInCaricoByTecnicoPreventivi.executeQuery()) {
                while (rs.next()) {
                    result.add(getRichiestaPresaInCarico(rs.getInt("ID")));
                }
            }
            return result;
        } catch (SQLException ex) {
            throw new DataException("Unable to load RichiestePreseInCarico by TecnicoPreventivi", ex);
        }
    }

    @Override
    public void storeRichiestaPresaInCarico(RichiestaPresaInCarico richiestaPresaInCarico) throws DataException {
        try {
            if (richiestaPresaInCarico.getKey() != null && richiestaPresaInCarico.getKey() > 0) { //update
                if (richiestaPresaInCarico instanceof DataItemProxy && !((DataItemProxy) richiestaPresaInCarico).isModified()) {
                    return;
                }
                /*D_tecnico_preventivi=1,
                ID_richiesta=2,
                version=3 WHERE
                ID=4 AND
                version=5*/
                uRichiestaPresaInCarico.setInt(1, richiestaPresaInCarico.getTecnicoPreventivi().getKey());
                uRichiestaPresaInCarico.setInt(2, richiestaPresaInCarico.getRichiesta().getKey());

                long current_version = richiestaPresaInCarico.getVersion();
                long next_version = current_version + 1;

                uRichiestaPresaInCarico.setLong(3, next_version);
                uRichiestaPresaInCarico.setInt(4, richiestaPresaInCarico.getKey());
                uRichiestaPresaInCarico.setLong(5, current_version);

                if (uRichiestaPresaInCarico.executeUpdate() == 0) {
                    throw new OptimisticLockException(richiestaPresaInCarico);
                } else {
                    richiestaPresaInCarico.setVersion(next_version);
                }
            } else { //insert
                /*
                * ID_tecnico_preventivi=1
                *  ID_richiesta=2
                * */
                iRichiestaPresaInCarico.setInt(1, richiestaPresaInCarico.getTecnicoPreventivi().getKey());
                iRichiestaPresaInCarico.setInt(2, richiestaPresaInCarico.getRichiesta().getKey());

                if (iRichiestaPresaInCarico.executeUpdate() == 1) {
                    try (ResultSet keys = iRichiestaPresaInCarico.getGeneratedKeys()) {
                        if (keys.next()) {
                            int key = keys.getInt(1);
                            richiestaPresaInCarico.setKey(key);
                            dataLayer.getCache().add(RichiestaPresaInCarico.class, richiestaPresaInCarico);
                        }
                    }
                }
            }
            if (richiestaPresaInCarico instanceof DataItemProxy) {
                ((DataItemProxy) richiestaPresaInCarico).setModified(false);
            }
        } catch (SQLException | OptimisticLockException ex) {
            throw new DataException("Unable to store RichiestaPresaInCarico", ex);
        }
    }

    @Override
    public void deleteRichiestaPresaInCarico(RichiestaPresaInCarico richiestaPresaInCarico) throws DataException {
        try {
            //Lo cancello prima dalla cache
            dataLayer.getCache().delete(RichiestaPresaInCarico.class, richiestaPresaInCarico);

            dRichiestaPresaInCarico.setInt(1, richiestaPresaInCarico.getKey());
            dRichiestaPresaInCarico.executeUpdate();

        } catch (SQLException e) {
            throw new DataException("Unable to delete RichiestaPresaInCarico", e);
        }
    }
}
