package com.osamabodiaf.guicomponents.signup;

import com.osamabodiaf.guicomponents.UIResources;
import com.osamabodiaf.guicomponents.labels.ColoredLabel;

import javax.swing.*;
import java.awt.*;

public class HeaderPanel extends JPanel {
    private ColoredLabel headerLabel = new ColoredLabel(UIResources.BLUE, "Join for Free");

    public HeaderPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(0, 100));

        headerLabel.setFont(UIResources.HEADING_FONT);
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        add(headerLabel, BorderLayout.SOUTH);
    }
}
