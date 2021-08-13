package com.osamabodiaf.guicomponents.others;

import javax.swing.*;
import java.awt.*;

public class UsersListCellRenderer implements ListCellRenderer {

    public Component getListCellRendererComponent(
            JList list,
            Object value,            // value to display
            int index,               // cell index
            boolean isSelected,      // is the cell selected
            boolean cellHasFocus)    // the list and the cell have the focus
    {
        UsersListItem item = (UsersListItem) value;
        if (isSelected) {
            item.getUserLabel().setForeground(list.getSelectionForeground());
            item.setBackground(list.getSelectionBackground());
            item.getMessageIcon().setVisible(false);
        }
        else {
            item.getUserLabel().setForeground(list.getForeground());
            item.setBackground(list.getBackground());
        }
        item.setEnabled(list.isEnabled());
        return item;
    }
}