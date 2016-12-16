import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.github.sarxos.webcam.Webcam;

public class HelloWorld {

	public static void main(String[] args) {

		Webcam cam = Webcam.getDefault();
		cam.open();

		Display dis = new Display(cam.getViewSize().width, cam.getViewSize().height);

		BufferedImage img, one, two;

		Graphics g;

		GrayScaleConverter gary = new GrayScaleConverter();

		Kernel gauss = new Kernel(new int[][] { { 1, 2, 1 }, { 2, 4, 2 }, { 1, 2, 1 } });

		Kernel sobelv = new Kernel(new int[][] { { 1, 0, -1 }, { 2, 0, -2 }, { 1, 0, -1 } });
		
		Kernel sobelh = new Kernel(new int[][] { { 1, 2, 1 }, { 0, 0, 0 }, { -1, -2, -1 } });
		
		Average aver = new Average();

		Threshold thre = new Threshold();
		
		while (dis != null && cam.isImageNew()) {

			img = cam.getImage();

			img = gary.apply(img);

			img = gauss.apply(img);
			
			one = sobelv.apply(img);
			
			two = sobelh.apply(img);
			
			img = aver.apply(one, two);
			
			img = thre.apply(img, 100);
			
			g = dis.getBuffer().getDrawGraphics();

			//g.drawImage(img, 0, 0, dis.getWidth(), dis.getHeight(), null);
			g.drawImage(img, 0, 0, cam.getViewSize().width, cam.getViewSize().height, null);
			
			dis.getBuffer().show();
		}
	}

}
