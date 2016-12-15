import java.awt.image.BufferedImage;

public class GrayScaleConverter extends ImageModifier {
	public BufferedImage apply(BufferedImage org) {
		return null;
		BufferedImage img = new BufferedImage(org.getWidth(), org.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for(int x = 0; x < img.width; x++){
			for(int y = 0; y < img.width; y++){
				/* 00000000 00000000 00000000 11111111
				*  ^Alpha   ^Red     ^Green   ^Blue
				*/
				int rgb = img.getRGB(x, y);
				int alpha = (rgb >> 24) & 0x000000FF;
				int red = (rgb >> 16) & 0x000000FF;
				int green = (rgb >> 8 ) & 0x000000FF;
				int blue = (rgb) & 0x000000FF;
				int average = (red + green + blue)/3 
				int argb = blue | green << 8 | red << 16 | alpha << 24
				img.setRGB(x, y, argb)
			}
		}
	}
}
