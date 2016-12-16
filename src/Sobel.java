import java.awt.image.BufferedImage;

public class Sobel {
	public BufferedImage combine(BufferedImage one, BufferedImage two){
		BufferedImage img = new BufferedImage(one.getWidth(), one.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int x = 1; x < one.getWidth() - 1; x++) {
			for (int y = 1; y < one.getHeight() - 1; y++) {
					int total[] = new int[4];
				
					int colorone[]=ColorConverter.extractRGB(one.getRGB(x, y));
					int colortwo[]=ColorConverter.extractRGB(two.getRGB(x, y));
					for (int i = 0; i <= 3; i++) {
						total[i] = (int) Math.sqrt(Math.pow(colorone[i],2)+Math.pow(colortwo[i],2));
						total[i] = ColorConverter.map(total[i],0,360,0,255);
					}
					img.setRGB(x, y, ColorConverter.makeRGB(total));
			}
		}
		return img;
	}
}
