/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typebottool_alpha.keyboard;

import org.jnativehook.keyboard.NativeKeyEvent;

/**
 *
 * @author Rajko
 */
public enum KeyBind {
    START(NativeKeyEvent.VC_F2),
    CANCEL(NativeKeyEvent.VC_F3),
    PAUSE_TYPING(NativeKeyEvent.VC_F5),
    CLOSE_APP(NativeKeyEvent.VC_F12);
    
    private int keyCode;
    
    KeyBind(int keyCode) {
        this.keyCode = keyCode;
    }
    
    public int getKeyCode() {
        return this.keyCode;
    }
}
