package conwaysGameOfLife;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

    private final JFrame attachedFrame;

    public KeyInput(JFrame attachedFrame) {
        this.attachedFrame = attachedFrame;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            System.out.println("Space pressed");
            attachedFrame.dispose();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
