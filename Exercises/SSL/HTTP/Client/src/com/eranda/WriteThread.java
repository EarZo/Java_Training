package com.eranda;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.Scanner;

public class WriteThread extends Thread {
    private PrintWriter writer;
    HttpURLConnection con;
    private Client client;
    private String userName;

    public WriteThread(Client client, HttpURLConnection con) {
        this.client = client;
        this.con = con;

        try {
            con.setDoOutput(true);
            OutputStream output = con.getOutputStream();
//            os.write(userName.getBytes());
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            throw new RuntimeException("Error getting output stream!", ex);
        }
    }

    public void run() {

        userName = client.getUsername();
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
    }
}