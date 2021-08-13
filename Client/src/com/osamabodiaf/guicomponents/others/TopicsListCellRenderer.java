package com.osamabodiaf.guicomponents.others;

import javax.swing.*;
import java.awt.*;

public class TopicsListCellRenderer implements ListCellRenderer {

    public Component getListCellRendererComponent(
            JList list,
            Object value,            // value to display
            int index,               // cell index
            boolean isSelected,      // is the cell selected
            boolean cellHasFocus)    // the list and the cell have the focus
    {
        TopicsListItem item = (TopicsListItem) value;
        if (isSelected) {
            item.getTopicLabel().setForeground(list.getSelectionForeground());
            item.setBackground(list.getSelectionBackground());
            item.getMessageIcon().setVisible(false);
        }
        else {
            item.getTopicLabel().setForeground(list.getForeground());
            item.setBackground(list.getBackground());
        }
        item.setEnabled(list.isEnabled());
        return item;
    }
}