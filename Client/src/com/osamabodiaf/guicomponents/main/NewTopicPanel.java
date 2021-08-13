package com.osamabodiaf.guicomponents.main;

import com.osamabodiaf.Client;
import com.osamabodiaf.guicomponents.UIResources;
import com.osamabodiaf.guicomponents.fields.TextField;
import com.osamabodiaf.guicomponents.buttons.ColoredButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewTopicPanel extends JPanel {
    private TextField newRoomField = new TextField();
    private ColoredButton newRoomButton = new ColoredButton(UIResources.WHITE, UIResources.BLUE,
            "New Topic");

    public NewTopicPanel(Client client) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(0, 32));
        add(newRoomField, BorderLayout.CENTER);
        newRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.getClientWorker().create(newRoomField.getText());
                newRoomField.setText("");
            }
        });
        add(newRoomButton, BorderLayout.EAST);
    }
}
