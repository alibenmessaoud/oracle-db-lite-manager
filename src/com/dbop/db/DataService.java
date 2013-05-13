package com.dbop.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Database object to load drivers and perform queries
 * @author Ali Ben Messaoud blog.salamtura.com
 */
public class DataService {

    /**
     * create Database object
     */
    public DataService() {
    }
    

    /**
     * to load the database base driver
     * @return a database connection
     * @throws SQLException throws an exception if an error occurs
     */
    public static Connection loadDriver(String user, String pwd) throws SQLException {
    	String Driver = "oracle.jdbc.driver.OracleDriver";
    	String ConnectionString = "jdbc:oracle:thin:@localhost:1521:xe";
    	try {
            Class.forName(Driver);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    	Connection con = DriverManager.getConnection(ConnectionString, user, pwd);
        return con;
    }

    /**
     * to get a result set of a query
     * @param query custom query
     * @return a result set of custom query
     * @throws SQLException throws an exception if an error occurs
     */
    public static ResultSet getResultSet(String user, String pwd, String query) throws SQLException {
        Connection con = loadDriver(user, pwd);
        ResultSet rs;
        PreparedStatement st = con.prepareStatement(query.toUpperCase());
        rs = st.executeQuery();

        return rs;
    }

    /**
     * to run an update query such as update, delete
     * @param query custom query
     * @return 
     * @throws SQLException throws an exception if an error occurs
     */
    public static int runQuery(String user, String pwd, String query) throws SQLException {
        Connection con = loadDriver(user, pwd);
        ResultSet rs;
        PreparedStatement st = con.prepareStatement(query.toUpperCase());
        int b = st.executeUpdate();
        return b;
    }
    
    
    public static int nbOfResultSet(String user, String pwd, String query, String columnName) throws SQLException {
    	ResultSet rsTBS = getResultSet(user, pwd, query.toUpperCase());
    	System.out.println(query.toUpperCase());
    	int rs = 0;
		while (rsTBS.next()) 
		{			
			rs = rsTBS.getInt(columnName);
		}
    	return rs;
    }
}