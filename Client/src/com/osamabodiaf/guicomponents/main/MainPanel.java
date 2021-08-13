package com.osamabodiaf.guicomponents.main;

import com.osamabodiaf.Client;
import com.osamabodiaf.guicomponents.others.TopicsListItem;
import com.osamabodiaf.guicomponents.others.UsersListItem;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private HeaderPanel headerPanel;
    private ChatPanel chatPanel;
    private SidePanel sidePanel;

    public MainPanel(Client client) {
        headerPanel = new HeaderPanel(client);
        chatPanel = new ChatPanel(client);
        sidePanel = new SidePanel(client);

        setLayout(new BorderLayout());

        add(headerPanel, BorderLayout.NORTH);
        add(chatPanel, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.EAST);
    }

    public DefaultListModel<UsersListItem> getUsersListModel() {
        return sidePanel.getUsersListModel();
    }

    public DefaultListModel<TopicsListItem> getTopicsListModel() {
        return sidePanel.getTopicsListModel();
    }

    public HeaderPanel getHeaderPanel() {
        return headerPanel;
    }

    public ChatPanel getChatPanel() {
        return chatPanel;
    }

    public SidePanel getSidePanel() {
        return sidePanel;
    }
}
