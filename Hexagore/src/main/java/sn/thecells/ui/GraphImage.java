package sn.thecells.ui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.imageio.spi.IIORegistry;
import javax.imageio.stream.ImageInputStream;

import net.sf.javavp8decoder.imageio.WebPImageReaderSpi;
import sn.thecells.support.Common;
import sn.thecells.support.Point2D;

public class GraphImage {

	BufferedImage image;
	boolean imageLoaded = false;
	
	static {
		IIORegistry r = javax.imageio.spi.IIORegistry.getDefaultInstance();
		WebPImageReaderSpi s = new WebPImageReaderSpi();
		r.registerServiceProvider(s);
	}

	public GraphImage(URL imageURL) {
//		File imageFile = urlToFile(imageURL);
//		Image sourceImage = null;

		try
		{
			ImageInputStream webPImageInputStream = ImageIO.createImageInputStream(Common.urlToStream(imageURL));
	        image = ImageIO.read(webPImageInputStream); // webPImageInputStream is closed here!
//	        System.out.println("Image type: " + sourceImage.getType());
			
////			sourceImage = ImageIO.read(imageURL); //imageFile);
//			GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
//			GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
//			GraphicsConfiguration graphicsConfiguration = graphicsDevice.getDefaultConfiguration();
//			
//			// If the source image has no alpha info use Transparency.OPAQUE instead
//			image = graphicsConfiguration.createCompatibleImage(sourceImage.getWidth(null), sourceImage.getHeight(null), Transparency.BITMASK);
////			System.out.println("Image type: " + image.getType());


			
//			// Copy image to buffered image
//			Graphics graphics = image.createGraphics();
//			
//			// Paint the image onto the buffered image
//			graphics.drawImage(sourceImage, 0, 0, null);
//			graphics.dispose();
		} catch (IOException e1) {
			// TODO Auto-generated catch block\
			System.out.println("Not found: " + Common.urlToFile(imageURL));
			e1.printStackTrace();
		}
	}
	public synchronized BufferedImage getImage() {
		return image;
	}
	public Point2D getSize() {
		return new Point2D(image.getWidth(), image.getHeight());
	}
//	public Dimension getSize() {
//		return new Dimension(image.getWidth(), image.getHeight());
//	}
	
//	static class CustomPaintComponent extends Component {
//		public void paint(Graphics g) {
//			// Retrieve the graphics context; this object is used to paint
//			// shapes
//			Graphics2D g2d = (Graphics2D) g;
//			int x = 0;
//			int y = 0;
//			g2d.drawImage(image, x, y, this);
//		}
//		public void update() {
//		    repaint();
//		}
//	}

}