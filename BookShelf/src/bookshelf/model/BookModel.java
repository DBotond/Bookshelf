/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookshelf.model;


import bookshelf.logger.Logger;
import static bookshelf.logger.Logger.consoleLogger;
import static bookshelf.logger.Logger.fileLogger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class BookModel {
   
    Logger logger = consoleLogger(Logger.LogLevel.all())
                .appendNext(fileLogger(Logger.LogLevel.WARNING, Logger.LogLevel.ERROR));
    
    /*
    Könyv db-be való tárolására szolgáló metódus
    */
    public void insertToDb(Book book, User user) {
        
        /*
        Query string amely az SQL parancsot tárolja
        */
      String query = " insert into book (userId, author, title, releaseDate)"
        + " values (?, ?, ?, ?)";  
        /*
        Kötelező try-catch blokk, az adatbázis kapcsolat létrehozásához, és a mysql insert preparedStatement futtatásához
        */
        try {
            PreparedStatement preparedStmt = MyConnection.getConnection().prepareStatement(query); 
            // mysql preparedStatement beállítása a beloggolt user és a létrehozott könyv példány alapján           
            preparedStmt.setInt (1, user.getUserId());
            preparedStmt.setString (2, book.getAuthor());
            preparedStmt.setString   (3, book.getTitle());
            preparedStmt.setString(4, book.getReleaseDate());
            // preparedstatement végrehajtása
            preparedStmt.execute();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
                logger.message(e.getMessage(), Logger.LogLevel.ERROR);
            }
        logger.message("A mysql parancs lefuttatva. A könyv az adatbázisba rakva. userId: " 
                + user.getUserId() + ", author: " + book.getAuthor() + ", title: " + book.getTitle() + 
                ", releaseDate: " + book.getReleaseDate(), Logger.LogLevel.INFO);
    }
    
    /*
    Beloggolt user könyveinek lekérése szolgáló metódus userId alapján
    */
    public ArrayList<Book> getUserBooks(int userId){
        ArrayList<Book> books = new ArrayList<>();
        /*
        Query string a mysl select paranccsal
        */
        String query = "Select * from book where userId = ?";
        PreparedStatement preparedStmt;
        /*
        ResultSet tárolja a db-ből lekérdezett adatokat
        */
        ResultSet rs;
        /*
        Kötelező try-catch blokk, az adatbázis kapcsolat létrehozásához, és a mysql insert preparedStatement futtatásához
        */
        try {
            preparedStmt = MyConnection.getConnection().prepareStatement(query);
            preparedStmt.setInt (1,userId);            
            rs = preparedStmt.executeQuery();
            /*
              Végigmegyünk a resultset-en és kinyerjük a megfelelő adatokat egy-egy rekordból, majd beállítjuk a létrehozott Book
                b példánynak
            */
            while(rs.next()){                  
                Book b = new Book();
                b.setBookId(rs.getInt("bookId"));                
                b.setAuthor(rs.getString("author"));                
                b.setTitle(rs.getString("title"));                
                b.setUserId(rs.getInt("userId"));                
                b.setReleaseDate(rs.getString("releaseDate"));                
                books.add(b);
            }
            
        }
        catch(SQLException e){
            logger.message(e.getMessage(), Logger.LogLevel.ERROR);
        }
        /*
        Visszatérünk a books listával melybe belepakoltuk a rekordonként létrehozott könyveket
        */
        return books;
    }
    
    /*
    Tábla modell típusú metódus ami bekér egy könyv ArrayList-et és egy beloggolt user-t.
    Feltölti a táblát a megfelelő oszlopcímekkel és adatokkal    
    */    
    public DefaultTableModel populateTable(ArrayList<Book> books, User loggedInUser){
    books = getUserBooks(loggedInUser.getUserId());                
    String col[] = {"Könyv azonosító:","Könyv címe:","Szerző:", "Kiadás ideje:"};
    DefaultTableModel tableModel = new DefaultTableModel(col, 0);
    for (int i = 0; i < books.size(); i++) {
        int tmpBookId = books.get(i).getBookId();
        String tmpTitle = books.get(i).getTitle();
        String tmpAuthor = books.get(i).getAuthor();
        String tmpReleaseDate = books.get(i).getReleaseDate();

        Object[] data = {tmpBookId,tmpTitle,tmpAuthor,tmpReleaseDate};
        tableModel.addRow(data);
    }
    logger.message("Tábla feltöltése adatokkal sikeres volt.", Logger.LogLevel.INFO);
    return tableModel;
    }
    
}
