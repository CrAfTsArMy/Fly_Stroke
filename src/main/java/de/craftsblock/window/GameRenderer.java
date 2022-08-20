package de.craftsblock.window;

import de.craftsblock.window.frame.FrameManager;

import javax.swing.*;
import java.awt.*;

public class GameRenderer extends JLabel {

    public static int i = 0;

    public GameRenderer() {
    }

    public GameRenderer(Icon image) {
        super(image);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        FrameManager.current().tick();
        FrameManager.current().render(g, getWidth(), getHeight());
    }

}
