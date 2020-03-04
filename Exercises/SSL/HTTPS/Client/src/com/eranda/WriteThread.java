package com.eranda;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WriteThread extends Thread {
    private Client client;
    private String username;
    HttpURLConnection httpURLConnection;
    private static URL url;
    private static final String USER_AGENT = "Mozilla/5.0";

    public WriteThread(Client client, URL url) {
        this.client = client;
        this.url = url;
        this.username = client.getUsername();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        String text;
        System.out.println("\nTo send a message to a user, use 'send <message>-><Receiver>'\n");

        try {
            do {
                System.out.print("Waiting for input: ");
                text = scanner.nextLine();

//                if (!text.equals("bye")) {
//                    text = text.substring(4);
//                }

                sendPOST(text);

            } while (!text.equals("bye"));
        } catch (IOException ex) {
            throw new RuntimeException("Error writing to the stream!", ex);
        }
    }

    private void sendPOST(String text) throws IOException {

        httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);
        httpURLConnection.setDoOutput(true);

        try( DataOutputStream dataOutputStream = new DataOutputStream( httpURLConnection.getOutputStream())) {
            dataOutputStream.write( text.getBytes() );
        }

        int responseCode = httpURLConnection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println("Message delivery successful!");

            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println(response.toString());
        } else {
            System.out.println("POST request not worked");
        }
    }
}
