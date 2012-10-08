/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jibIRC;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Welcome
 */
public class ServerMessage {
    private String prefix;
    private String command;
    private List<String> parameters;
    boolean wellFormed = true;
    
    private ServerMessage(){
        parameters = new ArrayList<String>();
    }

    public ServerMessage(String prefix, String command, List<String> parameters){
        this.prefix = prefix;
        this.command = command;
        this.parameters = parameters;
    }
    
    public ServerMessage(String prefix, String command, String parameters){
        this.prefix = prefix;
        this.command = command;
        this.parameters = new ArrayList<String>();
        this.parameters.add(parameters);
    }
   
    public static ServerMessage getNewMessage(String message){
        ServerMessage serverMessage = new ServerMessage();
        Pattern p;
        Matcher m;

        p = Pattern.compile(":(\\S+!\\S+) (JOIN) :?(.+)");
        m = p.matcher(message);
        if (m.matches()) {
            serverMessage.prefix = m.group(1);
            serverMessage.command = m.group(2);
            serverMessage.parameters.add(m.group(3));
            return serverMessage;
        }
        
        //message
        p = Pattern.compile("(:(\\S+)!\\S+ )?(\\w+) :(.+)");
        m = p.matcher(message);
        if (m.matches()) {
            serverMessage.prefix = m.group(2);
            serverMessage.command = m.group(3);
            serverMessage.parameters.add(m.group(4));
            return serverMessage;
        }
        //privmsg
        p = Pattern.compile(":(\\S+)!\\S+ (\\w+ #?\\w+) :(.+)");
        m = p.matcher(message);
        if (m.matches()) {
            serverMessage.prefix = m.group(1);
            serverMessage.command = m.group(2);
            serverMessage.parameters.add(m.group(3));
            return serverMessage;
        }
        //part
        p = Pattern.compile(":(\\S+)!\\S+ (\\w+ #\\w+)");
        m = p.matcher(message);
        if (m.matches()) {
            serverMessage.prefix = m.group(1);
            serverMessage.command = m.group(2);
            serverMessage.parameters.add("");
            return serverMessage;
        }
        
        //user list
        p = Pattern.compile(":\\S+ 353 \\w+ \\S (#\\w+ :.+)");
        m = p.matcher(message);
        if (m.matches()) {
            serverMessage.prefix = "";
            serverMessage.command = "USERS";
            serverMessage.parameters.add(m.group(1));
            return serverMessage;
        }
        
        serverMessage.wellFormed = false;
        return serverMessage;
    }
    
    public static ServerMessage getServerMessage(String message){
        ServerMessage serverMessage = new ServerMessage();
        //strip prefix
        String messageWithoutPrefix = serverMessage.stripPrefix(message);
        //strip suffix
        String messageWithoutPrefixOrSuffix = serverMessage.stripSuffix(messageWithoutPrefix);
        //remainder is the command + parameters
        String command = serverMessage.stripParameters(messageWithoutPrefixOrSuffix);
        serverMessage.command = command;
        return serverMessage;
    }
    
    private String stripPrefix(String message){
        if(message.startsWith(":")){
            prefix = message.substring(1, endOfPrefix(message));
            return message.substring(endOfPrefix(message)+1);
        }else{
            prefix = "";
            return message;
        }

    }
    
    private static int endOfPrefix(String message){
        return message.indexOf(" ");
    }
    
    //must have prefix stripped first
    private String stripSuffix(String message){
        int startOfSuffix = message.indexOf(":");
        if(startOfSuffix != -1){
            parameters.add(message.substring(startOfSuffix+1, message.length()));
            return message.substring(0, startOfSuffix-1);
        }else{
            return message;
        }
    }
    
    private String stripParameters(String message){
        String[] splitMessage = message.split(" ");
        List<String> paramList = new ArrayList<String>();
        for(int i = 1; i < splitMessage.length; i++){
            paramList.add(splitMessage[i]);
        }
        parameters.addAll(0, paramList);
        return splitMessage[0];
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
    
    public boolean isNewUserList(){
        return command.equals("353");
    }
    
    public boolean isWellFormed(){
        return wellFormed;
    }
    
    public String getPrefix(){
        return prefix;
    }
    
    public String getCommand(){
        return command;
    }
    
    public List<String> getParameters(){
        return parameters;
    }
    
    @Override
    public String toString(){
        return prefix + " " + command + " " + parameters;
    }
}
