package com.osamabodiaf.guicomponents.fields;

import com.osamabodiaf.guicomponents.UIResources;

import javax.swing.*;
import javax.swing.border.Border;

public class PasswordField extends JPasswordField {

    public PasswordField(int columns) {
        super(columns);
        init();
    }

    public PasswordField() {
        init();
    }

    private void init() {
        setBackground(UIResources.WHITE);
        Border emptyBorder = BorderFactory.createEmptyBorder(4,10,4,10);
        setBorder(emptyBorder);
    }
}
