package com.osamabodiaf.guicomponents.labels;

import javax.swing.*;
import java.awt.*;

public class ColoredLabel extends JLabel {

    public ColoredLabel(Color color, String text) {
        super(text);
        init(color);
    }

    public ColoredLabel(Color color) {
        init(color);
    }

    private void init(Color color) {
        setForeground(color);
    }
}
