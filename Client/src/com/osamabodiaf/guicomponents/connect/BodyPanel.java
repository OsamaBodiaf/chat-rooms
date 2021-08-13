package com.osamabodiaf.guicomponents.connect;

import com.osamabodiaf.Client;
import com.osamabodiaf.guicomponents.UIResources;
import com.osamabodiaf.guicomponents.buttons.ColoredButton;
import com.osamabodiaf.guicomponents.fields.BorderedTextField;
import com.osamabodiaf.guicomponents.labels.ColoredLabel;

import javax.swing.*;
import java.awt.*;

public class BodyPanel extends JPanel {
    private GridBagConstraints gbc = new GridBagConstraints();
    private ColoredLabel hostLabel = new ColoredLabel(UIResources.BLACK, "Host:");
    private BorderedTextField hostField = new BorderedTextField("localhost", 20);
    private ColoredLabel portLabel = new ColoredLabel(UIResources.BLACK, "Port:");
    private BorderedTextField portField = new BorderedTextField("4444", 20);
    private ColoredButton connectButton = new ColoredButton(UIResources.WHITE, UIResources.BLUE, "Connect");
    private JSeparator connectSeparator = new JSeparator();
    private ColoredLabel notificationLabel = new ColoredLabel(UIResources.RED, "Connection failed.");

    public BodyPanel(Client client) {
        setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 10;

        gbc.gridy = 0;
        add(hostLabel, gbc);

        gbc.gridy = 1;
        add(hostField, gbc);

        gbc.insets = new Insets(5,0,0,0);
        gbc.gridy = 2;
        add(portLabel, gbc);

        gbc.insets = new Insets(0,0,0,0);
        gbc.gridy = 3;
        add(portField, gbc);

        gbc.insets = new Insets(15,0,0,0);
        gbc.gridy = 4;
        connectButton.addActionListener(e -> {
            String host = hostField.getText();
            int port = Integer.parseInt(portField.getText());
            if (client.getClientWorker().connect(host, port))
                client.getClientGUIHandler().showPanel("login");
            else {
                connectSeparator.setVisible(true);
                notificationLabel.setVisible(true);
            }
        });
        add(connectButton, gbc);

        gbc.gridy = 5;
        connectSeparator.setVisible(false);
        add(connectSeparator, gbc);

        gbc.insets = new Insets(0,0,0,0);
        gbc.ipady = 0;
        gbc.gridy = 6;
        notificationLabel.setVisible(false);
        add(notificationLabel, gbc);
    }
}
