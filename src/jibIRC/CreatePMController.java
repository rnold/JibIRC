/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jibIRC;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;

/**
 *
 * @author Welcome
 */
public class CreatePMController extends MouseAdapter{
    ServerPanel server;
    
    public CreatePMController(ServerPanel irc){
        this.server = irc;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getClickCount() == 2) {
            JList userList = (JList) me.getSource();
            User user = (User) userList.getSelectedValue();
            server.joinPrivateMessage(user.getUsername());
        }

    }
}
