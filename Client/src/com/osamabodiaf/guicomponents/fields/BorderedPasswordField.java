package com.osamabodiaf.guicomponents.fields;

import com.osamabodiaf.guicomponents.UIResources;

import javax.swing.*;
import javax.swing.border.Border;

public class BorderedPasswordField extends PasswordField {
    public BorderedPasswordField(int columns) {
        super(columns);
        init();
    }

    public BorderedPasswordField() {
        init();
    }

    private void init() {
        Border lineBorder = BorderFactory.createLineBorder(UIResources.MEDIUM_GRAY);
        Border emptyBorder = BorderFactory.createEmptyBorder(4,10,4,10);
        Border compoundBorder = BorderFactory.createCompoundBorder(lineBorder, emptyBorder);
        setBorder(compoundBorder);
    }
}
