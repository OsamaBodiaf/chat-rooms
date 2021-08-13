package com.osamabodiaf.guicomponents.login;

import com.osamabodiaf.Client;
import com.osamabodiaf.guicomponents.UIResources;
import com.osamabodiaf.guicomponents.buttons.ColoredButton;
import com.osamabodiaf.guicomponents.fields.BorderedPasswordField;
import com.osamabodiaf.guicomponents.fields.BorderedTextField;
import com.osamabodiaf.guicomponents.labels.ColoredLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BodyPanel extends JPanel {
    private GridBagConstraints gbc = new GridBagConstraints();
    private ColoredLabel userLabel = new ColoredLabel(UIResources.BLACK,"User:");
    private BorderedTextField userField = new BorderedTextField(20);
    private ColoredLabel passwordLabel = new ColoredLabel(UIResources.BLACK, "Password:");
    private BorderedPasswordField passwordField = new BorderedPasswordField(20);
    private ColoredButton loginButton = new ColoredButton(UIResources.WHITE, UIResources.BLUE,
            "Log In");
    private JSeparator signupSeparator = new JSeparator();
    private ColoredLabel notificationLabel = new ColoredLabel(UIResources.ORANGE,
            "Don't have an account? Sign Up!");

    public BodyPanel(Client client) {
        setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 10;

        gbc.gridy = 0;
        add(userLabel, gbc);

        gbc.gridy = 1;
        add(userField, gbc);

        gbc.insets = new Insets(5,0,0,0);
        gbc.gridy = 2;
        add(passwordLabel, gbc);

        gbc.insets = new Insets(0,0,0,0);
        gbc.gridy = 3;
        add(passwordField, gbc);

        gbc.insets = new Insets(15,0,0,0);
        gbc.gridy = 4;
        loginButton.addActionListener(e -> {
            String user = userField.getText();
            String password = String.valueOf(passwordField.getPassword());
            notificationLabel.setForeground(UIResources.RED);
            if (user.equals(""))
                notificationLabel.setText("User field cannot be empty!");
            else if (password.length() < 4)
                notificationLabel.setText("Password must be at least 4 characters!");
            else
                client.getClientWorker().login(user, password);
        });
        add(loginButton, gbc);

        gbc.gridy = 5;
        add(signupSeparator, gbc);

        gbc.insets = new Insets(0,0,0,0);
        gbc.ipady = 0;
        gbc.gridy = 6;
        notificationLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                client.getClientGUIHandler().showPanel("signup");
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        add(notificationLabel, gbc);
    }

    public ColoredLabel getNotificationLabel() {
        return notificationLabel;
    }
}
