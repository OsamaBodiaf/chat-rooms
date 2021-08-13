package com.osamabodiaf.guicomponents.main;

import com.osamabodiaf.Client;
import com.osamabodiaf.guicomponents.labels.PanelLabel;
import com.osamabodiaf.guicomponents.others.ScrollPane;
import com.osamabodiaf.guicomponents.others.TopicsList;
import com.osamabodiaf.guicomponents.others.TopicsListItem;

import javax.swing.*;
import java.awt.*;

public class TopicsPanel extends JPanel {
    private PanelLabel topicsLabel = new PanelLabel("Topics");
    private DefaultListModel<TopicsListItem> topicsListModel = new DefaultListModel<>();
    private TopicsList topicsList = new TopicsList(topicsListModel);

    public TopicsPanel(Client client) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(0, 178));
        add(topicsLabel, BorderLayout.NORTH);

        topicsList.addListSelectionListener(e -> {
            if (topicsList.getSelectedValue() == null)
                return;
            client.getClientGUIHandler().handleDestinationChange(topicsList.getSelectedValue().toString());
            client.getClientGUI().getMainPanel()
                    .getSidePanel().getUsersPanel().getUsersList().clearSelection();
        });

        add(new ScrollPane(topicsList), BorderLayout.CENTER);
        add(new NewTopicPanel(client), BorderLayout.SOUTH);
    }

    public DefaultListModel<TopicsListItem> getTopicsListModel() {
        return topicsListModel;
    }

    public TopicsList getTopicsList() {
        return topicsList;
    }
}
