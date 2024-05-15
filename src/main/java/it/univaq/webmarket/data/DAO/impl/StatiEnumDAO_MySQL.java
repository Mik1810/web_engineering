package it.univaq.webmarket.data.DAO.impl;

import it.univaq.webmarket.data.DAO.StatiEnumDAO;
import it.univaq.webmarket.data.model.enums.Feedback;
import it.univaq.webmarket.data.model.enums.StatoConsegna;
import it.univaq.webmarket.data.model.enums.StatoEnum;
import it.univaq.webmarket.data.model.enums.StatoProposta;
import it.univaq.webmarket.data.model.impl.enums.StatoPropostaImpl;
import it.univaq.webmarket.data.model.impl.proxy.enums.FeedbackProxy;
import it.univaq.webmarket.data.model.impl.proxy.enums.StatoConsegnaProxy;
import it.univaq.webmarket.data.model.impl.proxy.enums.StatoPropostaProxy;
import it.univaq.webmarket.framework.data.DAO;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.data.DataLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatiEnumDAO_MySQL extends DAO implements StatiEnumDAO {

    private PreparedStatement sStatoPropostaByID;
    private PreparedStatement sStatiProposta;
    private PreparedStatement sStatoConsegnaByID;
    private PreparedStatement sStatiConsegna;
    private PreparedStatement sFeedbackByID;
    private PreparedStatement sFeedback;

    public StatiEnumDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public void init() throws DataException {
        try {
            super.init();

            sStatoPropostaByID = connection.prepareStatement("SELECT * FROM statoproposta WHERE ID=?");
            sStatiProposta = connection.prepareStatement("SELECT ID FROM statoproposta");
            sStatoConsegnaByID = connection.prepareStatement("SELECT * FROM statoconsegna WHERE ID=?");
            sStatiConsegna = connection.prepareStatement("SELECT ID FROM statoconsegna");
            sFeedbackByID = connection.prepareStatement("SELECT * FROM feedback WHERE ID=?");
            sFeedback = connection.prepareStatement("SELECT ID FROM feedback");

        } catch (SQLException ex) {
            throw new DataException("Error initializing webmarket data layer", ex);
        }
    }

    @Override
    public void destroy() throws DataException {
        try {
            sStatoPropostaByID.close();
            sStatiProposta.close();
            sStatoConsegnaByID.close();
            sStatiConsegna.close();
            sFeedbackByID.close();
            sFeedback.close();
        } catch (SQLException ex) {
            //
        }
        super.destroy();
    }

    @Override
    public StatoProposta createStatoProposta() {
        return new StatoPropostaProxy(getDataLayer());
    }

    @Override
    public StatoConsegna createStatoConsegna() {
        return new StatoConsegnaProxy(getDataLayer());
    }

    @Override
    public Feedback createFeedback() {
        return new FeedbackProxy(getDataLayer());
    }

    private StatoPropostaProxy createStatoProposta(ResultSet rs) throws DataException {
        try {
            StatoPropostaProxy sp = new StatoPropostaProxy(getDataLayer());
            sp.setKey(rs.getInt("ID"));
            sp.setVersion(rs.getLong("version"));
            sp.setNome(rs.getString("nome"));
            return sp;
        } catch (SQLException ex) {
            throw new DataException("Unable to create StatoProposta object form ResultSet", ex);
        }
    }

    private StatoConsegnaProxy createStatoConsegna(ResultSet rs) throws DataException {
        try {
            StatoConsegnaProxy sc = new StatoConsegnaProxy(getDataLayer());
            sc.setKey(rs.getInt("ID"));
            sc.setVersion(rs.getLong("version"));
            sc.setNome(rs.getString("nome"));
            return sc;
        } catch (SQLException ex) {
            throw new DataException("Unable to create StatoConsegna object form ResultSet", ex);
        }
    }

    private FeedbackProxy createFeedback(ResultSet rs) throws DataException {
        try {
            FeedbackProxy f = new FeedbackProxy(getDataLayer());
            f.setKey(rs.getInt("ID"));
            f.setVersion(rs.getLong("version"));
            f.setNome(rs.getString("nome"));
            return f;
        } catch (SQLException ex) {
            throw new DataException("Unable to create Feedback object form ResultSet", ex);
        }
    }

    @Override
    public StatoProposta getStatoProposta(int statoProposta_key) throws DataException {
        StatoProposta sp = null;
        if (dataLayer.getCache().has(StatoProposta.class, statoProposta_key)) {
            sp = dataLayer.getCache().get(StatoProposta.class, statoProposta_key);
        } else {
            try {
                sStatoPropostaByID.setInt(1, statoProposta_key);
                try (ResultSet rs = sStatoPropostaByID.executeQuery()) {
                    if (rs.next()) {
                        sp = createStatoProposta(rs);
                        dataLayer.getCache().add(StatoProposta.class, sp);
                    }
                }
            } catch (SQLException ex) {
                throw new DataException("Unable to load Stato Proposta by ID", ex);
            }
        }
        return sp;
    }

    @Override
    public StatoConsegna getStatoConsegna(int statoConsegna_key) throws DataException {
        StatoConsegna sc = null;
        if (dataLayer.getCache().has(StatoConsegna.class, statoConsegna_key)) {
            sc = dataLayer.getCache().get(StatoConsegna.class, statoConsegna_key);
        } else {
            try {
                sStatoConsegnaByID.setInt(1, statoConsegna_key);
                try (ResultSet rs = sStatoConsegnaByID.executeQuery()) {
                    if (rs.next()) {
                        sc = createStatoConsegna(rs);
                        dataLayer.getCache().add(StatoConsegna.class, sc);
                    }
                }
            } catch (SQLException ex) {
                throw new DataException("Unable to load Stato Consegna by ID", ex);
            }
        }
        return sc;
    }

    @Override
    public Feedback getFeedback(int feedback_key) throws DataException {
        Feedback f = null;
        if (dataLayer.getCache().has(Feedback.class, feedback_key)) {
            f = dataLayer.getCache().get(Feedback.class, feedback_key);
        } else {
            try {
                sFeedbackByID.setInt(1, feedback_key);
                try (ResultSet rs = sFeedbackByID.executeQuery()) {
                    if (rs.next()) {
                        f = createFeedback(rs);
                        dataLayer.getCache().add(Feedback.class, f);
                    }
                }
            } catch (SQLException ex) {
                throw new DataException("Unable to load Feedback by ID", ex);
            }
        }
        return f;
    }

    @Override
    public List<StatoProposta> getAllStatiProposta() throws DataException {
        List<StatoProposta> result = new ArrayList<>();

        try (ResultSet rs = sStatiProposta.executeQuery()) {
            while (rs.next()) {
                result.add(getStatoProposta(rs.getInt("ID")));
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load Stati Proposta", ex);
        }
        return result;
    }

    @Override
    public List<StatoConsegna> getAllStatiConsegna() throws DataException {
        List<StatoConsegna> result = new ArrayList<>();

        try (ResultSet rs = sStatiConsegna.executeQuery()) {
            while (rs.next()) {
                result.add(getStatoConsegna(rs.getInt("ID")));
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load Stati Consegna", ex);
        }
        return result;
    }

    @Override
    public List<Feedback> getAllFeedback() throws DataException {
        List<Feedback> result = new ArrayList<>();

        try (ResultSet rs = sFeedback.executeQuery()) {
            while (rs.next()) {
                result.add(getFeedback(rs.getInt("ID")));
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load Feedback", ex);
        }
        return result;
    }
}
