/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jibIRC;

import java.awt.event.ActionListener;

/**
 *
 * @author Welcome
 */
public class ServerMessageController implements ActionListener {

    IRCHandler handler;
    JibIRC irc;

    public ServerMessageController(IRCHandler handler, JibIRC irc) {
        this.handler = handler;
        this.irc = irc;
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
        String message = handler.receiveMessage();
        if (message != null) {
            ServerMessageParser parser = ServerMessageParser.parse(message);
            if (parser.isWellFormed()) {
                ServerMessage serverMessage = new ServerMessage(parser.getPrefix(), parser.getCommand(), parser.getParameters());
                if (serverMessage.isPing()) {
                    handler.sendCommand("/PONG " + serverMessage.getParameters());
                } else if (serverMessage.isJoinNewChannel(irc.getNick())) {
                    irc.joinChannel(serverMessage.getParameters());
                } else if (serverMessage.isLeaveChannel(irc.getNick())) {
                    String command = serverMessage.getCommand();
                    irc.leaveChannel(command.split(" ")[1]);
                } else if (serverMessage.isChannelMessage()) {
                    String command = serverMessage.getCommand();
                    String parameters = serverMessage.getParameters();
                    String user = serverMessage.getPrefix();
                    irc.addMessage(command.split(" ")[1], parameters, user);
                } else if (serverMessage.isPrivateMessage(irc.getNick())) {
                    String parameters = serverMessage.getParameters();
                    String command = serverMessage.getCommand();
                    String username = command.split(" ")[1];
                    if (!irc.isOpenChannel(username)) {
                        irc.joinChannel(username);
                    }
                    irc.addMessage(username, parameters, username);
                } else if (serverMessage.isUserList()) {
                    setUserList(serverMessage);
                } else {
                    irc.textDump(message + "\n");
                }
            }
        }
        

    }

    public void setUserList(ServerMessage serverMessage) {
        String parameters = serverMessage.getParameters();
        String[] splitParameters = parameters.split(" :");
        String channelName = splitParameters[0];
        String userList = splitParameters[1];
        String[] users = userList.split(" ");
        if(!irc.isOpenChannel(channelName)){
            System.err.println("error");
            return;
        }
        for (int i = 0; i < users.length; i++) {
            irc.addUser(channelName, users[i]);
        }
    }
}