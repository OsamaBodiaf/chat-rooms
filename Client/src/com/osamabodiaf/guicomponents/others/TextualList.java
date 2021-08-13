package com.osamabodiaf.guicomponents.others;

import com.osamabodiaf.guicomponents.UIResources;

import javax.swing.*;

public class TextualList extends JList {

    public TextualList(DefaultListModel listModel) {
        super(listModel);
        init();
    }

    public TextualList() {
        init();
    }

    private void init() {
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setForeground(UIResources.BLACK);
        setBackground(UIResources.LIGHT_GRAY);
        setSelectionForeground(UIResources.WHITE);
        setSelectionBackground(UIResources.TEAL);
        setCellRenderer(new TextualListCellRenderer());
    }
}
