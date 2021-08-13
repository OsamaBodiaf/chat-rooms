package com.osamabodiaf;

import com.osamabodiaf.guicomponents.connect.ConnectPanel;
import com.osamabodiaf.guicomponents.edit.EditPanel;
import com.osamabodiaf.guicomponents.main.MainPanel;
import com.osamabodiaf.guicomponents.login.LogInPanel;
import com.osamabodiaf.guicomponents.signup.SignUpPanel;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class ClientGUI extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private ConnectPanel connectPanel;
    private LogInPanel logInPanel;
    private SignUpPanel signUpPanel;
    private MainPanel mainPanel;
    private EditPanel editPanel;

    public ClientGUI(Client client) {
        chooseFonts();

        connectPanel = new ConnectPanel(client);
        signUpPanel = new SignUpPanel(client);
        logInPanel = new LogInPanel(client);
        mainPanel = new MainPanel(client);
        editPanel = new EditPanel(client);

        setLayout(cardLayout);
        setTitle("ChatRooms");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));

        add("connect", connectPanel);
        add("login", logInPanel);
        add("signup", signUpPanel);
        add("main", mainPanel);
        add("edit", editPanel);

        setVisible(true);
    }

    private void chooseFonts() {
        FontUIResource plainFont = new FontUIResource("Segoe UI",Font.PLAIN,12);
        FontUIResource boldFont = new FontUIResource("Segoe UI",Font.BOLD,12);
        UIManager.put("Button.font", boldFont);
        UIManager.put("ToggleButton.font", plainFont);
        UIManager.put("RadioButton.font", plainFont);
        UIManager.put("CheckBox.font", plainFont);
        UIManager.put("ColorChooser.font", plainFont);
        UIManager.put("ComboBox.font", plainFont);
        UIManager.put("Label.font", plainFont);
        UIManager.put("List.font", plainFont);
        UIManager.put("MenuBar.font", plainFont);
        UIManager.put("MenuItem.font", plainFont);
        UIManager.put("RadioButtonMenuItem.font", plainFont);
        UIManager.put("CheckBoxMenuItem.font", plainFont);
        UIManager.put("Menu.font", plainFont);
        UIManager.put("PopupMenu.font", plainFont);
        UIManager.put("OptionPane.font", plainFont);
        UIManager.put("Panel.font", plainFont);
        UIManager.put("ProgressBar.font", plainFont);
        UIManager.put("ScrollPane.font", plainFont);
        UIManager.put("Viewport.font", plainFont);
        UIManager.put("TabbedPane.font", plainFont);
        UIManager.put("Table.font", plainFont);
        UIManager.put("TableHeader.font", plainFont);
        UIManager.put("TextField.font", plainFont);
        UIManager.put("PasswordField.font", plainFont);
        UIManager.put("TextArea.font", plainFont);
        UIManager.put("TextPane.font", plainFont);
        UIManager.put("EditorPane.font", plainFont);
        UIManager.put("TitledBorder.font", plainFont);
        UIManager.put("ToolBar.font", plainFont);
        UIManager.put("ToolTip.font", plainFont);
        UIManager.put("Tree.font", plainFont);
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public ConnectPanel getConnectPanel() {
        return connectPanel;
    }

    public LogInPanel getLogInPanel() {
        return logInPanel;
    }

    public SignUpPanel getSignUpPanel() {
        return signUpPanel;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public EditPanel getEditPanel() {
        return editPanel;
    }

    public void setConnectPanel(ConnectPanel connectPanel) {
        this.connectPanel = connectPanel;
    }

    public void setLogInPanel(LogInPanel logInPanel) {
        this.logInPanel = logInPanel;
    }

    public void setSignUpPanel(SignUpPanel signUpPanel) {
        this.signUpPanel = signUpPanel;
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public void setEditPanel(EditPanel editPanel) { this.editPanel = editPanel; }
}
