import java.awt.image.BufferedImage;

public class Hysteresis {
	BufferedImage result = null;
	int upper = 0;
	int lower = 0;

	public BufferedImage apply(BufferedImage org, int upper, int lower) {
		result = new BufferedImage(org.getWidth(),org.getHeight(),BufferedImage.TYPE_INT_ARGB);
		org.copyData(result.getRaster());
		this.upper = upper;
		this.lower = lower;
		for(int x = 0;x<org.getWidth();x++){
			for(int y = 0; y < org.getHeight(); y++){
				checkPixel(x,y);
			}
		}
		return result;
	}

	private boolean checkPixel(int x, int y) {
		int value = ColorConverter.extractRGB(result.getRGB(x, y))[1];
		if (value > upper) {
			result.setRGB(x, y, 0xFFFFFFFF);
			return true;
		} else if (value < lower || x <= 0 || y <= 0 || x >= result.getWidth() || y >= result.getHeight()) {
			result.setRGB(x, y, 0xFF000000);
			return false;
		} else {
			result.setRGB(x, y, 0xFF000000);
			for (int xd = -1; xd <= 1; xd++) {
				for (int yd = -1; yd <= 1; yd++) {
					if(checkPixel(x+xd,y+yd)){
						result.setRGB(x, y, 0xFFFFFFFF);
						return true;
					}
				}
			}
			return false;
		}
	}
}
