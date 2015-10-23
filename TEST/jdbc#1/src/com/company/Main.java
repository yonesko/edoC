package com.company;

import oracle.jdbc.pool.OracleDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws SQLException, NamingException {
        Context ctx = new InitialContext();
        OracleDataSource ds = (OracleDataSource) ctx.lookup("jdbc/glebDB");
        Locale.setDefault(Locale.US);
        Connection con = ds.getConnection();

        CallableStatement cs = con.prepareCall("CREATE TABLE X (x INT)");
        cs.execute();
    }
}
