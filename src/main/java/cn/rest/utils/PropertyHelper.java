package cn.rest.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyHelper {

	public static final String UPLOAD_PROPERTIES = "upload_path";
	private static final String PATH = "upload.properties";

	private static Properties loadProperty(String name) {

		InputStream inStream = PropertyHelper.class.getClassLoader()
				.getResourceAsStream(PATH);
		Properties p = new Properties();
		try {
			p.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}

	public static String getProperties(String name) {
		Properties p = loadProperty(PATH);
		return p.getProperty(name);
	}

	public static String getProperties(String fileName, String name) {
		Properties p = loadProperty(fileName);
		return p.getProperty(name);
	}
}
