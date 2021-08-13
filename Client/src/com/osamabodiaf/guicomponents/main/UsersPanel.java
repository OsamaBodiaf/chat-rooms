package com.osamabodiaf.guicomponents.main;

import com.osamabodiaf.Client;
import com.osamabodiaf.guicomponents.labels.PanelLabel;
import com.osamabodiaf.guicomponents.others.*;
import com.osamabodiaf.guicomponents.others.ScrollPane;

import javax.swing.*;
import java.awt.*;

public class UsersPanel extends JPanel {
    private PanelLabel usersLabel = new PanelLabel("Online Users");
    private DefaultListModel<UsersListItem> usersListModel = new DefaultListModel<>();
    private UsersList usersList = new UsersList(usersListModel);

    public UsersPanel(Client client) {
        setLayout(new BorderLayout());

        usersList.addListSelectionListener(e -> {
            if (usersList.getSelectedValue() == null)
                return;
            client.getClientGUIHandler().handleDestinationChange(usersList.getSelectedValue().toString());
            client.getClientGUI().getMainPanel()
                    .getSidePanel().getRoomsPanel().getTopicsList().clearSelection();
        });

        add(usersLabel, BorderLayout.NORTH);
        add(new ScrollPane(usersList), BorderLayout.CENTER);
    }

    public DefaultListModel<UsersListItem> getUsersListModel() {
        return usersListModel;
    }

    public UsersList getUsersList() {
        return usersList;
    }
}
