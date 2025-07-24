package org.progetto.DetectImage;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Utility class for preprocessing images for ONNX models.
 */
public class Preprocessor {
    /**
     * Resize + normalize + reorder channels to NCHW float tensor.
     *
     * @param img    input BufferedImage
     * @param width  target width
     * @param height target height
     * @return float tensor [1][3][height][width]
     */
    public static float[][][][] toTensor(BufferedImage img, int width, int height) {
        // 1) Resize
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g = resized.createGraphics();
        g.drawImage(img, 0, 0, width, height, null);
        g.dispose();

        // 2) Normalize & reorder to NCHW
        float[][][][] tensor = new float[1][3][height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = resized.getRGB(x, y);
                // BGR in TYPE_3BYTE_BGR
                float b = ((rgb) & 0xFF) / 255.0f;
                float gVal = ((rgb >> 8) & 0xFF) / 255.0f;
                float r = ((rgb >> 16) & 0xFF) / 255.0f;
                tensor[0][0][y][x] = r;
                tensor[0][1][y][x] = gVal;
                tensor[0][2][y][x] = b;
            }
        }
        return tensor;
    }
}
