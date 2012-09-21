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
public class ServerMessageParserTest {
    
    public ServerMessageParserTest() {
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
    public void testSuccessfulParsing(){
        String shouldMatch = ":uguysaremean!uguysaremean@relic-mua211.theedge.ca JOIN :#hibob";
        ServerMessageParser parser = ServerMessageParser.parse(shouldMatch);
        assertTrue(parser.isWellFormed());
        assertEquals(parser.getPrefix(), "uguysaremean");
        assertEquals(parser.getCommand(), "JOIN");
        assertEquals(parser.getParameters(), "#hibob");

    }
    
    @Test
    public void testFreenodeJoin(){
        String shouldMatch = ":JibTest!~JibTest@d216-108-168-26.theedge.ca JOIN #cooldudes";
        ServerMessageParser parser = ServerMessageParser.parse(shouldMatch);
        assertTrue(parser.isWellFormed());
        assertEquals(parser.getPrefix(), "JibTest");
        assertEquals(parser.getCommand(), "JOIN");
        assertEquals(parser.getParameters(), "#cooldudes");
    }
    
    @Test
    public void testMoreSuccessfulParsing(){
        String shouldMatch = ":jibril13!jibril_13@relic-b5s.33u.108.216.IP PRIVMSG #hi :asdfa";
        assertTrue(ServerMessageParser.parse(shouldMatch).isWellFormed());
    }
    
    @Test
    public void testParseUsers(){
        String shouldMatch = ":critical.relic.net 353 JibTest = #GameReaper :+ubi_21 &GameReaper +SouL +Worseley %lionelione43 +aretha +iniquity @pegasus &alogwe JibTest %KG +supapaint +Metallica ~Mayhemisis +Taranis +inferna +tomt00001 +Guest10513 +driv +matrix12 +jibril13 +Zoen &Monochrome +Pyro +GrinningDoom +Kid @Zeus +enissay +scribblez +WiseBoy +WickedJester @Shrap +aeon2 @Apollo ~Anthrax +Meathook %aroldao ";
        ServerMessageParser parser = ServerMessageParser.parse(shouldMatch);
        assertTrue(parser.isWellFormed());
        assertEquals(parser.getPrefix(), "");
        assertEquals(parser.getCommand(), "USERS");
        assertEquals(parser.getParameters(), "#GameReaper :+ubi_21 &GameReaper +SouL +Worseley %lionelione43 +aretha +iniquity @pegasus &alogwe JibTest %KG +supapaint +Metallica ~Mayhemisis +Taranis +inferna +tomt00001 +Guest10513 +driv +matrix12 +jibril13 +Zoen &Monochrome +Pyro +GrinningDoom +Kid @Zeus +enissay +scribblez +WiseBoy +WickedJester @Shrap +aeon2 @Apollo ~Anthrax +Meathook %aroldao ");
        
    }
    
    @Test
    public void testRelicUsers(){
        String shouldMatch = ":thecastle.au.relic.net 353 JibTest = #GameReaper :+iniquity +ubi_21 +GrinningDoom +aeon &ibanez @Zeus +aroldao-work +inferna @pegasus +Hen +supapaint +acien +comper @Shrapnal ~Anthrax %aroldao +Worseley +tomt00001 %lionelione43 +WickedJester +jibril13 +SouL +Meathook +Metallica @Apollo +Zoen &Mayhemisis JibTest +ewgola @HNT[HCL] &GameReaper ";
        ServerMessageParser parser = ServerMessageParser.parse(shouldMatch);
        assertTrue(parser.isWellFormed());
        assertEquals(parser.getPrefix(), "");
        assertEquals(parser.getCommand(), "USERS");
        assertEquals(parser.getParameters(), "#GameReaper :+iniquity +ubi_21 +GrinningDoom +aeon &ibanez @Zeus +aroldao-work +inferna @pegasus +Hen +supapaint +acien +comper @Shrapnal ~Anthrax %aroldao +Worseley +tomt00001 %lionelione43 +WickedJester +jibril13 +SouL +Meathook +Metallica @Apollo +Zoen &Mayhemisis JibTest +ewgola @HNT[HCL] &GameReaper ");
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
    public void testParsePart2(){
        String shouldMatch = ":SomeoneElse!SomeoneElse@relic-mua211.theedge.ca PART #hibob";
        ServerMessageParser parser = ServerMessageParser.parse(shouldMatch);
        assertEquals("SomeoneElse", parser.getPrefix());
        assertEquals("PART #hibob", parser.getCommand());
        assertEquals("", parser.getParameters());
    }
    
    @Test
    public void testParseGroups() throws Exception{
        String shouldMatch = ":JibTest!JibTest@relic-mua211.theedge.ca JOIN :#hibob";
        ServerMessageParser parser = ServerMessageParser.parse(shouldMatch);
        assertEquals("JibTest", parser.getPrefix());
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
    public void testPingMessage(){
        ServerMessageParser parser = ServerMessageParser.parse("PING :razorzedge.relic.net");
        assertTrue(parser.isWellFormed());
        assertTrue(parser.getPrefix() == null);
        assertTrue(parser.getCommand().equals("PING"));
        assertTrue(parser.getParameters().equals("razorzedge.relic.net"));
    }
}
