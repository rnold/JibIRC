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
public class InputMessageController implements ActionListener{
    IRCHandler handler;
    JibIRC irc;
    
    public InputMessageController(IRCHandler handler, JibIRC irc){
        this.handler = handler;
        this.irc = irc;
    }

    public void actionPerformed(ActionEvent e) {
        String contents = irc.getInput();
        if (isCommand(contents)) {
            handler.sendCommand(contents);
        } else {
            handler.sendMessage(contents, irc.getActiveChannelName());
            irc.addMessage(irc.getActiveChannelName(), contents, irc.getNick());
        }
        irc.resetInputBox();

        irc.getContentPane().repaint();

    }
    
    private boolean isCommand(String input) {
        if (input.charAt(0) == '/') {
            return true;
        } else {
            return false;
        }
    }
}
