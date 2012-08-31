/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jibIRC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Welcome
 */
public class IRCHandler {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;   

    public boolean connect(String server, int port, String nick, String name){
        try{
            socket = new Socket(server, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("NICK " + nick);
            out.println("USER " + nick + " " + nick + " " + server + " :" + name);
            //out.println("PROTOCTL NAMESX");
            return true;
        }catch(UnknownHostException e){
            return false;
        }catch(IOException e){
            return false;
        }
    }

    public void sendMessage(String message, String user){
        out.println("PRIVMSG " + user + " :" + message);
    }

    public void sendCommand(String command){
        String blah = command.substring(1);
        out.println(blah);

    }

    public void quit(){
        out.println("QUIT :Insert reason here");
    }



    public String receiveMessage(){
        try{
            if(in.ready()){
                String what = in.readLine();
                System.out.println(what);
                return what;
            }else{
                return null;
            }

        }catch(IOException e){
            return null;
        }
    }
    
    public boolean isInitialized(){
        return socket != null;
    }

}
