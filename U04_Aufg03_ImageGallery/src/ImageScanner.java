import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

public class ImageScanner {
	private final ImagePool imagePool;
	private String folderPath;
	
	public ImageScanner(ImagePool imagePool, String folderPath) {
		this.imagePool = imagePool;
		this.folderPath = folderPath;
		startScanner();
	}
	
	private void startScanner() {
		new Thread() {
			@Override
			public void run() {
				scanForImages();
			}
		}.start();
	}
	
	private void scanForImages() {
		File folder = new File(folderPath);
		for (File file : folder.listFiles()) {
			Image image = tryOpenImage(file);
			if (image != null) {
				imagePool.addImage(image);
			}
		}
	}
	
	// null if no readable image
	private Image tryOpenImage(File file) {
		ImageIcon image = new ImageIcon(file.getPath());
		return image.getImage();
	}
}
