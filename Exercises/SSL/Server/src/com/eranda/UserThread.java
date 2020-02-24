package com.eranda;

import java.io.*;
import java.net.*;
import java.util.*;
 
/**
 * This thread handles connection for each connected client, so the server
 * can handle multiple clients at the same time.
 */
public class UserThread extends Thread {
    private Socket socket;
    private ChatServer server;
    private PrintWriter writer;
    private String userName;
 
    public UserThread(Socket socket, ChatServer server, String userName) {
        this.socket = socket;
        this.server = server;
        this.userName = userName;
    }
 
    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
 
            printUsers();
 
            String serverMessage = "New user [" + userName + "] connected to the chat!";
            server.broadcast(serverMessage, this);
 
            String clientMessage;
 
            do {
                clientMessage = reader.readLine();

                if(!clientMessage.equals("bye")) {
                    String[] parts = clientMessage.split("->");
                    serverMessage = "[" + userName + "]: " + parts[0];
                    server.broadcastToOne(serverMessage, parts[1]);
                }
 
            } while (!clientMessage.equals("bye"));
 
            server.removeUser(userName, this);
            socket.close();
 
            serverMessage = userName + " left the chat!";
            server.broadcast(serverMessage, this);
 
        } catch (IOException ex) {
            throw new RuntimeException("Error in UserThread!", ex);
        }
    }
 
    /**
     * Sends a list of online users to the newly connected user.
     */
    void printUsers() {
        if (server.hasUsers() && server.hasMoreThanOneUser()) {
            writer.println("Connected users: " + server.getUserNames());
        } else {
            writer.println("No other users connected");
        }
    }
 
    /**
     * Sends a message to the client.
     */
    void sendMessage(String message) {
        writer.println(message);
    }
}