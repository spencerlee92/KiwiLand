/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 * This class handles database operations
 *  
 * @author Chaitanya Varma
 * @version April 2017
 */
public class Database {
    
    
    private Connection connection = null;
    
    
    public static String dbUrl = "";
    public static String userName = "";
    public static String password = "";
        
    /**
     * Constructor. Reads database connection properties from kiwiIsland.cfg file.
     */
    public Database()
    {
        try
        {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File("kiwiIsland.cfg")));
            
            if(!(properties.getProperty("dburl")==null || properties.getProperty("dburl").equals("")))
            {
		dbUrl = properties.getProperty("dburl");		
            }
            if(!(properties.getProperty("username")==null || properties.getProperty("username").equals("")))
            {
		userName = properties.getProperty("username");
            }
            if(!(properties.getProperty("password")==null || properties.getProperty("password").equals("")))
            {
		password = properties.getProperty("password");
            }
        }
        catch(Exception ex)
        {
            java.util.logging.Logger.getLogger(Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Opens a MySQL database connection
     */
    public void openConnection()
    {
        try
        {
            String dbDriverURL = "com.mysql.jdbc.Driver";
            Class.forName(dbDriverURL);
            
            connection = DriverManager.getConnection(dbUrl, userName, password);
        }
        catch(Exception ex)
        {
            java.util.logging.Logger.getLogger(Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Gets facts from database for specified occupant
     * @param occupant occupant like Kiwi or Rat etc
     */
    public ArrayList<String> getFacts(String occupant)
    {
        ArrayList<String> facts = null;
        try
        {
           String sqlQuery = "SELECT fact FROM facts Where occupant='" + occupant +"'";
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery(sqlQuery);
           facts = new ArrayList<String>();
           while(resultSet.next())
           {
               facts.add(resultSet.getString(1));
           }
           resultSet.close();
           statement.close();           
        }
        catch(Exception ex)
        {
            java.util.logging.Logger.getLogger(Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return facts;
    }
    
    /**
     * Gets all different occupants from the database
     */
    public ArrayList<String> getOccupants()
    {
        ArrayList<String> occupants = null;
        try
        {
           String sqlQuery = "SELECT DISTINCT occupant FROM facts";
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery(sqlQuery);
           occupants = new ArrayList<String>();
           while(resultSet.next())
           {
               occupants.add(resultSet.getString(1));
           }
           resultSet.close();
           statement.close();           
        }
        catch(Exception ex)
        {
            java.util.logging.Logger.getLogger(Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return occupants;
    }
    
    /**
     * Closes database connection
     */
    public void closeConnection()
    {
        try
        {
            if(connection != null)
                connection.close();
        }
        catch(Exception ex)
        {
            java.util.logging.Logger.getLogger(Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
