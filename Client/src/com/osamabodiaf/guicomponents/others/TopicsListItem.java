package com.osamabodiaf.guicomponents.others;

import javax.swing.*;
import java.awt.*;

public class TopicsListItem extends JPanel {
    private JLabel topicLabel;
    private JLabel messageIcon;

    public TopicsListItem(String username) {
        topicLabel = new JLabel(username);
        messageIcon = new JLabel(new ImageIcon("assets\\message.png",
                "image icon"), JLabel.CENTER);

        setLayout(new BorderLayout());
        add(topicLabel, BorderLayout.CENTER);
        messageIcon.setVisible(false);
        add(messageIcon, BorderLayout.EAST);
        setBorder(BorderFactory.createEmptyBorder(4,7,4,7));
    }

    public JLabel getTopicLabel() {
        return topicLabel;
    }

    public JLabel getMessageIcon() {
        return messageIcon;
    }

    public String getTopic() {
        return topicLabel.getText();
    }

    @Override
    public String toString() {
        return getTopic();
    }
}
