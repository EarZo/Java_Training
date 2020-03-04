package com.eranda;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Please enter the port to run the server: ");
            int port = Integer.parseInt(scanner.nextLine());

            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/", new MyHandler());
            server.start();
            System.out.println("Server started successfully!");
        } catch (IOException e) {
            throw new RuntimeException("IO Exception occurred in the server side!", e);
        }
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {

            if(httpExchange.getRequestBody().available() > 0) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()));
                System.out.println(bufferedReader.readLine());
                bufferedReader.close();
            }

            System.out.println(httpExchange.getRemoteAddress().toString());

            String response = "Hi there!";
            httpExchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream outputStream = httpExchange.getResponseBody();
            outputStream.write(response.getBytes());
            outputStream.close();
            httpExchange.close();
        }
    }

}
