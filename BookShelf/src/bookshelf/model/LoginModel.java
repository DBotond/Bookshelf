/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookshelf.model;
import static bookshelf.model.MyConnection.getConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class LoginModel {
    /*
    Lokális User típusú változó a beloggolt user tárolásához
    */
    private User LoggedInUser = new User();
    
    public User getLoggedInUser(){
        return LoggedInUser;
    }
    /*
    Metódus, mely a view-ből kapott username és password Stringek alapján megpróbál bejelentkezni az adatbázisban lévő adatok összehasonlításával
    Boolean típusú, tehát ha sikerül a bejelentkezés true értékkel tér vissza, ellenkező esetben false.
    */
    public boolean tryLogin(String username, String password){ 
        
        boolean canlogin = false;
        /*
        Query string sql parancsot tárolja
        */
        String query = "Select * from user where username=? and password=?";
        PreparedStatement pst;
        /*
        Try-catch blokk az sql kapcsolat sikerességének és a lefuttatott parancs vizsgálatához
        */
        try {                  
                pst = getConnection().prepareStatement(query);                
                pst.setString(1, username);
                pst.setString(2, password);
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    canlogin = true;
                    /*
                    Ha van ilyen user az adatbázisba, beállítja a loggedInUser User objektum értékeit
                    */
                    LoggedInUser.setUserId(rs.getInt("userId"));
                    LoggedInUser.setUsername(username);
                    LoggedInUser.setPassword(password);
                }
                else 
                {
                    canlogin = false;
                }
                
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        /*
        canlogin boolean típusú változó visszatérése, mely true vagy false, bejelentkezés sikerességétől függően
        */
        return canlogin;    
    
    }
    
}
