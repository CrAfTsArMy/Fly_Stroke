package de.craftsblock.window;

import de.craftsblock.logic.RenderSystem;
import de.craftsblock.window.frame.FrameManager;

import javax.swing.*;
import java.awt.*;

public class GameRenderer extends JLabel {

    public long frameStart;
    public long frameCount = 0;
    public long reportedFramerate = 60;
    public long totalElapsedTime = 0;


    public GameRenderer() {
    }

    public GameRenderer(Icon image) {
        super(image);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        RenderSystem.setGraphics2D(graphics2D);
        frameStart = System.currentTimeMillis();

        RenderSystem.setContent(this);
        super.paintComponent(g);
        FrameManager.current().tick();
        FrameManager.current().render(g, getWidth(), getHeight());

        if (RenderSystem.shouldRenderMouseLocation()) {
            graphics2D.setColor(new Color(0x4193a9));
            RenderSystem.setSize(12);
            RenderSystem.setStyle(Font.BOLD);
            RenderSystem.rotate(90);
            RenderSystem.update();
            graphics2D.drawString(RenderSystem.getMouseY() + "px", RenderSystem.getMouseX(), RenderSystem.getMouseY() - 72);
            graphics2D.drawLine(RenderSystem.getMouseX() - 8, 0, RenderSystem.getMouseX() - 8, RenderSystem.getWindow().getWidth());

            RenderSystem.resetFont();
            RenderSystem.setSize(12);
            RenderSystem.setStyle(Font.BOLD);
            RenderSystem.update();
            graphics2D.drawString(RenderSystem.getMouseX() + "px", RenderSystem.getMouseX() - 48, RenderSystem.getMouseY() - 38);
            graphics2D.drawLine(0, RenderSystem.getMouseY() - 31, RenderSystem.getWindow().getWidth(), RenderSystem.getMouseY() - 31);
            RenderSystem.reset();
        }

        if(RenderSystem.shouldRenderFPS()) {
            graphics2D.setColor(new Color(0x4193a9));
            RenderSystem.setSize(12);
            RenderSystem.setStyle(Font.BOLD);
            RenderSystem.update();
            graphics2D.drawString("FPS: " + reportedFramerate, 5, 15);
        }

//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//        }
        ++frameCount;
        totalElapsedTime += (System.currentTimeMillis() - frameStart);
        if (totalElapsedTime > 50) {
            reportedFramerate = (long) ((double) frameCount
                    / (double) totalElapsedTime * 1000.0);
            System.out.println("fps: " + reportedFramerate);
            frameCount = 0;
            totalElapsedTime = 0;
        }

        graphics2D.dispose();
    }

}
