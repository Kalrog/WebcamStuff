import java.awt.image.BufferedImage;


public class ImgToArray {
	public static int convert(BufferedImage org) {
		int[][][] img;
		for (int x = 1; x < org.getWidth() - 1; x++) {
			for (int y = 1; y < org.getHeight() - 1; y++) {
				for (int i = 0; i <= 3; i++) {
					img[i][x][y] = ColorConverter.extractRGB(org.getRGB(x, y))[i];
				}
			}
		}
		return img;
	}
}
