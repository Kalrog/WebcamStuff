import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.github.sarxos.webcam.Webcam;

public class HelloWorld {

	public static void main(String[] args) {

		Webcam cam = Webcam.getDefault();
		cam.open();

		Display dis = new Display(cam.getViewSize().width, cam.getViewSize().height);

		BufferedImage img;

		Graphics g;

		GrayScaleConverter gary = new GrayScaleConverter();

		Kernel gauss = new Kernel(new int[][] { { 1, 2, 2 }, { 2, 4, 2 }, { 1, 2, 1 } });

		Kernel sobel = new Kernel(new int[][] { { 1, 0, -1 }, { 2, 0, -2 }, { 1, 0, -1 } });

		while (dis != null && cam.isImageNew()) {

			img = cam.getImage();

			img = gary.apply(img);

			img = gauss.apply(img);
			
			img = sobel.apply(img);

			g = dis.getBuffer().getDrawGraphics();

			g.drawImage(img, 0, 0, null);

			dis.getBuffer().show();
		}
	}

}
