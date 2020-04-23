package sn.thecells.ui;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.imageio.spi.IIORegistry;
import javax.imageio.stream.ImageInputStream;

import net.sf.javavp8decoder.imageio.WebPImageReaderSpi;
import sn.thecells.support.Point2D;

public class GraphDrawer {

//	private static final Point2D PIECE_SIZE = new Point2D(210,226);
//    private static final int COLOR_TRANSPARENT = -8355840;
    
	private GraphImage image;
	private final Point2D size;
//	static boolean imageLoaded = false;
	
//	final Color red = Color.red;
//	final Color white = Color.red;
	
	public GraphDrawer(URL imageURL) {
		this.image = new GraphImage(imageURL);
		this.size = extractSize(imageURL);
	}
	public GraphDrawer(URL imageURL, Point2D size) {
		
//		image = new GraphImage("I:\\DevData\\Eclipse\\TheCells\\pieces.png");
		this.image = new GraphImage(imageURL);
		this.size = size;
		
		IIORegistry r = javax.imageio.spi.IIORegistry.getDefaultInstance();
		WebPImageReaderSpi s = new WebPImageReaderSpi();
		r.registerServiceProvider(s);
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

	// Copy an image onto another
	public void copySrcIntoDstAt(final BufferedImage src, final BufferedImage dst, final int dx, final int dy) {
	    int[] srcbuf = ((DataBufferInt) src.getRaster().getDataBuffer()).getData();
	    int[] dstbuf = ((DataBufferInt) dst.getRaster().getDataBuffer()).getData();
	    int width = src.getWidth();
	    int height = src.getHeight();
	    int dstoffs = dx + dy * dst.getWidth();
	    int srcoffs = 0;
	    for (int y = 0 ; y < height ; y++ , dstoffs+= dst.getWidth(), srcoffs += width ) {
	        System.arraycopy(srcbuf, srcoffs , dstbuf, dstoffs, width);
	    }
	}
//	/**
//	 * @param pos
//	 * @return The coordinate corresponding to the Position
//	 */
//	public Point2D getCoordinate(int pos) {
//		return new Point2D(pos % HEXES_PER_ROW, (int)Math.floor(pos / HEXES_PER_ROW));
//	}
//	/**
//	 * @param drawingPoint
//	 * @return The coordinate given a drawing point
//	 */
//	public Point2D getCoordinate(Point2D drawingPoint) {
//
//		int y = (drawingPoint.y - BOARD_START_Y) / BOARD_PIECE_HEIGHT;
//		boolean odd = y % 2 == 1;
//		int x = (drawingPoint.x - BOARD_START_X - (odd ? BOARD_PIECE_ODD_WIDTH : 0)) / (PIECE_WIDTH - 1);
//		return (x < 0 || x >= HEXES_PER_ROW || y < 0 || y >=  NUMBER_OF_ROWS) ? null : new Point2D(x, y);
//	}
//	
//	/**
//	 * @param p
//	 * @return The exact point where to draw the piece into the Board
//	 */
//	public Point2D getDrawingPoint(Point2D p) {
//		boolean odd = p.y % 2 == 1;
//		return new Point2D(BOARD_START_X + (odd ? BOARD_PIECE_ODD_WIDTH : 0) + (PIECE_WIDTH - 1) * p.x, BOARD_START_Y + BOARD_PIECE_HEIGHT * p.y);
//	}

	
//	public void drawPiece(final BufferedImage dst, int index, int pos) {
//		
//	}
	
//	public void drawPiece(final BufferedImage dst, int index, Point2D coord) {
//		
//		if (coord == null)
//			return;
//		Point2D p = coord.minus(PIECE_SIZE.dividedBy(2));
//		//Point2D p = coord; //getDrawingPoint(coord);
//		
////		int range = (int)Math.floor(pos / HEXES_PER_TWO_ROWS);
////		int rem = pos % HEXES_PER_TWO_ROWS;
////		boolean odd = rem > HEXES_PER_EVEN_ROW;
////		int count = odd ? rem - HEXES_PER_EVEN_ROW - 1 : rem;
//		
////		boolean odd = p.y % 2 == 1;
////		int dx = BOARD_START_X + (odd ? BOARD_PIECE_ODD_WIDTH : 0) + (PIECE_WIDTH - 1) * p.x;
////		int dy = BOARD_START_Y + BOARD_PIECE_HEIGHT * p.y;
//		
//		BufferedImage src = image.getImage();
////		int[] srcbuf = ((DataBufferInt) src.getRaster().getDataBuffer()).getData();
////		int[] dstbuf = ((DataBufferInt) dst.getRaster().getDataBuffer()).getData();
////	    int width = src.getWidth();
////	    int height = src.getHeight();
////	    int dstoffs = dx + dy * dst.getWidth();
////	    int srcoffs = index * 10;
//////	    for (int y = 0 ; y < 10 ; y++ , dstoffs+= dst.getWidth(), srcoffs += width ) {
//////	        System.arraycopy(srcbuf, srcoffs , dstbuf, dstoffs, width);
//////	    }
////long begin = System.currentTimeMillis();
//	    int start = index * PIECE_SIZE.x;
//	    for (int x = 0; x < PIECE_SIZE.x; x++)
//            for (int y = 0; y < PIECE_SIZE.y; y++) {
//            	int rgb = src.getRGB(start + x, y);
//            	if (rgb != COLOR_TRANSPARENT)
//            		dst.setRGB(p.x + x, p.y + y, src.getRGB(start + x, y));
//            }
////long end = System.currentTimeMillis();
////System.out.println("" + (end - begin));
//                
//	}
	// https://onlinepngtools.com/create-transparent-png
	// https://github.com/stalk-calvin/webpimagereader/blob/master/src/test/java/org/imageio/WebPReaderTest.java
	// Maybe it is the file that is not valid...
	public void drawPiece(final BufferedImage dst, int index, Point2D coord, Point2D newSize) { // WEBP image Not working...
		Point2D p = coord.minus(newSize.dividedBy(2));
		
//		int x = (int)Math.ceil(Math.random() * 4);
//		URL imageURL = this.getClass().getClassLoader().getResource("\\img\\hero" + x + ".png");
//		File f;
//		try {

//			IIORegistry r = javax.imageio.spi.IIORegistry.getDefaultInstance();
//			WebPImageReaderSpi s = new WebPImageReaderSpi();
//			r.registerServiceProvider(s);
			
//			System.out.println(imageURL.toURI());
//			f = Paths.get(imageURL.toURI()).toFile();
//			BufferedImage src = ImageIO.read(f);
			
			
//			InputStream stream = new BufferedInputStream(new FileInputStream(f));
//	        ImageInputStream webPImageInputStream = ImageIO.createImageInputStream(stream);
//	        BufferedImage bufImage = ImageIO.read(webPImageInputStream);
			
	        // Resize
//	        int w = bufImage.getWidth();  
//	        int h = bufImage.getHeight();
//	        BufferedImage dimg = new BufferedImage(60, 60, bufImage.getType());  
//	        Graphics2D g = dimg.createGraphics();  
//	        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
//	        RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
//	        g.drawImage(bufImage, 0, 0, 60, 60, 0, 0, w, h, null); 
//	        g.dispose();
			
//			BufferedImage dst = image.getImage();
			
//			dst.getGraphics().drawImage(src, dx, dy, null);
			
//			File outputfile = new File("test.jpg");
//			System.out.println(outputfile.getAbsoluteFile());
//			ImageIO.write(bufImage, "jpg", outputfile);
			
			addImage(dst, resizeImage(index, newSize), 1f, p);
			
//		    int[] srcbuf = ((DataBufferByte) src.getRaster().getDataBuffer()).getData();
//		    int[] dstbuf = ((DataBufferByte) dst.getRaster().getDataBuffer()).getData();
//		    int width = src.getWidth();
//		    int height = src.getHeight();
//		    int dstoffs = dx + dy * dst.getWidth();
//		    int srcoffs = 0;
//		    for (int y = 0 ; y < height ; y++ , dstoffs+= dst.getWidth(), srcoffs += width ) {
//		        System.arraycopy(srcbuf, srcoffs , dstbuf, dstoffs, width);
//		    }
			
//		} catch (URISyntaxException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	private void addImage(BufferedImage buff1, BufferedImage buff2, float opaque, Point2D pos) {
		Graphics2D g2d = buff1.createGraphics();
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opaque));
		g2d.drawImage(buff2, pos.x, pos.y, null);
		g2d.dispose();
	}
	public BufferedImage resizeImage(int index, Point2D newSize) {
//        int w = bufImage.getWidth();  
//        int h = bufImage.getHeight();
		BufferedImage bufImage = image.getImage();
        BufferedImage dimg = new BufferedImage(newSize.x, newSize.y, bufImage.getType());  
        Graphics2D g = dimg.createGraphics();  
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
        RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
        g.drawImage(bufImage, 0, 0, newSize.x, newSize.y, index * size.x, 0, index * size.x + size.x, size.y, null); 
        g.dispose();
        return dimg;
	}
}
