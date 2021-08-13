package com.osamabodiaf.guicomponents.buttons;

import java.awt.*;

public class ColoredButton extends Button {
    public ColoredButton(Color fgColor, Color bgColor, String text) {
        super(text);
        setForeground(fgColor);
        setBackground(bgColor);
    }
}
