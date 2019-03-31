package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JPanel implements ActionListener {
    JLabel userLabel = new JLabel("Username : ");
    JLabel pwdLabel = new JLabel("Password : ");
    JTextField userField = new JTextField(10);
    JTextField pwdField = new JTextField(10);
    JButton loginButton = new JButton("Login");
    JButton registerButton = new JButton("Register");
    JPanel fieldsPanel = new JPanel(new GridLayout(3, 2));

    public Login() {
        fieldsPanel.add(userLabel);
        fieldsPanel.add(userField);
        fieldsPanel.add(pwdLabel);
        fieldsPanel.add(pwdField);
        fieldsPanel.add(loginButton);
        fieldsPanel.add(registerButton);
        add(fieldsPanel);
    }

    public void actionPerformed(ActionEvent event) {

    }
}
