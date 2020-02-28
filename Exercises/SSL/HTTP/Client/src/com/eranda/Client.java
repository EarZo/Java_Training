package com.eranda;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static String username;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Client program started!"
                +"\n\nTo connect to the server use 'connect <server ip-address>:<server port> as <Your name>': ");
        String response = scanner.nextLine();

        String[] parts = response.split("\\s+");
        String[] connection = parts[1].split(":");

        username = parts[3];
        String hostname = connection[0];
        int port = Integer.parseInt(connection[1]);

        Client client = new Client();
        client.sendPOST(username, hostname, port);
//        sendGET(hostname, port);
//        System.out.println("GET Done!");
    }

    static void sendGET(String hostname, int port) throws IOException {
        URL url = new URL("http://" + hostname + ":" + port);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if(responseCode == HttpURLConnection.HTTP_OK){
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while((inputLine = bufferedReader.readLine()) != null){
                response.append(inputLine);
            }
            bufferedReader.close();

            System.out.println(response.toString());
        }else{
            System.out.println("GET Request not worked!");
        }
    }

    private void sendPOST(String username, String hostname, int port) throws IOException {
        URL url = new URL("http://" + hostname + ":" + port);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);

        // For POST only - START
//        con.setDoOutput(true);
//        OutputStream os = con.getOutputStream();
//        os.write(username.getBytes());
//        os.flush();
//        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println("Connected to the chat server successfully!");
            new ReadThread(this, con).start();
            new WriteThread(this, hostname, port).start();
        } else {
            System.out.println("POST request not worked");
        }

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

    public String getUsername() {
        return username;
    }
}
