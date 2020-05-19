package bookshelf.controller;


import bookshelf.controller.LoginController;
import bookshelf.logger.Logger;
import bookshelf.logger.Logger.LogLevel;
import static bookshelf.logger.Logger.consoleLogger;
import bookshelf.model.BookBuilder;
import bookshelf.model.BookModel;
import bookshelf.model.LoginModel;
import bookshelf.view.LoginView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




public class BookShelf {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Logger logger = consoleLogger(LogLevel.all());
       logger.message("A program működése elkezdődött.", LogLevel.INFO);
       /*
        Program működésének megkezdése, login view, modell és controller létrehozása
        */
       LoginView view = new LoginView();
       LoginModel model = new LoginModel();
        
        LoginController controller =
                new LoginController(view, model);
        
        
    }
    
}
