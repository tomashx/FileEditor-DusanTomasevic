package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PredlogKorisnickogInterfejsa extends JDialog {
    private JPanel contentPane;
    private JButton buttonClose;
    private JButton saveNewButton;
    private JButton openTopButton;
    private JButton openBottomButton;
    private JButton bottomNewButton;
    private JButton allFromBottomNewButton;
    private JTextArea textAreaTop;
    private JTextArea textAreaBottom;
    private JTextArea textAreaNew;
    private JButton allFromTopNewButton;
    private JButton topNewButton;


    String directory; //Default directory

    String selectionTop, selectionBottom;

    public PredlogKorisnickogInterfejsa() {
        setContentPane(contentPane);
        setModal(true);
        //    getRootPane().setDefaultButton(buttonOpen);

        saveNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {onSaveNewButton();
            }
        });

        openTopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {onOpenTopButton();
            }
        });

        openBottomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {onOpenBottomButton();
            }
        });

        buttonClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onButtonClose();
            }
        });

        allFromTopNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {onAllFromTopNewButton();
            }
        });

        topNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {onTopNewButton();
            }
        });

        allFromBottomNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {onAllFromBottomNewButton();
            }
        });

        bottomNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {onBottomNewButton();
            }
        });

        // call onCancel() when cross is clicked
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onButtonClose();
            }
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onButtonClose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /* CUVANJE FAJLA IZ NOVE SEKCIJE */

    public void saveFileNew(String directory, String filename) {
        if ((filename == null) || (filename.length() == 0))
            return;
        File file;
        FileWriter out = null;
        try {
            file = new File(directory, filename); // Create a file object
            out = new FileWriter(file); // And a char stream to write it
            textAreaNew.getLineCount(); // Get text from the text area
            String s = textAreaNew.getText();
            out.write(s);
        }
        // Display messages if something goes wrong
        catch (IOException e) {
            textAreaNew.setText(e.getClass().getName() + ": " + e.getMessage());
            this.setTitle("FileViewer: " + filename + ": I/O Exception");
        }
        // Always be sure to close the input stream!
        finally {
            try {
                if (out != null)
                    out.close();
            }
            catch (IOException e) {
            }
        }
    }

    private void onSaveNewButton() {
        // Create a file dialog box to prompt for a new file to display
        FileDialog f = new FileDialog(this, "Otvori fajl", FileDialog.SAVE);
        f.setDirectory(directory); // Set the default directory
        // Display the dialog and wait for the user's response
        f.setVisible(true);
        directory = f.getDirectory(); // Remember new default directory
        saveFileNew(directory, f.getFile()); // Load and display selection
        f.dispose(); // Get rid of the dialog box
    }

    /* UCITAVANJE GORNJE SEKCIJE */
    public void loadAndDisplayFileTop(String directory, String filename) {
        if ((filename == null) || (filename.length() == 0))
            return;
        File file;
        FileReader in = null;
        // Read and display the file contents. Since we're reading text, we
        // use a FileReader instead of a FileInputStream.
        try {
            file = new File(directory, filename); // Create a file object
            in = new FileReader(file); // And a char stream to read it
            char[] buffer = new char[4096]; // Read 4K characters at a time
            int len; // How many chars read each time
            textAreaTop.setText(""); // Clear the text area
            while ((len = in.read(buffer)) != -1) { // Read a batch of chars
                String s = new String(buffer, 0, len); // Convert to a string
                textAreaTop.append(s); // And display them
            }
            this.setTitle("FileViewer: " + filename); // Set the window title
            textAreaTop.setCaretPosition(0); // Go to start of file
        }
        // Display messages if something goes wrong
        catch (IOException e) {
            textAreaTop.setText(e.getClass().getName() + ": " + e.getMessage());
            this.setTitle("FileViewer: " + filename + ": I/O Exception");
        }
        // Always be sure to close the input stream!
        finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
            }
        }
    }

    private void onOpenTopButton() {
        // Create a file dialog box to prompt for a new file to display
        FileDialog f = new FileDialog(this, "Otvori fajl", FileDialog.LOAD);
        f.setDirectory(directory); // Set the default directory
        // Display the dialog and wait for the user's response
        f.setVisible(true);
        directory = f.getDirectory(); // Remember new default directory
        loadAndDisplayFileTop(directory, f.getFile()); // Load and display selection
        f.dispose(); // Get rid of the dialog box
    }
    /* UCITAVANJE DONJE SEKCIJE */
    public void loadAndDisplayFileBottom(String directory, String filename) {
        if ((filename == null) || (filename.length() == 0))
            return;
        File file;
        FileReader in = null;
        // Read and display the file contents. Since we're reading text, we
        // use a FileReader instead of a FileInputStream.
        try {
            file = new File(directory, filename); // Create a file object
            in = new FileReader(file); // And a char stream to read it
            char[] buffer = new char[4096]; // Read 4K characters at a time
            int len; // How many chars read each time
            textAreaBottom.setText(""); // Clear the text area
            while ((len = in.read(buffer)) != -1) { // Read a batch of chars
                String s = new String(buffer, 0, len); // Convert to a string
                textAreaBottom.append(s); // And display them
            }
            this.setTitle("FileViewer: " + filename); // Set the window title
            textAreaBottom.setCaretPosition(0); // Go to start of file
        }
        // Display messages if something goes wrong
        catch (IOException e) {
            textAreaBottom.setText(e.getClass().getName() + ": " + e.getMessage());
            this.setTitle("FileViewer: " + filename + ": I/O Exception");
        }
        // Always be sure to close the input stream!
        finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
            }
        }
    }

    private void onOpenBottomButton() {
        // Create a file dialog box to prompt for a new file to display
        FileDialog f = new FileDialog(this, "Otvori fajl", FileDialog.LOAD);
        f.setDirectory(directory); // Set the default directory
        // Display the dialog and wait for the user's response
        f.setVisible(true);
        directory = f.getDirectory(); // Remember new default directory
        loadAndDisplayFileBottom(directory, f.getFile()); // Load and display selection
        f.dispose(); // Get rid of the dialog box
    }

    private void onButtonClose() {
        // add your code here if necessary
        dispose();
    }

    /* KOPIRANJE CELE GORNJE SEKCIJE */
    private void onAllFromTopNewButton()
    {
        selectionTop = textAreaTop.getText();
        textAreaNew.append(selectionTop);
    }

    /* KOPIRANJE SELEKCIJE GORNJE SEKCIJE */
    private void onTopNewButton()
    {
        selectionTop = textAreaTop.getSelectedText();
        textAreaNew.append(selectionTop);
    }

    /* KOPIRANJE CELE DONJE SEKCIJE */
    private void onAllFromBottomNewButton()
    {
        selectionBottom = textAreaBottom.getText();
        textAreaNew.append(selectionBottom);
    }

    /* KOPIRANJE SELEKCIJE DONJE SEKCIJE */
    private void onBottomNewButton()
    {
        selectionBottom = textAreaBottom.getSelectedText();
        textAreaNew.append(selectionBottom);
    }

    public static void main(String[] args) {
        PredlogKorisnickogInterfejsa dialog = new PredlogKorisnickogInterfejsa();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
