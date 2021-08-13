package com.osamabodiaf.guicomponents.login;

import com.osamabodiaf.Client;
import com.osamabodiaf.guicomponents.labels.ColoredLabel;

import javax.swing.*;
import java.awt.*;

public class LogInPanel extends JPanel {
    private HeaderPanel headerPanel;
    private BodyPanel bodyPanel;
    private FooterPanel footerPanel;

    public LogInPanel(Client client) {
        headerPanel = new HeaderPanel();
        bodyPanel = new BodyPanel(client);
        footerPanel = new FooterPanel(client);

        setLayout(new BorderLayout());

        add(headerPanel, BorderLayout.NORTH);
        add(bodyPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
    }

    public ColoredLabel getNotificationLabel() {
        return bodyPanel.getNotificationLabel();
    }
}
