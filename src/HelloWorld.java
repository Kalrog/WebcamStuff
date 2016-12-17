import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.github.sarxos.webcam.Webcam;

public class HelloWorld {

	static long logtime = System.currentTimeMillis();

	public static void main(String[] args) {

		Webcam cam = Webcam.getDefault();
		cam.open();

		Display dis = new Display(cam.getViewSize().width, cam.getViewSize().height);

		BufferedImage img;

		int[][][] sobeloutput;

		int[][] imgarray;

		Graphics g;

		GrayScaleConverter gary = new GrayScaleConverter();

		Kernel gauss = new Kernel(new int[][] { { 1, 2, 1 }, { 2, 4, 2 }, { 1, 2, 1 } });

		Kernel gausss = new Kernel(new int[][] { { 2, 4, 5, 4, 2 }, { 4, 9, 12, 9, 4 }, { 5, 12, 15, 12, 5 },
				{ 4, 9, 12, 9, 4 }, { 2, 4, 5, 4, 2 } });

		Kernel sobelv = new Kernel(new int[][] { { 3, 0, -3 }, { 10, 0, -10 }, { 3, 0, -3 } });

		Kernel sobelh = new Kernel(new int[][] { { 3, 10, 3 }, { 0, 0, 0 }, { -3, -10, -3 } });

		Average aver = new Average();

		Sobel sob = new Sobel(sobelh, sobelv);

		Threshold thre = new Threshold();

		Invert inv = new Invert();

		Mirror mir = new Mirror();

		NonMaximumSuppression nms = new NonMaximumSuppression();

		Hysteresis hyst = new Hysteresis();

		long time = System.currentTimeMillis();
		int frames = 0;

		while (dis != null && cam.isImageNew()) {

			if (System.currentTimeMillis() - time > 1000) {
				time = System.currentTimeMillis();
				System.out.println("FPS: " + frames);
				frames = 0;
			}

			img = cam.getImage();

			if (dis.check1.isSelected() == true) {
				log("Starting Grayscale conversion");
				img = gary.apply(img);
				log("done");
			}

			if (dis.check2.isSelected() == true) {
				img = inv.apply(img);
			}

			if (dis.check3.isSelected() == true) {
				img = mir.apply(img);
			}

			if (dis.check4.isSelected() == true) {
				log("Starting Gauss blur");
				for (int i = 1; i <= dis.blur.getValue(); i++) {
					img = gauss.apply(img);
				}

				log("done");
			}

			if (dis.check5.isSelected() == true) {
				sobeloutput = sob.apply(img);

				// img = stre;

				imgarray = nms.apply(sobeloutput);

				//imgarray = sobeloutput[0];
				
				if (dis.check6.isSelected() == true) {
					imgarray = hyst.apply(imgarray, dis.min.getValue(), dis.max.getValue());
				}
				int [][][] finalimg = new int[4][imgarray.length][imgarray[0].length];
				for(int x = 0; x < finalimg[0].length;x++){
					for(int y = 0; y< finalimg[0][0].length; y++){
						finalimg[0][x][y]= 255;
						finalimg[1][x][y]= (int) ColorConverter.map(imgarray[x][y],0,2500,0,255);
						finalimg[2][x][y]= (int) ColorConverter.map(imgarray[x][y],0,2500,0,255);
						finalimg[3][x][y]= (int) ColorConverter.map(imgarray[x][y],0,2500,0,255);
					}
				}
				img = ArrayConverter.arraytoimg(finalimg);
			}

			log("Starting Threshold");
			// one = thre.apply(two, 10);
			log("done");

			log("Starting Threshold");
			// two = thre.apply(one, 10);
			log("done");

			// img = aver.apply(one, two);

			g = dis.getBuffer().getDrawGraphics();

			if (dis.check7.isSelected() == true) {
				g.drawImage(img, 0, 0, dis.getWidth(), dis.getHeight(), null);
				dis.addComponentListener(dis);
			} else {
				g.drawImage(img, 0, 0, null);
			}

			dis.getBuffer().show();

			frames++;
		}
	}

	public static void log(String message) {
		// logtime = System.currentTimeMillis() - logtime;
		// System.out.println(logtime + " : " + message);
		// logtime = System.currentTimeMillis();
	}
}
