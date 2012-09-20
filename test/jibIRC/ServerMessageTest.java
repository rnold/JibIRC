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
public class ServerMessageTest {
    
    public ServerMessageTest() {
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
    public void testUsers(){
        ServerMessage message = new ServerMessage("", "USERS", "#GameReaper :+jibril @cookies");
        assertTrue(message.isUserList());
    }
    

    
    @Test
    public void testIfJoinMessage(){
        ServerMessage message = new ServerMessage("JibTest", "JOIN", "#hibob");
        if(!message.isJoinChannel()){
            fail();
        }
    }
    
    @Test
    public void testIfPartMessage(){
        ServerMessage message = new ServerMessage("JibTest", "PART #hibob", "");
        if(!message.isLeaveChannel("JibTest")){
            fail();
        }
    }
    
    @Test
    public void testPing(){
        ServerMessage message = new ServerMessage("", "PING", "razorzedge.relic.net");
        assertTrue(message.isPing());
    }
}
