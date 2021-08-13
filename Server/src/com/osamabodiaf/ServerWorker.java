package com.osamabodiaf;

import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServerWorker implements Runnable {
    private Server server;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String username;
    private String onlineStatus;
    private Statement statement;
    private ResultSet resultSet;

    public ServerWorker(Server server, Socket socket) throws IOException {
        this.server = server;
        this.socket = socket;
        onlineStatus = "offline";
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        connectToDB();
    }

    private void connectToDB() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root");
            statement = connection.createStatement();
            statement.executeUpdate("USE chatrooms;");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void run() {
        handleRequests();
    }

    private void handleRequests() {
        Exchange exchange;
        while ((exchange = readExchange()) != null) {
            String action = exchange.action;
            String[] tokens = exchange.tokens;
            if ("message".equalsIgnoreCase(action)) {
                handleMessage(tokens);
            } else if ("login".equalsIgnoreCase(action)) {
                handleLogin(tokens);
            } else if ("logout".equalsIgnoreCase(action)) {
                handleLogout();
            } else if ("users".equalsIgnoreCase(action)) {
                handleUsers();
            } else if ("topics".equalsIgnoreCase(action)) {
                handleTopics();
            } else if ("create".equalsIgnoreCase(action)) {
                handleCreate(tokens);
            } else if ("details".equalsIgnoreCase(action)) {
                handleDetails();
            } else if ("update".equalsIgnoreCase(action)) {
                handleUpdate(tokens);
            } else if ("signup".equalsIgnoreCase(action)) {
                handleSignup(tokens);
            } else if ("conversation".equalsIgnoreCase(action)) {
                handleConversation(tokens[0]);
            }
        }
        disconnect();
    }

    private void handleConversation(String destination) {
        List<String> tokensList = new ArrayList<>();
        try {
            resultSet = statement.executeQuery("SELECT * FROM messages WHERE " +
                    "(source = '" + username + "' AND destination = '" + destination + "') " +
                    "OR (source = '" + destination + "' AND destination = '" + username + "')");
            while (resultSet.next()) {
                if (resultSet.getString("source").equals(username))
                    tokensList.add("you: " + resultSet.getString("body"));
                else
                    tokensList.add(destination + ": " + resultSet.getString("body"));
            }
            String[] tokens = tokensList.toArray(new String[0]);
            sendExchange(new Exchange("conversation", tokens));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private void handleUpdate(String[] tokens) {
        String firstName = tokens[0];
        String lastName = tokens[1];
        String email = tokens[2];
        String username = tokens[3].toLowerCase();
        String phone = tokens[4];
        String address = tokens[5];
        String password = tokens[6];
        try {
            resultSet = statement.executeQuery("SELECT * FROM users WHERE username = '" + username + "'");
            if (username.equals(this.username) || !resultSet.next()) {
                statement.executeUpdate("UPDATE users SET " +
                        "first_name = '" + firstName + "', " +
                        "last_name = '" + lastName + "', " +
                        "email = '" + email + "', " +
                        "username = '" + username + "', " +
                        "phone = '" + phone + "', " +
                        "address = '" + address + "' " +
                        "WHERE username = '" + this.username + "'");
                if (!password.equals(""))
                    statement.executeUpdate("UPDATE users SET " +
                            "password = '" + PasswordUtils.encryptPassword(password) + "' " +
                            "WHERE username = '" + username + "'");
                broadcastUsernameUpdate(this.username, username);
                this.username = username;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private void broadcastUsernameUpdate(String oldUsername, String newUsername) {
        server.getOnlineUsers().remove(oldUsername);
        server.getOnlineUsers().put(newUsername, this);
        for (ServerWorker worker : server.getOnlineUsers().values())
            worker.sendExchange(new Exchange("update", new String[]{oldUsername, newUsername}));
    }

    private void handleDetails() {
        try {
            resultSet = statement.executeQuery("SELECT * FROM users WHERE username = '" + username + "'");
            resultSet.next();
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            String username = resultSet.getString("username");
            String phone = resultSet.getString("phone");
            String address = resultSet.getString("address");
            sendExchange(new Exchange("details",
                    new String[]{firstName, lastName, email, username, phone, address}));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private void handleSignup(String[] tokens) {
        if (tokens.length == 2) {
            String email = tokens[0];
            String encryptedPassword = PasswordUtils.encryptPassword(tokens[1]);
            try {
                resultSet = statement.executeQuery("SELECT * FROM users WHERE email = '" + email + "'");
                if (!resultSet.next()) {
                    resultSet = statement.executeQuery("SELECT AUTO_INCREMENT " +
                                    "FROM information_schema.TABLES " +
                                    "WHERE TABLE_SCHEMA = 'chatrooms' " +
                                    "AND TABLE_NAME = 'users'");
                    resultSet.next();
                    String nextId = resultSet.getString("AUTO_INCREMENT");
                    statement.executeUpdate("INSERT INTO users (username, email, password) " +
                            "VALUES ('user_" + nextId + "', '" + email + "', '" + encryptedPassword + "')");

                    handleLogin(tokens);
                }
                else
                    sendExchange(new Exchange("signup", "error",
                            new String[]{"0", "Email already exists!"}));
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        else
            sendExchange(new Exchange("signup", "error"));
    }

    private String userIdentifierType(String token) {
        if (token.contains("@"))
                return "email";
        else if ("+0123456789".contains(String.valueOf(token.charAt(0))))
            return "phone";
        else
            return "username";
    }

    private void handleLogin(String[] tokens) {
        if (tokens.length == 2) {
            String user = tokens[0];
            String password = tokens[1];
            try {
                resultSet = statement.executeQuery("SELECT * FROM users WHERE " +
                        userIdentifierType(user) + " = '" + user + "'");
                if (resultSet.next()) {
                    String encryptedPassword = resultSet.getString("password");
                    if (PasswordUtils.verifyPassword(password, encryptedPassword)) {
                        String username = resultSet.getString("username");
                        this.username = username;
                        server.getOnlineUsers().put(username, this);
                        onlineStatus = "online";
                        broadcastUserStatus(onlineStatus);
                        sendExchange(new Exchange("login", "ok", new String[]{username}));
                    }
                    else
                        sendExchange(new Exchange("login", "error",
                            new String[]{"1", "Wrong password!"}));
                }
                else
                    sendExchange(new Exchange("login", "error",
                            new String[]{"0", "User does not exist!"}));
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        else
            sendExchange(new Exchange("login", "error"));
    }

    private void handleCreate(String[] tokens) {
        String topic = "#" + tokens[0].replace("#", "");
        server.getTopics().add(topic);
        broadcastNewTopic(topic);
    }

    private void handleMessage(String[] tokens) {
        String destination = tokens[0];
        String body = tokens[1];
        if (destination.startsWith("#")) {
            for (ServerWorker serverWorker : server.getOnlineUsers().values())
                if (serverWorker != this)
                    serverWorker.sendExchange(
                            new Exchange("message", new String[]{destination + "/" + username, body}));
        }
        else {
            ServerWorker recipientWorker = server.getOnlineUsers().get(destination);
            if (recipientWorker != null)
                recipientWorker.sendExchange(new Exchange("message", new String[]{username, body}));
        }
        sendExchange(new Exchange("delivery", "ok"));
        try {
            statement.executeUpdate("INSERT INTO messages (source, destination, body)" +
                    "VALUES ('" + username + "', '" + destination + "', '" + body + "')");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private void handleTopics() {
        String[] topics = server.getTopics().toArray(new String[0]);
        sendExchange(new Exchange("topics", topics));
    }

    private void handleUsers() {
        List<String> usernames = new ArrayList<>();
        for (String username : server.getOnlineUsers().keySet())
            if (!username.equals(this.username))
                usernames.add(username);
        sendExchange(new Exchange("users", usernames.toArray(new String[0])));
    }

    private void handleLogout() {
        server.getOnlineUsers().remove(username);
        sendExchange(new Exchange("logout", "ok"));
        broadcastUserStatus("offline");
    }

    private void broadcastUserStatus(String status) {
        for (ServerWorker worker : server.getOnlineUsers().values())
            if (worker != this)
                worker.sendExchange(new Exchange(status, new String[]{username}));
    }

    private void broadcastNewTopic(String topic) {
        for (ServerWorker worker : server.getOnlineUsers().values())
            worker.sendExchange(new Exchange("create", "ok", new String[]{topic}));
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

    private void disconnect() {
        try {
            socket.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}