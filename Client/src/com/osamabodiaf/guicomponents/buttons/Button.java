package com.osamabodiaf.guicomponents.buttons;

import javax.swing.*;

public class Button extends JButton {
    public Button(String text) {
        super(text);
        setBorder(BorderFactory.createEmptyBorder(6,10,7,10));
        setFocusPainted(false);
    }
}
