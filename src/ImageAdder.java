import java.awt.image.BufferedImage;

public class ImageAdder {
	public BufferedImage add(double factor1, BufferedImage one, double factor2, BufferedImage two) {
		BufferedImage img = new BufferedImage(one.getWidth(), one.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int x = 1; x < img.getWidth() - 1; x++) {
			for (int y = 1; y < img.getHeight() - 1; y++) {
				int total[] = new int[4];
				int colorone[]=ColorConverter.extractRGB(one.getRGB(x, y));
				int colortwo[]=ColorConverter.extractRGB(two.getRGB(x, y));
				for (int i = 1; i <= 3; i++) {
					total[i] = (int) (factor1 * colorone[i] + factor2 * colortwo[i]);
					if (total[i] < 0)
						total[i] = 0;
					if (total[i] > 255)
						total[i] = 255;
				}
				total[0] = 255;
				img.setRGB(x, y, ColorConverter.makeRGB(total));
			}
		}
		return img;
	}
}
