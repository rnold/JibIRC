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
    public void testSuccessfulParsing() throws Exception{
        String shouldMatch = ":uguysaremean!uguysaremean@relic-mua211.theedge.ca JOIN :#hibob";
        ServerMessageParser.parse(shouldMatch);
    }
    
    @Test
    public void testUnsuccessfulParsing(){
        try{
        String shouldntMatch = ":asfdassadfas JOIN :#asdf";
        ServerMessageParser.parse(shouldntMatch);
        fail("exception should be thrown");
        }catch(Exception e){
            
        }
    }
    
    @Test
    public void testParseGroups() throws Exception{
        String shouldMatch = ":uguysaremean!uguysaremean@relic-mua211.theedge.ca JOIN :#hibob";
        ServerMessageParser parser = ServerMessageParser.parse(shouldMatch);
        assertEquals("uguysaremean", parser.getPrefix());
        assertEquals("JOIN", parser.getCommand());
        assertEquals("#hibob", parser.getParameters());
    }

    /**
     * Test of sendMessage method, of class JibIRC.
     */


}