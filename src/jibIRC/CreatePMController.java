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
    ServerPanel irc;
    
    public CreatePMController(ServerPanel irc){
        this.irc = irc;
    }
    
    @Override
    public void mouseClicked(MouseEvent me){
        JList userList = (JList)me.getSource();
        String channelName = (String)userList.getSelectedValue();
        if(me.getClickCount() == 2){
            String strippedChannelName = stripMode(channelName);
            irc.joinPrivateMessage(strippedChannelName);
        }
        
    }
    
    private String stripMode(String userName){
        if(hasMode(userName)){
            return userName.substring(1);
        }
        return userName;
    }
    
    private boolean hasMode(String userName){
        return userName.startsWith("@") ||
                userName.startsWith("+") ||
                userName.startsWith("&") ||
                userName.startsWith("%") ||
                userName.startsWith("~");
    }
    
}
