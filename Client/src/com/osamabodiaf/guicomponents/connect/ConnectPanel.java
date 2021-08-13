package com.osamabodiaf.guicomponents.connect;

import com.osamabodiaf.Client;

import javax.swing.*;
import java.awt.*;

public class ConnectPanel extends JPanel {
    private HeaderPanel headerPanel;
    private BodyPanel bodyPanel;
    private FooterPanel footerPanel;

    public ConnectPanel(Client client) {
        headerPanel = new HeaderPanel();
        bodyPanel = new BodyPanel(client);
        footerPanel = new FooterPanel();

        setLayout(new BorderLayout());

        add(headerPanel, BorderLayout.NORTH);
        add(bodyPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
    }
}
