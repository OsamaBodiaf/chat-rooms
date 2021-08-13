package com.osamabodiaf.guicomponents.fields;

import com.osamabodiaf.guicomponents.UIResources;

import javax.swing.*;
import javax.swing.border.Border;

public class BorderedTextField extends TextField {

    public BorderedTextField() { init(); }

    public BorderedTextField(int columns) {
        super(columns);
        init();
    }

    public BorderedTextField(String text) {
        super(text);
        init();
    }

    public BorderedTextField(String text, int columns) {
        super(text, columns);
        init();
    }

    private void init() {
        Border lineBorder = BorderFactory.createLineBorder(UIResources.MEDIUM_GRAY);
        Border emptyBorder = BorderFactory.createEmptyBorder(4,10,4,10);
        Border compoundBorder = BorderFactory.createCompoundBorder(lineBorder, emptyBorder);
        setBorder(compoundBorder);
    }
}
