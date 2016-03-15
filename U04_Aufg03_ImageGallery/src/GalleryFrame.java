import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GalleryFrame extends JFrame {
	private static final long serialVersionUID = 1923411307785002696L;
	
	public GalleryFrame(ImagePool imagePool, int nofPanels) {
		initialize(imagePool, nofPanels);
	}
	
	private void initialize(ImagePool imagePool, int nofPanels) {
		setTitle("Image Gallery");
		JPanel rootPanel = new JPanel(new GridLayout(nofPanels, 1));
		add(rootPanel);
		
		for (int i = 0; i < nofPanels; i++) {
			RotatingPanel imagePanel = new RotatingPanel(imagePool);
			rootPanel.add(imagePanel);
		}
	}
}
