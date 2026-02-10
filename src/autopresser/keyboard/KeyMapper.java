/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autopresser.keyboard;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class KeyMapper {
    
    
    private final Map<Character, KeyCombo> map = new HashMap<>();

    public KeyMapper() {
        add('!', KeyEvent.VK_1, true);
        add('@', KeyEvent.VK_2, true);
        add('#', KeyEvent.VK_3, true);
        add('$', KeyEvent.VK_4, true);
        add('%', KeyEvent.VK_5, true);
        add('^', KeyEvent.VK_6, true);
        add('&', KeyEvent.VK_7, true);
        add('*', KeyEvent.VK_8, true);
        add('(', KeyEvent.VK_9, true);
        add(')', KeyEvent.VK_0, true);
        add('_', KeyEvent.VK_MINUS, true);
        add('[', KeyEvent.VK_OPEN_BRACKET, false);
        add(']', KeyEvent.VK_CLOSE_BRACKET, false);
        add(';', KeyEvent.VK_SEMICOLON, false);
        add('~', KeyEvent.VK_ENTER, false);
        add(' ', KeyEvent.VK_SPACE, false);
    }

    private void add(char c, int keyCode, boolean shift) {
        map.put(c, new KeyCombo(keyCode, shift));
    }

    public KeyCombo get(char c) {
        return map.get(c);
    }

    public static class KeyCombo {
        public final int keyCode;
        public final boolean shift;

        public KeyCombo(int keyCode, boolean shift) {
            this.keyCode = keyCode;
            this.shift = shift;
        }
    }
    
}
