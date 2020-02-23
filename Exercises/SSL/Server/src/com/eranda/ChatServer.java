package com.eranda;

import java.io.*;
import java.net.*;
import java.util.*;
 
/**
 * This is the chat server program.
 * Press Ctrl + C to terminate the program.
 */
public class ChatServer {
    private int port;
	private String userName;
    private Set<String> userNames = new HashSet<>();
    private Set<UserThread> userThreads = new HashSet<>();
	private Map<String, UserThread> userMap = new HashMap<String, UserThread>();
	private InputStream input;
	private BufferedReader reader;
 
    public void execute() {
	Scanner scanner = new Scanner(System.in);

	System.out.print("Please enter the port to run the server: ");
	port = Integer.parseInt(scanner.nextLine());

	try (ServerSocket serverSocket = new ServerSocket(port)) {
 
            System.out.println("Chat Server is listening on port " + port);
 
            while (true) {
                Socket socket = serverSocket.accept();
				input = socket.getInputStream();
				reader = new BufferedReader(new InputStreamReader(input));
				userName = reader.readLine();
                System.out.println(userName + " has connected to the server succesfully!");
 
                UserThread newUser = new UserThread(socket, this);
				userMap.put(userName, newUser);
                userThreads.add(newUser);
                newUser.start();
 
            }
 
        } catch (IOException ex) {
            throw new RuntimeException("Error in the server!", ex);
        }
    }
 
    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        server.execute();
    }
 
    /**
     * Delivers a message from one user to others (broadcasting)
     */
    void broadcast(String message, UserThread excludeUser) {
        for (UserThread aUser : userThreads) {
            if (aUser != excludeUser) {
                aUser.sendMessage(message);
            }
        }
    }
	
	
 
    /**
     * Delivers a message from one user to others (broadcasting)
     */
    void broadcastToOne(String message, String targetUser) {
		userMap.get(targetUser).sendMessage(message);
    }
 
    /**
     * Stores username of the newly connected client.
     */
    void addUserName(String userName) {
        userNames.add(userName);
    }
 
    /**
     * When a client is disconneted, removes the associated username and UserThread
     */
    void removeUser(String userName, UserThread aUser) {
        boolean removed = userNames.remove(userName);
        if (removed) {
            userThreads.remove(aUser);
            System.out.println("The user " + userName + " quitted");
        }
    }
 
    Set<String> getUserNames() {
        return this.userNames;
    }
 
    /**
     * Returns true if there are other users connected (not count the currently connected user)
     */
    boolean hasUsers() {
        return !this.userNames.isEmpty();
    }
}