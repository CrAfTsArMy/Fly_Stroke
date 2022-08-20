package de.craftsblock.window.frame.frames;

import de.craftsblock.logic.RenderSystem;
import de.craftsblock.logic.ResourceLocation;
import de.craftsblock.window.frame.FrameManager;
import de.craftsblock.window.frame.IFrame;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class MenuFrame implements IFrame, MouseListener, MouseMotionListener, KeyListener {

    private float alpha = 0F;
    private boolean start = false;
    private boolean fadeIn;

    private final BufferedImage image = new ResourceLocation("img/bloodybutton.png").toImage();
    private int width;
    private int height;

    public MenuFrame() {
        fadeIn = true;
        RenderSystem.getWindow().addMouseListener(this);
        RenderSystem.getWindow().addMouseMotionListener(this);
        RenderSystem.getWindow().addKeyListener(this);
    }

    public void dispose() {
        RenderSystem.getWindow().removeMouseListener(this);
        RenderSystem.getWindow().removeMouseMotionListener(this);
        RenderSystem.getWindow().removeKeyListener(this);
    }

    @Override
    public void render(Graphics g, int width, int height) {
        this.width = width;
        this.height = height;

        Graphics2D gr = (Graphics2D) g;
        if (start)
            alpha -= 0.2;
        else if (fadeIn) {
            alpha += 0.01;
        }

        if (alpha <= 0F) {
            dispose();
            FrameManager.current(new MenuFrame());
            return;
        } else if (alpha >= 0.8F)
            fadeIn = false;

        RenderSystem.setAlpha(alpha, gr);
        gr.drawImage(image, (width - 565) / 2, (height - 206) / 2, null);
        RenderSystem.resetAlpha(gr);

        RenderSystem.renderLayoutBorders(true);
        if (RenderSystem.shouldRenderLayoutBorders()) {
            gr.setColor(Color.YELLOW);
            gr.drawRect((width - image.getWidth()) / 2, (height - image.getHeight()) / 2 + 16, image.getWidth() - 16, image.getHeight() - 32);
        }
    }

    @Override
    public void tick() {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!fadeIn)
            start = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (fadeIn)
            return;
        boolean clicked = e.getX() >= (width - image.getWidth()) / 2 + 4 &&
                e.getY() >= (height - image.getHeight()) / 2 + 48 &&
                e.getX() <= (width - image.getWidth()) / 2 + image.getWidth() &&
                e.getY() <= (height - image.getHeight()) / 2 + image.getHeight() + 16;
        if (clicked) {
            start = true;
            RenderSystem.getWindow().setCursor(Cursor.DEFAULT_CURSOR);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (fadeIn)
            return;
        boolean over = e.getX() >= (width - image.getWidth()) / 2 + 4 &&
                e.getY() >= (height - image.getHeight()) / 2 + 48 &&
                e.getX() <= (width - image.getWidth()) / 2 + image.getWidth() &&
                e.getY() <= (height - image.getHeight()) / 2 + image.getHeight() + 16;
        if (over)
            RenderSystem.getWindow().setCursor(Cursor.HAND_CURSOR);
        else
            RenderSystem.getWindow().setCursor(Cursor.DEFAULT_CURSOR);
    }
}
