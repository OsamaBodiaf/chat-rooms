package com.osamabodiaf.guicomponents.main;

import com.osamabodiaf.Client;
import com.osamabodiaf.guicomponents.UIResources;
import com.osamabodiaf.guicomponents.buttons.ColoredButton;
import com.osamabodiaf.guicomponents.labels.ColoredLabel;

import javax.swing.*;
import java.awt.*;

public class HeaderPanel extends JPanel {
    private ColoredLabel usernameLabel = new ColoredLabel(UIResources.SUBTLE_GRAY,
            "Logged in as @username");
    private JPanel buttonsPanel = new JPanel();
    private ColoredButton editButton = new ColoredButton(UIResources.BLUE, UIResources.SUBTLE_GRAY,
            "Edit Profile");
    private ColoredButton logoutButton = new ColoredButton(UIResources.BLUE, UIResources.SUBTLE_GRAY,
            "Log Out");

    public HeaderPanel(Client client) {
        setLayout(new BorderLayout());
        setBackground(UIResources.BLUE);
        usernameLabel.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
        add(usernameLabel, BorderLayout.WEST);
        editButton.addActionListener(e -> client.getClientWorker().details());
        buttonsPanel.add(editButton);
        logoutButton.addActionListener(e -> client.getClientWorker().logout());
        buttonsPanel.add(logoutButton);
        buttonsPanel.setOpaque(false);
        add(buttonsPanel, BorderLayout.EAST);
    }

    public ColoredLabel getUsernameLabel() {
        return usernameLabel;
    }
}
