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
package autopresser;

import org.jnativehook.keyboard.NativeKeyEvent;

/**
 *
 * @author Rajko
 */
public enum KeyBind {
    MOUSE_START_STOP(NativeKeyEvent.VC_F2),
    CANCEL(NativeKeyEvent.VC_F3),
    START_STOP(NativeKeyEvent.VC_F5),
    CLOSE_APP(NativeKeyEvent.VC_F12);
    
    private int keyCode;
    
    KeyBind(int keyCode) {
        this.keyCode = keyCode;
    }
    
    public int getKeyCode() {
        return this.keyCode;
    }
}
