package de.craftsblock.logic;

import javax.swing.*;
import java.awt.*;

public class RenderSystem {

    private static JFrame window;

    private static boolean renderLayoutBorders = false;

    public static void setWindow(JFrame window) {
        RenderSystem.window = window;
    }

    public static JFrame getWindow() {
        return window;
    }

    public static void setAlpha(float alpha, Graphics2D graphics2D) {
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        graphics2D.setComposite(ac);
    }

    public static void resetAlpha(Graphics2D graphics2D) {
        setAlpha(1F, graphics2D);
    }

    public static boolean shouldRenderLayoutBorders() {
        return renderLayoutBorders;
    }

    public static void renderLayoutBorders(boolean renderLayoutBorders) {
        RenderSystem.renderLayoutBorders = renderLayoutBorders;
    }

}
