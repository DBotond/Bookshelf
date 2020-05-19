package bookshelf.model;

import bookshelf.logger.Logger;
import static bookshelf.logger.Logger.consoleLogger;
import static bookshelf.logger.Logger.fileLogger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * 
 * Adatbázis kapcsolat definiálásra szolgáló osztály, statikus mindenhonnan elérhető getConnection metódussal 
 */
public class MyConnection {
  
    public static Connection getConnection(){
        Logger logger = consoleLogger(Logger.LogLevel.all())
                .appendNext(fileLogger(Logger.LogLevel.WARNING, Logger.LogLevel.ERROR));
        
        
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "");
        properties.setProperty("useSSL", "false");
        
        Connection con = null;
        try{
            /*
            Driver beállítása
            */
            Class.forName("com.mysql.jdbc.Driver");
            /*
            Adatbázis adatainak, elérhetőségének megadása
            */
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelf", properties);
        }
        catch (Exception ex){
            logger.message("Nem sikerült elérni az adatbázist!", Logger.LogLevel.ERROR);
            
        }
        return con;
        
    }
}
