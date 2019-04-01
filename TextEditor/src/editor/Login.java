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
    JPanel panel = new JPanel();
    CardLayout cardLayout;

    public Login() {
        setLayout(new CardLayout());
        fieldsPanel.add(userLabel);
        fieldsPanel.add(userField);
        fieldsPanel.add(pwdLabel);
        fieldsPanel.add(pwdField);
        fieldsPanel.add(loginButton);
        fieldsPanel.add(registerButton);
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
        panel.add(fieldsPanel);
        add(panel, "login");
        cardLayout = (CardLayout) getLayout();
    }

    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == registerButton){
            add(new Register(),"register");
            cardLayout.show(this,"register");
        }
    }
}
