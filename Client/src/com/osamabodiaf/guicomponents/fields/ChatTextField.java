package com.osamabodiaf.guicomponents.fields;

import com.osamabodiaf.guicomponents.UIResources;

import javax.swing.*;
import javax.swing.border.Border;

public class ChatTextField extends TextField {

    public ChatTextField(int columns) {
        super(columns);
        init();
    }

    public ChatTextField() {
        init();
    }

    private void init() {
        setFont(UIResources.LARGE_FONT);
        Border lineBorder = BorderFactory.createMatteBorder(1, 0, 0, 0, UIResources.GRAY);
        Border emptyBorder = BorderFactory.createEmptyBorder(4,10,4,10);
        Border compoundBorder = BorderFactory.createCompoundBorder(lineBorder, emptyBorder);
        setBorder(compoundBorder);
    }
}
