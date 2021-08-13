package com.osamabodiaf.guicomponents.labels;

import com.osamabodiaf.guicomponents.UIResources;

import javax.swing.*;

public class PanelLabel extends JLabel {

    public PanelLabel(String text) {
        super(text);
        init();
    }

    public PanelLabel() {
        init();
    }

    private void init() {
        setBorder(BorderFactory.createEmptyBorder(5,7,5,7));
        setForeground(UIResources.BLACK);
        setBackground(UIResources.LIGHT_BLUE);
        setOpaque(true);
    }
}
