package de.craftsblock.window.frame;

import de.craftsblock.window.frame.frames.MenuFrame;

public class FrameManager {

    private static IFrame current = new MenuFrame();
    public static IFrame current() {
        return current;
    }
    public static void current(IFrame current) {
        FrameManager.current = current;
    }

}
