package com.eranda;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;

public class Server {

    public static void main(String[] args) {
        try {
//            URL url = new URL("http://[::1]:8082/");
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setConnectTimeout(5000);
//            con.setReadTimeout(5000);


            HttpServer server = HttpServer.create(new InetSocketAddress(8082), 0);
            HttpContext httpContext = server.createContext("/");
            httpContext.setHandler(Server::handle);
            server.start();
        } catch (IOException e) {
            throw new RuntimeException("IO Exception occurred in the server side!", e);
        }
    }

    static void handle(HttpExchange httpExchange) throws IOException {
        System.out.println("Inside handle() method");
        String response = "Hi there!";
        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }
}
