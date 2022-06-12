package com.mycompany.p5.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class for manipulating with the database
 */
public class DatabaseManipulator { //klassen der anvnedes til at skabe forbindelse til databasen
    
    /**
     * Method for connecting to the database. The method will return a
     * connection object if connection is established or null if no
     * connection could be made
     * @return A connection object or null
     */
    public static Connection getConnection(){
        
        Connection conn = null; //opretter en instans conn, der starter med at være null
        
        // Try to load the Driver. This is done for compatibility reasons
        try { // starter med at loade driveren
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Try to get the connection up and running
            try { // prøver at få forbindelsen op at kører
                conn = DriverManager.getConnection(DatabaseDetails.host, //hostnavn
                DatabaseDetails.username, //brugernavn til databasen
                DatabaseDetails.password); //adgangskode til databasen (ikke "heartsmartshare01")
            }
            catch (SQLException sqlex) { //fejlmeddelser(SQL fejl) herefter printer den dem ud, hvis der findes nogle
                System.out.println(sqlex.getMessage());
            }
            
        }
        catch (ClassNotFoundException ex){ //fejlmeddelse, hvis en klasse ikke er fundet
            System.out.println(ex.getMessage());
        }
        
        // Return the connection object
        return conn;
    }
    
    /**
     * Method for making a SQL query to the database. 
     * @param sqlStatement The SQL statement as a String
     * @param queryable An Queryable object that handles the result set from the database (@see {@link Queryable})
     */
    public static void executeQueryWithResultSet(String sqlStatement, Queryable queryable){
        // Get the connection object
        Connection conn = getConnection();
        
        Statement stmt = null;  //laver et statement og sætter den til null
        ResultSet rs = null; //laver et resultset og sætter den til null
        
        if (conn != null){ //hvis conn ikke er nul, så skal der prøves at blive lavet et statement og hente resultset fra databasen
            try {
                
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sqlStatement);
                
                // Do something about the result
                queryable.processResultSet(rs);
                
            }
            catch (SQLException ex){ //fejlmeddelser
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
            finally {
                // it is a good idea to release
                // resources in a finally{} block
                // in reverse-order of their creation
                // if they are no-longer needed
                
                // Close result set
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException sqlEx) { 
                        // handle any errors
                        System.out.println("SQLException: " + sqlEx.getMessage());
                        System.out.println("SQLState: " + sqlEx.getSQLState());
                        System.out.println("VendorError: " + sqlEx.getErrorCode());
                    }
                    
                    rs = null;
                }
                
                // Close statement
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException sqlEx) { 
                        // handle any errors
                        System.out.println("SQLException: " + sqlEx.getMessage());
                        System.out.println("SQLState: " + sqlEx.getSQLState());
                        System.out.println("VendorError: " + sqlEx.getErrorCode());
                    }
                    
                    stmt = null;
                }
            }
        }
    }
    
    /**
     * Overloading method for execute query with result set. 
     * This method only uses the Queryable object
     * @param queryable A Queryable object
     */
    public static void executeQueryWithResultSet(Queryable queryable){
        executeQueryWithResultSet(queryable.returnSqlQuery(), queryable); 
    }

    /**
     * Method for making a SQL UPDATE query to the database. 
     * @param sqlStatement The SQL UPDATE statement as a String
     * @param queryable An Queryable object that handles the result set from the database (@see {@link Queryable})
     */
    public static void executeUpdateQuery(String sqlStatement, Queryable queryable){
        // Get the connection object
        Connection conn = getConnection();
        
        Statement stmt = null;
        
        if (conn != null){
            try {
                
                stmt = conn.createStatement();
                stmt.executeUpdate(sqlStatement);
                                
            }
            catch (SQLException ex){
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
            finally {
                // it is a good idea to release
                // resources in a finally{} block
                // in reverse-order of their creation
                // if they are no-longer needed
                
                // Close statement
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException sqlEx) { 
                        // handle any errors
                        System.out.println("SQLException: " + sqlEx.getMessage());
                        System.out.println("SQLState: " + sqlEx.getSQLState());
                        System.out.println("VendorError: " + sqlEx.getErrorCode());
                    }
                    
                    stmt = null;
                }
            }
        }
    }
    
    /**
     * Overloading method for execute UPDATE query with result set. 
     * This method only uses the Queryable object
     * @param queryable A Queryable object
     */
    public static void executeUpdateQuery(Queryable queryable){
        executeUpdateQuery(queryable.returnSqlUpdate(), queryable); 
    }

    /**
     * Method for executing a INSERT query for a Queryable object
     * @param queryable A Queryable object 
     */
    public static void executeInsertQuery(Queryable queryable){
        executeUpdateQuery(queryable.returnSqlQuery(), queryable); 
    }
    
}