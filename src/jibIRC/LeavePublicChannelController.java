/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jibIRC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Welcome
 */
public class LeavePublicChannelController implements ActionListener{
    PublicChannel publicChannel;
    IRCHandler handler;
    JibIRC irc;
    
    public LeavePublicChannelController(PublicChannel publicChannel, IRCHandler handler, JibIRC irc){
        this.publicChannel = publicChannel;
        this.handler = handler;
        this.irc = irc;
    }
    
    public void actionPerformed(ActionEvent ae){
        JButton button = (JButton)ae.getSource();
        Channel channel = (Channel)button.getParent();
        handler.sendCommand("/PART " + channel);
    }
    
}
