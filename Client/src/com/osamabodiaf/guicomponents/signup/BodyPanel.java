package com.osamabodiaf.guicomponents.signup;

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
import java.util.regex.Pattern;

public class BodyPanel extends JPanel {
    private GridBagConstraints gbc = new GridBagConstraints();
    private ColoredLabel usernameLabel = new ColoredLabel(UIResources.BLACK, "Email:");
    private BorderedTextField emailField = new BorderedTextField(20);
    private ColoredLabel passwordLabel = new ColoredLabel(UIResources.BLACK, "Password:");
    private BorderedPasswordField passwordField = new BorderedPasswordField(20);
    private ColoredLabel passwordConfirmationLabel = new ColoredLabel(UIResources.BLACK, "Confirm Password:");
    private BorderedPasswordField passwordConfirmationField = new BorderedPasswordField(20);
    private ColoredButton signupButton = new ColoredButton(UIResources.WHITE, UIResources.ORANGE, "Sign Up");
    private JSeparator signupSeparator = new JSeparator();
    private ColoredLabel notificationLabel = new ColoredLabel(UIResources.ORANGE,
            "Already have an account? Sign In!");

    public BodyPanel(Client client) {
        setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 10;

        gbc.gridy = 0;
        add(usernameLabel, gbc);

        gbc.gridy = 1;
        add(emailField, gbc);

        gbc.insets = new Insets(5,0,0,0);
        gbc.gridy = 2;
        add(passwordLabel, gbc);

        gbc.insets = new Insets(0,0,0,0);
        gbc.gridy = 3;
        add(passwordField, gbc);

        gbc.insets = new Insets(5,0,0,0);
        gbc.gridy = 4;
        add(passwordConfirmationLabel, gbc);

        gbc.insets = new Insets(0,0,0,0);
        gbc.gridy = 5;
        add(passwordConfirmationField, gbc);

        gbc.insets = new Insets(15,0,0,0);
        gbc.gridy = 6;
        signupButton.addActionListener(e -> {
            Pattern pattern = Pattern.compile("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$");
            String email = emailField.getText();
            String password = String.valueOf(passwordField.getPassword());
            String passwordConfirmation = String.valueOf(passwordConfirmationField.getPassword());
            notificationLabel.setForeground(UIResources.RED);
            if (!pattern.matcher(email).matches())
                notificationLabel.setText("You must enter a valid email!");
            else if (password.length() < 4)
                notificationLabel.setText("Password must be at least 4 characters!");
            else if (!password.equals(passwordConfirmation))
                notificationLabel.setText("Passwords must match!");
            else
                client.getClientWorker().signup(email, password);
        });
        add(signupButton, gbc);

        gbc.gridy = 7;
        add(signupSeparator, gbc);

        gbc.insets = new Insets(0,0,0,0);
        gbc.ipady = 0;
        gbc.gridy = 8;
        notificationLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                client.getClientGUIHandler().showPanel("login");
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
