package de.craftsblock.window;

import de.craftsblock.Main;
import de.craftsblock.logic.RenderSystem;

import javax.swing.*;
import java.awt.*;
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

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
