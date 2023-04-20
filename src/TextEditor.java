import javax.imageio.IIOException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class TextEditor implements ActionListener {
    //Declaring the properties of TextEditor
    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;

    //file menu items
    JMenuItem newFile, openFile, saveFile;

    // edit menu items
    JMenuItem cut, copy, paste, selectAll, close;

    JTextArea textArea;

    TextEditor() {
         //initialilze a frame
        frame = new JFrame();

        //initilize menubar
        menuBar = new JMenuBar();

        // initialilze text area
        textArea = new JTextArea();

        //initilize menu bar
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //initilize file menu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        //add action litener to file menuitems
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //add menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);



        //Initialize edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Cope");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        //adding action llistener to edit menu items
        cut.addActionListener(this);;
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //add menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //Add menus to menubar
        menuBar.add(file);
        menuBar.add(edit);

        //set menubar to frame
        frame.setJMenuBar(menuBar);

//        //add text area to frame
//        frame.add(textArea);

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //add text area to panel
        panel.add(textArea, BorderLayout.CENTER);
        //create scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //add scroll pane to panel
        panel.add(scrollPane);

        //add panel to frame
        frame.add(panel);

        //set dimension of frame
        frame.setBounds(0, 0, 400, 400);

        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == cut) {
            //perform cut operation
            textArea.cut();
        }
        if(actionEvent.getSource() == copy){
            //perform copy operation
            textArea.copy();
        }
        if(actionEvent.getSource() == paste) {
            textArea.paste();
        }
        if(actionEvent.getSource() == selectAll) {
            textArea.selectAll();
        }
        if(actionEvent.getSource() == close) {
             System.exit(0);
        }
        if(actionEvent.getSource() == openFile) {
            //open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            //if we have clicked on open button
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                //getting selected file
                File file = fileChooser.getSelectedFile();
                //get the path of selected file
                String filePath = file.getPath();
                try {
                    //initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    //initialize buffered reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    //read contents of file line by line
                    while ((intermediate = bufferedReader.readLine()) != null) {
                        output += intermediate + "/n";
                    }
                    //set the output string to text area
                    textArea.setText(output);

                } catch (IOException fileNotFoundEXception) {
                    fileNotFoundEXception.printStackTrace();
                }
            }
        }
            if(actionEvent.getSource() == saveFile) {
                //initialize file picker
               JFileChooser  fileChooser = new JFileChooser("C:");
                //get choose option from file chooser
               int chooseOption = fileChooser.showSaveDialog(null);
                //check if we clicked on save button
                if(chooseOption == JFileChooser.APPROVE_OPTION) {
                    //create a new file withchosen directry path and file name
                    File file = new File(fileChooser.getSelectedFile().getAbsoluteFile() + ".txt");
                    try {
                        //initilize file writer
                        FileWriter fileWriter = new FileWriter(file);
                        //initialize buffered writer
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        //write contents of text area to file
                        textArea.write(bufferedWriter);
                        bufferedWriter.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }
            }
            if(actionEvent.getSource() == newFile) {
                TextEditor newTextEditor = new TextEditor();
            }

    }


    public static void main(String[] args) {
      TextEditor textEditor = new TextEditor();
    }
}