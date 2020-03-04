package com.eranda;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Client {

    private static String username;
    private static HttpURLConnection httpURLConnection;
    private static URL url;
    private static final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("To connect to the server use 'connect <server ip-address>:<server port> as <Your name>': ");
        String response = scanner.nextLine();

        String[] parts = response.split("\\s+");
        String[] connection = parts[1].split(":");

        username = parts[3];
        String hostname = connection[0];
        int port = Integer.parseInt(connection[1]);

        url = new URL("http://" + hostname + ":" + port);
        httpURLConnection = (HttpURLConnection) url.openConnection();

        Client client = new Client();
        client.checkConnection();
    }

    private void checkConnection() throws IOException {
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);
        httpURLConnection.setDoOutput(true);
        int port = new Listener().listen();
        String userDetails = username + " " + port;

        try( DataOutputStream dataOutputStream = new DataOutputStream( httpURLConnection.getOutputStream())) {
            dataOutputStream.write( userDetails.getBytes() );
        }

        int responseCode = httpURLConnection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println("CONNECTED TO THE SERVER SUCCESSFULLY AS *" + username.toUpperCase() + "*!");
            new Writer(this, url).start();
        } else {
            System.out.println("GET request not worked");
        }

//        for(int i = 0; i < 10; i++){
//            System.out.println(httpURLConnection.getHeaderFieldKey(i) + " = " + httpURLConnection.getHeaderField(i));
//        }
    }

    public String getUsername() {
        return username;
    }
}