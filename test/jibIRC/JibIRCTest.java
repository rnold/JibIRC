/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jibIRC;

import java.io.BufferedReader;
import java.io.StringReader;
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

    @Test
    public void testCreateServer(){
        String serverName = "irc.relic.net";
        String nick = "JibTest";
        JibIRC irc = new JibIRC(null, null);
        ServerPanel server = irc.createServer(serverName, nick);
        assertTrue(server.getNick().equals("JibTest"));
    }
    
    @Test
    public void testAddServer(){
        String serverName = "irc.relic.net";
        String nick = "JibTest";
        JibIRC irc = new JibIRC(null, null);
        ServerPanel server = irc.createServer(serverName, nick);
        irc.addServer(server);
        assertTrue(irc.serverIsAdded(server));
    }
    
    @Test
    public void testGetDefaultSettings(){
        BufferedReader reader = new BufferedReader(new StringReader("JibTest\nrnold\nirc.relic.net\n6667"));
        DefaultSettings settings = DefaultSettings.getDefaultSettings(reader);
        assertTrue(settings.getNick().equals("JibTest"));
        assertTrue(settings.getName().equals("rnold"));
        assertTrue(settings.getServerName().equals("irc.relic.net"));
        assertTrue(settings.getPort().equals("6667"));
    }
    
/*    @Test
    public void testLeaveServer(){
        String serverName = "irc.relic.net";
        String channelName = "#GameReaper";
        JibIRC irc = new JibIRC(null);
        irc.server = serverName;
        Channel channel = new PublicChannel(channelName, null, null);
        irc.joinChannel(channel);
        fail();
        
    }
*/
    



}