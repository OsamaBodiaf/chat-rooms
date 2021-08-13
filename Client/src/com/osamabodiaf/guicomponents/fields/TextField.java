package com.osamabodiaf.guicomponents.fields;

import com.osamabodiaf.guicomponents.UIResources;

import javax.swing.*;
import javax.swing.border.Border;

public class TextField extends JTextField {

    public TextField() {
        init();
    }

    public TextField(String text) {
        super(text);
        init();
    }

    public TextField(int columns) {
        super(columns);
        init();
    }

    public TextField(String text, int columns) {
        super(text, columns);
        init();
    }

    private void init() {
        setBackground(UIResources.WHITE);
        Border emptyBorder = BorderFactory.createEmptyBorder(4,10,4,10);
        setBorder(emptyBorder);
    }
}
