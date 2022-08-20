package de.craftsblock.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ResourceLocation {

    private final String path;

    public ResourceLocation(String path) {
        this.path = path;
    }

    public BufferedImage toImage() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public Font toFont() {
        try {
            return Font.createFont(Font.PLAIN, Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(path)));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
