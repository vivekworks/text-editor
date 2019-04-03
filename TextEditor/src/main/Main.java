package main;

import editor.Login;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        JFrame frame = new JFrame("Text Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.getContentPane().add(new Login());
        frame.setVisible(true);
    }
}