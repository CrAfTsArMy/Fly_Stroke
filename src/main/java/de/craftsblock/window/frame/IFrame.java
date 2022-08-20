package de.craftsblock.window.frame;

import javax.swing.*;
import java.awt.*;

public interface IFrame {

    void render(Graphics g, int width, int height);
    void tick();

}
