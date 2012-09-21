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
    JibIRC irc;
    
    public LeaveChannelController(IRCHandler handler, Channel channel, JibIRC irc){
        this.handler = handler;
        this.channel = channel;
        this.irc = irc;
    }
    
    public void actionPerformed(ActionEvent ae){
        if(channel.toString().startsWith("#")){
            handler.sendCommand("/PART " + channel);
        }else{
            irc.leaveChannel(channel.toString());
        }
    }
    
}
