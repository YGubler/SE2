import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ImagePool {
	private List<Image> imageList = new ArrayList<Image>();
	private Random random = new Random();
	
	public synchronized void addImage(Image image) {
		imageList.add(image);
		notifyAll();
	}
	
	public synchronized Image selectRandom() throws InterruptedException {
		while (imageList.isEmpty()) { wait(); }
		int position = random.nextInt(imageList.size());
		return imageList.get(position);
	}
}
