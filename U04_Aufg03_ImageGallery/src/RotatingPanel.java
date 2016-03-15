import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class RotatingPanel extends JPanel {
	private static final long serialVersionUID = 3112403598943072486L;
	private static final Dimension size = new Dimension(200, 200);
	private static final int SleepTimeMillis = 1000;
	private final ImagePool imagePool;
	private Image currentImage;

	public RotatingPanel(ImagePool imagePool) {
		this.imagePool = imagePool;
		setBackground(Color.BLACK);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		startRotation();
	}
	
	private void startRotation() {
		new Thread() {
			@Override
			public void run() {
				while (true) {
          try {
	          final Image image = imagePool.selectRandom();
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								setImage(image);							
							}
						});
	          sleep(SleepTimeMillis);
          } catch (InterruptedException e) {
	          e.printStackTrace();
          }
				}
			}
		}.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(currentImage, 0, 0, null);
	}
	
	private void setImage(Image image) {
		this.currentImage = image;
		repaint();
	}
}