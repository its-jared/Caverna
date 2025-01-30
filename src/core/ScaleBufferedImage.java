package core;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class ScaleBufferedImage {
    
    private static BufferedImage scale(BufferedImage before, double scale, int type) {
        int w = before.getWidth();
        int h = before.getHeight();

        int w2 = (int) (w * scale);
        int h2 = (int) (h * scale);

        BufferedImage after = new BufferedImage(w2, h2, before.getType());
        AffineTransform scaleInstance = AffineTransform.getScaleInstance(scale, scale);
        AffineTransformOp scaleOp = new AffineTransformOp(scaleInstance, type);

        scaleOp.filter(before, after);

        return after;
    }

    public static BufferedImage scaleBilinear(BufferedImage before, double scale) {
        int interpolation = AffineTransformOp.TYPE_BILINEAR;
        return scale(before, scale, interpolation);
    }

    public static BufferedImage scaleBicubic(BufferedImage before, double scale) {
        int interpolation = AffineTransformOp.TYPE_BICUBIC;
        return scale(before, scale, interpolation);
    }

    public static BufferedImage scaleNearest(BufferedImage before, double scale) {
        int interpolation = AffineTransformOp.TYPE_NEAREST_NEIGHBOR;
        return scale(before, scale, interpolation);
    }

}
