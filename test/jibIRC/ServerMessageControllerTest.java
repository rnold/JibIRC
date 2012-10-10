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
    public void testJoinChannel(){
        JoinHandler handler = new JoinHandler();
        TestJoinChannel blah = new TestJoinChannel(handler);
        ServerMessageController instance = new ServerMessageController(handler, blah);
        instance.actionPerformed(null);
        assertTrue(blah.joinSuccess);
    }
    
    @Test
    public void testLeaveChannel(){
        IRCHandler handler = new JoinHandler();
        TestJoinChannel blah = new TestJoinChannel(null);
        ServerMessageController instance = new ServerMessageController(handler, blah);
        instance.actionPerformed(null);
        assertTrue(blah.joinSuccess);
        
        handler = new LeaveHandler();
        instance = new ServerMessageController(handler, blah);
        instance.actionPerformed(null);
        assertFalse(blah.joinSuccess);
    }
    
    @Test
    public void testAddUser(){
        IRCHandler handler = new JoinHandler();
        TestJoinChannel blah = new TestJoinChannel(null, "SomeoneElse");
        ServerMessageController instance = new ServerMessageController(handler, blah);
        instance.actionPerformed(null);
        assertTrue(blah.addSuccess);
        
    }
    
    @Test
    public void testRemoveUser(){
        IRCHandler handler = new JoinHandler();
        TestJoinChannel blah = new TestJoinChannel(null, "SomeoneElse");
        ServerMessageController instance = new ServerMessageController(handler, blah);
        instance.actionPerformed(null);
        assertTrue(blah.addSuccess);
        
        handler = new LeaveHandler();
        instance = new ServerMessageController(handler, blah);
        instance.actionPerformed(null);
        assertFalse(blah.addSuccess);
    }
    
    @Test
    public void testUserList(){
        IRCHandler handler = new UserListHandler();
        TestUserList blah = new TestUserList(handler);
        ServerMessageController instance = new ServerMessageController(handler, blah);
        instance.actionPerformed(null);
        assertEquals(2, blah.getNumberOfUsers());
    }
    
    @Test
    public void testPrivMsg(){
        IRCHandler handler = new PrivMsgHandler();
        TestPrivMsg irc = new TestPrivMsg();
        ServerMessageController instance = new ServerMessageController(handler, irc);
        instance.actionPerformed(null);
        assertTrue(irc.privMsgSuccess);
    }
    
    @Test
    public void testUserAlerter(){
        IRCHandler handler = new AlerterHandler();
        AlerterIRC irc = new AlerterIRC();
        ServerMessageController instance = new ServerMessageController(handler, irc);
        instance.actionPerformed(null);
        assertTrue(irc.alerted);
    }
    
    @Test
    public void testUserNonAlert(){
        IRCHandler handler = new NonAlerterHandler();
        AlerterIRC irc = new AlerterIRC();
        ServerMessageController instance = new ServerMessageController(handler, irc);
        instance.actionPerformed(null);
        assertFalse(irc.alerted);
    }
    
    class TestUserList extends ServerPanel{
        ArrayList<String> users;
        
        public TestUserList(IRCHandler handler){
            super(null, null, null, null);
            users = new ArrayList<String>();
        }
        
        @Override
        public void addUser(String channelName, User user){
            users.add(user.getUsername());
        }
        
        @Override
        public boolean channelExists(String channelName){
            return channelName.equals("#GameReaper") || channelName.equals("#hi");
        }
        
        public int getNumberOfUsers(){
            return users.size();
        }
    }
    
    class TestJoinChannel extends ServerPanel{
        public boolean joinSuccess = false;
        public boolean addSuccess = false;
        
        public TestJoinChannel(IRCHandler handler){
            super(null, null, null, null);
            nick = "uguysaremean";
        }
        
        public TestJoinChannel(IRCHandler handler, String nick){
            super(null, null, null, null);
            this.nick = nick;
        }
        
        @Override
        public void joinPublicChannel(String channelName){
            joinSuccess = true;
        }
        
        @Override
        public void leaveChannel(String channelName){
            joinSuccess = false;
        }
        
        @Override
        public void addUser(String channelName, User user){
            addSuccess = true;
        }
        
        @Override
        public void removeUser(String channelName, User user){
            addSuccess = false;
        }
        
    }
    
    class TestPrivMsg extends ServerPanel{
        public boolean privMsgSuccess = false;
        public TestPrivMsg(){
            super(null, null, null, null);
            nick = "JibTest";
        }
        
        @Override
        public void addMessage(String one, String two, String three){
            privMsgSuccess = true;
        }
        
        @Override
        public boolean channelExists(String channelName){
            return true;
        }
    }
    
    class AlerterIRC extends ServerPanel{
        public boolean alerted = false;
        public AlerterIRC(){
            super(null, null, null, null);
            nick = "JibTest";
        }
        
        @Override
        public void alertUser(){
            alerted = true;
        }
        
        @Override
        public void addMessage(String channelName, String message, String user){
            
        }
    }
    
    class JoinHandler extends IRCHandler{
        
        @Override
        public String receiveMessage(){
            return ":uguysaremean!uguysaremean@relic-mua211.theedge.ca JOIN :#hibob";
        }
    }
    
    class LeaveHandler extends IRCHandler{
        
        @Override
        public String receiveMessage(){
            return ":uguysaremean!uguysaremean@relic-mua211.theedge.ca PART #hibob";
        }
    }
    
    class PrivMsgHandler extends IRCHandler{
        
        @Override
        public String receiveMessage(){
            return ":jibril13!jibril_13@relic-mua211.theedge.ca PRIVMSG JibTest :dude";
        }
    }
    
    class UserListHandler extends IRCHandler{
        
        @Override
        public String receiveMessage(){
            return ":critical.relic.net 353 JibTest = #hi :JibTest @jibril13";
        }
    }
    
    class AlerterHandler extends IRCHandler{
        
        @Override
        public String receiveMessage(){
            return ":jibril13!jibril_13@relic-mua211.theedge.ca PRIVMSG #cookies :dude JibTest you should be pinged";
        }
    }
    
    class NonAlerterHandler extends IRCHandler{
        
        @Override
        public String receiveMessage(){
            return ":jibril13!jibril_13@relic-mua211.theedge.ca PRIVMSG #cookies :dude bobdole you shouldnt be pinged";
        }
    }
}
