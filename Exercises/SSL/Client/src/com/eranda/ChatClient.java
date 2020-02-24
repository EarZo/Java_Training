package com.eranda;

import java.net.*;
import java.io.*;
import java.util.Scanner;
 
/**
 * This is the chat client program.
 * Type 'bye' to terminate the program.
 */
public class ChatClient {
    private String hostname;
    private int port;
    private String userName;
 
    public void execute() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Client program started!"
							+"\n\nTo connect to the server use 'connect <server ip-address>:<server port> as <Your name>': ");
		String response = scanner.nextLine();
		
		String[] parts = response.split("\\s+");
		String[] connection = parts[1].split(":");
		
		userName = parts[3];
		hostname = connection[0];
		port = Integer.parseInt(connection[1]);
		
        try {
            Socket socket = new Socket(hostname, port);
 
            System.out.println("Connected to the chat server successfully!");
 
            new ReadThread(socket, this).start();
            new WriteThread(socket, this).start();
 
        } catch (UnknownHostException ex) {
            throw new RuntimeException("Server not found!", ex);
        } catch (IOException ex) {
            throw new RuntimeException("I/O Error!", ex);
        }
 
    }
 
    void setUserName(String userName) {
        this.userName = userName;
    }
 
    String getUserName() {
        return this.userName;
    }
 
 
    public static void main(String[] args) {
        ChatClient client = new ChatClient();
        client.execute();
    }
}