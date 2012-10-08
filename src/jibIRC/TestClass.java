/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jibIRC;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;



/**
 *
 * @author Owner
 */
public class TestClass {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DefaultSettings settings = DefaultSettings.getDefaultSettings(getDefaultReader());
                JibIRC frame = new JibIRC(new IRCHandler(), settings);
                frame.setSize(800, 600);
                frame.setVisible(true);
            }
        });



    }

    private static BufferedReader getDefaultReader() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("default.txt"));
        } catch (Exception e) {
            reader = new BufferedReader(new StringReader("\n\n\n"));
        }
        return reader;
    }
}
