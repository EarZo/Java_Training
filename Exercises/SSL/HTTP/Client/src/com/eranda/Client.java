package com.eranda;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Client program started!"
                    +"\n\nTo connect to the server use 'connect <server ip-address>:<server port> as <Your name>': ");
            String response = scanner.nextLine();

            String[] parts = response.split("\\s+");
            String[] connection = parts[1].split(":");

            String username = parts[3];
            String hostname = connection[0];
            int port = Integer.parseInt(connection[1]);
            URL url = new URL("http://" + hostname + ":" + port + "/");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            System.setProperty("java.net.preferIPv4Stack" , "true");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            String jsonInputString = "{\"username\": \"" + username + "\"}";

            try(OutputStream outputStream = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }

            con.disconnect();
        } catch (IOException e) {
            throw new RuntimeException("IO Exception occurred in the client side!", e);
        }
    }
}
