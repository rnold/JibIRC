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
    ServerPanel irc;
    
    public LeavePrivateMessageController(ServerPanel irc){
        this.irc = irc;
    }
    
    public void actionPerformed(ActionEvent ae){
        JButton button = (JButton)ae.getSource();
        Channel channel = (Channel)button.getParent();
        irc.leaveChannel(channel.toString());
    }
    
}
