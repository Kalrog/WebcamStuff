import java.awt.image.BufferedImage;


public class ArrayConverter {
	public static int[][][] imgtoarray(BufferedImage org) {
		int[][][] img = new int[4][org.getWidth()][org.getHeight()];
		for (int x = 1; x < org.getWidth() - 1; x++) {
			for (int y = 1; y < org.getHeight() - 1; y++) {
				for (int i = 0; i <= 3; i++) {
					img[i][x][y] = ColorConverter.extractRGB(org.getRGB(x, y))[i];
				}
			}
		}
		return img;
	}
	public static BufferedImage arraytoimg(int org[][][]) {
		BufferedImage img = new BufferedImage(org[0].length,org[0][0].length, BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < org[0].length; x++) {
			for (int y = 0; y < org[0][0].length; y++) {
				int[] color = new int[4];
				for (int i = 0; i <= 3; i++) {
					color[i] = org[i][x][y];
				}
				img.setRGB(x, y, ColorConverter.makeRGB(color));
			}
		}
		return img;
	}
}
