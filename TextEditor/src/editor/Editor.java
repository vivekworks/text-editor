package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Editor extends JPanel implements ActionListener {
    JTextArea editorArea = new JTextArea(25, 40);
    JScrollPane editorPane = new JScrollPane(editorArea);
    JButton saveButton = new JButton("Save");
    String filePath;
    JButton returnButton = new JButton("Return");
    JButton saveCloseButton = new JButton("Save & Close");

    public Editor(String filename) throws IOException {
        this.filePath = filename;
        setSize(new Dimension(300, 150));
        System.out.println(filename);
        File file = new File(filename);
        if (!file.exists())
            file.createNewFile();
        else {
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = reader.readLine()) != null)
                    editorArea.append(line + "\n");
            }
        }
        add(editorPane);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(returnButton);
        buttonPanel.add(saveCloseButton);
        saveButton.addActionListener(this);
        returnButton.addActionListener(this);
        saveCloseButton.addActionListener(this);
        add(buttonPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton eventButton = (JButton) e.getSource();
        if (eventButton == saveButton || eventButton == saveCloseButton) {
            try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(filePath)), true)) {
                printWriter.println(editorArea.getText());
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            if (eventButton == saveCloseButton) {
                System.exit(0);
            }
        }
        if (eventButton == returnButton) {
            Login login = (Login) getParent();
            login.cardLayout.show(login, "filebrowser");
        }
    }
}
