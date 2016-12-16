import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EditImage {

	public static void main(String[] args) throws Exception {
		if (args.length > 1) {
			BufferedImage img, one, two;
			img = ImageIO.read(new File(args[0]));
			GrayScaleConverter gary = new GrayScaleConverter();

			Kernel gauss = new Kernel(new int[][] { { 1, 2, 1 }, { 2, 4, 2 }, { 1, 2, 1 } });

			Kernel sobelv = new Kernel(new int[][] { { 3, 0, -3 }, { 10, 0, -10 }, { 3, 0, -3 } });

			Kernel sobelh = new Kernel(new int[][] { { 3, 10, 3 }, { 0, 0, 0 }, { -3, -10, -3 } });

			Average aver = new Average();

			Sobel sob = new Sobel();

			Threshold thre = new Threshold();

			img = gary.apply(img);
			img = gauss.apply(img);
			img = gauss.apply(img);
			one = sobelv.apply(img);
			two = sobelh.apply(img);
			two = thre.apply(two, 10);
			one = thre.apply(one, 10);
			// img = aver.apply(one, two);
			img = sob.makeGradient(two, one);
			// img = sob.combine(one, two);
			
			ImageIO.write(img, "PNG", new File(args[1]));

		} else {
			System.out.println("Use of Edit Image: EditImage [Location of input File] [Location of output File]");
		}
	}

}
