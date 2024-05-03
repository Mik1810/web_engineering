package it.univaq.webmarket.business.jdbc;

import com.mysql.cj.PreparedQuery;
import com.mysql.cj.x.protobuf.MysqlxPrepare;


import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Query_JDBC {

    private Connection connection;
    private boolean supports_procedures;
    private boolean supports_function_calls;

    public Query_JDBC(Connection c) throws ApplicationException {
        connect(c);
    }

    public final void connect(Connection c) throws ApplicationException {
        disconnect();
        this.connection = c;
        //verifichiamo quali comandi supporta il DBMS corrente
        supports_procedures = false;
        supports_function_calls = false;
        try {
            supports_procedures = connection.getMetaData().supportsStoredProcedures();
            supports_function_calls = supports_procedures && connection.getMetaData().supportsStoredFunctionsUsingCallSyntax();
        } catch (SQLException ex) {
            Logger.getLogger(Query_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void disconnect() throws ApplicationException {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                System.out.println("\n**** CHIUSURA CONNESSIONE (modulo query) ************");
                this.connection.close();
                this.connection = null;
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Errore di disconnessione", ex);
        }
    }


    //********************QUERY***************************//


}
