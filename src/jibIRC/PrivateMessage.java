/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jibIRC;

import javax.swing.DefaultListModel;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author Welcome
 */
public class PrivateMessage extends Channel{
    
    ServerPanel irc;
    DefaultListModel currentChannelUsers;
    String channelName;

    /**
     * Creates new form PublicChannel
     */
    public PrivateMessage(String channelName, IRCHandler handler, ServerPanel irc) {
        this.channelName = channelName;
        this.irc = irc;
        initComponents();
        jButton1.addActionListener(new LeavePrivateMessageController(irc));
        messageBox.addMouseListener(new RightClickMenuCreator(messageBox));
        messageBox.setEditable(false);
    }

    public void addMessage(String message){
        messageBox.append(message);
    }
    
    public void addUser(User user){

    }
    
    public void removeUser(User user){

    }
    
    public boolean userExists(User user){
        return false;
    }
    
    public User getSelectedUser(){
        return null;
    }
    
    public void changeUserNick(User user, String newUsername){
        
    }
    
    @Override
    public String toString(){
        return channelName;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        messageBox = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        messageBox.setColumns(20);
        messageBox.setLineWrap(true);
        messageBox.setRows(5);
        messageBox.setWrapStyleWord(true);
        jScrollPane1.setViewportView(messageBox);

        jButton1.setText("Leave Channel " + channelName);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addGap(116, 116, 116))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 261, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(226, 226, 226))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea messageBox;
    // End of variables declaration//GEN-END:variables
}
