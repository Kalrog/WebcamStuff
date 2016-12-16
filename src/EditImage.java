import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EditImage {

	public static void main(String[] args) throws Exception {
		if (args.length > 1) {
			BufferedImage img, one, two,inte,grad;
			img = ImageIO.read(new File(args[0]));
			GrayScaleConverter gary = new GrayScaleConverter();

			Kernel gauss = new Kernel(new int[][] { { 2, 4, 5, 4, 2 },
													{ 4, 9,12, 9, 4 }, 
													{ 5,12,15,12, 5 }, 
													{ 4, 9,12, 9, 4 },
													{ 2, 4, 5, 4, 2 } });

			Kernel sobelv = new Kernel(new int[][] { { 3, 0, -3 }, { 10, 0, -10 }, { 3, 0, -3 } });

			Kernel sobelh = new Kernel(new int[][] { { 3, 10, 3 }, { 0, 0, 0 }, { -3, -10, -3 } });

			Average aver = new Average();

			Sobel sob = new Sobel();

			Threshold thre = new Threshold();
			
			NonMaximumSuppression noma = new NonMaximumSuppression();
			
			Hysteresis hys = new Hysteresis();

			img = gary.apply(img);
			img = gauss.apply(img);
			img = gauss.apply(img);
			one = sobelv.apply(img);
			two = sobelh.apply(img);
			// img = aver.apply(one, two);
			grad = sob.makeGradient(two, one);
			inte = sob.combine(one, two);
			
			img = noma.apply(grad,inte);
			
			img = hys.apply(img,20,8);
			
			ImageIO.write(img, "PNG", new File(args[1]));

		} else {
			System.out.println("Use of Edit Image: EditImage [Location of input File] [Location of output File]");
		}
	}

}
