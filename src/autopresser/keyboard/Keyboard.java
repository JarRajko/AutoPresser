package autopresser.keyboard;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.TimerTask;
import autopresser.gui.InfoFrame;

public class Keyboard extends TimerTask {

    private Robot robot;
    private int delay_ms;
    private String stringToType;
    private int keysTyped = 0;
    private boolean paused = false;
    private InfoFrame frame;
    private KeyMapper mapper = new KeyMapper();

    public Keyboard(String stringToType, int delay_ms, InfoFrame frame) {
        this.frame = frame;
        this.stringToType = stringToType;
        this.delay_ms = delay_ms;
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        if (keysTyped >= stringToType.length()) {
            stopTyping();
            return;
        }

        startedTyping();
        char c = stringToType.charAt(keysTyped);
        
        try {
            type(c);
            updateStatus();
        } catch (Exception e) {
            frame.changeKeyboardStatusColor(Color.RED);
        }
        
        keysTyped++;
    }

    private void type(char c) {
        KeyMapper.KeyCombo combo = mapper.get(c);

        if (combo != null) {
            pressCombo(combo.keyCode, combo.shift);
        } else if (Character.isUpperCase(c)) {
            int code = KeyEvent.getExtendedKeyCodeForChar(Character.toLowerCase(c));
            pressCombo(code, true);
        } else {
            int code = KeyEvent.getExtendedKeyCodeForChar(c);
            pressCombo(code, false);
        }
    }

    private void pressCombo(int keyCode, boolean shift) {
        if (shift) robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(keyCode);
        robot.keyRelease(keyCode);
        if (shift) robot.keyRelease(KeyEvent.VK_SHIFT);
    }

    private void updateStatus() {
        double progress = (double) keysTyped * 100 / stringToType.length();
        frame.changeKeyboardStatus(keysTyped + " / " + stringToType.length() + " (" + (int)progress + "%)");
        frame.changeKeyboardStatusColor(new Color(0, 250, 0));
    }

    public void stopTyping() {
        cancel();
        keysTyped = 0;
        frame.setKeyboardStatusIdle();
    }

    public void startedTyping() { frame.changeKeyboardStatusColor(Color.GREEN); }
    public int pauseTyping() { paused = true; cancel(); return keysTyped; }
    public boolean isPaused() { return paused; }
    public int getTypingDelay() { return delay_ms; }
    public void setKeysTyped(int keysTyped) { this.keysTyped = keysTyped; }
    public int getKeysTyped() { return keysTyped; }
    public void endedTyping() { frame.setKeyboardStatusIdle(); }
}