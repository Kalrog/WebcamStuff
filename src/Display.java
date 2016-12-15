import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends JFrame {

	private JPanel panel;
	private Canvas canvas;

	/**
	 * Creates a new display with given width and height
	 * @param width the width
	 * @param height the height
	 */
	public Display(int width, int height) {
		
		super();
		panel = new JPanel();
		canvas = new Canvas();
		
		this.setSize(width, height);
		panel.setSize(width, height);
		canvas.setSize(width, height);
		
		canvas.setBackground(Color.BLACK);
		canvas.setIgnoreRepaint(true);
		
		panel.add(canvas);
		this.add(panel);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		canvas.createBufferStrategy(2);
		
		this.pack();
	}
	
	/**
	 * gets the BufferStrategy currently use by the Display
	 * commonly used for obtaining the Graphics Object for the back-Buffer
	 * 
	 * @return BufferStrategy used by Display
	 */
	public BufferStrategy getBuffer() {
		return canvas.getBufferStrategy();
	}

}
