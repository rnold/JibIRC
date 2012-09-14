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
    
    public static ServerMessageParser parse(String message) {
        ServerMessageParser parser = new ServerMessageParser();
        Pattern p = Pattern.compile("(:(\\S+)!\\S+ )?([A-Z].*) :?(.+)");
        Matcher m = p.matcher(message);
        if (m.matches()) {
            parser.prefix = m.group(2);
            parser.command = m.group(3);
            parser.parameters = m.group(4);
            return parser;
        }
        
        p = Pattern.compile("");
        m = p.matcher(message);
        if (m.matches()) {
            
        }
        
        parser.wellFormed = false;
        return parser;
    }

    public String getPrefix() {
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
