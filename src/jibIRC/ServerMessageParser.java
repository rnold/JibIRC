/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jibIRC;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author Welcome
 */
public class ServerMessageParser {
    String prefix;
    String command;
    String parameters;
    boolean wellFormed = true;
    
    private ServerMessageParser(){
    }
    
    public static ServerMessageParser parse(String message){
        ServerMessageParser parser = new ServerMessageParser();
        Pattern p = Pattern.compile(":(\\S+)!\\S+ (.+) :(.+)");
        Matcher m = p.matcher(message);
        if(!m.matches()){
            parser.wellFormed = false;
            return parser;
        }
        parser.prefix = m.group(1);
        parser.command = m.group(2);
        parser.parameters = m.group(3);
        return parser;
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
    
    public boolean isWellFormed(){
        return wellFormed;
    }

}
