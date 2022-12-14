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

public class MenuFrame implements IFrame {

    private float alpha = 0F;
    private boolean start = false;
    private boolean fadeIn;

    private final BufferedImage image;
    private final ImageButton button;

    public MenuFrame() {
        fadeIn = true;

        image = new ResourceLocation("img/buttons/startbutton.png").toImage();
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
        if (start)
            alpha -= 0.2;
        else if (fadeIn) {
            alpha += 0.01;
        }

        if (alpha <= 0F) {
            dispose();
            FrameManager.current(new IngameFrame());
            return;
        } else if (alpha >= 0.8F)
            fadeIn = false;

        RenderSystem.setAlpha(alpha);
        button.render((width - image.getWidth()) / 2, (height - image.getHeight()) / 2);
//        button.setImage(RenderSystem.resize(new ResourceLocation("img/fly.png").toImage(), 32, 32));
    }

    @Override
    public void tick() {
        button.setClickable(!fadeIn);
    }

}
