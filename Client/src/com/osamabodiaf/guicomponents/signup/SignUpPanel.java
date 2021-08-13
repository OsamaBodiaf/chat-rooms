package com.osamabodiaf.guicomponents.signup;

import com.osamabodiaf.Client;
import com.osamabodiaf.guicomponents.labels.ColoredLabel;

import javax.swing.*;
import java.awt.*;

public class SignUpPanel extends JPanel {
    private HeaderPanel headerPanel;
    private BodyPanel bodyPanel;
    private FooterPanel footerPanel;

    public SignUpPanel(Client client) {
        headerPanel = new HeaderPanel();
        bodyPanel = new BodyPanel(client);
        footerPanel = new FooterPanel();

        setLayout(new BorderLayout());

        add(headerPanel, BorderLayout.NORTH);
        add(bodyPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
    }

    public ColoredLabel getNotificationLabel() {
        return bodyPanel.getNotificationLabel();
    }
}
