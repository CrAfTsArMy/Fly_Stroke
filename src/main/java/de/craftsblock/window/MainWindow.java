package de.craftsblock.window;

import de.craftsblock.Main;
import de.craftsblock.logic.RenderSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.net.URL;

public class MainWindow extends JFrame {

    public MainWindow() {
        super("Fly Stroke");

        RenderSystem.setWindow(this);

        URL url = Main.class.getClassLoader().getResource("img/sky.gif");
        assert url != null;
        ImageIcon imageIcon = new ImageIcon(url);
        getContentPane().add(new GameRenderer(imageIcon));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(800, 600));

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                RenderSystem.setMouseX(e.getX());
                RenderSystem.setMouseY(e.getY());
            }
        });

//        Thread t = new Thread(() -> {
//
//        });
//        t.setDaemon(true);
//        t.setName("Mouse Updater");
//        t.start();

//        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_C)
                    RenderSystem.renderLayoutBorders(!RenderSystem.shouldRenderLayoutBorders());
                else if (e.getKeyCode() == KeyEvent.VK_X)
                    RenderSystem.renderMouseLocation(!RenderSystem.shouldRenderMouseLocation());
                else if (e.getKeyCode() == KeyEvent.VK_Y)
                    RenderSystem.showFPS(!RenderSystem.shouldRenderFPS());
            }
        });
    }

}
