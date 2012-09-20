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
        irc.joinChannel(channelName);
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
    public void testCreateChannel(){
        String channelName = "#GameReaper";
        JibIRC irc = new JibIRC(null);
        irc.createChannel(channelName);
        assertTrue(irc.channelExists(channelName));
    }
    
    @Test
    public void testAddUser(){
        String channelName = "#GameReaper";
        String username = "JibTest";
        JibIRC irc = new JibIRC(null);
        irc.createChannel(channelName);
        irc.addUser(channelName, username);
        
        Channel channel = irc.channelBoxes.get(channelName);
        assertTrue(channel.userExists(username));
    }
    
    @Test
    public void testRemoveUser(){
        String channelName = "#GameReaper";
        String userName = "JibTest";
        JibIRC irc = new JibIRC(null);
        irc.createChannel(channelName);
        irc.addUser(channelName, userName);
        irc.removeUser(channelName, userName);
        Channel channel = irc.channelBoxes.get(channelName);
        assertFalse(channel.userExists(userName));
    }
    
    @Test
    public void testChangeChannel(){
        String channelName = "#GameReaper";
        JibIRC irc = new JibIRC(null);
        irc.createChannel(channelName);
        irc.createChannel("#bye");
        irc.switchToChannel(channelName);
        assertEquals(channelName, irc.getActiveChannelName());
    }
    
    @Test
    public void testLeaveChannel(){
        String channelName = "#GameReaper";
        JibIRC irc = new JibIRC(null);
        irc.joinChannel(channelName);
        assertTrue(irc.channelExists(channelName));
        assertEquals(channelName, irc.getActiveChannelName());
        irc.leaveChannel(channelName);
        assertFalse(irc.channelExists(channelName));
    }
    
    @Test
    public void putMessage(){
        JibIRC irc = new JibIRC(null);
        irc.joinChannel("#GameReaper");
        
    }
    

    /**
     * Test of sendMessage method, of class JibIRC.
     */


}