import java.awt.image.BufferedImage;

public class Kernel extends ImageModifier {
	int[][] values;
	int possum;
	int negsum;
	int threshold;

	public Kernel(int[][] values) {
		this.values = values;
		this.possum = 0;
		this.negsum = 0;
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
		for (int x = values[0].length/2; x < img.getWidth() - values[0].length/2; x++) {
			for (int y = values.length/2; y < img.getHeight() - values.length/2; y++) {
				int total[] = new int[4];
				total[0] = ColorConverter.extractRGB(org.getRGB(x, y))[0];
				for (int xd = -1*values[0].length/2; xd <= values[0].length/2; xd++) {
					for (int yd = -1*values.length/2; yd <= values.length/2; yd++) {
						int rgb[] = ColorConverter.extractRGB(org.getRGB(x + xd, y + yd));
						total[1] += rgb[1] * values[yd + values.length/2][xd + values[0].length/2];
						total[2] += rgb[2] * values[yd + values.length/2][xd + values[0].length/2];
						total[3] += rgb[3] * values[yd + values.length/2][xd + values[0].length/2];
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
