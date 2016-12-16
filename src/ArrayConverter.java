import java.awt.image.BufferedImage;


public class ArrayConverter {
	public static int[][][] imgtoarray(BufferedImage org) {
		int[][][] img = new int[3][org.getWidth()][org.getHeight()];
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
