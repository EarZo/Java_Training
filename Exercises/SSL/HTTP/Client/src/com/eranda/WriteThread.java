package com.eranda;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
        this.url = url;
        this.username = client.getUsername();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        String text;
        System.out.println("To send a message to a user, use 'send <message>-><Receiver>'");

        try {
            do {
                System.out.println("Waiting for input!");
                text = scanner.nextLine();
                System.out.println("Input received!");
                if (!text.equals("bye")) {
//                    text = text.substring(4);
                }

                sendPOST(text);

            } while (!text.equals("bye"));
        } catch (IOException ex) {
            throw new RuntimeException("Error writing to the stream!", ex);
        }
    }

    private void sendPOST(String text) throws IOException {

        System.out.println("Posting to server!");

        System.out.println(url.toString());
        httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);

        // For POST only - START
        httpURLConnection.setDoOutput(true);

        try( DataOutputStream dataOutputStream = new DataOutputStream( httpURLConnection.getOutputStream())) {
            dataOutputStream.write( text.getBytes() );
        }

        int responseCode = httpURLConnection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println("Message delivery successful!");
        } else {
                System.out.println("POST request not worked");
        }
//        OutputStream os = httpURLConnection.getOutputStream();
//        os.write(text.getBytes());
//        os.flush();
//        os.close();
        // For POST only - END

        System.out.println("Post complete!!");



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
