package jibIRC;


import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Welcome
 */
public class ClipboardCopier implements ActionListener{
    JTextArea messageBox;
    Clipboard clipboard;
    
    public ClipboardCopier(JTextArea messageBox){
        this.messageBox = messageBox;
        Toolkit kit = Toolkit.getDefaultToolkit();
        clipboard = kit.getSystemClipboard();
    }
    public void actionPerformed(ActionEvent ae){
        String clipboardContents = messageBox.getSelectedText();
        clipboard.setContents(new StringSelection(clipboardContents), null);
    }
}
