package com.osamabodiaf;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server implements Runnable {
    private final int PORT;
    private Map<String, ServerWorker> onlineUsers;
    private Set<String> topics;

    public Server(int port) {
        PORT = port;
        onlineUsers = new HashMap<>();
        topics = new HashSet<>();
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ServerWorker(this, socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, ServerWorker> getOnlineUsers() {
        return onlineUsers;
    }

    public Set<String> getTopics() {
        return topics;
    }
}
