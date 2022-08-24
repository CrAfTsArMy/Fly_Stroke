package de.craftsblock.logic;

import de.craftsblock.window.GameRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class RenderSystem {

    private static JFrame window;

    private static GameRenderer content;
    private static Graphics2D graphics2D;

    private static Font font = new Font("TimesRoman", Font.PLAIN, 32);
    private static boolean renderLayoutBorders = false;
    private static boolean renderMouseLocation = false;
    private static boolean showFPS = false;

    private static int mouseX;
    private static int mouseY;

    public static void setWindow(JFrame window) {
        RenderSystem.window = window;
    }

    public static JFrame getWindow() {
        return window;
    }

    public static void setContent(GameRenderer content) {
        RenderSystem.content = content;
    }

    public static GameRenderer getContent() {
        return content;
    }

    public static void setAlpha(float alpha) {
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        graphics2D.setComposite(ac);
    }

    public static void resetAlpha() {
        setAlpha(1F);
    }

    public static boolean shouldRenderLayoutBorders() {
        return renderLayoutBorders;
    }

    public static void renderLayoutBorders(boolean renderLayoutBorders) {
        RenderSystem.renderLayoutBorders = renderLayoutBorders;
    }

    public static boolean shouldRenderMouseLocation() {
        return renderMouseLocation;
    }

    public static void renderMouseLocation(boolean renderMouseLocation) {
        RenderSystem.renderMouseLocation = renderMouseLocation;
    }

    public static boolean shouldRenderFPS() {
        return showFPS;
    }

    public static void showFPS(boolean showFPS) {
        RenderSystem.showFPS = showFPS;
    }

    public static void updateFont() {
        graphics2D.setFont(font);
    }

    public static Font getFont() {
        return font;
    }

    public static void setSize(int font$size) {
        font = new Font(font.getName(), font.getStyle(), font$size);
    }

    public static void setStyle(int font$style) {
        font = new Font(font.getName(), font$style, font.getSize());
    }

    public static void setFont(String font) {
        RenderSystem.font = new Font(font, RenderSystem.font.getStyle(), RenderSystem.font.getSize());
    }

    public static void resetFont() {
        font = new Font("TimesRoman", Font.PLAIN, 32);
    }

    public static void rotate(int angle) {
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(Math.toRadians(angle), 0, 0);
        font = font.deriveFont(affineTransform);
    }

    public static void setGraphics2D(Graphics2D graphics2D) {
        RenderSystem.graphics2D = graphics2D;
    }

    public static Graphics2D getGraphics2D() {
        return graphics2D;
    }

    public static BufferedImage resize(BufferedImage img, int width, int height) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return dimg;
    }

    // TODO: Scale to right Size

    public static BufferedImage rotate(BufferedImage img, double angle) {
        int width = img.getWidth();
        int height = img.getHeight();
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(angle * Math.PI / 180.0, width / 2, height / 2);
        double offset = (width - height) / 2;
        affineTransform.translate(offset, offset);
        return new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BILINEAR).filter(img, null);
    }

    public static void reset() {
        resetAlpha();
        resetFont();
        graphics2D.setColor(new Color(0x0));
        update();
    }

    public static void update() {
        updateFont();
    }

    public static int getMouseX() {
        return mouseX;
    }

    public static int getMouseY() {
        return mouseY;
    }

    public static void setMouseX(int mouseX) {
        RenderSystem.mouseX = mouseX;
    }

    public static void setMouseY(int mouseY) {
        RenderSystem.mouseY = mouseY;
    }

    public static boolean isSystemKey(int keycode) {
        return keycode != KeyEvent.VK_C &&
                keycode != KeyEvent.VK_X &&
                keycode != KeyEvent.VK_Y;
    }

}
