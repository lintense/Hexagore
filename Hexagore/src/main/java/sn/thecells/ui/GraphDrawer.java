package sn.thecells.ui;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;

import sn.thecells.generic.Drawable;
import sn.thecells.support.Point2D;

public class GraphDrawer {

//	private static final Point2D PIECE_SIZE = new Point2D(210,226);
//    private static final int COLOR_TRANSPARENT = -8355840;
    
	private static final HashMap<String,GraphDrawer> drawers = new HashMap();
	public static GraphDrawer getDrawer(Drawable dr) { // FIXME - no need for the index... cache the images...
		
		String propFileName = dr.getPropFile();
		GraphDrawer gd = drawers.get(propFileName);
		if (gd == null) {
			URL imageURL = GraphDrawer.class.getClassLoader().getResource("\\img\\" + propFileName + ".png");
			gd = new GraphDrawer(imageURL);
			drawers.put(propFileName, gd);
		}
		return gd;
	}
	
//	private final int index;
	private final GraphImage image;
	private final Point2D size;
	private final int imagesPerRow;
	
	private GraphDrawer(URL imageURL) {
		this.image = new GraphImage(imageURL);
		this.size = extractSize(imageURL);
		this.imagesPerRow = image.image.getWidth() / size.x;
	}
	private static Point2D extractSize(URL imageURL) {
		String s = imageURL.getFile();
		String sub = s.substring(s.lastIndexOf("_") + 1, s.lastIndexOf("."));
		String[] pp = sub.split("x");
		return new Point2D(Integer.parseInt(pp[0]), Integer.parseInt(pp[1]));
	}
	public GraphImage getImage() {
		return image;
	}

//	// Copy an image onto another
//	public void copySrcIntoDstAt(final BufferedImage src, final BufferedImage dst, final int dx, final int dy) {
//	    int[] srcbuf = ((DataBufferInt) src.getRaster().getDataBuffer()).getData();
//	    int[] dstbuf = ((DataBufferInt) dst.getRaster().getDataBuffer()).getData();
//	    int width = src.getWidth();
//	    int height = src.getHeight();
//	    int dstoffs = dx + dy * dst.getWidth();
//	    int srcoffs = 0;
//	    for (int y = 0 ; y < height ; y++ , dstoffs+= dst.getWidth(), srcoffs += width ) {
//	        System.arraycopy(srcbuf, srcoffs , dstbuf, dstoffs, width);
//	    }
//	}

	// https://onlinepngtools.com/create-transparent-png
	// https://github.com/stalk-calvin/webpimagereader/blob/master/src/test/java/org/imageio/WebPReaderTest.java
	// Maybe it is the file that is not valid...
	public void drawPiece(final BufferedImage dst, int index, Point2D coord, Point2D newSize) { // WEBP image Not working... // TODO - To be removed
		Point2D p = coord.minus(newSize.dividedBy(2));
		addImage(dst, resizeImage(index, newSize), 1f, p);
	}
	private void addImage(BufferedImage buff1, BufferedImage buff2, float opaque, Point2D pos) { // TODO - To be removed
		Graphics2D g2d = buff1.createGraphics();
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opaque));
		g2d.drawImage(buff2, pos.x, pos.y, null);
		g2d.dispose();
	}
	public BufferedImage resizeImage(int index, Point2D newSize) {
		
		int x = index % imagesPerRow * size.x;
		int y = index / imagesPerRow * size.y;
		BufferedImage bufImage = image.getImage();
        BufferedImage dimg = new BufferedImage(newSize.x, newSize.y, bufImage.getType());  
        Graphics2D g = dimg.createGraphics();  
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
        RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
        g.drawImage(bufImage, 0, 0, newSize.x, newSize.y, x, y, x + size.x, y + size.y, null); 
        g.dispose();
        return dimg;
	}
}
