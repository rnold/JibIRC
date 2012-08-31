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
    String message;
    String result;
    
    public ServerMessage(String message){
        this.message = message;
    }
    
    public boolean isJoinNewChannel() {
        if (isJoinMessage(message)) {
            result = message.split(" ")[2];
            return true;
        } else {
            return false;
            
        }
    }
    
    public boolean isJoinMessage(String message) {
        return message.contains("JOIN");
    }
    
    public String getResult(){
        return result;
    }
}
