/*
 * Copyright (C) 2020 Rajko
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package autopresser.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.dispatcher.SwingDispatchService;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import autopresser.KeyBind;
import autopresser.keyboard.Keyboard;
import autopresser.mouse.Mouse;

/**
 *
 * @author Rajko
 */
public class UI extends javax.swing.JFrame implements NativeKeyListener {

    private Keyboard keyboard;
    private Timer timer = new Timer();
    private InfoFrame frame;
    private Mouse mouse;

    /**
     * Creates new form UI
     */
    public UI() {
        initComponents();
        this.delaySlider.setMaximum(200);
        this.delaySlider.setMinimum(1);
        this.delaySlider.setValue(100);
        this.delayLabel.setText("Delay for letters (ms) " + delaySlider.getValue());
        this.delaySlider.addChangeListener(new javax.swing.event.ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                delayLabel.setText("Delay for letters (ms) " + delaySlider.getValue());
            }
        });

        // Get the logger for "org.jnativehook" and set the level to warning.
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        // Don't forget to disable the parent handlers.
        //logger.setUseParentHandlers(false);
        // Set the event dispatcher to a swing safe executor service.
        GlobalScreen.setEventDispatcher(new SwingDispatchService());

        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(this);
        } catch (NativeHookException ex) {
            System.out.println("pir");
        }
        this.setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        this.setLocation((width / 2) - this.getWidth() / 2, (height / 2) - this.getHeight() / 2);
        frame = new InfoFrame();

        mouse = new Mouse(frame);
        mouse.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        loadButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        addPrefixButton = new javax.swing.JButton();
        deletePrefixButton = new javax.swing.JButton();
        delaySlider = new javax.swing.JSlider();
        delayLabel = new javax.swing.JLabel();
        shuffleButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        loadButton.setText("Load file");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        saveButton.setText("Save file");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        addPrefixButton.setText("Add prefix");
        addPrefixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPrefixButtonActionPerformed(evt);
            }
        });

        deletePrefixButton.setText("Del prefix");
        deletePrefixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePrefixButtonActionPerformed(evt);
            }
        });

        delayLabel.setText("Delay for letters (ms)");

        shuffleButton.setText("Shuffle");
        shuffleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shuffleButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(loadButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addPrefixButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(shuffleButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deletePrefixButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(delayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(delaySlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loadButton)
                    .addComponent(saveButton)
                    .addComponent(addPrefixButton)
                    .addComponent(deletePrefixButton)
                    .addComponent(shuffleButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(delaySlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(delayLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed

        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                File selectedFile = fileChooser.getSelectedFile();
                Scanner scan = new Scanner(selectedFile);
                String s = "";

                while (scan.hasNext()) {
                    s += scan.nextLine() + "\n";
                }

                jTextArea1.setText(jTextArea1.getText() + "\n" + s);

            } catch (FileNotFoundException ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_loadButtonActionPerformed

    private void deletePrefixButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePrefixButtonActionPerformed
        if (!jTextArea1.getText().isEmpty()) {
            try {
                int subS = Integer.parseInt(JOptionPane.showInputDialog("Enter number of lerres to delete from start."));
                String res = "";

                String s[] = jTextArea1.getText().split("\n");

                for (int i = 0; i < s.length; i++) {
                    res = res + s[i].substring(subS) + "\n";
                }

                jTextArea1.setText(res);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_deletePrefixButtonActionPerformed

    private void addPrefixButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPrefixButtonActionPerformed
        if (!jTextArea1.getText().isEmpty()) {
            String s[] = jTextArea1.getText().split("\n");
            String prefix = JOptionPane.showInputDialog("Enter prefix to add:");

            String result = "";

            for (int i = 0; i < s.length; i++) {
                s[i] = prefix + s[i];
                result += s[i] + "\n";
            }

            jTextArea1.setText(result);

        } else {
            JOptionPane.showMessageDialog(this, "Field is empty!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addPrefixButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(JOptionPane.showInputDialog("Enter file name: ") + ".txt");

            printWriter.print(jTextArea1.getText());
            JOptionPane.showMessageDialog(null, "File saved!");
            printWriter.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        } finally {
            printWriter.close();
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void shuffleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shuffleButtonActionPerformed
        String s[] = jTextArea1.getText().split("\n");

        List<String> l = Arrays.asList(s);
        Collections.shuffle(l);
        s = l.toArray(new String[l.size()]);

        String res = "";

        for (int i = 0; i < s.length; i++) {
            res = res + s[i] + "\n";
        }
        jTextArea1.setText(res);
    }//GEN-LAST:event_shuffleButtonActionPerformed

    @Override
    public void nativeKeyTyped(NativeKeyEvent nke) {
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nke) {

        if (nke.getKeyCode() == KeyBind.START_STOP.getKeyCode()) {
            if (keyboard == null) {
                keyboard = new Keyboard(jTextArea1.getText(), this.delaySlider.getValue(), frame);
                timer.schedule(keyboard, 0, keyboard.getTypingDelay());
            } else if (keyboard.isPaused()) {
                int keysTyped = keyboard.getKeysTyped();
                keyboard = new Keyboard(jTextArea1.getText(), this.delaySlider.getValue(), frame);
                keyboard.setKeysTyped(keysTyped);
                timer.schedule(keyboard, 0, keyboard.getTypingDelay());
            } else if (keyboard.getKeysTyped() != 0) {
                keyboard.pauseTyping();
                frame.changeKeyboardStatus("Typing paused");
                frame.changeKeyboardStatusColor(new Color(255, 150, 0));
            } else {
                keyboard = new Keyboard(jTextArea1.getText(), this.delaySlider.getValue(), frame);
                timer.schedule(keyboard, 0, keyboard.getTypingDelay());
            }
        } else if (nke.getKeyCode() == KeyBind.CANCEL.getKeyCode()) {
            if (keyboard != null) {
                keyboard.stopTyping();
                frame.setKeyboardStatus("Typing cancelled by user.");
            }
        } else if (nke.getKeyCode() == KeyBind.CLOSE_APP.getKeyCode()) {
            System.out.println("User exiting application...");
            System.exit(0);
        } /*else if (nke.getKeyCode() == KeyBind.START_STOP.getKeyCode()) {

        }*/ else if (nke.getKeyCode() == KeyBind.MOUSE_START_STOP.getKeyCode()) {
            if (mouse.isRunning()) {
                mouse.pause();
                System.out.println("paused");
            } else {
                mouse.unpause();
                mouse.run();
                System.out.println("unpaused");
            }
        }

    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nke) {
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPrefixButton;
    private javax.swing.JLabel delayLabel;
    private javax.swing.JSlider delaySlider;
    private javax.swing.JButton deletePrefixButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton loadButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton shuffleButton;
    // End of variables declaration//GEN-END:variables
}