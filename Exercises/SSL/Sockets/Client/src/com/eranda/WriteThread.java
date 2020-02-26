package com.eranda;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * This thread is responsible for reading user's input and send it
 * to the server.
 * It runs in an infinite loop until the user types 'bye' to quit.
 */
public class WriteThread extends Thread {
    private PrintWriter writer;
    private Socket socket;
    private ChatClient client;
	private String userName;
 
    public WriteThread(Socket socket, ChatClient client) {
        this.socket = socket;
        this.client = client;
 
        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            throw new RuntimeException("Error getting output stream!", ex);
        }
    }
 
    public void run() {

        userName = client.getUserName();
        writer.println(userName);

        Scanner scanner = new Scanner(System.in);
 
        String text;
        System.out.println("To send a message to a user, use 'send <message>-><Receiver>'");
 
        do {
            text = scanner.nextLine();

            if(!text.equals("bye")) {
                text = text.substring(4);
            }

            writer.println(text);
 
        } while (!text.equals("bye"));
		
        try {
            socket.close();
        } catch (IOException ex) {
            throw new RuntimeException("Error writing to server!", ex);
        }
    }
}