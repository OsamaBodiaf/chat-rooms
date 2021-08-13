package com.osamabodiaf.guicomponents.others;

import javax.swing.*;
import java.awt.*;

public class UsersListItem extends JPanel {
    private JLabel statusIcon;
    private JLabel usernameLabel;
    private JLabel messageIcon;

    public UsersListItem(String username) {
        statusIcon = new JLabel(new ImageIcon("assets\\message.png",
                "status icon"), JLabel.CENTER);
        usernameLabel = new JLabel(username);
        messageIcon = new JLabel(new ImageIcon("assets\\message.png",
                "image icon"), JLabel.CENTER);

        setLayout(new BorderLayout());
//        add(statusIcon, BorderLayout.WEST);
        add(usernameLabel, BorderLayout.CENTER);
        messageIcon.setVisible(false);
        add(messageIcon, BorderLayout.EAST);
        setBorder(BorderFactory.createEmptyBorder(4,7,4,7));
    }

    public JLabel getStatusIcon() {
        return statusIcon;
    }

    public JLabel getUserLabel() {
        return usernameLabel;
    }

    public JLabel getMessageIcon() {
        return messageIcon;
    }

    public String getUsername() {
        return usernameLabel.getText();
    }

    public void setUsername(String username) {
        usernameLabel.setText(username);
    }

    @Override
    public String toString() {
        return getUsername();
    }
}
