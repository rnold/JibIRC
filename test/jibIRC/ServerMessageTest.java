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
    public void testIfJoinMessage(){
        ServerMessage message = new ServerMessage("JibTest", "JOIN", "#hibob");
        if(!message.isJoinChannel()){
            fail();
        }
    }
    
    @Test
    public void testIfPartMessage(){
        ServerMessage message = new ServerMessage("JibTest", "PART #hibob", "");
        if(!message.isLeaveChannel()){
            fail();
        }
    }
    
    @Test
    public void testIfPartMessage2(){
        ServerMessage message = new ServerMessage("SomeoneElse", "PART #hibob", "");
        assertTrue(message.isLeaveChannel());
        
    }
    
    @Test
    public void testPing(){
        ServerMessage message = new ServerMessage("", "PING", "razorzedge.relic.net");
        assertTrue(message.isPing());
    }
    
    @Test
    public void testPrivMsgParse(){
        String message = ":jibril13!jibril_13@relic-mua211.theedge.ca PRIVMSG #cookies :dude JibTest you should be pinged";
        ServerMessage serverMessage = ServerMessage.getServerMessage(message);
        assertEquals(serverMessage.getPrefix(), "jibril13!jibril_13@relic-mua211.theedge.ca");
        assertEquals(serverMessage.getCommand(), "PRIVMSG");
        assertEquals(serverMessage.getParameters().get(0), "#cookies");
        assertEquals(serverMessage.getParameters().get(1), "dude JibTest you should be pinged");
    }

}
