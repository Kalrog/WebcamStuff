import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JCheckBox;

public class Display extends JFrame implements ComponentListener {

	private JPanel panel;
	private Canvas canvas;
	public JCheckBox check1 = new JCheckBox("BW");
	public JCheckBox check2 = new JCheckBox("Inv");
	public JCheckBox check3 = new JCheckBox("Gauss");
	public JCheckBox check4 = new JCheckBox("Sobel");
	public JCheckBox check5 = new JCheckBox("BW");
	
	/**
	 * Creates a new display with given width and height
	 * @param width the width
	 * @param height the height
	 */
	public Display(int width, int height) {
		
		super();
		panel = new JPanel();
		canvas = new Canvas();
		
	    panel.add(check1);
	    panel.add(check2);
	    panel.add(check3);
	    panel.add(check4);
	    panel.add(check5);
	    
		this.setSize(width, height);
		panel.setSize(this.getWidth(), this.getHeight());
		canvas.setSize(this.getWidth(), this.getHeight());
		
		canvas.setBackground(Color.BLACK);
		canvas.setIgnoreRepaint(true);
		
		//this.addComponentListener(this);
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

	@Override
	public void componentHidden(ComponentEvent arg0) {
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		panel.setSize(this.getWidth(), this.getHeight());
		canvas.setSize(this.getWidth(), this.getHeight());
		panel.validate();
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		
	}
	

}