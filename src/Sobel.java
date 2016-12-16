import java.awt.image.BufferedImage;

public class Sobel {
	/**
	 * combines the horizontal and vertical components of a
	 * Sobel Image Operation
	 * @param one first component
	 * @param two second component
	 * @return overall edge gradient
	 */
	public BufferedImage combine(BufferedImage one, BufferedImage two){
		BufferedImage img = new BufferedImage(one.getWidth(), one.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int x = 1; x < one.getWidth() - 1; x++) {
			for (int y = 1; y < one.getHeight() - 1; y++) {
					int total[] = new int[4];
				
					int colorone[]=ColorConverter.extractRGB(one.getRGB(x, y));
					int colortwo[]=ColorConverter.extractRGB(two.getRGB(x, y));
					for (int i = 1; i <= 3; i++) {
						total[i] = (int) Math.sqrt(Math.pow(colorone[i],2)+Math.pow(colortwo[i],2));
						total[i] = (int) ColorConverter.map(total[i],0,360,0,255);
					}
					total[0] = 255;
					img.setRGB(x, y, ColorConverter.makeRGB(total));
			}
		}
		return img;
	}
	
	/**
	 * Uses the two components of a Sobel Image Operation
	 * to calculate the angles of the edges
	 * @param Gx Sobel X Component
	 * @param Gy Sobel Y Component
	 * @return Gradient Image
	 */
	public BufferedImage makeGradient(BufferedImage Gx,BufferedImage Gy){
		BufferedImage img = new BufferedImage(Gx.getWidth(), Gx.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int x = 1; x < Gx.getWidth() - 1; x++) {
			for (int y = 1; y < Gx.getHeight() - 1; y++) {
					int total[] = new int[4];
				
					int colorGx[]=ColorConverter.extractRGB(Gx.getRGB(x, y));
					int colorGy[]=ColorConverter.extractRGB(Gy.getRGB(x, y));
					for (int i = 1; i <= 3; i++) {
						total[i] = (int) ColorConverter.map(
								Math.atan2(colorGy[i],colorGx[i])
								,0,Math.PI
								,0,255);
					}
					total[0] = 255;
					img.setRGB(x, y, ColorConverter.makeRGB(total));
			}
		}
		return img;
	}
}
