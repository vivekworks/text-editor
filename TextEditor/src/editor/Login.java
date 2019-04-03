package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login extends JPanel implements ActionListener {
    JLabel userLabel = new JLabel("Username : ");
    JLabel pwdLabel = new JLabel("Password : ");
    JTextField userField = new JTextField(10);
    JPasswordField pwdField = new JPasswordField(10);
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
        } else if(event.getSource() == loginButton){
            String pwd="";
            File pwdFile = new File(System.getProperty("user.dir") + "\\files\\password.txt");
            try (BufferedReader reader = new BufferedReader(new FileReader(pwdFile))) {
                String line;
                while((line = reader.readLine())!= null){
                    String[] userPwd = line.split("/");
                    if(userField.getText().equals(userPwd[0]))
                        pwd=userPwd[1];
                }
            } catch (IOException ioe){
                ioe.printStackTrace();
                System.exit(0);
            }
            StringBuffer sb = new StringBuffer();
            try {
                MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
                msgDigest.update(new String(pwdField.getPassword()).getBytes());
                byte[] byteData = msgDigest.digest();
                for (int i = 0, len = byteData.length; i < len; i++)
                    sb.append(Integer.toString((byteData[i] & 0xFF) + 0x100, 16).substring(1));
            } catch (NoSuchAlgorithmException nsae) {
                nsae.printStackTrace();
                System.exit(0);
            }
            if(!pwd.equals(sb.toString())) {
                System.out.println("Wrong password!");
                System.exit(0);
            } else{
                FileBrowser fileBrowser = new FileBrowser(userField.getText());
                add(fileBrowser,"filebrowser");
                cardLayout.show(this,"filebrowser");
            }
        }
    }
}
