package de.craftsblock.window.frame.frames;

import de.craftsblock.logic.RenderSystem;
import de.craftsblock.logic.ResourceLocation;
import de.craftsblock.window.components.IListener;
import de.craftsblock.window.components.buttons.ImageButton;
import de.craftsblock.window.frame.FrameManager;
import de.craftsblock.window.frame.IFrame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class IngameFrame implements IFrame {

    private float alpha = 0F;
    private boolean start = false;
    private boolean fadeIn;

    private final BufferedImage image;
    private final ImageButton button;

    public IngameFrame() {
        fadeIn = true;

        image = RenderSystem.rotate(new ResourceLocation("img/fly.png").toImage(), 45);
        button = new ImageButton(image, new IListener() {
            @Override
            public void click(MouseEvent e) {
                start = true;
                RenderSystem.getWindow().setCursor(Cursor.DEFAULT_CURSOR);
            }

            @Override
            public void key(KeyEvent e) {
                start = true;
            }
        });
    }

    @Override
    public void dispose() {
        button.unregister();
    }

    @Override
    public void render(Graphics g, int width, int height) {
        if (start) alpha -= 0.2;
        else if (fadeIn) {
            alpha += 0.1;
        }

        if (alpha <= 0F) {
            dispose();
            FrameManager.current(new MenuFrame());
            return;
        } else if (alpha >= 0.8F) fadeIn = false;

        RenderSystem.setAlpha(alpha);

        button.render(10, 10);

        RenderSystem.reset();
    }

    @Override
    public void tick() {
        button.setClickable(!fadeIn);
    }

}
