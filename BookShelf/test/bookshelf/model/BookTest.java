/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookshelf.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {
    
    public BookTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testBookVariablesReferToDifferentObject(){
        Book b = new Book();
        
        b.setAuthor("Gipsz Jakab");
        b.setTitle("Winnetou");
        b.setReleaseDate("2020");
        
        Book b2 = new Book();
        
        b2.setAuthor("Gipsz Jakab");
        b2.setTitle("Winnetou");
        b2.setReleaseDate("2020");
        
        
        assertNotSame(b,b2);
    }
    
    @Test
    public void testBookObjectIsNotNull(){
        Book b = new Book();
        
        b.setAuthor("Gipsz Jakab");
        b.setTitle("Winnetou");
        b.setReleaseDate("2020");
        
        assertNotNull(b);
    }
    
}
