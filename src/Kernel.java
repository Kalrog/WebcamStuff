import java.awt.image.BufferedImage;

public class Kernel extends ImageModifier {
	int[][] values;
	int possum;
	int negsum;
	int threshold;

	public Kernel(int[][] values) {
		this.values = values;
		this.possum = 0;
		for (int x = 0; x < values[0].length; x++) {
			for (int y = 0; y < values.length; y++) {
				if( values[y][x] > 0){
				possum += values[y][x];
				}else{
				negsum += values[y][x];
				}
			}
		}
	}

	@Override
	public BufferedImage apply(BufferedImage org) {
		BufferedImage img = new BufferedImage(org.getWidth(), org.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int x = 1; x < img.getWidth() - 1; x++) {
			for (int y = 1; y < img.getHeight() - 1; y++) {
				int total[] = new int[4];
				total[0] = ColorConverter.extractRGB(org.getRGB(x, y))[0];
				for (int xd = -1; xd <= 1; xd++) {
					for (int yd = -1; yd <= 1; yd++) {
						int rgb[] = ColorConverter.extractRGB(org.getRGB(x + xd, y + yd));
						total[1] += rgb[1] * values[yd + 1][xd + 1];
						total[2] += rgb[2] * values[yd + 1][xd + 1];
						total[3] += rgb[3] * values[yd + 1][xd + 1];
					}
				}

				for (int i = 1; i <= 3; i++) {
					total[i] = (int) ColorConverter.map(total[i], 255*negsum, 255*possum, 0, 255);
				}

				int argb = ColorConverter.makeRGB(total);
				img.setRGB(x, y, argb);
			}
		}
		return img;
	}
}
