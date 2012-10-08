/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jibIRC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Welcome
 */
public class SettingSaver implements ActionListener{
    JibIRC irc;
    
    public SettingSaver(JibIRC irc){
        this.irc = irc;
    }
    
    public void actionPerformed(ActionEvent ae){
        DefaultSettings settings = irc.getSettings();
        try{
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("default.txt")));
            writer.println(settings.getNick());
            writer.println(settings.getName());
            writer.println(settings.getServerName());
            writer.println(settings.getPort());
            writer.close();
        }catch(IOException e){
            
        }
    }
}
