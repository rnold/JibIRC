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
    
    //TODO: fix this retarded regex
    public static ServerMessageParser parse(String message) {
        Pattern p;
        Matcher m;
        ServerMessageParser parser = new ServerMessageParser();
        /*
        p = Pattern.compile("(:(\\S+)!\\S+ )?([A-Z].*)( :(.+))?");
        m = p.matcher(message);
        if (m.matches()) {
            parser.prefix = (m.group(2) != null) ? m.group(2) : "";
            parser.command = m.group(3);
            parser.parameters = (m.group(5) != null) ? m.group(5) : "";
            return parser;
        }*/
        
        //message
        p = Pattern.compile("(:(\\S+)!\\S+ )?(\\w+) :(.+)");
        m = p.matcher(message);
        if (m.matches()) {
            parser.prefix = m.group(2);
            parser.command = m.group(3);
            parser.parameters = m.group(4);
            return parser;
        }
        //join
        p = Pattern.compile(":(\\S+)!\\S+ (\\w+ #\\w+) :(.+)");
        m = p.matcher(message);
        if (m.matches()) {
            parser.prefix = m.group(1);
            parser.command = m.group(2);
            parser.parameters = m.group(3);
            return parser;
        }
        //part
        p = Pattern.compile(":(\\S+)!\\S+ (\\w+ #\\w+)");
        m = p.matcher(message);
        if (m.matches()) {
            parser.prefix = m.group(1);
            parser.command = m.group(2);
            parser.parameters = "";
            return parser;
        }
        
        //user list
        p = Pattern.compile(":\\w+.\\w+.\\w+ 353 \\w+ \\S (#\\w+ :.+)");
        m = p.matcher(message);
        if (m.matches()) {
            parser.prefix = "";
            parser.command = "USERS";
            parser.parameters = m.group(1);
            return parser;
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
