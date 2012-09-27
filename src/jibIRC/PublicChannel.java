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
public class PublicChannel extends Channel{
    
    DefaultListModel currentChannelUsers;
    String channelName;

    /**
     * Creates new form PublicChannel
     */
    public PublicChannel(String channelName, IRCHandler handler, JibIRC irc) {
        this.channelName = channelName;
        initComponents();
        jButton1.addActionListener(new LeavePublicChannelController(this, handler, irc));
        usersList.addMouseListener(new CreatePMController(irc));
        messageBox.setEditable(false);
    }

    public void addMessage(String message){
        messageBox.append(message);
    }
    
    public void addUser(String username){
        DefaultListModel model = (DefaultListModel)usersList.getModel();
        String comparedTo;
        int i;
        for(i = 0; i < model.size(); i++){
            comparedTo = (String)model.get(i);
            if(comparedTo.compareTo(username) > 0){
                break;
            }
        }
        model.add(i, username);
    }
    
    public void removeUser(String username){
        DefaultListModel model = (DefaultListModel) usersList.getModel();
        model.removeElement(username);
    }
    
    public boolean userExists(String userName){
        DefaultListModel userModel = (DefaultListModel)usersList.getModel();
        return userModel.contains(userName);
    }
    
    public String getSelectedUser(){
        int index = usersList.getSelectedIndex();
        DefaultListModel userModel = (DefaultListModel)usersList.getModel();
        return (String)userModel.get(index);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        usersList = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();

        messageBox.setColumns(20);
        messageBox.setLineWrap(true);
        messageBox.setRows(5);
        messageBox.setWrapStyleWord(true);
        jScrollPane1.setViewportView(messageBox);

        currentChannelUsers = new javax.swing.DefaultListModel();
        usersList.setModel(currentChannelUsers);
        jScrollPane2.setViewportView(usersList);

        jButton1.setText("Leave Channel " + channelName);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 261, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(226, 226, 226))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    public javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTextArea messageBox;
    public javax.swing.JList usersList;
    // End of variables declaration//GEN-END:variables
}
