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
package typebottool_alpha.mouse;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import typebottool_alpha.gui.InfoFrame;

/**
 *
 * @author Rajko
 */
public class Mouse extends Thread {

    private Robot robot;
    private boolean run = false;
    private Random rand = new Random();
    private int width;
    private int height;
    private InfoFrame frame;

    public Mouse(InfoFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) screenSize.getWidth();
        height = (int) screenSize.getHeight();
        this.frame = frame;
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(Mouse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() { //mouse runs only a primitive movement right now
        while (run) {
            mouseRandomMovement();
        }
    }

    public void pause() {
        run = false;
        frame.changeMouseStatusColor(Color.orange);
        frame.changeMouseStatus("PAUSED by user");
    }

    public void unpause() {
        run = true;
        frame.changeMouseStatusColor(Color.green);
        frame.changeMouseStatus("Hovering mouse\n\t randomly.");
    }

    public boolean isRunning() {
        return this.run;
    }

    private void mouseRandomMovement() {
        robot.mouseMove(rand.nextInt(width), rand.nextInt(height));
    }

}
