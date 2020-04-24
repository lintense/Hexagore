package sn.thecells.support;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class Label {

	private static final String FILE_LABEL_PROPERTIES = "\\img\\labels.properties";
	private static final Properties labels = new Properties();
	
	public static String get(String label, String... parms) {
		if (labels.isEmpty())
			loadProperties();
		
		String result = labels.getOrDefault(label, "LABEL[" + label + "]").toString();
		if (parms == null || parms.length == 0) 
			return result;
		
		return MessageFormat.format(result, Arrays.stream(parms).map(Label::basicGet).findAny().get());
	}
	private static String basicGet(String label) {
		return labels.getOrDefault(label, label).toString();
	}
	private static void loadProperties() {
		
//		Locale[] locale = Locale.getAvailableLocales();		
////		new Locale("de", "DE"); //no bundle for German -> default
//		ResourceBundle bundle = ResourceBundle.getBundle("labels", locale[0]);
		
		
		URL res = Common.getResourceURL(FILE_LABEL_PROPERTIES);
        try (InputStream input = Common.urlToStream(res)) {
            
            if (input == null) {
                System.out.println("Sorry, unable to find " + res);
                return;
            }
            labels.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}
}
