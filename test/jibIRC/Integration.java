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
public class Integration {
    
    
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
    public void testUserList(){
        TestHandler handler = new TestHandler();
        JibIRC irc = new JibIRC(handler);
        irc.nick = "JibTest";
        ServerMessageController controller = new ServerMessageController(handler, irc);
        controller.actionPerformed(null);
        controller.actionPerformed(null);
        
        assertTrue(irc.channelExists("#GameReaper"));
        Channel channel = irc.channelBoxes.get("#GameReaper");
        assertTrue(channel.userExists("JibTest"));
        assertTrue(channel.userExists("Jibril_13"));
        irc.printChannels();
    }
}

class TestHandler extends IRCHandler {
    public int numTimesInvoked = 0;

    @Override
    public String receiveMessage(){
        String returned;
        if(numTimesInvoked == 0){
            returned = ":JibTest!JibTest@relic-mua211.theedge.ca JOIN :#GameReaper";
        }else if(numTimesInvoked == 1){
            returned = ":critical.relic.net 353 JibTest = #GameReaper :JibTest Jibril_13";
        }else{
            returned = "";
        }
        numTimesInvoked++;
        return returned;
    }
}

