package com.osamabodiaf.guicomponents.others;

import com.osamabodiaf.guicomponents.UIResources;

import javax.swing.*;

public class TopicsList extends JList {

    public TopicsList(DefaultListModel listModel) {
        super(listModel);
        init();
    }

    public TopicsList() {
        init();
    }

    private void init() {
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setForeground(UIResources.BLACK);
        setBackground(UIResources.LIGHT_GRAY);
        setSelectionForeground(UIResources.WHITE);
        setSelectionBackground(UIResources.TEAL);
        setCellRenderer(new TopicsListCellRenderer());
    }

}
