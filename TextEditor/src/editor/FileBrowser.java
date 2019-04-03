package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileBrowser extends JPanel implements ActionListener {
    JLabel fileListLabel = new JLabel("File List:");
    JButton openButton = new JButton("Open");
    JButton newFileButton = new JButton("New File");
    ButtonGroup buttonGroup;
    JTextField fileName = new JTextField(10);
    File currentDirectory;
    List<JRadioButton> filesList;
    JPanel openFieldsPanel = new JPanel();
    JPanel newFieldsPanel = new JPanel();

    public FileBrowser(String username) {
        setLayout(new GridLayout(2, 0));
        filesList = new ArrayList<>();
        buttonGroup = new ButtonGroup();
        currentDirectory = new File(System.getProperty("user.dir") + "\\files\\" + username + "\\");
        openFieldsPanel.add(fileListLabel);
        if (!currentDirectory.exists())
            currentDirectory.mkdirs();
        for (File file : currentDirectory.listFiles()) {
            JRadioButton fileo = new JRadioButton(file.getName());
            fileo.setActionCommand(file.getName());
            filesList.add(fileo);
            buttonGroup.add(fileo);
            openFieldsPanel.add(fileo);
        }
        openButton.addActionListener(this);
        newFileButton.addActionListener(this);
        openFieldsPanel.add(openButton);
        newFieldsPanel.add(fileName);
        newFieldsPanel.add(newFileButton);
        add(openFieldsPanel);
        add(newFieldsPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String fileToEdit="";
            if (e.getSource() == newFileButton)
                fileToEdit=fileName.getText()+ ".txt";
             else if (e.getSource() == openButton) {
                fileToEdit = buttonGroup.getSelection().getActionCommand();
                System.out.println("FIle to Edit :"+fileToEdit);
            }
            Editor editor = new Editor(currentDirectory.getAbsolutePath() + "\\" + fileToEdit);
            Login login = (Login) getParent();
            login.add(editor, "editor");
            login.cardLayout.show(login, "editor");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
