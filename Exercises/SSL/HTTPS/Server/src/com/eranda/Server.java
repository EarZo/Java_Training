package com.eranda;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private static ConcurrentHashMap<String, Integer> clients = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Please enter the port to run the server: ");
            int port = Integer.parseInt(scanner.nextLine());

            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/", new MyHandler());
            server.start();
            System.out.println("SERVER IS UP AND RUNNING!");
        } catch (IOException e) {
            throw new RuntimeException("IO Exception occurred in the server side!", e);
        }
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {

            if (httpExchange.getRequestBody().available() > 0) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody(), StandardCharsets.UTF_8));
                StringBuilder request = new StringBuilder();
                String requestLine;

                while ((requestLine = bufferedReader.readLine()) != null) {
                    request.append(requestLine.trim());
                }

                System.out.println(request.toString());

                if(!request.toString().equals("bye")) {
                    if(request.toString().contains("->")) {

                        String newResponse = "Hi there!";
                        httpExchange.sendResponseHeaders(200, newResponse.getBytes().length);
                        OutputStream outputStream = httpExchange.getResponseBody();
                        outputStream.write(newResponse.getBytes());
                        outputStream.close();
                        httpExchange.close();

                        String[] parts = request.toString().split("->");
                        URL url = getClientURL(parts[1]);
                        pushMessage(url, parts[0]);
                    }else{
                        String[] parts = request.toString().split("\\s+");
                        clients.put(parts[0], Integer.parseInt(parts[1]));
                    }
                }

                bufferedReader.close();
            }

            String response = "Hi there!";
            httpExchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream outputStream = httpExchange.getResponseBody();
            outputStream.write(response.getBytes());
            outputStream.close();
            httpExchange.close();
        }

        public URL getClientURL(String clientUsername) throws MalformedURLException {
            int clientPort = clients.get(clientUsername);
            return new URL("http://localhost:" + clientPort);
        }

        public void pushMessage(URL url, String message) throws IOException {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
            httpURLConnection.setDoOutput(true);

            try( DataOutputStream dataOutputStream = new DataOutputStream( httpURLConnection.getOutputStream())) {
                dataOutputStream.write( message.getBytes() );
            }

            int responseCode = httpURLConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Message delivered successfully!");
            } else {
                System.out.println("GET request not worked");
            }
        }
    }

}
