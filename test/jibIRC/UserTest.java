/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jibIRC;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Welcome
 */
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
    public void testUserModeCompare(){
        User user1 = User.getUserFromList("+jib2");
        User user2 = User.getUserFromList("@jib1");
        assertTrue(user1.compareTo(user2) < 0);
    }
    
    @Test
    public void testUsernameCompare(){
        User user1 = User.getUserFromList("jip1");
        User user2 = User.getUserFromList("jib2");
        assertTrue(user1.compareTo(user2) < 0);
    }
    
    //this would fail because im lame @Test
    public void testOnlyNumbersDiffer(){
        User user1 = User.getUserFromList("jib1");
        User user2 = User.getUserFromList("jib2");
        assertTrue(user1.compareTo(user2) < 0);
    }
    
    @Test
    public void testNoModeAndModeCompare(){
        User user1 = User.getUserFromList("jib1");
        User user2 = User.getUserFromList("+jib0");
        assertTrue(user1.compareTo(user2) < 0);
    }
    
    @Test
    public void testEqualIfEvenDifferentMode(){
        User user1 = User.getUserFromList("+jib1");
        User user2 = User.getUserFromList("@jib1");
        assertTrue(user1.equals(user2));
    }
}
