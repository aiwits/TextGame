package org.xl.framework.build.factory.support;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xl.framework.build.factory.*;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;

public class XmlAttributeDefinitionReader implements XmlDefinitionReader<AttributeDefinitionRegistry> {
	private final AttributeDefinitionRegistry registry;
	private static final String ID = "id";

	public XmlAttributeDefinitionReader() {
		this.registry = new AttributeDefinitionRegistry();
	}

	@Override
	public AttributeDefinitionRegistry getRegistry() {
		return this.registry;
	}

	@Override
	public void loadDefinitions(String resource) throws BeanDefinitionException {
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(XmlAttributeDefinitionReader.class.getClassLoader().getResourceAsStream(resource));
			load(document);
		} catch(DocumentException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void loadDefinitions(File file) throws BeanDefinitionException {
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(file);
			load(document);
		} catch(MalformedURLException | DocumentException e) {
			e.printStackTrace();
		}
	}

	public void load(Document document) {
		Element rootElement = document.getRootElement();
		Element element = rootElement.element("attributes");
		List<Element> elements = element.elements();
		for(Element e : elements) {
			AttributeDefinitionValueMap valueMap = new AttributeDefinitionValueMap();
			List<Attribute> attributes = e.attributes();
			attributes.forEach(attribute -> {
				valueMap.put(attribute.getName(), attribute.getData());
			});
			registry.putValueMap((String) valueMap.get(ID), valueMap);
		}
	}
}
