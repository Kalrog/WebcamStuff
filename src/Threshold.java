import java.awt.image.BufferedImage;


public class Threshold {
	public BufferedImage apply(BufferedImage org, int threshold) {
		BufferedImage img = new BufferedImage(org.getWidth(), org.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for(int x = 0; x < img.getWidth(); x++){
			for(int y = 0; y < img.getHeight(); y++){
				int color[]=ColorConverter.extractRGB(org.getRGB(x, y));
				for (int i = 1; i <= 3; i++) {
					if(color[i] < threshold) {
						color[i] = 0;
					}
					img.setRGB(x, y, ColorConverter.makeRGB(color));
				}
			}
		}
		return img;
	}
}
