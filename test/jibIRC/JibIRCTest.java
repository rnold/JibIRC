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
        ServerMessageParser parser = ServerMessageParser.parse(shouldMatch);
        assertTrue(parser.isWellFormed());
        assertEquals(parser.getPrefix(), "uguysaremean");
        assertEquals(parser.getCommand(), "JOIN");
        assertEquals(parser.getParameters(), "#hibob");

    }
    
    @Test
    public void testMoreSuccessfulParsing(){
        String shouldMatch = ":jibril13!jibril_13@relic-b5s.33u.108.216.IP PRIVMSG #hi :asdfa";
        assertTrue(ServerMessageParser.parse(shouldMatch).isWellFormed());
    }
    
    @Test
    public void testUsers(){
        ServerMessage message = new ServerMessage("", "USERS", "#GameReaper :+jibril @cookies");
        assertTrue(message.isUserList());
    }
    
    @Test
    public void testParseUsers(){
        String shouldMatch = ":critical.relic.net 353 JibTest = #GameReaper :+ubi_21 &GameReaper +SouL +Worseley %lionelione43 +aretha +iniquity @pegasus &alogwe JibTest %KG +supapaint +Metallica ~Mayhemisis +Taranis +inferna +tomt00001 +Guest10513 +driv +matrix12 +jibril13 +Zoen &Monochrome +Pyro +GrinningDoom +Kid @Zeus +enissay +scribblez +WiseBoy +WickedJester @Shrap +aeon2 @Apollo ~Anthrax +Meathook %aroldao";
        ServerMessageParser parser = ServerMessageParser.parse(shouldMatch);
        assertTrue(parser.isWellFormed());
        assertEquals(parser.getPrefix(), "");
        assertEquals(parser.getCommand(), "USERS");
        assertEquals(parser.getParameters(), "#GameReaper :+ubi_21 &GameReaper +SouL +Worseley %lionelione43 +aretha +iniquity @pegasus &alogwe JibTest %KG +supapaint +Metallica ~Mayhemisis +Taranis +inferna +tomt00001 +Guest10513 +driv +matrix12 +jibril13 +Zoen &Monochrome +Pyro +GrinningDoom +Kid @Zeus +enissay +scribblez +WiseBoy +WickedJester @Shrap +aeon2 @Apollo ~Anthrax +Meathook %aroldao");
        
    }
    
    @Test
    public void testParsePart(){
        String shouldMatch = ":JibTest!JibTest@relic-mua211.theedge.ca PART #hibob";
        ServerMessageParser parser = ServerMessageParser.parse(shouldMatch);
        assertEquals("JibTest", parser.getPrefix());
        assertEquals("PART #hibob", parser.getCommand());
        assertEquals("", parser.getParameters());
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
    public void testParsePrivmsg(){
        String shouldMatch = ":jibril13!jibril_13@relic-mua211.theedge.ca PRIVMSG JibTest :dude";
        ServerMessageParser parser = ServerMessageParser.parse(shouldMatch);
        assertEquals("jibril13", parser.getPrefix());
        assertEquals("PRIVMSG JibTest", parser.getCommand());
        assertEquals("dude", parser.getParameters());
    }
    
    @Test
    public void testIfChannelMessage(){
        ServerMessage sMessage = new ServerMessage("Meathook", "PRIVMSG #GameReaper", "yup.. we know and we care!!");
        if(!sMessage.isChannelMessage()){
            fail();
        }
    }
    
    @Test
    public void testIfJoinMessage(){
        ServerMessage message = new ServerMessage("JibTest", "JOIN", "#hibob");
        if(!message.isJoinNewChannel("JibTest")){
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
    
    @Test
    public void testPingMessage(){
        ServerMessageParser parser = ServerMessageParser.parse("PING :razorzedge.relic.net");
        assertTrue(parser.isWellFormed());
        assertTrue(parser.getPrefix() == null);
        assertTrue(parser.getCommand().equals("PING"));
        assertTrue(parser.getParameters().equals("razorzedge.relic.net"));
    }
    
    @Test
    public void testJoinChannel(){
        String channelName = "#GameReaper";
        JibIRC irc = new JibIRC(null);
        irc.joinChannel(channelName);
        if(irc.messageBoxes.get(channelName) == null){
            fail();
        }
        if(irc.usersList.get(channelName) == null){
            fail();
        }
        if(irc.channels.get(0) == null){
            fail();
        }
    }
    

    /**
     * Test of sendMessage method, of class JibIRC.
     */


}