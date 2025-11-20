package org.example.nasa.util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AsciiRenderer {

    private static final char[] ASCII_CHARS = (
            "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. "
    ).toCharArray();

    public String render(BufferedImage image, int maxWidth) {

        int width = image.getWidth();
        int height = image.getHeight();

        double scale = (double) maxWidth / width;
        int newWidth = maxWidth;
        int newHeight = (int) (height * scale * 0.5);

        Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        BufferedImage resized = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resized.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(scaledImage, 0, 0, null);
        g.dispose();

        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {

                int rgb = resized.getRGB(x, y);

                int r = (rgb >> 16) & 0xFF;
                int gcol = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                double luminance = 0.2126 * r + 0.7152 * gcol + 0.0722 * b;

                int index = (int) ((luminance / 255.0) * (ASCII_CHARS.length - 1));

                sb.append(ASCII_CHARS[index]);
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
