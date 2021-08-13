package com.osamabodiaf;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class ClientChatBuffer {
    private Map<String, DefaultListModel> conversations = new HashMap<>();

    public DefaultListModel getConversation(String recipient) {
        if (!conversations.containsKey(recipient))
            conversations.put(recipient, new DefaultListModel());
        return conversations.get(recipient);
    }

    public DefaultListModel removeConversation(String recipient) {
        return conversations.remove(recipient);
    }

    public void addConversation(String recipient, DefaultListModel conversation) {
        conversations.put(recipient, conversation);
    }
}
