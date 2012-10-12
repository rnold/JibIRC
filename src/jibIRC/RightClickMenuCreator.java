/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jibIRC;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

/**
 *
 * @author Welcome
 */
public class RightClickMenuCreator extends MouseAdapter{
    JTextArea messageBox;
    JPopupMenu menu;
    
    public RightClickMenuCreator(JTextArea messageBox){
        this.messageBox = messageBox;
        menu = new JPopupMenu();
        JMenuItem copyItem = new JMenuItem("Copy");
        copyItem.addActionListener(new ClipboardCopier(messageBox));
        menu.add(copyItem);
    }
    
    @Override
    public void mouseClicked(MouseEvent me){
        if(me.getButton() == MouseEvent.BUTTON3){
            menu.show(messageBox, me.getX(), me.getY());
        }
    }
    
}
