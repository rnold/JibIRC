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
    @Test
    public void testGetMessage() {
        System.out.println("getMessage");
        JibIRC instance = new JibIRC();
        instance.getMessage();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of connect method, of class JibIRC.
     */
    @Test
    public void testConnect() {
        System.out.println("connect");
        String server = "";
        int port = 0;
        String nick = "";
        String name = "";
        IRCHandler instance = new IRCHandler();
        boolean expResult = false;
        boolean result = instance.connect(server, port, nick, name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendMessage method, of class JibIRC.
     */
    @Test
    public void testSendMessage() {
        System.out.println("sendMessage");
        String message = "";
        String user = "";
        IRCHandler instance = new IRCHandler();
        instance.sendMessage(message, user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendCommand method, of class JibIRC.
     */
    @Test
    public void testSendCommand() {
        System.out.println("sendCommand");
        String command = "";
        IRCHandler instance = new IRCHandler();
        instance.sendCommand(command);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of quit method, of class JibIRC.
     */
    @Test
    public void testQuit() {
        System.out.println("quit");
        IRCHandler instance = new IRCHandler();
        instance.quit();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of receiveMessage method, of class JibIRC.
     */
    @Test
    public void testReceiveMessage() {
        System.out.println("receiveMessage");
        IRCHandler instance = new IRCHandler();
        String expResult = "";
        String result = instance.receiveMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isCommand method, of class JibIRC.
     */
    @Test
    public void testIsCommand() {
        System.out.println("isCommand");
        String input = "";
        JibIRC instance = new JibIRC(null);
        boolean expResult = false;
        boolean result = instance.isCommand(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isServerCommand method, of class JibIRC.
     */
    @Test
    public void testIsServerCommand() {
        System.out.println("isServerCommand");
        String input = "";
        JibIRC instance = new JibIRC(null);
        boolean expResult = false;
        boolean result = instance.isServerCommand(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isJoinMessage method, of class JibIRC.
     */
    @Test
    public void testIsJoinMessage() {
        System.out.println("isJoinMessage");
        String message = "";
        JibIRC instance = new JibIRC(null);
        boolean expResult = false;
        boolean result = instance.isJoinMessage(message);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}