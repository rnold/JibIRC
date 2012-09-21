/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jibIRC;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        if(me.getClickCount() == 2){
            irc.createPM();
        }
        
    }
    
}
