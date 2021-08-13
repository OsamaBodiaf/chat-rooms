package com.osamabodiaf.guicomponents.login;

import com.osamabodiaf.Client;
import com.osamabodiaf.guicomponents.UIResources;
import com.osamabodiaf.guicomponents.buttons.ColoredButton;

import javax.swing.*;
import java.awt.*;

public class FooterPanel extends JPanel {
    private ColoredButton disconnectButton = new ColoredButton(UIResources.DARK_GRAY, UIResources.GRAY, "Disconnect");

    public FooterPanel(Client client) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(0, 100));

        disconnectButton.setPreferredSize(new Dimension(240, 35));
        disconnectButton.addActionListener(e -> {
            client.getClientWorker().disconnect();
            client.getClientGUIHandler().showPanel("connect");
        });
        JPanel panel = new JPanel();
        panel.add(disconnectButton);
        add(panel, BorderLayout.NORTH);
    }
}
