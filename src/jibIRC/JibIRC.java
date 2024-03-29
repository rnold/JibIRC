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


import java.awt.CardLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 *
 * @author Owner
 */
public class JibIRC extends javax.swing.JFrame {

    IRCHandler handler;
    ServerPanel iServer;

    /**
     * Creates new form JibIRC
     */
    public JibIRC(IRCHandler handler, DefaultSettings settings) {
        super("JibIRC");
        this.handler = handler;
        this.addWindowListener(new Quitter());
        this.addWindowFocusListener(new IconChanger());
        initComponents();
        settingsButton.addActionListener(new SettingSaver(this));
        if (settings != null) {
            textNick.setText(settings.getNick());
            textName.setText(settings.getName());
            textServer.setText(settings.getServerName());
            textPort.setText(settings.getPort());
        }
        Image image = getRegularIcon();
        setIconImage(image);
        getContentPane().remove(serverPanel);

    }

    class Quitter extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            if (handler.isInitialized()) {
                handler.quit();
            }
        }
    }
    
    class IconChanger implements WindowFocusListener{

        public void windowGainedFocus(WindowEvent e) {
            Image image = getRegularIcon();
            setIconImage(image);
        }

        public void windowLostFocus(WindowEvent e) {
        }
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        serverPanel = new javax.swing.JPanel();
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
        settingsButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        serverPanel.setLayout(new java.awt.CardLayout());

        textNick.setText("JibTest");
        textNick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNickActionPerformed(evt);
            }
        });

        textName.setText("rnold");
        textName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNameActionPerformed(evt);
            }
        });

        textServer.setText("irc.relic.net");

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

        settingsButton.setText("Save Settings As Default");

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addContainerGap(561, Short.MAX_VALUE)
                .addComponent(settingsButton)
                .addGap(55, 55, 55))
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
                .addGap(18, 18, 18)
                .addComponent(settingsButton)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(serverPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(loginPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(serverPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(loginPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textNickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNickActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textNickActionPerformed

    private void textNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textNameActionPerformed

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        handler.connect(textServer.getText(), Integer.parseInt(textPort.getText()), textNick.getText(), textName.getText());
        String serverName = textServer.getText();
        String nick = textNick.getText();
        ServerPanel server = createServer(serverName, nick);
        addServer(server);
        switchPanels();
        ServerMessageController messageController = new ServerMessageController(handler, server);
        messageController.startListeningForServerMessages();
        
    }//GEN-LAST:event_connectButtonActionPerformed

    public ServerPanel createServer(String serverName, String nick){
        ServerPanel server = new ServerPanel(this, handler, serverName, nick);
        return server;
    }
    
    public void addServer(ServerPanel server){
        serverPanel.add(server, server.getServerName());
        CardLayout layout = (CardLayout)serverPanel.getLayout();
        layout.show(serverPanel, server.getServerName());
        this.iServer = server;
    }
    
    public void leaveServer(ServerPanel server){
        serverPanel.remove(server);
        getContentPane().remove(serverPanel);
        getContentPane().add(loginPanel);
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    
    public boolean serverIsAdded(ServerPanel server){
        return this.iServer == server;
    }
    
    private void switchPanels() {
        getContentPane().remove(loginPanel);
        getContentPane().add(serverPanel);
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    public void alertUser() {
        if (!this.hasFocus()) {
            Image image = getAlertIcon();
            this.setIconImage(image);
            this.revalidate();
            this.repaint();
        }

    }

    public Image getAlertIcon() {
        Image image = null;
        try {
            image = ImageIO.read(new File("icon.jpg"));
        } catch (IOException e) {
            System.err.println("image does not exist");
        }
        return image;
    }
    
    public final Image getRegularIcon(){
        Image image = null;
        try{
            image = ImageIO.read(new File("regularicon.jpg"));
        }catch(IOException e){
            
        }
        return image;
    }
    
    public DefaultSettings getSettings(){
        return new DefaultSettings(textNick.getText(), textName.getText(), textServer.getText(), textPort.getText());
    }


    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connectButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JPanel serverPanel;
    private javax.swing.JButton settingsButton;
    private javax.swing.JTextField textName;
    private javax.swing.JTextField textNick;
    private javax.swing.JTextField textPort;
    private javax.swing.JTextField textServer;
    // End of variables declaration//GEN-END:variables
}
