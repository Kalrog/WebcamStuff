import java.awt.image.BufferedImage;


public class Mirror {
	public BufferedImage apply(BufferedImage org) {
		BufferedImage img = new BufferedImage(org.getWidth(), org.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int x = 1; x < img.getWidth() - 1; x++) {
			for (int y = 1; y < img.getHeight() - 1; y++) {
				img.setRGB(x, y, org.getRGB(img.getWidth() - x, y));
				}
		}
		return img;
	}
}
