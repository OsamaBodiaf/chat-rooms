package com.osamabodiaf.guicomponents.main;

import com.osamabodiaf.guicomponents.others.TextualList;
import com.osamabodiaf.guicomponents.others.ScrollPane;
import com.osamabodiaf.guicomponents.UIResources;

import javax.swing.*;
import java.awt.*;

public class ChatOutputPanel extends JPanel {
    TextualList messagesList = new TextualList();

    public ChatOutputPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, UIResources.LIGHT_BLUE));
        add(new ScrollPane(messagesList), BorderLayout.CENTER);
    }

    public DefaultListModel getCurrentConversation() {
        return (DefaultListModel) messagesList.getModel();
    }

    public void setCurrentConversation(DefaultListModel defaultListModel) {
        messagesList.setModel(defaultListModel);
    }
}
