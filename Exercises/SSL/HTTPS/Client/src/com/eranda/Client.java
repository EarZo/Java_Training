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
        System.out.println("Client program started!"
                + "\n\nTo connect to the server use 'connect <server ip-address>:<server port> as <Your name>': ");
        String response = scanner.nextLine();

        String[] parts = response.split("\\s+");
        String[] connection = parts[1].split(":");

        username = parts[3];
        String hostname = connection[0];
        int port = Integer.parseInt(connection[1]);

        url = new URL("http://" + hostname + ":" + port + "/handle");
        System.out.println(url.toString());
        url = new URL("http://" + hostname + ":" + port);
        httpURLConnection = (HttpURLConnection) url.openConnection();

        Client client = new Client();
        client.checkConnection();
    }

    private void checkConnection() throws IOException {

//        String command = "Hi to the server!";
//
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//        InputStream inputStream = conn.getInputStream();
//        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//
//        String inputLine;
//
//        while ((inputLine = bufferedReader.readLine()) != null) {
//            System.out.println(inputLine);
//        }
//
//        bufferedReader.close();
//        inputStreamReader.close();
//        inputStream.close();

        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = httpURLConnection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println("Connected to the chat server successfully!");
//            new ReadThread(httpURLConnection).start();
            new WriteThread(this, url).start();
        } else {
            System.out.println("GET request not worked");
        }
    }

    public String getUsername() {
        return username;
    }
}