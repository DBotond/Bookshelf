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


public class UserTest {
    
    public UserTest() {
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
    public void testGetUserId() {
        System.out.println("getUserId");
        User instance = new User();
        instance.setUserId(0);
        int expResult = 0;
        int result = instance.getUserId();
        assertEquals(expResult, result);
        
    }
    
    @Test
    public void testSetUserId() {
        System.out.println("setUserId");
        User instance = new User();
        instance.setUserId(0);        
        int expResult = 0;
        int result = instance.getUserId();
        assertEquals(expResult, result);
        
    }

}
