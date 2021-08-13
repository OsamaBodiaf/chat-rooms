package com.osamabodiaf.guicomponents.edit;

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
    private ColoredLabel firstNameLabel = new ColoredLabel(UIResources.BLACK,"First Name:");
    private BorderedTextField firstNameField = new BorderedTextField(20);
    private ColoredLabel lastNameLabel = new ColoredLabel(UIResources.BLACK,"Last Name:");
    private BorderedTextField lastNameField = new BorderedTextField(20);
    private ColoredLabel emailLabel = new ColoredLabel(UIResources.BLACK,"Email:");
    private BorderedTextField emailField = new BorderedTextField(20);
    private ColoredLabel usernameLabel = new ColoredLabel(UIResources.BLACK,"Username:");
    private BorderedTextField usernameField = new BorderedTextField(20);
    private ColoredLabel phoneLabel = new ColoredLabel(UIResources.BLACK,"Phone:");
    private BorderedTextField phoneField = new BorderedTextField(20);
    private ColoredLabel addressLabel = new ColoredLabel(UIResources.BLACK,"Address:");
    private BorderedTextField addressField = new BorderedTextField(20);
    private ColoredLabel passwordLabel = new ColoredLabel(UIResources.BLACK, "New Password:");
    private BorderedPasswordField passwordField = new BorderedPasswordField(20);
    private ColoredLabel passwordConfirmationLabel = new ColoredLabel(UIResources.BLACK, "Confirm New Password:");
    private BorderedPasswordField passwordConfirmationField = new BorderedPasswordField(20);
    private ColoredButton cancelButton = new ColoredButton(UIResources.DARK_GRAY, UIResources.GRAY,
            "Cancel");
    private ColoredButton updateButton = new ColoredButton(UIResources.WHITE, UIResources.BLUE,
            "Update");
    private JSeparator editSeparator = new JSeparator();
    private ColoredLabel notificationLabel = new ColoredLabel(UIResources.ORANGE,
            "TIP: Leave the password fields blank to keep using the old one.");

    public BodyPanel(Client client) {
        setLayout(new GridBagLayout());
        int paddingX = 5, paddingY = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 10;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,0, paddingX);
        add(firstNameLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0,0,0, paddingX);
        add(firstNameField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, paddingX,0,0);
        add(lastNameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, paddingX,0,0);
        add(lastNameField, gbc);

        gbc.insets = new Insets(paddingY,0,0, paddingX);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(emailLabel, gbc);

        gbc.insets = new Insets(0,0,0,5);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(emailField, gbc);

        gbc.insets = new Insets(paddingY, paddingX,0,0);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(usernameLabel, gbc);

        gbc.insets = new Insets(0, paddingX,0,0);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(usernameField, gbc);

        gbc.insets = new Insets(paddingY,0,0, paddingX);
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(phoneLabel, gbc);

        gbc.insets = new Insets(0,0,0,5);
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(phoneField, gbc);

        gbc.insets = new Insets(paddingY, paddingX,0,0);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(addressLabel, gbc);

        gbc.insets = new Insets(0, paddingX,0,0);
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(addressField, gbc);

        gbc.insets = new Insets(paddingY,0,0, paddingX);
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(passwordLabel, gbc);

        gbc.insets = new Insets(0,0,0, paddingX);
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(passwordField, gbc);

        gbc.insets = new Insets(paddingY, paddingX,0,0);
        gbc.gridx = 1;
        gbc.gridy = 6;
        add(passwordConfirmationLabel, gbc);

        gbc.insets = new Insets(0, paddingX,0,0);
        gbc.gridx = 1;
        gbc.gridy = 7;
        add(passwordConfirmationField, gbc);

        gbc.insets = new Insets(paddingY + 10, 0,0, paddingX);
        gbc.gridx = 0;
        gbc.gridy = 8;
        cancelButton.addActionListener(e -> client.getClientGUIHandler().showPanel("main"));
        add(cancelButton, gbc);

        gbc.insets = new Insets(paddingY + 10, paddingX,0, 0);
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 8;
        updateButton.addActionListener(e -> {
            String password = String.valueOf(passwordField.getPassword());
            String passwordConfirmation = String.valueOf(passwordConfirmationField.getPassword());
            if (password.equals(passwordConfirmation))
                client.getClientWorker().update(
                    firstNameField.getText(), lastNameField.getText(),
                    emailField.getText(), usernameField.getText(),
                    phoneField.getText(), addressField.getText(), password
                    );
            else {
                notificationLabel.setForeground(UIResources.RED);
                notificationLabel.setText("Passwords must match!");
            }
        });
        add(updateButton, gbc);

        gbc.insets = new Insets(paddingY + 10, 0,0, 0);
        gbc.gridx = 0;
        gbc.gridy = 9;
        add(editSeparator, gbc);

        gbc.insets = new Insets(0,0,0,0);
        gbc.ipady = 0;
        gbc.gridx = 0;
        gbc.gridy = 10;
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

    public BorderedTextField getFirstNameField() {
        return firstNameField;
    }

    public BorderedTextField getLastNameField() {
        return lastNameField;
    }

    public BorderedTextField getEmailField() {
        return emailField;
    }

    public BorderedTextField getUsernameField() {
        return usernameField;
    }

    public BorderedTextField getPhoneField() {
        return phoneField;
    }

    public BorderedTextField getAddressField() {
        return addressField;
    }

    public ColoredLabel getNotificationLabel() {
        return notificationLabel;
    }
}
