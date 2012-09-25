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
    JibIRC irc;
    
    public CreatePMController(JibIRC irc){
        this.irc = irc;
    }
    
    @Override
    public void mouseClicked(MouseEvent me){
        JList userList = (JList)me.getSource();
        String channelName = (String)userList.getSelectedValue();
        if(me.getClickCount() == 2){
            irc.joinPrivateMessage(channelName);
        }
        
    }
    
}
