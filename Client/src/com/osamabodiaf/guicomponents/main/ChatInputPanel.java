package com.osamabodiaf.guicomponents.main;

import com.osamabodiaf.Client;
import com.osamabodiaf.guicomponents.UIResources;
import com.osamabodiaf.guicomponents.fields.ChatTextField;
import com.osamabodiaf.guicomponents.buttons.ColoredButton;

import javax.swing.*;
import java.awt.*;

public class ChatInputPanel extends JPanel {
    private ChatTextField inputField = new ChatTextField(20);
    private ColoredButton sendButton = new ColoredButton(UIResources.WHITE, UIResources.ORANGE, "Send");

    public ChatInputPanel(Client client) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(0, 48));

        add(inputField, BorderLayout.CENTER);
        sendButton.setPreferredSize(new Dimension(90, 0));
        sendButton.addActionListener(e -> {
            client.getClientWorker().message(inputField.getText());
        });
        add(sendButton, BorderLayout.EAST);
    }

    public ChatTextField getInputField() {
        return inputField;
    }
}
