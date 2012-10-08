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
            ServerMessage serverMessage = ServerMessage.getNewMessage(message);
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
                    if(serverMessage.getPrefix().equals(server.getNick())){
                        String command = serverMessage.getCommand();
                        server.leaveChannel(command.split(" ")[1]);
                    }else{
                        server.removeUser(serverMessage.getParameters().get(0), serverMessage.getPrefix());
                    }
                } else if (serverMessage.isChannelMessage()) {
                    String command = serverMessage.getCommand();
                    String parameters = serverMessage.getParameters().get(0);
                    String user = serverMessage.getPrefix();
                    server.addMessage(command.split(" ")[1], parameters, user);
                    if(parameters.contains(server.getNick())){
                        server.alertUser();
                    }
                } else if (serverMessage.isPrivateMessage(server.getNick())) {
                    String username = serverMessage.getPrefix();
                    String parameters = serverMessage.getParameters().get(0);
                    if (!server.channelExists(username)) {
                        server.joinPrivateMessage(username);
                    }
                    server.addMessage(username, parameters, username);
                } else if (serverMessage.isUserList()) {
                    setUserList(serverMessage);
                } else if (serverMessage.isNewUserList()){
                    setNewUserList(serverMessage);
                }
            }
        }
        

    }

    public void setUserList(ServerMessage serverMessage) {
        System.out.println("userlist " + serverMessage);
        String parameters = serverMessage.getParameters().get(0);
        String[] splitParameters = parameters.split(" :");
        String channelName = splitParameters[0];
        String userList = splitParameters[1];
        String[] users = userList.split(" ");
        if(!server.channelExists(channelName)){
            System.err.println("error " + channelName);
            return;
        }
        for (int i = 0; i < users.length; i++) {
            User user = new User(users[i], "");
            server.addUser(channelName, user);
        }
    }
    
    public void setNewUserList(ServerMessage serverMessage) {
        String channelName = serverMessage.getParameters().get(2);
        String userList = serverMessage.getParameters().get(3);
        String[] users = userList.split(" ");
        if(!server.channelExists(channelName)){
            System.err.println("error " + channelName);
            return;
        }
        for (int i = 0; i < users.length; i++) {
            User user = new User(users[i], "");
            server.addUser(channelName, user);
        }
    }
}