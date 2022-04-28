package org.xl.framework.build.factory.support;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xl.framework.build.factory.BeanDefinitionException;
import org.xl.framework.build.factory.TextDefinitionValue;
import org.xl.framework.build.factory.TextDefinitionRegistry;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

public class XmlTextDefinitionReader implements XmlDefinitionReader<TextDefinitionRegistry> {
	private final TextDefinitionRegistry values;

	public XmlTextDefinitionReader() {
		this.values = new TextDefinitionRegistry();
	}

	@Override
	public TextDefinitionRegistry getRegistry() {
		return this.values;
	}

	@Override
	public void loadDefinitions(String resource) {
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(XmlTextDefinitionReader.class.getClassLoader().getResourceAsStream(resource));
			load(document);
		} catch(DocumentException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void loadDefinitions(File file) throws BeanDefinitionException {
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(file);
			load(document);
		} catch(DocumentException | MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public void load(Document document) {
		Element rootElement = document.getRootElement();
		Element texts = rootElement.element("texts");
		List<Element> elements = texts.elements("text-description");
		for(Element element : elements) {
			String id = element.attributeValue("id");
			String name = element.attributeValue("name");
			String type = element.attributeValue("type");
			String text = element.getTextTrim();
			TextDefinitionValue value = new TextDefinitionValue(id, name, type, text);
			if(!values.containsKey(id)) {
				values.putValue(id, value);
			} else {
				throw new IllegalArgumentException("error...");
			}
		}
	}
}
