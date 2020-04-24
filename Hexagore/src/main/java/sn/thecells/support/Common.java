package sn.thecells.support;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

public class Common {

	public static URL getResourceURL(String res) {
		return Common.class.getClassLoader().getResource(res);
	}
	
	public static File urlToFile(URL url) {
		try {
			return Paths.get(url.toURI()).toFile();
		} catch (URISyntaxException e1) {
			System.out.println("Cannot find url: " + url);
			e1.printStackTrace();
		}
		return null;
	}
	
	public static InputStream urlToStream(URL url) throws FileNotFoundException {
		File f = Common.urlToFile(url);
		return new BufferedInputStream(new FileInputStream(f));
	}
	public static String extractAny(List<String> ss) {
    	int index = (int)Math.floor(Math.random() * ss.size());
    	return ss.remove(index);
    }
}
