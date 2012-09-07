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
    public void testSuccessfulParsing(){
        String shouldMatch = ":uguysaremean!uguysaremean@relic-mua211.theedge.ca JOIN :#hibob";
        assertTrue(ServerMessageParser.parse(shouldMatch).isWellFormed());

    }
    
    @Test
    public void testMoreSuccessfulParsing(){
        String shouldMatch = ":jibril13!jibril_13@relic-b5s.33u.108.216.IP PRIVMSG #hi :asdfa";
        assertTrue(ServerMessageParser.parse(shouldMatch).isWellFormed());
    }
    
    @Test
    public void testParseGroups() throws Exception{
        String shouldMatch = ":uguysaremean!uguysaremean@relic-mua211.theedge.ca JOIN :#hibob";
        ServerMessageParser parser = ServerMessageParser.parse(shouldMatch);
        assertEquals("uguysaremean", parser.getPrefix());
        assertEquals("JOIN", parser.getCommand());
        assertEquals("#hibob", parser.getParameters());
    }
    
    @Test
    public void testIfChannelMessage(){
        ServerMessage sMessage = new ServerMessage("Meathook", "PRIVMSG #GameReaper", "yup.. we know and we care!!");
        if(!sMessage.isChannelMessage()){
            fail();
        }
    }
    
    @Test
    public void testPing(){
        ServerMessage message = new ServerMessage("", "PING", "razorzedge.relic.net");
        assertTrue(message.isPing());
    }
    
    @Test
    public void testPingMessage(){
        ServerMessageParser parser = ServerMessageParser.parse("PING :razorzedge.relic.net");
        assertTrue(parser.isWellFormed());
        assertTrue(parser.getPrefix() == null);
        assertTrue(parser.getCommand().equals("PING"));
        assertTrue(parser.getParameters().equals("razorzedge.relic.net"));
    }

    /**
     * Test of sendMessage method, of class JibIRC.
     */


}