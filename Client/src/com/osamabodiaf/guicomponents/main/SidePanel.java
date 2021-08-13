package com.osamabodiaf.guicomponents.main;

import com.osamabodiaf.Client;
import com.osamabodiaf.guicomponents.UIResources;
import com.osamabodiaf.guicomponents.others.TopicsListItem;
import com.osamabodiaf.guicomponents.others.UsersListItem;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel {
    private TopicsPanel topicsPanel;
    private UsersPanel usersPanel;

    public SidePanel(Client client) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(220, 0));
        setBackground(UIResources.RED);

        topicsPanel = new TopicsPanel(client);
        usersPanel = new UsersPanel(client);

        add(topicsPanel, BorderLayout.NORTH);
        add(usersPanel, BorderLayout.CENTER);
    }

    public DefaultListModel<UsersListItem> getUsersListModel() {
        return usersPanel.getUsersListModel();
    }

    public DefaultListModel<TopicsListItem> getTopicsListModel() {
        return topicsPanel.getTopicsListModel();
    }

    public TopicsPanel getRoomsPanel() {
        return topicsPanel;
    }

    public UsersPanel getUsersPanel() {
        return usersPanel;
    }
}
