/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jibIRC;

/**
 *
 * @author Welcome
 */
public class User {
    private String username;
    private String hostName;
    
    public User(String username, String hostName){
        this.username = username;
        this.hostName = hostName;
    }
    
    public static User getUserFromPrefix(String prefix){
        String[] userFields = prefix.split("!");
        return new User(userFields[0], userFields[1]);
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getHostName(){
        return hostName;
    }
    
}
