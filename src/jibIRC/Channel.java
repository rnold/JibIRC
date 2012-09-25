/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jibIRC;

import javax.swing.JPanel;

/**
 *
 * @author Welcome
 */
public abstract class Channel extends JPanel{
    public abstract void addMessage(String message);
    public abstract void addUser(String username);
    public abstract void removeUser(String username);
    public abstract boolean userExists(String userName);
    public abstract String getSelectedUser();
}
