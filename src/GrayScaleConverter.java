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
				int color[] = ColorConverter.extractRGB(org.getRGB(x, y));
				int average = (color[1] + color[2] + color[3])/3;
				
				img.setRGB(x, y, ColorConverter.makeRGB(new int[] {color[0], average, average, average}));
			}
		}
		return img;
	}
}
