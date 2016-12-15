import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.github.sarxos.webcam.Webcam;

public class HelloWorld {
	
	public static void main (String[] args){
		
		Webcam cam = Webcam.getDefault();
		cam.open();
		
		Display dis = new Display(cam.getViewSize().width,cam.getViewSize().height);
		
		BufferedImage img;
		
		Graphics g;
		
		GrayScaleConverter gary = new GrayScaleConverter();
		
		while(dis != null && cam.isImageNew()){
			
			img = cam.getImage();
			
			img = gary.apply();
			
			g = dis.getBuffer().getDrawGraphics();
			
			g.drawImage(img, 0, 0, null);
			
			dis.getBuffer().show();
		}
	}
	
	
}
