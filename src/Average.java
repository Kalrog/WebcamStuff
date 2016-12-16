import java.awt.image.BufferedImage;

public class Average {
	public BufferedImage apply(BufferedImage one, BufferedImage two) {
		BufferedImage img = new BufferedImage(one.getWidth(), one.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int x = 1; x < one.getWidth() - 1; x++) {
			for (int y = 1; y < one.getHeight() - 1; y++) {
					int total[] = new int[4];
				
					int colorone[]=ColorConverter.extractRGB(one.getRGB(x, y));
					int colortwo[]=ColorConverter.extractRGB(two.getRGB(x, y));
					for (int i = 0; i <= 3; i++) {
						total[i] = (colorone[i]+colortwo[i])/2;
					}
					img.setRGB(x, y, ColorConverter.makeRGB(total));
			}
		}
		return img;
	}
}
