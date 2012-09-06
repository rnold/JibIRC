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
    
    public boolean isJoinNewChannel() {
        if (isJoinMessage(command)) {
            return true;
        } else {
            return false;
            
        }
    }
    
    private boolean isJoinMessage(String message) {
        return message.contains("JOIN");
    }
    
    public String getParameters(){
        return parameters;
    }
}
