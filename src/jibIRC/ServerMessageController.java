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
    JibIRC irc;
    Timer timer;

    public ServerMessageController(IRCHandler handler, JibIRC irc) {
        this.handler = handler;
        this.irc = irc;
    }
    
    public void startListeningForServerMessages(){
        timer = new Timer(30, this);
        timer.start();
    }

    //this isn't ugly at all.....
    public void actionPerformed(java.awt.event.ActionEvent e) {
        String message = handler.receiveMessage();
        if (message != null) {
            ServerMessageParser parser = ServerMessageParser.parse(message);
            if (parser.isWellFormed()) {
                ServerMessage serverMessage = new ServerMessage(parser.getPrefix(), parser.getCommand(), parser.getParameters());
                if (serverMessage.isPing()) {
                    handler.sendCommand("/PONG " + serverMessage.getParameters());
                } else if (serverMessage.isJoinChannel()) {
                    if(serverMessage.getPrefix().equals(irc.getNick())){
                        irc.joinPublicChannel(serverMessage.getParameters());
                    }else{
                        irc.addUser(serverMessage.getParameters(), serverMessage.getPrefix());
                    }
                } else if (serverMessage.isLeaveChannel()) {
                    if(serverMessage.getPrefix().equals(irc.getNick())){
                        String command = serverMessage.getCommand();
                        irc.leaveChannel(command.split(" ")[1]);
                    }else{
                        irc.removeUser(serverMessage.getParameters(), serverMessage.getPrefix());
                    }
                } else if (serverMessage.isChannelMessage()) {
                    String command = serverMessage.getCommand();
                    String parameters = serverMessage.getParameters();
                    String user = serverMessage.getPrefix();
                    irc.addMessage(command.split(" ")[1], parameters, user);
                    if(parameters.contains(irc.getNick())){
                        irc.alertUser();
                    }
                } else if (serverMessage.isPrivateMessage(irc.getNick())) {
                    String username = serverMessage.getPrefix();
                    String parameters = serverMessage.getParameters();
                    if (!irc.channelExists(username)) {
                        irc.joinPrivateMessage(username);
                    }
                    irc.addMessage(username, parameters, username);
                } else if (serverMessage.isUserList()) {
                    setUserList(serverMessage);
                }
            }
        }
        

    }

    public void setUserList(ServerMessage serverMessage) {
        System.out.println("userlist " + serverMessage);
        String parameters = serverMessage.getParameters();
        String[] splitParameters = parameters.split(" :");
        String channelName = splitParameters[0];
        String userList = splitParameters[1];
        String[] users = userList.split(" ");
        if(!irc.channelExists(channelName)){
            System.err.println("error " + channelName);
            return;
        }
        for (int i = 0; i < users.length; i++) {
            irc.addUser(channelName, users[i]);
        }
    }
}