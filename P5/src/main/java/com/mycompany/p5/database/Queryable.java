package com.mycompany.p5.database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implement this interface when calling the {@link DatabaseManipulator#executeQueryWithResultSet(Queryable)} method
 */
public interface Queryable { //er et interface med tomme metoder
        
    /**
     * Method for processing a ResultSet from a MySQL database.
     * @param rs ResultSet from a MySQL database to be processed
     * @throws SQLException The SQL Exception from the MySQL Driver
     */
    void processResultSet(ResultSet rs) throws SQLException;
    
    /**
     * Return SQL Query String
     * @return String with a SQL Query
     */
    String returnSqlQuery();

    /**
     * Return SQL Update Query String 
     * @return String with a SQL Update Query
     */
    String returnSqlUpdate();

}