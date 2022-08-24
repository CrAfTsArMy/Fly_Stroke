package de.craftsblock.window.components.buttons;

import de.craftsblock.logic.RenderSystem;
import de.craftsblock.window.components.IComponent;
import de.craftsblock.window.components.IListener;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class ImageButton implements IComponent, MouseListener, MouseMotionListener, KeyListener {

    private BufferedImage image;
    private final IListener listener;

    private boolean clickable = false;

    private int x;
    private int y;

    public ImageButton(BufferedImage image) {
        this(image, null);
    }

    public ImageButton(BufferedImage image, IListener listener) {
        this.image = image;
        this.listener = listener;

        RenderSystem.getWindow().addMouseListener(this);
        RenderSystem.getWindow().addMouseMotionListener(this);
        RenderSystem.getWindow().addKeyListener(this);
    }

    @Override
    public void render(int x, int y) {
        this.x = x;
        this.y = y;

        Graphics2D graphics2D = RenderSystem.getGraphics2D();
        graphics2D.drawImage(image, x, y, null);
        RenderSystem.reset();

        if (RenderSystem.shouldRenderLayoutBorders()) {
            graphics2D.setColor(new Color(0xf3e16b));
            graphics2D.drawLine(x, y, x + image.getWidth(), y + image.getHeight());
            graphics2D.drawLine(x, y + image.getHeight(), x + image.getWidth(), y);

            if (!clickable)
                graphics2D.setColor(new Color(0xff4c30));
            else
                graphics2D.setColor(new Color(0x2ecc71));
            graphics2D.drawRect(x, y, image.getWidth(), image.getHeight());

            graphics2D.setColor(new Color(0xc44d56));
            graphics2D.drawLine(x, y, x, y + 15);
            graphics2D.drawLine(x, y, x + 15, y);

            graphics2D.drawLine(x + image.getWidth(), y, x + image.getWidth(), y + 15);
            graphics2D.drawLine(x + image.getWidth() - 15, y, x + image.getWidth(), y);

            graphics2D.drawLine(x, y + image.getHeight() - 15, x, y + image.getHeight());
            graphics2D.drawLine(x, y + image.getHeight(), x + 15, y + image.getHeight());

            graphics2D.drawLine(x + image.getWidth(), y + image.getHeight() - 15, x + image.getWidth(), y + image.getHeight());
            graphics2D.drawLine(x + image.getWidth() - 15, y + image.getHeight(), x + image.getWidth(), y + image.getHeight());

            graphics2D.setColor(new Color(0xf3e16b));
            RenderSystem.setSize(12);
            RenderSystem.setStyle(Font.BOLD);
            RenderSystem.update();
            graphics2D.drawString(image.getWidth() + "px", x, y + image.getHeight() + RenderSystem.getFont().getSize() + 3);

            RenderSystem.rotate(90);
            RenderSystem.update();
            graphics2D.drawString(image.getHeight() + "px", x + image.getWidth() + 6, y);

            RenderSystem.reset();
        }

    }

    @Override
    public void tick() {

    }

    public void unregister() {
        RenderSystem.getWindow().removeMouseListener(this);
        RenderSystem.getWindow().removeMouseMotionListener(this);
        RenderSystem.getWindow().removeKeyListener(this);
    }

    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }

    public boolean isClickable() {
        return clickable;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        boolean clicked = e.getX() >= x + 8 &&
                e.getY() >= y + 31 &&
                e.getX() <= x + image.getWidth() + 8 &&
                e.getY() <= y + image.getHeight() + 31;
        if (clickable && listener != null && clicked)
            listener.click(e);
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
        boolean over = e.getX() >= x + 8 &&
                e.getY() >= y + 31 &&
                e.getX() <= x + image.getWidth() + 8 &&
                e.getY() <= y + image.getHeight() + 31;
        if (over && clickable)
            RenderSystem.getContent().setCursor(new Cursor(Cursor.HAND_CURSOR));
        else
            RenderSystem.getContent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (clickable && listener != null)
            if (RenderSystem.isSystemKey(e.getKeyCode()))
                listener.key(e);
    }
}
