/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookshelf.controller;

import bookshelf.logger.Logger;
import static bookshelf.logger.Logger.consoleLogger;
import static bookshelf.logger.Logger.fileLogger;
import bookshelf.model.BookModel;
import bookshelf.model.LoginModel;
import bookshelf.model.User;
import bookshelf.view.BookView;
import bookshelf.view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;



public class LoginController {
    
    private LoginView view;
    private LoginModel model;
    Logger logger = consoleLogger(Logger.LogLevel.all())
                .appendNext(fileLogger(Logger.LogLevel.WARNING, Logger.LogLevel.ERROR));
    
    
    public LoginController(LoginView view, LoginModel model){
       
        this.view = view;
        this.model = model;
        
        view.AddLoginActionListener(new LoginListener());
        view.setVisible(true);
        
        logger.message("Login view, model létrehozva.", Logger.LogLevel.INFO);
    }
    class LoginListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = view.GetUsername();
            String password = view.GetPassword();
            
            if (model.tryLogin(username, password)){
                User u = model.getLoggedInUser();
                BookView bookview = new BookView();
                BookModel bookmodel = new BookModel();
                
                BookController controller =
                new BookController(bookview,bookmodel,u);
                
                view.setVisible(false);
                logger.message("Sikeres bejelentkezés! Usernév: " + u.getUsername(), Logger.LogLevel.INFO);
            }
            else 
            {
                JOptionPane.showMessageDialog(null, "A felhasználónév és/vagy jelszó nem egyezik!");
                logger.message("A felhasználónévnek és jelszónak nem megfelelő adatok lettek megadva.", Logger.LogLevel.WARNING);
            }
        }
    
}
}
