/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jibIRC;

import java.util.ArrayList;
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
public class ServerMessageControllerTest {
    
    public ServerMessageControllerTest() {
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

    /**
     * Test of actionPerformed method, of class ServerMessageController.
     */

    /**
     * Test of setUserList method, of class ServerMessageController.
     */
    @Test
    public void testSetUserList() {
        ServerMessage serverMessage = new ServerMessage("", "USERS", "#GameReaper :+jibril @cookies");
        TestUserList blah = new TestUserList(null);
        ServerMessageController instance = new ServerMessageController(null, blah);
        instance.setUserList(serverMessage);
        
        assertEquals(blah.getNumberOfUsers(), 2);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testJoinChannel(){
        JoinHandler handler = new JoinHandler();
        TestJoinChannel blah = new TestJoinChannel(handler);
        ServerMessageController instance = new ServerMessageController(handler, blah);
        instance.actionPerformed(null);
    }
    
    class TestUserList extends JibIRC{
        ArrayList<String> users;
        
        public TestUserList(IRCHandler handler){
            super(null);
            users = new ArrayList<String>();
        }
        
        @Override
        public void addUser(String channelName, String user){
            users.add(user);
        }
        
        public int getNumberOfUsers(){
            return users.size();
        }
    }
    
    class TestJoinChannel extends JibIRC{
        
        public TestJoinChannel(IRCHandler handler){
            super(handler);
            nick = "uguysaremean";
        }
        
        @Override
        public void joinChannel(String channelName){
            fail("this should fail");
        }
        
    }
    
    class JoinHandler extends IRCHandler{
        
        @Override
        public String receiveMessage(){
            return ":uguysaremean!uguysaremean@relic-mua211.theedge.ca JOIN :#hibob";
        }
    }
}
