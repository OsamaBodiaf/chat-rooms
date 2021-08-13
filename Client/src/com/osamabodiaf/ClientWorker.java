package com.osamabodiaf;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;

public class ClientWorker {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private ClientGUIHandler clientGUIHandler;
    private ClientChatBuffer clientChatBuffer;
    private String username;
    private String destination;

    public ClientWorker(Client client) {
        clientGUIHandler = client.getClientGUIHandler();
        clientChatBuffer = client.getClientChatBuffer();
    }

    private void handleResponses() {
        Exchange exchange;
        while ((exchange = readExchange()) != null) {
            String action = exchange.action;
            String status = exchange.status;
            String[] tokens = exchange.tokens;
            if ("login".equalsIgnoreCase(action))
                onLogin(exchange);
            else if ("logout".equalsIgnoreCase(action))
                onLogout(status);
            else if ("delivery".equalsIgnoreCase(action))
                onDelivery(status);
            else if ("users".equalsIgnoreCase(action))
                onUsers(tokens);
            else if ("topics".equalsIgnoreCase(action))
                onTopics(tokens);
            else if ("create".equalsIgnoreCase(action))
                onCreate(exchange);
            else if ("online".equalsIgnoreCase(action))
                onOnline(tokens[0]);
            else if ("offline".equalsIgnoreCase(action))
                onOffline(tokens[0]);
            else if ("message".equalsIgnoreCase(action))
                onMessage(tokens);
            else if ("details".equalsIgnoreCase(action))
                onDetails(tokens);
            else if ("update".equalsIgnoreCase(action))
                onUpdate(tokens);
            else if ("signup".equalsIgnoreCase(action))
                onSignup(exchange);
            else if ("conversation".equalsIgnoreCase(action))
                onConversation(tokens);
        }
    }

    private void onConversation(String[] tokens) {
        clientChatBuffer.getConversation(destination).addAll(Arrays.asList(tokens));
    }

    private void onSignup(Exchange exchange) {
        String[] tokens = exchange.tokens;
        if ("ok".equalsIgnoreCase(exchange.status)) {
            clientGUIHandler.handleSignup(true, tokens);
        }
        else
            clientGUIHandler.handleSignup(false, tokens);
    }

    private void onUpdate(String[] tokens) {
        String oldUsername = tokens[0];
        String newUsername = tokens[1];

        DefaultListModel conversation = clientChatBuffer.removeConversation(oldUsername);
        if (conversation == null)
            conversation = new DefaultListModel();
        clientChatBuffer.addConversation(newUsername, conversation);
        clientGUIHandler.updateUsername(oldUsername, newUsername);

        if (oldUsername.equals(username)) {
            username = newUsername;
            clientGUIHandler.handleLogin(true, new String[]{username});
        }
        else if (oldUsername.equals(destination)) {
            destination = newUsername;
            clientGUIHandler.handleDestinationChange(destination);
        }
    }

    private void onDetails(String[] tokens) {
        clientGUIHandler.handleEdit(tokens);
    }

    private void onLogin(Exchange exchange) {
        String[] tokens = exchange.tokens;
        if ("ok".equalsIgnoreCase(exchange.status)) {
            clientGUIHandler.handleLogin(true, tokens);
            username = tokens[0];
            sendExchange(new Exchange("users"));
            sendExchange(new Exchange("topics"));
        }
        else
            clientGUIHandler.handleLogin(false, tokens);
    }

    private void onCreate(Exchange exchange) {
        if ("ok".equalsIgnoreCase(exchange.status))
            clientGUIHandler.handleCreate(true, exchange.tokens[0]);
        else
            clientGUIHandler.handleCreate(false);
    }

    private void onMessage(String[] tokens) {
        String source = tokens[0];
        String body = tokens[1];
        String[] temp = source.split("/");
        String userOrTopic = temp[0];
        String user = temp.length == 1 ? temp[0] : temp[1];
        clientChatBuffer.getConversation(userOrTopic).addElement(user + ": " + body);
        clientGUIHandler.handleMessage(userOrTopic);
    }

    private void onDelivery(String status) {
        if (status.equalsIgnoreCase("ok"))
            clientGUIHandler.handleSend(true);
    }

    private void onTopics(String[] tokens) {
        clientGUIHandler.handleTopics(tokens);
    }

    private void onUsers(String[] tokens) {
        clientGUIHandler.handleUsers(tokens);
    }

    private void onOnline(String user) {
        clientGUIHandler.handleOnline(user);
    }

    private void onOffline(String user) {
        clientGUIHandler.handleOffline(user);
    }

    private void onLogout(String status) {
        if ("ok".equalsIgnoreCase(status))
            clientGUIHandler.handleLogout(true);
    }

    private void sendExchange(Exchange exchange) {
        try {
            out.writeObject(exchange);
        } catch (IOException exception) {
//            exception.printStackTrace();
        }
    }

    private Exchange readExchange() {
        try {
            return (Exchange) in.readObject();
        } catch (ClassNotFoundException exception) {
//            exception.printStackTrace();
        } catch (IOException exception) {
//            exception.printStackTrace();
        }
        return null;
    }

    public void login(String user, String password) {
        sendExchange(new Exchange("login", new String[]{user, password}));
    }

    public void signup(String email, String password) {
        sendExchange(new Exchange("signup", new String[]{email, password}));
    }

    public void create(String topic) {
        sendExchange(new Exchange("create", new String[]{topic}));
    }

    public void message(String body) {
        sendExchange(new Exchange("message", new String[]{destination, body}));
    }

    public void logout() {
        sendExchange(new Exchange("logout"));
    }

    public void details() { sendExchange(new Exchange("details")); }

    public boolean connect(String host, int port) {
        try {
            socket = new Socket(InetAddress.getByName(host), port);
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            new Thread(() -> handleResponses()).start();
            return true;
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public void disconnect() {
        try {
            socket.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void update(String firstName, String lastName, String email, String username,
                       String phone, String address, String password) {
        sendExchange(new Exchange("update",
                new String[]{firstName, lastName, email, username, phone, address, password}));
    }

    public void conversation(String destination) {
        sendExchange(new Exchange("conversation", new String[]{destination}));
    }
}
