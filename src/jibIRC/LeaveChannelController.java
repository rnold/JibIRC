/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jibIRC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Welcome
 */
public class LeaveChannelController implements ActionListener{
    Channel channel;
    IRCHandler handler;
    
    public LeaveChannelController(IRCHandler handler, Channel channel){
        this.handler = handler;
        this.channel = channel;
    }
    
    public void actionPerformed(ActionEvent ae){
        handler.sendCommand("/PART " + channel);
    }
    
}
