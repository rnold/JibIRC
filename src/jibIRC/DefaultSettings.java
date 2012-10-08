/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jibIRC;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author Welcome
 */
public class DefaultSettings {
    private String nick = "";
    private String name = "";
    private String serverName = "";
    private String port = "";
    
    public DefaultSettings(String nick, String name, String serverName, String port){
        this.nick = nick;
        this.name = name;
        this.serverName = serverName;
        this.port = port;
    }
    public static DefaultSettings getDefaultSettings(BufferedReader reader){
        String nick = "";
        String name = "";
        String serverName = "";
        String port = "";
        try{
        nick = reader.readLine();
        name = reader.readLine();
        serverName = reader.readLine();
        port = reader.readLine();
        }catch(IOException e){
            
        }
        return new DefaultSettings(nick, name, serverName, port);
    }
    
    public String getNick(){
        return nick;
    }
    
    public String getName() {
        return name;
    }
    
    public String getServerName() {
        return serverName;
    }
    
    public String getPort() {
        return port;
    }
}
