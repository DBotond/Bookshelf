/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookshelf.controller;

import bookshelf.logger.Logger;
import bookshelf.logger.Logger.LogLevel;
import static bookshelf.logger.Logger.consoleLogger;
import static bookshelf.logger.Logger.fileLogger;
import bookshelf.model.Book;
import bookshelf.model.BookBuilder;
import bookshelf.model.BookModel;
import bookshelf.model.LoginModel;
import bookshelf.model.User;
import bookshelf.view.BookView;
import bookshelf.view.LoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BookController {
    User loggedInUser;
    private BookView view;
    private BookModel model;
    DefaultTableModel tmodel;
    Logger logger = consoleLogger(LogLevel.all())
                .appendNext(fileLogger(LogLevel.WARNING, LogLevel.ERROR));
    
    
    
    /*
    BookController osztály konstruktora
    Bekéri a view-ot a model-t és a bejelentkezett user-t
    Beállítja a local változóknak az értékeit
    Láthatóságot beállítja true-ra (hogy látható legyen a view)
    SetWelcomeUser köszönti a beloggolt user-t    
    */
    
    public BookController(BookView view, BookModel model, User u) {
        this.view = view;
        this.model = model;
        this.loggedInUser = u;
        view.AddInsertBookActionListener(new BookController.InsertBookListener());
        view.setVisible(true);
        view.SetWelcomeUser(u.getUsername());
        ArrayList<Book> books = new ArrayList<>();
        books = model.getUserBooks(loggedInUser.getUserId());
        tmodel = model.populateTable(books, loggedInUser);
        view.SetTableModel(tmodel);
        logger.message("Book view, model létrehozva. Beloggolt user id-ja: " + u.getUserId(), LogLevel.INFO);
    }
    
    
    /*
    InsertBookListener metódus mely elvégzi a view-gombjára kattintva a feladatokat:
    - megkapja a view-ból a felhasználó éltal bevitt adatokat
    - Builder létrehozza ezáltal a könyvet
    - insertToDb metódus berakja a modellen keresztül az adatbázisba a könyvet
    - getUserBooks metódus moddel neresztül lekéri a bejelentkezett user összes könyvét egy ArrayList-be
    - populateTable metódus létrehozza a tábla modell-t és feltölti azt, a lekért adatokkal
    - SetTableModel beállítja a view-on a már létrehozott tmodel tábla modell-t    
    */
    class InsertBookListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = view.GetTitle();
            String author = view.GetAuthor();
            String releaseDate = view.GetReleaseDate();             
            if (!(title.equals("") && author.equals("") && releaseDate.equals(""))) {
               BookBuilder builder = new BookBuilder();
               Book book = builder.setAuthor(author).setTitle(title).setReleaseDate(releaseDate).Build();            
               model.insertToDb(book,loggedInUser);        
               JOptionPane.showMessageDialog(null,"Könyv felrakva a könyvespolcra!");
               logger.message("Könyv felrakva a könyvespolcra!", LogLevel.INFO);
               ArrayList<Book> books = new ArrayList<>();
               books = model.getUserBooks(loggedInUser.getUserId());
               tmodel = model.populateTable(books, loggedInUser);
               view.SetTableModel(tmodel);
            }
            else {
                JOptionPane.showMessageDialog(null,"Meg kell adni a könyv adatait!");
                logger.message("Könyv adatai hiányosan lettek megadva, nem kerülhet az adatbázisba!", LogLevel.WARNING);
            }
       
        }
    }

    
    
}
