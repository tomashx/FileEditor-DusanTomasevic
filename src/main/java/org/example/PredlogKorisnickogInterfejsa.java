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
    private JButton topNewButton;
    private JButton bottomNewButton;
    private JTextArea textAreaTop;
    private JTextArea textAreaBottom;
    private JTextArea textAreaNew;

    String directory; //Default directory

    String selection;

    public PredlogKorisnickogInterfejsa() {
        setContentPane(contentPane);
        setModal(true);
        //    getRootPane().setDefaultButton(buttonOpen);

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

        topNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {onTopNewButton();
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

    private void onButtonClose() {
        // add your code here if necessary
        dispose();
    }
    public static void main(String[] args) {
        PredlogKorisnickogInterfejsa dialog = new PredlogKorisnickogInterfejsa();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
