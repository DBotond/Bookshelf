/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookshelf.model;

/**
 *
 * Book osztály mely a book példány elkészítéséhez szükséges változókat, propertyket tartalmazza
 */
public class Book {
    private int userId;
    private int bookId;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
   
    private String author;
    private String title;
    private String releaseDate;
    
    
     public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    
    /*
    Konstruktorok a könyv osztályhoz, alapértékek beállításához
    */
    
    public Book(){
        this.author = "";
        this.title = "";
        this.releaseDate = "";
    }
    
    public Book(String author, String title, String releaseDate){
        this.author = author;
        this.title = title;
        this.releaseDate = releaseDate;
    }
}
