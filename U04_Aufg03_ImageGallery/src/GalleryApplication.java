import javax.swing.SwingUtilities;

public class GalleryApplication {
	private static final String imageFolderPath = "images";
	private static final int nofPanels = 4;

	public static void main(String[] args) {
		final ImagePool imagePool = new ImagePool();
		new ImageScanner(imagePool, imageFolderPath);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				GalleryFrame frame = new GalleryFrame(imagePool, nofPanels);
				frame.pack();
				frame.setVisible(true);
			}
		});
	}
}
