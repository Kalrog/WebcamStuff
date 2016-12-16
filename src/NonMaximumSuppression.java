import java.awt.image.BufferedImage;


public class NonMaximumSuppression {
	public int apply(int org[][][]){
		int[][] img;
		for (int x = 1; x < org[0][0].length - 1; x++) {
			for (int y = 1; y < org[0].length - 1; y++) {
				int nx, ny;
				if( org[1][x][y] < -45 ) {
					nx = 1;
					ny = 0;
				} else if( org[1][x][y] < 0) {
					nx = -1;
					ny = 1;
				} else if( org[1][x][y] < 45) {
					nx = 0;
					ny = 1;
				} else if( org[1][x][y] < 90) {
					nx = 1;
					ny = 1;
				} else {
					nx = 1;
					ny = 0;
				}
				if (org[0][x + nx][y + ny] > org[0][x][y] || 
						org[0][x - nx][y - ny] > org[0][x][y]) {
						img[x][y] = 0;
				} else {
						img[x][y] = org[0][x][y];
				}
			}
		}
		return img;
	}
}
