package com.osamabodiaf.guicomponents.edit;

import com.osamabodiaf.Client;
import com.osamabodiaf.guicomponents.fields.BorderedTextField;
import com.osamabodiaf.guicomponents.labels.ColoredLabel;

import javax.swing.*;
import java.awt.*;

public class EditPanel extends JPanel {
    private HeaderPanel headerPanel;
    private BodyPanel bodyPanel;
    private FooterPanel footerPanel;

    public EditPanel(Client client) {
        headerPanel = new HeaderPanel();
        bodyPanel = new BodyPanel(client);
        footerPanel = new FooterPanel(client);

        setLayout(new BorderLayout());

        add(headerPanel, BorderLayout.NORTH);
        add(bodyPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
    }

    public BorderedTextField getFirstNameField() {
        return bodyPanel.getFirstNameField();
    }

    public BorderedTextField getLastNameField() {
        return bodyPanel.getLastNameField();
    }

    public BorderedTextField getEmailField() {
        return bodyPanel.getEmailField();
    }

    public BorderedTextField getUsernameField() {
        return bodyPanel.getUsernameField();
    }

    public BorderedTextField getPhoneField() {
        return bodyPanel.getPhoneField();
    }

    public BorderedTextField getAddressField() {
        return bodyPanel.getAddressField();
    }

    public ColoredLabel getNotificationLabel() {
        return bodyPanel.getNotificationLabel();
    }
}
