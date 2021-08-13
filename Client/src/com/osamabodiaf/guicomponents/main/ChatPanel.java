package com.osamabodiaf.guicomponents.main;

import com.osamabodiaf.Client;
import com.osamabodiaf.guicomponents.labels.PanelLabel;

import javax.swing.*;
import java.awt.*;

public class ChatPanel extends JPanel {
    private PanelLabel recipientLabel = new PanelLabel("Select a user or topic to start chatting!");
    private ChatOutputPanel chatOutputPanel = new ChatOutputPanel();
    private ChatInputPanel chatInputPanel;

    public ChatPanel(Client client) {
        chatInputPanel = new ChatInputPanel(client);

        setLayout(new BorderLayout());
        add(recipientLabel, BorderLayout.NORTH);
        add(chatOutputPanel, BorderLayout.CENTER);
        add(chatInputPanel, BorderLayout.SOUTH);
    }

    public ChatInputPanel getChatInputPanel() {
        return chatInputPanel;
    }

    public ChatOutputPanel getChatOutputPanel() {
        return chatOutputPanel;
    }

    public PanelLabel getDestinationLabel() {
        return recipientLabel;
    }
}
