package it.univaq.webmarket.data.DAO.impl;

import it.univaq.webmarket.data.DAO.RichiestaAcquistoDAO;
import it.univaq.webmarket.framework.data.DAO;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.data.DataLayer;
import it.univaq.webmarket.model.RichiestaAcquisto;

import java.util.List;

public class RichiestaAcquistoDAO_MySQL extends DAO implements RichiestaAcquistoDAO {


    public RichiestaAcquistoDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public void init() throws DataException {
        /*try {
            super.init();

            //precompiliamo tutte le query utilizzate nella classe
            //precompile all the queries uses in this class
            sArticleByID = connection.prepareStatement("SELECT * FROM article WHERE ID=?");
            sArticlesByIssue = connection.prepareStatement("SELECT ID AS articleID FROM article WHERE issueID=?");
            sArticles = connection.prepareStatement("SELECT ID AS articleID FROM article");
            sUnassignedArticles = connection.prepareStatement("SELECT ID AS articleID FROM article WHERE issueID IS NULL");

            //notare l'ultimo paametro extra di questa chiamata a
            //prepareStatement: lo usiamo per assicurarci che il JDBC
            //restituisca la chiave generata automaticamente per il
            //record inserito
            //note the last parameter in this call to prepareStatement:
            //it is used to ensure that the JDBC will sotre and return
            //the auto generated key for the inserted recors
            iArticle = connection.prepareStatement("INSERT INTO article (title,text,authorID,issueID,page) VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            uArticle = connection.prepareStatement("UPDATE article SET title=?,text=?,authorID=?,issueID=?, page=?, version=? WHERE ID=? and version=?");
            dArticle = connection.prepareStatement("DELETE FROM article WHERE ID=?");

        } catch (SQLException ex) {
            throw new DataException("Error initializing newspaper data layer", ex);
        }*/
    }

    @Override
    public void destroy() throws DataException {
        //anche chiudere i PreparedStamenent � una buona pratica...
        //also closing PreparedStamenents is a good practice...
         /*try {

           sArticleByID.close();

            sArticlesByIssue.close();
            sArticles.close();
            sUnassignedArticles.close();

            iArticle.close();
            uArticle.close();
            dArticle.close();

        } catch (SQLException ex) {
            //
        }
        super.destroy();*/
    }

    @Override
    public RichiestaAcquisto createRichiestaAcquisto() {
        return null;
    }

    @Override
    public RichiestaAcquisto getRichiestaAcquisto(int richiesta_key) throws DataException {
        return null;
    }

    @Override
    public List<RichiestaAcquisto> getAllRichiesteAcquisto() throws DataException {
        return List.of();
    }
}
