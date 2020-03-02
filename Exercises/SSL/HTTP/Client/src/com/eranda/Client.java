package com.eranda;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {

    private static String username;
    private static HttpURLConnection httpURLConnection;
    private static URL url;
    private static final String USER_AGENT = "Mozilla/5.0";

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


        url = new URL("http://" + hostname + ":" + port);
        httpURLConnection = (HttpURLConnection) url.openConnection();

        Client client = new Client();
        client.checkConnection();
    }

    private void checkConnection() throws IOException {

        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = httpURLConnection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println("Connected to the chat server successfully!");
            new ReadThread(httpURLConnection).start();
            new WriteThread(this, url).start();
        } else {
            System.out.println("POST request not worked");
        }
    }

    public String getUsername() {
        return username;
    }

    static void sendGET(String hostname, int port) throws IOException {
//        URL url = new URL("http://" + hostname + ":" + port);
//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        con.setRequestMethod("GET");
//        con.setRequestProperty("User-Agent", USER_AGENT);
//        int responseCode = con.getResponseCode();
//        System.out.println("GET Response Code :: " + responseCode);
//
//        if(responseCode == HttpURLConnection.HTTP_OK){
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//
//            while((inputLine = bufferedReader.readLine()) != null){
//                response.append(inputLine);
//            }
//            bufferedReader.close();
//
//            System.out.println(response.toString());
//        }else{
//            System.out.println("GET Request not worked!");
//        }
    }
}
