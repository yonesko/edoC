package com.company;

import oracle.jdbc.pool.OracleDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.SQLException;

/**
 * Created by Glebushka on 19.10.2015.
 */
public class DataSource {

    static Context ctx;
    static OracleDataSource glebDS;

    static {
        try {
            glebDS = getOracleDataSource();
            ctx = new InitialContext();
            ctx.bind("jdbc/glebDB", glebDS);
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }

    private static OracleDataSource getOracleDataSource() throws SQLException {
        OracleDataSource oracleDS = null;
        try {
            oracleDS = new OracleDataSource();
            oracleDS.setURL("jdbc:oracle:thin:gleb/1@//localhost:1521/XE");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oracleDS;
    }

}
