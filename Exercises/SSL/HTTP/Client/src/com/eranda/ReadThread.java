package com.eranda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketException;

public class ReadThread extends Thread {
    private BufferedReader reader;
    HttpURLConnection con;

    public ReadThread(Client client, HttpURLConnection con) {
        this.con = con;

        try {
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
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
