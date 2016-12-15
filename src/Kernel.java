import java.awt.image.BufferedImage;

public class Kernel extends ImageModifier {
	int[][] values;
	public Kernel(int[][] values) {
		this.values = values;
	}
	
	@Override
	public BufferedImage apply(BufferedImage org) {
		BufferedImage img = new BufferedImage(org.getWidth(), org.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for(int x = 1; x < img.getWidth()-1; x++){
			for(int y = 1; y < img.getHeight()-1; y++){
				int redtotal = 0;
				int greentotal = 0;
				int bluetotal = 0;
				int alphacenter = org.getRGB(x, y) & 0xFF000000;
				for(int xd = -1; xd <= 1; xd++){
					for(int yd = -1; yd <= 1; yd++){
						int rgb = org.getRGB(x, y);
						int red = (rgb >> 16) & 0x000000FF;
						int green = (rgb >> 8 ) & 0x000000FF;
						int blue = (rgb) & 0x000000FF;
						redtotal+=red*values[yd+1][xd+1];
						greentotal+=green*values[yd+1][xd+1];
						bluetotal+=blue*values[yd+1][xd+1];
					}
				}
				int argb = redtotal | greentotal << 8 | bluetotal << 16 | alphacenter << 24;
				img.setRGB(x, y, argb);
			}
		}
		return img;
	}
}
