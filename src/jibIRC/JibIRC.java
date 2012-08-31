/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JibIRC.java
 *
 * Created on 29-Aug-2010, 1:07:44 PM
 */

package jibIRC;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Timer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author Owner
 */
public class JibIRC extends javax.swing.JFrame {
    Timer timer;
    IRCHandler handler;

    DefaultListModel channels;
    ArrayList<String> channelTexts;
    String activeChannel;
    String nick;
    String server;



    /** Creates new form JibIRC */
    public JibIRC(IRCHandler handler) {
        this.handler = handler;
        this.addWindowListener(new Quitter());
        initComponents();
        getContentPane().remove(channelPanel);

    }
    
    class Quitter extends WindowAdapter{
        public void windowClosing(WindowEvent e){
            if(handler.isInitialized()){
                handler.quit();
            }
        }
    }




    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        channelPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        messageBox = new javax.swing.JTextArea();
        inputBox = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        channelList = new javax.swing.JList();
        loginPanel = new javax.swing.JPanel();
        textNick = new javax.swing.JTextField();
        textName = new javax.swing.JTextField();
        textServer = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        textPort = new javax.swing.JTextField();
        connectButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        messageBox.setColumns(20);
        messageBox.setRows(5);
        jScrollPane1.setViewportView(messageBox);

        inputBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputBoxActionPerformed(evt);
            }
        });

        channels = new javax.swing.DefaultListModel();
        channelList.setModel(channels);
        jScrollPane2.setViewportView(channelList);

        javax.swing.GroupLayout channelPanelLayout = new javax.swing.GroupLayout(channelPanel);
        channelPanel.setLayout(channelPanelLayout);
        channelPanelLayout.setHorizontalGroup(
            channelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(channelPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(channelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(inputBox, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, channelPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        channelPanelLayout.setVerticalGroup(
            channelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(channelPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(channelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        textNick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNickActionPerformed(evt);
            }
        });

        textName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNameActionPerformed(evt);
            }
        });

        jLabel1.setText("Nick");

        jLabel2.setText("Real Name");

        jLabel3.setText("Server");

        jLabel4.setText("Port");

        textPort.setText("6667");

        connectButton.setText("Connect");
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(25, 25, 25)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(connectButton)
                    .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(textPort)
                        .addComponent(textServer, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                        .addComponent(textName)
                        .addComponent(textNick)))
                .addContainerGap(495, Short.MAX_VALUE))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textNick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(textPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(connectButton)
                .addContainerGap(141, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(channelPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(loginPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(channelPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(loginPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputBoxActionPerformed
        String contents = inputBox.getText();
        if(isCommand(contents)){
            handler.sendCommand(contents);
        }else{
            handler.sendMessage(contents, activeChannel);
        }
        inputBox.setText("");
        messageBox.append(contents + "\n");
        getContentPane().repaint();
        
    }//GEN-LAST:event_inputBoxActionPerformed

    private void textNickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNickActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textNickActionPerformed

    private void textNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textNameActionPerformed

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        handler.connect(textServer.getText(), Integer.parseInt(textPort.getText()), textNick.getText(), textName.getText());
        nick = textNick.getText();
        server = textServer.getText();
        getContentPane().remove(loginPanel);
        getContentPane().add(channelPanel);
        getContentPane().repaint();
        timer = new Timer(30, new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent e){
                getMessage();

            }
        });
        timer.start();
    }//GEN-LAST:event_connectButtonActionPerformed
    public void getMessage(){
        String message = handler.receiveMessage();
        if(message != null){
            if(isServerCommand(message)){
                
                String[] splitMessage = message.split(":");
                for(String command : splitMessage){
                    if(isJoinMessage(command)){
                        joinChannel(command.split(" ")[2]);
                    }
                }
            }else{
                messageBox.append(message + "\n");
            }
            getContentPane().repaint();
        }
    }
    
    public void joinChannel(String channelName){
        channels.add(channels.getSize(), channelName); //adds to list
        channelList.setSelectedIndex(channels.getSize());
        String welcomeMessage = "now talking in " + channelName;
        messageBox.setText(welcomeMessage);        
    }

    public boolean isCommand(String input){
        if(input.charAt(0) == '/'){
            return true;
        }else{
            return false;
        }
    }

    public boolean isServerCommand(String input){
        return input.startsWith(":"+nick+"!");
    }

    public boolean isJoinMessage(String message){
        return message.contains("JOIN");
    }
    
    /**
    * @param args the command line arguments
    */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList channelList;
    private javax.swing.JPanel channelPanel;
    private javax.swing.JButton connectButton;
    private javax.swing.JTextField inputBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JTextArea messageBox;
    private javax.swing.JTextField textName;
    private javax.swing.JTextField textNick;
    private javax.swing.JTextField textPort;
    private javax.swing.JTextField textServer;
    // End of variables declaration//GEN-END:variables

}
