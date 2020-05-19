/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookshelf.model;

/**
 *
 * Builder osztály a könyv osztályhoz az Építő tervezési minta alapján
 * felépíti a user által megadott adatok alapján a könyvet
 */
public class BookBuilder {
    private Book book;
    
    public BookBuilder setUserId(int userId){
        this.book.setUserId(userId);
        return this;
    }
    public BookBuilder setAuthor(String author){
        this.book.setAuthor(author);
        return this;
    }
    public BookBuilder setTitle(String title){
        this.book.setTitle(title);
        return this;
    }
    
    public BookBuilder setReleaseDate(String releaseDate){
        this.book.setReleaseDate(releaseDate);
        return this;
    }
    /*
    Build metódus mely visszaadja a privát book objektumot
    */
    public Book Build(){
        return book;
    }
    /*
    Konstruktor mely létrehozza a könyvet
    */
    public BookBuilder(){
        this.book = new Book();
    }
}
