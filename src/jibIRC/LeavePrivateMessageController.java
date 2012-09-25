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
public class LeavePrivateMessageController implements ActionListener{
    PrivateMessage pm;
    IRCHandler handler;
    JibIRC irc;
    
    public LeavePrivateMessageController(PrivateMessage pm, IRCHandler handler, JibIRC irc){
        this.pm = pm;
        this.handler = handler;
        this.irc = irc;
    }
    
    public void actionPerformed(ActionEvent ae){
        JButton button = (JButton)ae.getSource();
        Channel channel = (Channel)button.getParent();
        irc.leaveChannel(channel.toString());
    }
    
}
