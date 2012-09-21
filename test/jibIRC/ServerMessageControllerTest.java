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
        
        assertEquals(2, blah.getNumberOfUsers());
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testSetUserListWithSpaceAtEnd(){
        ServerMessage serverMessage = new ServerMessage("", "USERS", "#hi :@JibTest $asdf +safij34 +sdaf3 ");
        TestUserList blah = new TestUserList(null);
        ServerMessageController instance = new ServerMessageController(null, blah);
        instance.setUserList(serverMessage);
        
        assertEquals(4, blah.getNumberOfUsers());
    }
    
    @Test
    public void testRelicUserList(){
        ServerMessage serverMessage = new ServerMessage("", "USERS", "#GameReaper :+iniquity +ubi_21 +GrinningDoom +aeon &ibanez @Zeus +aroldao-work +inferna @pegasus +Hen +supapaint +acien +comper @Shrapnal ~Anthrax %aroldao +Worseley +tomt00001 %lionelione43 +WickedJester +jibril13 +SouL +Meathook +Metallica @Apollo +Zoen &Mayhemisis JibTest +ewgola @HNT[HCL] &GameReaper ");
        TestUserList blah = new TestUserList(null);
        ServerMessageController instance = new ServerMessageController(null, blah);
        instance.setUserList(serverMessage);
        
        assertEquals(31, blah.getNumberOfUsers());
    }
    
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
    public void testPrivMsg(){
        IRCHandler handler = new PrivMsgHandler();
        TestPrivMsg irc = new TestPrivMsg();
        ServerMessageController instance = new ServerMessageController(handler, irc);
        instance.actionPerformed(null);
        assertTrue(irc.privMsgSuccess);
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
        
        @Override
        public boolean channelExists(String channelName){
            return channelName.equals("#GameReaper") || channelName.equals("#hi");
        }
        
        public int getNumberOfUsers(){
            return users.size();
        }
    }
    
    class TestJoinChannel extends JibIRC{
        public boolean joinSuccess = false;
        public boolean addSuccess = false;
        
        public TestJoinChannel(IRCHandler handler){
            super(handler);
            nick = "uguysaremean";
        }
        
        public TestJoinChannel(IRCHandler handler, String nick){
            super(handler);
            this.nick = nick;
        }
        
        @Override
        public void joinChannel(String channelName){
            joinSuccess = true;
        }
        
        @Override
        public void leaveChannel(String channelName){
            joinSuccess = false;
        }
        
        @Override
        public void addUser(String channelName, String username){
            addSuccess = true;
        }
        
        @Override
        public void removeUser(String channelName, String username){
            addSuccess = false;
        }
        
    }
    
    class TestPrivMsg extends JibIRC{
        public boolean privMsgSuccess = false;
        public TestPrivMsg(){
            super(null);
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
}
