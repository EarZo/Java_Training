package com.eranda;

import java.io.*;
import java.net.*;

/**
 * This thread is responsible for reading server's input and printing it
 * to the console.
 * It runs in an infinite loop until the client disconnects from the server.
 */
public class ReadThread extends Thread {
    private BufferedReader reader;
    private Socket socket;
    private ChatClient client;

    public ReadThread(Socket socket, ChatClient client) {
        this.socket = socket;
        this.client = client;

        try {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException ex) {
            throw new RuntimeException("Error getting input stream!", ex);
        }
    }

    public void run() {

        try {
            while (true) {
                String response = reader.readLine();
                System.out.println(response);
            }
        } catch (SocketException ex){
            System.out.println("See you again soon!");
        } catch (IOException ex) {
            throw new RuntimeException("Error reading from server!", ex);
        }
    }
}