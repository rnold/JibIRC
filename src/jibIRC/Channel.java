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
    public abstract void addUser(User user);
    public abstract void removeUser(User user);
    public abstract boolean userExists(User user);
    public abstract User getSelectedUser();
    public abstract void changeUserNick(User user, String newUsername);
}
