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
public class ServerPanelTest {
    
    public ServerPanelTest() {
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
    public void testJoinChannel() {
        String channelName = "#GameReaper";
        ServerPanel server = new ServerPanel(null, null, channelName, null);
        Channel channel = new PublicChannel(channelName, null, null);
        server.joinChannel(channel);
        if(server.channelBoxes.get(channelName) == null){
            fail();
        }
        if(server.channels.get(0) == null){
            fail();
        }
        assertEquals(1, server.channelBoxes.size());
        assertTrue(server.channelExists(channelName));
    }
    
    @Test
    public void testChannelExists(){
        String channelName = "#GameReaper";
        ServerPanel server = new ServerPanel(null, null, channelName, null);
        Channel channel = new PublicChannel(channelName, null, null);
        server.joinChannel(channel);
        assertTrue(server.channelExists(channelName));
    }
    
    @Test
    public void testChannelDoesNotExist(){
        String channelName = "#GameReaper";
        ServerPanel server = new ServerPanel(null, null, channelName, null);
        assertFalse(server.channelExists(channelName));
    }
    
    @Test
    public void testCreateChannel(){
        String channelName = "#GameReaper";
        ServerPanel server = new ServerPanel(null, null, channelName, null);
        Channel channel = new PublicChannel(channelName, null, null);
        server.createChannel(channel);
        assertTrue(server.channelExists(channelName));
    }
    
    @Test
    public void testAddUser(){
        String channelName = "#GameReaper";
        User user = new User("JibTest", "", "");
        ServerPanel server = new ServerPanel(null, null, channelName, null);
        Channel channel = new PublicChannel(channelName, null, null);
        server.createChannel(channel);
        server.addUser(channelName, user);
        assertTrue(channel.userExists(user));
    }
    
    @Test
    public void testRemoveUser(){
        String channelName = "#GameReaper";
        User user = new User("JibTest", "", "");
        ServerPanel server = new ServerPanel(null, null, channelName, null);
        Channel channel = new PublicChannel(channelName, null, null);
        server.createChannel(channel);
        server.addUser(channelName, user);
        server.removeUser(channelName, user);
        assertFalse(channel.userExists(user));
    }
    
    @Test
    public void testRemoveUserFromAllChannels(){
        //setup
        User user = new User("JibTest", "", "");
        ServerPanel server = new ServerPanel(null, null, null, null);
        Channel hiChannel = new PublicChannel("#hi", null, null);
        server.joinChannel(hiChannel);
        Channel byeChannel = new PublicChannel("#bye", null, null);
        server.joinChannel(byeChannel);
        server.addUser("#hi", user);
        server.addUser("#bye", user);
        assertTrue(hiChannel.userExists(user));
        assertTrue(byeChannel.userExists(user));
        //test
        server.removeUserFromAllChannels(user);
        assertFalse(hiChannel.userExists(user));
        assertFalse(byeChannel.userExists(user));
    }
    
    @Test
    public void testChangeUserNick(){
        User user = new User("JibTest", "", "");
        ServerPanel server = new ServerPanel(null, null, null, null);
        Channel hiChannel = new PublicChannel("#hi", null, null);
        server.joinChannel(hiChannel);
        Channel byeChannel = new PublicChannel("#bye", null, null);
        server.joinChannel(byeChannel);
        server.addUser("#hi", user);
        server.addUser("#bye", user);
        assertTrue(hiChannel.userExists(user));
        assertTrue(byeChannel.userExists(user));
        //test
        server.changeNickForAllChannels(user, "fernando");
        assertFalse(hiChannel.userExists(user));
        assertFalse(byeChannel.userExists(user));
        User changedUser = new User("fernando", "", "+");
        assertTrue(hiChannel.userExists(changedUser));
        assertTrue(byeChannel.userExists(changedUser));
    }
    
    @Test
    public void testChangeChannel(){
        String channelName = "#GameReaper";
        ServerPanel server = new ServerPanel(null, null, channelName, null);
        Channel channel = new PublicChannel(channelName, null, null);
        Channel channel2 = new PublicChannel("#bye", null, null);
        server.createChannel(channel);
        server.createChannel(channel2);
        server.switchToChannel(channelName);
        assertEquals(channelName, server.getActiveChannelName());
    }
    
    @Test
    public void testLeaveChannel(){
        String channelName = "#GameReaper";
        ServerPanel server = new ServerPanel(null, null, channelName, null);
        server.joinPublicChannel(channelName);
        assertTrue(server.channelExists(channelName));
        assertEquals(channelName, server.getActiveChannelName());
        server.leaveChannel(channelName);
        assertFalse(server.channelExists(channelName));
    }
    
    @Test
    public void putMessage(){
        ServerPanel irc = new ServerPanel(null, null, "#GameReaper", null);
        irc.joinPublicChannel("#GameReaper");
        irc.addMessage("#GameReaper", "this message should be added", "JibTest");
    }
}
