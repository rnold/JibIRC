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
 * @author Owner
 */
public class JibIRCTest {

    public JibIRCTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getMessage method, of class JibIRC.
     */

    /**
     * Test of connect method, of class JibIRC.
     */
    

    
    
    

    
    @Test
    public void testJoinChannel(){
        String channelName = "#GameReaper";
        JibIRC irc = new JibIRC(null);
        Channel channel = new PublicChannel(channelName, null, null);
        irc.joinChannel(channel);
        if(irc.channelBoxes.get(channelName) == null){
            fail();
        }
        if(irc.channels.get(0) == null){
            fail();
        }
        assertEquals(1, irc.channelBoxes.size());
        assertTrue(irc.channelExists(channelName));
    }
    
    @Test
    public void testChannelExists(){
        String channelName = "#GameReaper";
        JibIRC irc = new JibIRC(null);
        Channel channel = new PublicChannel(channelName, null, null);
        irc.joinChannel(channel);
        assertTrue(irc.channelExists(channelName));
    }
    
    @Test
    public void testChannelDoesNotExist(){
        String channelName = "#GameReaper";
        JibIRC irc = new JibIRC(null);
        assertFalse(irc.channelExists(channelName));
    }
    
    @Test
    public void testCreateChannel(){
        String channelName = "#GameReaper";
        JibIRC irc = new JibIRC(null);
        Channel channel = new PublicChannel(channelName, null, null);
        irc.createChannel(channel);
        assertTrue(irc.channelExists(channelName));
    }
    
    @Test
    public void testAddUser(){
        String channelName = "#GameReaper";
        String username = "JibTest";
        JibIRC irc = new JibIRC(null);
        Channel channel = new PublicChannel(channelName, null, null);
        irc.createChannel(channel);
        irc.addUser(channelName, username);
        assertTrue(channel.userExists(username));
    }
    
    @Test
    public void testRemoveUser(){
        String channelName = "#GameReaper";
        String userName = "JibTest";
        JibIRC irc = new JibIRC(null);
        Channel channel = new PublicChannel(channelName, null, null);
        irc.createChannel(channel);
        irc.addUser(channelName, userName);
        irc.removeUser(channelName, userName);
        assertFalse(channel.userExists(userName));
    }
    
    @Test
    public void testChangeChannel(){
        String channelName = "#GameReaper";
        JibIRC irc = new JibIRC(null);
        Channel channel = new PublicChannel(channelName, null, null);
        Channel channel2 = new PublicChannel("#bye", null, null);
        irc.createChannel(channel);
        irc.createChannel(channel2);
        irc.switchToChannel(channelName);
        assertEquals(channelName, irc.getActiveChannelName());
    }
    
    @Test
    public void testLeaveChannel(){
        String channelName = "#GameReaper";
        JibIRC irc = new JibIRC(null);
        irc.joinPublicChannel(channelName);
        assertTrue(irc.channelExists(channelName));
        assertEquals(channelName, irc.getActiveChannelName());
        irc.leaveChannel(channelName);
        assertFalse(irc.channelExists(channelName));
    }
    
    @Test
    public void putMessage(){
        JibIRC irc = new JibIRC(null);
        irc.joinPublicChannel("#GameReaper");
        irc.addMessage("#GameReaper", "this message should be added", "JibTest");
    }
    
    

    /**
     * Test of sendMessage method, of class JibIRC.
     */


}