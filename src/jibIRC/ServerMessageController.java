/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jibIRC;

import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Welcome
 */
public class ServerMessageController implements ActionListener {
    IRCHandler handler;
    ServerPanel server;
    Timer timer;

    public ServerMessageController(IRCHandler handler, ServerPanel server) {
        this.handler = handler;
        this.server = server;
    }
    
    public void startListeningForServerMessages(){
        timer = new Timer(30, this);
        timer.start();
    }

    //this isn't ugly at all.....
    public void actionPerformed(java.awt.event.ActionEvent e) {
        String message = handler.receiveMessage();
        if (message != null) {
            ServerMessage serverMessage = ServerMessage.getServerMessage(message);
            if (serverMessage.isWellFormed()) {
                if (serverMessage.isPing()) {
                    handler.sendCommand("/PONG " + serverMessage.getParameters().get(0));
                } else if (serverMessage.isJoinChannel()) {
                    User user = User.getUserFromPrefix(serverMessage.getPrefix());
                    if(user.getUsername().equals(server.getNick())){
                        server.joinPublicChannel(serverMessage.getParameters().get(0));
                    }else{
                        server.addUser(serverMessage.getParameters().get(0), user);
                    }
                } else if (serverMessage.isLeaveChannel()) {
                    User user = User.getUserFromPrefix(serverMessage.getPrefix());
                    if(user.getUsername().equals(server.getNick())){
                        server.leaveChannel(serverMessage.getParameters().get(0));
                    }else{
                        server.removeUser(serverMessage.getParameters().get(0), user);
                    }
                } else if (serverMessage.isChannelMessage()) {
                    String channelName = serverMessage.getParameters().get(0);
                    String channelMessage = serverMessage.getParameters().get(1);
                    User user = User.getUserFromPrefix(serverMessage.getPrefix());
                    server.addMessage(channelName, channelMessage, user.getUsername());
                    if(channelMessage.contains(server.getNick())){
                        server.alertUser();
                    }
                } else if (serverMessage.isPrivateMessage(server.getNick())) {
                    User user = User.getUserFromPrefix(serverMessage.getPrefix());
                    String privMsg = serverMessage.getParameters().get(1);
                    if (!server.channelExists(user.getUsername())) {
                        server.joinPrivateMessage(user.getUsername());
                    }
                    server.addMessage(user.getUsername(), privMsg, user.getUsername());
                } else if (serverMessage.isUserList()){
                    setUserList(serverMessage);
                } else if (serverMessage.isQuit()){
                    User leavingUser = User.getUserFromPrefix(serverMessage.getPrefix());
                    server.removeUserFromAllChannels(leavingUser);
                } else if(serverMessage.isChannelMode()){
                    changeUsersMode(serverMessage);
                } else if(serverMessage.isNickChange()){
                    //changeNick(serverMessage);
                }
            }
        }
        

    }
    
    public void setUserList(ServerMessage serverMessage) {
        String channelName = serverMessage.getParameters().get(2);
        String userList = serverMessage.getParameters().get(3);
        String[] users = userList.split(" ");
        if(!server.channelExists(channelName)){
            System.err.println("error " + channelName);
            return;
        }
        for (int i = 0; i < users.length; i++) {
            User user = User.getUserFromList(users[i]);
            server.addUser(channelName, user);
        }
    }
    
    public void changeUsersMode(ServerMessage serverMessage) {
        //remove old user
        User user = User.getUserFromList(serverMessage.getParameters().get(2));
        server.removeUser(serverMessage.getParameters().get(0), user);
        //add new user
        user = user.modifyMode(serverMessage.getParameters().get(1));
        server.addUser(serverMessage.getParameters().get(0), user);
    }
    
    public void changeNick(ServerMessage serverMessage){
        User user = User.getUserFromPrefix(serverMessage.getPrefix());
        String newUsername = serverMessage.getParameters().get(0);
    }
}