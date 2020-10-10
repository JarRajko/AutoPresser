/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typebottool_alpha.keyboard;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.KeyStroke;
import typebottool_alpha.gui.InfoFrame;

/**
 *
 * @author Rajko
 */
public class Keyboard extends TimerTask {

    private Robot robot;
    private int delay_ms;
    private String stringToType;
    private int keysTyped = 0;
    private boolean paused = false;
    private InfoFrame frame;

    public Keyboard(String stringToType, int delay_ms, InfoFrame frame) {
        this.frame = frame;
        try {
            this.delay_ms = delay_ms;
            this.stringToType = stringToType;
            robot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(Keyboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {

        if (keysTyped == 0) {
            startedTyping();
        } else if (keysTyped == stringToType.length()) {
            endedTyping();
        }

        if (keysTyped < stringToType.length()) {
            try {
                if (Character.isUpperCase(stringToType.charAt(keysTyped))) {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    KeyStroke ks = KeyStroke.getKeyStroke(stringToType.toLowerCase().charAt(keysTyped), 0);
                    robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(ks.getKeyCode()));
                    robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(ks.getKeyCode()));
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else {
                    robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(stringToType.charAt(keysTyped)));
                    robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(stringToType.charAt(keysTyped)));
                }
                frame.changeKeyboardStatus(stringToType.charAt(keysTyped) + "");
                frame.changeKeyboardStatusColor(new Color(0, 250, 0));
            } catch (IllegalArgumentException e) {
                if (stringToType.charAt(keysTyped) == '!') {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_1);
                    robot.keyRelease(KeyEvent.VK_1);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else if (stringToType.charAt(keysTyped) == '@') {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_2);
                    robot.keyRelease(KeyEvent.VK_2);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else if (stringToType.charAt(keysTyped) == '#') {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_3);
                    robot.keyRelease(KeyEvent.VK_3);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else if (stringToType.charAt(keysTyped) == '$') {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_4);
                    robot.keyRelease(KeyEvent.VK_4);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else if (stringToType.charAt(keysTyped) == '%') {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_5);
                    robot.keyRelease(KeyEvent.VK_5);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else if (stringToType.charAt(keysTyped) == '^') {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_6);
                    robot.keyRelease(KeyEvent.VK_6);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else if (stringToType.charAt(keysTyped) == '&') {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_7);
                    robot.keyRelease(KeyEvent.VK_7);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else if (stringToType.charAt(keysTyped) == '*') {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_8);
                    robot.keyRelease(KeyEvent.VK_8);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else if (stringToType.charAt(keysTyped) == '(') {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_9);
                    robot.keyRelease(KeyEvent.VK_9);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else if (stringToType.charAt(keysTyped) == ')') {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_0);
                    robot.keyRelease(KeyEvent.VK_0);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else if (stringToType.charAt(keysTyped) == '_') {
                    robot.keyPress(KeyEvent.VK_UNDERSCORE);
                    robot.keyRelease(KeyEvent.VK_UNDERSCORE);
                } else if (stringToType.charAt(keysTyped) == ']') {
                    robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
                    robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
                } else if (stringToType.charAt(keysTyped) == '[') {
                    robot.keyPress(KeyEvent.VK_OPEN_BRACKET);
                    robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
                } else if (stringToType.charAt(keysTyped) == ';') {
                    robot.keyPress(KeyEvent.VK_SEMICOLON);
                    robot.keyRelease(KeyEvent.VK_SEMICOLON);
                } else {
                    System.out.println("Errornous character: " + stringToType.charAt(keysTyped));
                    frame.changeKeyboardStatusColor(new Color(255, 0, 0));
                }

                frame.changeKeyboardStatus(stringToType.charAt(keysTyped) + "");

            } catch (Exception e) {
                e.printStackTrace();
                frame.changeKeyboardStatusColor(new Color(255, 0, 0));
                frame.changeKeyboardStatus("Error has occured!");
            }
            keysTyped++;
        } else {
            stopTyping();
        }
    }

    public String pauseTyping() {
        paused = true;
        cancel();
        return stringToType.substring(keysTyped, stringToType.length());
    }

    public void resumeTyping() {
        paused = false;
    }

    public void stopTyping() {
        cancel();
        keysTyped = 0;
        endedTyping();
    }

    public int getTypingDelay() {
        return this.delay_ms;
    }

    public boolean isPaused() {
        return this.paused;
    }

    public void startedTyping() {
        frame.changeKeyboardStatusColor(new Color(0, 255, 0));
        frame.changeKeyboardStatus("Typing... ");
    }

    public void endedTyping() {
        frame.setKeyboardStatusIdle();
    }

    

}
