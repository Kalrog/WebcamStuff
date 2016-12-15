import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;

public class HelloWorld {
	public static void main (String[] args){
		Webcam webcam = Webcam.getDefault();
		webcam.open();
		try {
			ImageIO.write(webcam.getImage(), "PNG", new File("HelloWorld.png"));
		} catch (IOException e) {
			System.out.println("Failed to save Image printing Stack Trace");
			e.printStackTrace();
		}
	}
	
}
