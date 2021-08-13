package com.osamabodiaf.guicomponents.others;

import javax.swing.*;
import java.awt.*;

public class ScrollPane extends JScrollPane {
    public ScrollPane(Component view) {
        super(view);
        init();
    }

    public ScrollPane() {
        init();
    }

    private void init() {
        setBorder(BorderFactory.createEmptyBorder());
    }
}
