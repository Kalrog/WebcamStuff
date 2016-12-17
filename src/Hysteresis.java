import java.awt.image.BufferedImage;

public class Hysteresis {
	int[][] result = null;
	int upper = 0;
	int lower = 0;

	public int[][] apply(int[][] org, int upper, int lower) {
		result = org.clone();
		this.upper = upper;
		this.lower = lower;
		for(int x = 0;x<org.length;x++){
			for(int y = 0; y < org[x].length; y++){
				checkPixel(x,y);
			}
		}
		return result;
	}

	private boolean checkPixel(int x, int y) {
		int value = result[x][y];
		if (value > upper) {
			result[x][y] = 1000000000;
			return true;
		} else if (value < lower || x <= 0 || y <= 0 || x >= result.length || y >= result[x].length) {
			result[x][y] = 0;
			return false;
		} else {
			result[x][y] = 0;
			for (int xd = -1; xd <= 1; xd++) {
				for (int yd = -1; yd <= 1; yd++) {
					if(checkPixel(x+xd,y+yd)){
						result[x][y] = 1000000000;
						return true;
					}
				}
			}
			return false;
		}
	}
}
