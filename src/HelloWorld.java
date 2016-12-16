import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.github.sarxos.webcam.Webcam;

public class HelloWorld {

	static long logtime = System.currentTimeMillis();
	
	public static void main(String[] args) {

		Webcam cam = Webcam.getDefault();
		cam.open();

		Display dis = new Display(cam.getViewSize().width, cam.getViewSize().height);

		BufferedImage img, one, two, grad, stre;

		Graphics g;

		GrayScaleConverter gary = new GrayScaleConverter();

		Kernel gauss = new Kernel(new int[][] { { 1, 2, 1 }, { 2, 4, 2 }, { 1, 2, 1 } });
		
		Kernel gausss = new Kernel(new int[][] { { 1, 2, 3, 2, 1 }, { 2, 3, 4, 3, 2 }, { 3, 4, 5, 4, 3 }, { 2, 3, 4, 3, 2 }, { 1, 2, 3, 2, 1 } });

		Kernel sobelv = new Kernel(new int[][] { { 1, 0, -1 }, { 2, 0, -2 }, { 1, 0, -1 } });
		
		Kernel sobelh = new Kernel(new int[][] { { 1, 2, 1 }, { 0, 0, 0 }, { -1, -2, -1 } });
		
		Average aver = new Average();
		
		Sobel sob = new Sobel();

		Threshold thre = new Threshold();
		
		Invert inv = new Invert();
		
		NonMaximumSuppression nms = new NonMaximumSuppression();
		
		long time = System.currentTimeMillis();
		int frames = 0;
		
		while (dis != null && cam.isImageNew()) {
			
			if(System.currentTimeMillis() - time > 1000){
				time = System.currentTimeMillis();
				System.out.println("FPS: "+frames);
				frames = 0;
			}

			img = cam.getImage();
			
			log("Starting Grayscale conversion");
			img = gary.apply(img);
			log("done");
			
			img = inv.apply(img);
			
			log("Starting Gauss blur");
			//img = gausss.apply(img);
			img = gauss.apply(img);
			log("done");
			
			log("Starting Sobel Vertical");
			one = sobelv.apply(img);
			log("done");
			
			log("Starting Sobel Horizontal");
			two = sobelh.apply(img);
			log("done");
			
			log("Starting Threshold");
			//one = thre.apply(two, 10);
			log("done");
			
			log("Starting Threshold");
			//two = thre.apply(one, 10);
			log("done");
			
			//img = aver.apply(one, two);
			
			log("Starting Sobel Gradient");
			grad = sob.makeGradient(two, one);
			log("done");
			
			log("Starting Sobel Combine");
			stre = sob.combine(one, two);
			log("done");
			
			img = nms.apply(grad, stre);
			
			g = dis.getBuffer().getDrawGraphics();

			//g.drawImage(img, 0, 0, dis.getWidth(), dis.getHeight(), null);
			//g.drawImage(img, 0, 0, cam.getViewSize().width, cam.getViewSize().height, null);
			g.drawImage(img, 0, 0, null);
			
			dis.getBuffer().show();
			
			frames++;
		}
	}

	public static void log(String message){
		logtime = System.currentTimeMillis() - logtime;
		System.out.println(logtime + " : " + message);
		logtime = System.currentTimeMillis();
	}
}
