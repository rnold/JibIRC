/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jibIRC;

/**
 *
 * @author Welcome
 */
public class ServerMessage {
    String prefix;
    String command;
    String parameters;
    
    public ServerMessage(String prefix, String command, String parameters){
        this.prefix = prefix;
        this.command = command;
        this.parameters = parameters;
    }
    
    public boolean isJoinChannel() {
           return command.equals("JOIN");
    }
    
    public boolean isChannelMessage(){
        return command.matches("PRIVMSG #\\w+");
    }
    
    public boolean isPrivateMessage(String nick){
        return command.matches("PRIVMSG " + nick);
    }
    
    public boolean isPing(){
        return command.equals("PING");
    }
    
    public boolean isLeaveChannel(){
        return command.startsWith("PART");
    }
    
    public boolean isUserList(){
        return command.equals("USERS");
    }
    
    public String getPrefix(){
        return prefix;
    }
    
    public String getCommand(){
        return command;
    }
    
    public String getParameters(){
        return parameters;
    }
    
    @Override
    public String toString(){
        return prefix + " " + command + " " + parameters;
    }
}
