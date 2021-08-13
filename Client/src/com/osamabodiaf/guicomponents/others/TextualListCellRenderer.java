package com.osamabodiaf.guicomponents.others;

import javax.swing.*;
import java.awt.*;

public class TextualListCellRenderer extends JLabel implements ListCellRenderer {

    public Component getListCellRendererComponent(
            JList list,
            Object value,            // value to display
            int index,               // cell index
            boolean isSelected,      // is the cell selected
            boolean cellHasFocus)    // the list and the cell have the focus
    {
        setText(value.toString());
        if (isSelected) {
            setForeground(list.getSelectionForeground());
            setBackground(list.getSelectionBackground());
        }
        else {
            setForeground(list.getForeground());
            setBackground(list.getBackground());
        }
        setBorder(BorderFactory.createEmptyBorder(4,7,4, 7));
        setEnabled(list.isEnabled());
        setOpaque(true);
        return this;
    }
}