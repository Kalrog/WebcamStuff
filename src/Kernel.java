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
				int total[] = new int[4];
				/* 0 - Alpha
				 * 1 - Red
				 * 2 - Green
				 * 3 - Blue
				*/
				total[1] = 0;
				total[2] = 0;
				total[3] = 0;
				total[0] = org.getRGB(x, y) & 0xFF000000;
				for(int xd = -1; xd <= 1; xd++){
					for(int yd = -1; yd <= 1; yd++){
						int rgb[] = ColorConverter.extractRGB(org.getRGB(x+xd, y+yd));
						total[1]+=rgb[1]*values[yd+1][xd+1];
						total[2]+=rgb[2]*values[yd+1][xd+1];
						total[3]+=rgb[3]*values[yd+1][xd+1];
					}
				}
				int argb = ColorConverter.makeRGB(total);
				img.setRGB(x, y, argb);
			}
		}
		return img;
	}
}
