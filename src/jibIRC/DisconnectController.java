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
public class DisconnectController implements ActionListener{
    IRCHandler handler;
    JibIRC irc;
    ServerPanel server;
    
    public DisconnectController(IRCHandler handler, JibIRC irc, ServerPanel server){
        this.handler = handler;
        this.irc = irc;
        this.server = server;
    }
    
    public void actionPerformed(ActionEvent ae){
        handler.quit();
        irc.leaveServer(server);
    }
}
