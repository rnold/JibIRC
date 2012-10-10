/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jibIRC;

import java.util.HashMap;

/**
 *
 * @author Welcome
 */
public class User implements Comparable<User>{

    private String username;
    private String hostName;
    private String mode;
    private static final HashMap<String, Integer> modeOrder = new HashMap<String, Integer>();
    private static final HashMap<String, String> modeString = new HashMap<String, String>();
    
    static{
        modeOrder.put("~", 5);
        modeOrder.put("&", 4);
        modeOrder.put("@", 3);
        modeOrder.put("%", 2);
        modeOrder.put("+", 1);
        modeOrder.put("", 0);
        modeString.put("+v", "+");
        modeString.put("+h", "%");
        modeString.put("+o", "@");
    }

    public User(String username, String hostName, String mode) {
        this.username = username;
        this.hostName = hostName;
        this.mode = mode;
    }

    public static User getUserFromPrefix(String prefix) {
        String[] userFields = prefix.split("!");
        return new User(userFields[0], userFields[1], "");
    }

    public static User getUserFromList(String listElement) {
        if (listElement.charAt(0) == '+'
                || listElement.charAt(0) == '@'
                || listElement.charAt(0) == '%'
                || listElement.charAt(0) == '~'
                || listElement.charAt(0) == '&') {
            return new User(listElement.substring(1), "", listElement.substring(0, 1));

        } else {
            return new User(listElement, "", "");
        }
    }

    public User modifyMode(String mode) {
        if (mode.startsWith("-")) {
            return new User(username, hostName, "");
        } else {
            String newMode = modeString.get(mode);
            if (newMode != null) {
                return new User(username, hostName, newMode);
            } else {
                return this;
            }
        }
    }

    public int compareTo(User user){
        if(username.equals(user.getUsername())){
            return 0;
        }
        int x = compareMode(user.getMode());
        if(x != 0){
            return x;
        }else{
            int y = compareUsername(user.getUsername());
            return y;
        }
    }
    
    private int compareMode(String mode){
        return modeOrder.get(this.mode).compareTo(modeOrder.get(mode));
    }
    
    private int compareUsername(String username){
        return -this.username.toLowerCase().compareTo(username.toLowerCase());
    }

    public String getUsername() {
        return username;
    }

    public String getHostName() {
        return hostName;
    }
    
    public String getMode(){
        return mode;
    }
    
    @Override
    public String toString(){
        return mode+username;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj instanceof User){
            User user = (User) obj;
            return this.username.equals(user.getUsername());
        }else{
            return false;
        }
    }
    
    @Override
    public int hashCode(){
        return username.hashCode();
    }
}
