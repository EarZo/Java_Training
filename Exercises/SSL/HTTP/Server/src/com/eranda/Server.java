package com.eranda;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Server {

    public static void main(String[] args) {
        try {
//            URL url = new URL("http://[::1]:8082/");
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setConnectTimeout(5000);
//            con.setReadTimeout(5000);


            HttpServer server = HttpServer.create(new InetSocketAddress(8082), 0);
            server.createContext("/", new MyHandler());
            server.setExecutor(null); // creates a default executor
            server.start();
        } catch (IOException e) {
            throw new RuntimeException("IO Exception occurred in the server side!", e);
        }
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            while (true) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;

                    while (true) {
                        if (((responseLine = br.readLine()) != null)) {
                            response.append(responseLine.trim());
                            break;
                        }
                    }
                    System.out.println(response.toString());
                }
            }
        }
    }
}
