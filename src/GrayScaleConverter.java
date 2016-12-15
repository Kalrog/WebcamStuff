import java.awt.image.BufferedImage;

public class GrayScaleConverter extends ImageModifier {
	public GrayScaleConverter() {}
	
	@Override
	public BufferedImage apply(BufferedImage org) {
		BufferedImage img = new BufferedImage(org.getWidth(), org.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for(int x = 0; x < img.getWidth(); x++){
			for(int y = 0; y < img.getHeight(); y++){
				/* 00000000 00000000 00000000 11111111
				*  ^Alpha   ^Red     ^Green   ^Blue
				*/
				int rgb = org.getRGB(x, y);
				int alpha = (rgb >> 24) & 0x000000FF;
				int red = (rgb >> 16) & 0x000000FF;
				int green = (rgb >> 8 ) & 0x000000FF;
				int blue = (rgb) & 0x000000FF;
				int average = (red + green + blue)/3;
				int argb = average | average << 8 | average << 16 | alpha << 24;
				img.setRGB(x, y, argb);
			}
		}
		return img;
	}
}
