package com.osamabodiaf;

import com.osamabodiaf.guicomponents.UIResources;
import com.osamabodiaf.guicomponents.connect.ConnectPanel;
import com.osamabodiaf.guicomponents.edit.EditPanel;
import com.osamabodiaf.guicomponents.fields.ChatTextField;
import com.osamabodiaf.guicomponents.login.LogInPanel;
import com.osamabodiaf.guicomponents.main.ChatPanel;
import com.osamabodiaf.guicomponents.main.MainPanel;
import com.osamabodiaf.guicomponents.others.TopicsListItem;
import com.osamabodiaf.guicomponents.others.UsersListItem;
import com.osamabodiaf.guicomponents.signup.SignUpPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ClientGUIHandler {
    private Client client;
    private ClientGUI clientGUI;
    private ClientChatBuffer clientChatBuffer;
    private CardLayout cardLayout;
    private ConnectPanel connectPanel;
    private LogInPanel logInPanel;
    private SignUpPanel signUpPanel;
    private MainPanel mainPanel;
    private EditPanel editPanel;

    public ClientGUIHandler(Client client) {
        this.client = client;
        clientGUI = client.getClientGUI();
        clientChatBuffer = client.getClientChatBuffer();
        cardLayout = clientGUI.getCardLayout();
        connectPanel = clientGUI.getConnectPanel();
        logInPanel = clientGUI.getLogInPanel();
        signUpPanel = clientGUI.getSignUpPanel();
        mainPanel = clientGUI.getMainPanel();
        editPanel = clientGUI.getEditPanel();
    }

    public void showPanel(String name) {
        cardLayout.show(clientGUI.getContentPane(), name);
    }

    public void resetPanel(String name) {
        switch (name) {
            case "connect":
                clientGUI.remove(clientGUI.getConnectPanel());
                clientGUI.setConnectPanel(connectPanel = new ConnectPanel(client));
                clientGUI.add("connect", clientGUI.getConnectPanel());
                break;

            case "signup":
                clientGUI.remove(clientGUI.getSignUpPanel());
                clientGUI.setSignUpPanel(signUpPanel = new SignUpPanel(client));
                clientGUI.add("signup", clientGUI.getSignUpPanel());
                break;

            case "login":
                clientGUI.remove(clientGUI.getLogInPanel());
                clientGUI.setLogInPanel(logInPanel = new LogInPanel(client));
                clientGUI.add("login", clientGUI.getLogInPanel());
                break;

            case "main":
                clientGUI.remove(clientGUI.getMainPanel());
                clientGUI.setMainPanel(mainPanel = new MainPanel(client));
                clientGUI.add("main", clientGUI.getMainPanel());
                break;
        }
    }

    public void handleLogin(boolean isSuccessful, String[] tokens) {
        if (isSuccessful) {
            String username = tokens[0];
            mainPanel.getHeaderPanel().getUsernameLabel().setText("Logged in as @" + username);
            showPanel("main");
        }
        else {
            String errorMessage = tokens[1];
            logInPanel.getNotificationLabel().setText(errorMessage + " Sign Up?");
            logInPanel.getNotificationLabel().setForeground(UIResources.RED);
        }
    }

    public void handleLogin(boolean isSuccessful) {
        if (!isSuccessful)
            logInPanel.getNotificationLabel().setText("Login failed.");
    }

    public void handleOnline(String user) {
        mainPanel.getUsersListModel().addElement(new UsersListItem(user));
    }

    public void handleOffline(String user) {
        DefaultListModel<UsersListItem> listModel = mainPanel.getUsersListModel();
        for (int i = 0; i < listModel.getSize(); i++)
            if (listModel.getElementAt(i).getUsername().equals(user)) {
                listModel.remove(i);
                break;
            }
    }

    public void handleUsers(String[] users) {
        List<UsersListItem> usersList = new ArrayList<>();
        for (String user : users)
            usersList.add(new UsersListItem(user));
        mainPanel.getUsersListModel().removeAllElements();
        mainPanel.getUsersListModel().addAll(usersList);
    }

    public void handleTopics(String[] topics) {
        List<TopicsListItem> topicsList = new ArrayList<>();
        for (String topic : topics)
            topicsList.add(new TopicsListItem(topic));
        mainPanel.getTopicsListModel().addAll(topicsList);
    }

    public void handleCreate(boolean isSuccessful, String topic) {
        if (isSuccessful)
            mainPanel.getTopicsListModel().addElement(new TopicsListItem(topic));
    }

    public void handleCreate(boolean isSuccessful) {
        if (!isSuccessful) return;
    }

    public void handleSend(boolean isSuccessful) {
        ChatPanel chatPanel = mainPanel.getChatPanel();
        ChatTextField inputField = chatPanel.getChatInputPanel().getInputField();
        if (isSuccessful) {
            chatPanel.getChatOutputPanel().getCurrentConversation().addElement("you: " + inputField.getText());
            inputField.setText("");
        }
    }

    public void handleLogout(boolean isSuccessful) {
        if (isSuccessful) {
            resetPanel("main");
            resetPanel("login");
            showPanel("login");
        }
    }

    public void handleDestinationChange(String destination) {
        client.getClientWorker().setDestination(destination);
        DefaultListModel conversation = clientChatBuffer.getConversation(destination);
        if (conversation.getSize() == 0)
            client.getClientWorker().conversation(destination);
        ChatPanel chatPanel = clientGUI.getMainPanel().getChatPanel();
        chatPanel.getChatOutputPanel().setCurrentConversation(conversation);
        if (destination.startsWith("#")) {
            chatPanel.getDestinationLabel().setText("Chatting about " + destination + "...");
        }
        else {
            chatPanel.getDestinationLabel().setText("Chatting with @" + destination + "...");
        }
    }

    public void handleMessage(String source) {
        if (source.startsWith("#")) {
            DefaultListModel<TopicsListItem> listModel = mainPanel.getTopicsListModel();
            for (int i = 0; i < listModel.getSize(); i++) {
                TopicsListItem item = listModel.get(i);
                if (item.getTopic().equals(source)) {
                    item.getMessageIcon().setVisible(true);
                    break;
                }
            }
        }
        else {
            DefaultListModel<UsersListItem> listModel = mainPanel.getUsersListModel();
            for (int i = 0; i < listModel.getSize(); i++) {
                UsersListItem item = listModel.get(i);
                if (item.getUsername().equals(source)) {
                    item.getMessageIcon().setVisible(true);
                    break;
                }
            }
        }
        mainPanel.repaint();
    }

    public void handleEdit(String[] tokens) {
        editPanel.getFirstNameField().setText(tokens[0]);
        editPanel.getLastNameField().setText(tokens[1]);
        editPanel.getEmailField().setText(tokens[2]);
        editPanel.getUsernameField().setText(tokens[3]);
        editPanel.getPhoneField().setText(tokens[4]);
        editPanel.getAddressField().setText(tokens[5]);
        showPanel("edit");
    }

    public void updateUsername(String oldUsername, String newUsername) {
        DefaultListModel<UsersListItem> usersListModel = mainPanel.getUsersListModel();
        int size = usersListModel.getSize();
        int index;
        for (index = 0; index < size; index++) {
            if (oldUsername.equals(usersListModel.get(index).getUsername()))
                break;
        }
        if (index < size) {
            usersListModel.get(index).setUsername(newUsername);
            mainPanel.repaint();
        }
    }

    public void handleSignup(boolean isSuccessful, String[] tokens) {
        if (!isSuccessful)
            signUpPanel.getNotificationLabel().setText(tokens[1]);
    }
}
