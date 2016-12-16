import java.awt.image.BufferedImage;

public class Sobel {
	private Kernel H;
	private Kernel V;
	/**
	 * Create a new Sobel Operator with a Horizontal and a Vertical Kernel
	 * @param H Horizontal Kernel
	 * @param V Vertical Kernel
	 */
	public Sobel(Kernel H,Kernel V){
		this.H = H;
		this.V = V;
	}
	public int[][][] apply(BufferedImage org){
		int[][] horizontal = ImgToArray.convert(H.apply(org))[1];
		int[][] vertical = ImgToArray.convert(V.apply(org))[1];
		int[][][] result = new int[2][org.getWidth()][org.getHeight()];
		for (int x = 1; x + 1 < horizontal.length;x++){
			for (int y = 1; y + 1 < horizontal[x].length;y++){
				result[0][x][y] = (int) Math.sqrt(Math.pow(horizontal[x][y],2)+Math.pow(horizontal[x][y],2));
				result[1][x][y] = (int) ColorConverter.map(Math.atan2(vertical[x][y], horizontal[x][y]), -Math.PI/2, Math.PI/2, -90, 90);
			}
		}
		return result;
	}
	
	
//   Old Version kept just in case
//	/**
//	 * combines the horizontal and vertical components of a
//	 * Sobel Image Operation
//	 * @param one first component
//	 * @param two second component
//	 * @return overall edge gradient
//	 */
//	public BufferedImage combine(BufferedImage one, BufferedImage two){
//		BufferedImage img = new BufferedImage(one.getWidth(), one.getHeight(), BufferedImage.TYPE_INT_ARGB);
//		for (int x = 1; x < one.getWidth() - 1; x++) {
//			for (int y = 1; y < one.getHeight() - 1; y++) {
//					int total[] = new int[4];
//				
//					int colorone[]=ColorConverter.extractRGB(one.getRGB(x, y));
//					int colortwo[]=ColorConverter.extractRGB(two.getRGB(x, y));
//					for (int i = 1; i <= 3; i++) {
//						colorone[i] = (int) ColorConverter.map(colorone[i], 0, 255, -127, 127);
//						colortwo[i] = (int) ColorConverter.map(colortwo[i], 0, 255, -127, 127);
//						total[i] = (int) Math.sqrt(Math.pow(colorone[i],2)+Math.pow(colortwo[i],2));
//						total[i] = (int) ColorConverter.map(total[i],0,55,0,255);
//						if(total[i]>255)
//							total[i] = 255;
//					}
//					total[0] = 255;
//					img.setRGB(x, y, ColorConverter.makeRGB(total));
//			}
//		}
//		return img;
//	}
//	
//	/**
//	 * Uses the two components of a Sobel Image Operation
//	 * to calculate the angles of the edges
//	 * @param Gx Sobel X Component
//	 * @param Gy Sobel Y Component
//	 * @return Gradient Image
//	 */
//	public BufferedImage makeGradient(BufferedImage Gx,BufferedImage Gy){
//		BufferedImage img = new BufferedImage(Gx.getWidth(), Gx.getHeight(), BufferedImage.TYPE_INT_ARGB);
//		for (int x = 1; x < Gx.getWidth() - 1; x++) {
//			for (int y = 1; y < Gx.getHeight() - 1; y++) {
//					int total[] = new int[4];
//					int colorGx[]=ColorConverter.extractRGB(Gx.getRGB(x, y));
//					int colorGy[]=ColorConverter.extractRGB(Gy.getRGB(x, y));
//					for (int i = 1; i <= 3; i++) {
//						colorGx[i] = (int) ColorConverter.map(colorGx[i]
//															, 0, 255, -127.5, 127.5);
//						colorGy[i] = (int) (ColorConverter.map(colorGy[i], 0, 255, -127.5, 127.5) 
//								* Math.copySign(1,colorGx[i]));
//						colorGx[i] = Math.abs(colorGx[i]);
//						total[i] = (int) ColorConverter.map(
//								Math.atan2(colorGy[i],colorGx[i])
//								,-Math.PI/2,Math.PI/2
//								,0,255);
//					}
//					total[0] = 255;
//					img.setRGB(x, y, ColorConverter.makeRGB(total));
//			}
//		}
//		return img;
//	}
}
