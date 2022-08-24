package de.craftsblock.window.components;

import de.craftsblock.logic.RenderSystem;

import javax.swing.*;
import java.awt.*;

public interface IComponent {

    JFrame window = RenderSystem.getWindow();

    void render(int x, int y);

    void tick();

}
