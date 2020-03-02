package com.eranda;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WriteThread extends Thread {
    private Client client;
    private String username;
    HttpURLConnection httpURLConnection;
    private static URL url;
    private static final String USER_AGENT = "Mozilla/5.0";

    public WriteThread(Client client, URL url) throws IOException {
        this.client = client;
        this.httpURLConnection = (HttpURLConnection) url.openConnection();
        this.username = client.getUsername();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        String text;
        System.out.println("To send a message to a user, use 'send <message>-><Receiver>'");

        try {
            do {
                text = scanner.nextLine();

                if (!text.equals("bye")) {
//                    text = text.substring(4);
                }

                sendPOST(text, httpURLConnection);

            } while (!text.equals("bye"));
        } catch (IOException ex) {
            throw new RuntimeException("Error writing to the stream!", ex);
        }
    }

    private void sendPOST(String text, HttpURLConnection httpURLConnection) throws IOException {

        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);

        // For POST only - START
        httpURLConnection.setDoOutput(true);
        OutputStream os = httpURLConnection.getOutputStream();
        os.write(text.getBytes());
        os.flush();
        os.close();
        // For POST only - END


        // For POST only - START
//        con.setDoOutput(true);
//        OutputStream os = con.getOutputStream();
//        os.write(username.getBytes());
//        os.flush();
//        os.close();
//        // For POST only - END
//
//        int responseCode = con.getResponseCode();
//        System.out.println("POST Response Code :: " + responseCode);
//
//        if (responseCode == HttpURLConnection.HTTP_OK) { //success
//            BufferedReader in = new BufferedReader(new InputStreamReader(
//                    con.getInputStream()));
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//
//            // print result
//            System.out.println(response.toString());
//        } else {
//            System.out.println("POST request not worked");
//        }
    }
}