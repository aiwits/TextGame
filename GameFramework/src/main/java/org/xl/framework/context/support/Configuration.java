package org.xl.framework.context.support;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Configuration {
	private static final Config c = new Config();
	private static final Map<String, String> configMap = new HashMap<>();

	public static Config parser(String resource) {
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(Configuration.class.getClassLoader().getResourceAsStream(resource));
			load(document);
		} catch(DocumentException e) {
			e.printStackTrace();
		}
		return c;
	}

	public static Config parser(File file) {
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(file);
			load(document);
		} catch(DocumentException | MalformedURLException e) {
			e.printStackTrace();
		}
		return c;
	}

	private static void load(Document document) {
		Element element = document.getRootElement();
		Element scan = element.element("scan");
		String value = null;
		if(scan != null) {
			value = scan.attributeValue("package");
			configMap.put("scan", value);
		}
		List<Element> config1 = element.elements("config");
		for(Element element1 : config1) {
			String name = element1.attributeValue("name");
			value = element1.attributeValue("value");
			configMap.put(name, value);
		}
	}

	public static class Config {
		public Map<String, String> getConfig(Class<?> aClass) {
			if(configMap.get("scan") == null) {
				String packageName = aClass.getPackageName();
				configMap.put("scan", packageName);
			}
			return configMap;
		}
	}
}
