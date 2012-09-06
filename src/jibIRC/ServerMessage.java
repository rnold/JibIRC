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
    
    public boolean isJoinNewChannel(String nick) {
           return prefix.equals(nick) && command.equals("JOIN");
    }
    
    public boolean isChannelMessage(){
        return command.matches("PRIVMSG #\\w+");
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
}
