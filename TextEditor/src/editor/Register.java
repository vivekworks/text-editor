package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Register extends JPanel implements ActionListener {
    JLabel userLabel = new JLabel("Enter a username");
    JTextField userField = new JTextField(10);
    JLabel pwdLabel = new JLabel("Password");
    JPasswordField pwdField = new JPasswordField(10);
    JLabel confirmPwdLabel = new JLabel("Confirm Password");
    JPasswordField confirmPwdField = new JPasswordField(10);
    JButton registerButton = new JButton("Register");
    JButton backButton = new JButton("Back");

    public Register() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(userLabel);
        panel.add(userField);
        panel.add(pwdLabel);
        panel.add(pwdField);
        panel.add(confirmPwdLabel);
        panel.add(confirmPwdField);
        panel.add(registerButton);
        panel.add(backButton);
        add(panel);
        registerButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            Login login = (Login) getParent();
            login.cardLayout.show(login, "login");
        } else if (e.getSource() == registerButton) {
            if (pwdField.getPassword().length == 0 || confirmPwdField.getPassword().length == 0) {
                System.out.println("Password fields should not be null");
                return;
            }
            String password = String.valueOf(pwdField.getPassword());
            if (password.equals(String.valueOf(confirmPwdField.getPassword()))) {
                System.out.println("Passwords Match");
                File pwdFile = new File(System.getProperty("user.dir") + "\\files\\password.txt");
                if (!pwdFile.exists()) {
                    try {
                        pwdFile.createNewFile();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                        System.exit(0);
                    }
                }
                try (BufferedReader buffReader = new BufferedReader(new FileReader(pwdFile))) {
                    String line;
                    while ((line = buffReader.readLine()) != null) {
                        String[] userPwd = line.split("/");
                        if (userPwd[0].equals(userField.getText())) {
                            System.out.println("User already exists");
                            return;
                        }
                    }
                } catch (IOException ioe) {
                    System.out.println("Error - " + ioe.getMessage());
                    System.exit(0);
                }
                StringBuffer sb = new StringBuffer();
                try {
                    MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
                    msgDigest.update(password.getBytes());
                    byte[] byteData = msgDigest.digest();
                    for (int i = 0, len = byteData.length; i < len; i++)
                        sb.append(Integer.toString((byteData[i] & 0xFF) + 0x100, 16).substring(1));
                } catch (NoSuchAlgorithmException nsae) {
                    nsae.printStackTrace();
                    System.exit(0);
                }
                try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(pwdFile,true)),true)){
                    writer.println(userField.getText()+"/"+sb.toString());
                } catch (IOException ioe){
                    ioe.printStackTrace();
                    System.exit(0);
                }
                Login login = (Login)getParent();
                login.cardLayout.show(login,"login");
            }
        }
    }
}
