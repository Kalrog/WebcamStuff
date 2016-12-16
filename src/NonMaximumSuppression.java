import java.awt.image.BufferedImage;


public class NonMaximumSuppression {
	public BufferedImage apply(BufferedImage grad, BufferedImage stre){
		BufferedImage img = new BufferedImage(grad.getWidth(), grad.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int x = 1; x < grad.getWidth() - 1; x++) {
			for (int y = 1; y < grad.getHeight() - 1; y++) {
				int gradient = ColorConverter.extractRGB(grad.getRGB(x, y))[1];
				int nx, ny;
				if( gradient < 32 ) {
					nx = 0;
					ny = 1;
				} else if( gradient < 96) {
					nx = 1;
					ny = -1;
				} else if( gradient < 160) {
					nx = 1;
					ny = 0;
				} else if( gradient < 224) {
					nx = 1;
					ny = 1;
				} else {
					nx = 0;
					ny = 1;
				}
				if (ColorConverter.extractRGB(stre.getRGB(x + nx, y + ny))[1] > ColorConverter.extractRGB(stre.getRGB(x, y))[1] || 
						ColorConverter.extractRGB(stre.getRGB(x - nx, y - ny))[1] > ColorConverter.extractRGB(stre.getRGB(x, y))[1]) {
					img.setRGB(x, y, 0xFF000000);
				} else {
					img.setRGB(x, y, stre.getRGB(x, y));
				}
			}
		}
		return img;
	}
}
