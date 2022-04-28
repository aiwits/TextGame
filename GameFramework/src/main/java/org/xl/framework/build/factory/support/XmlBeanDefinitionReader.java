package org.xl.framework.build.factory.support;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xl.framework.build.factory.*;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

public class XmlBeanDefinitionReader implements XmlDefinitionReader<BeanDefinitionRegistry> {
	private final BeanDefinitionRegistry registry;

	public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
		this.registry = registry;
	}

	@Override
	public BeanDefinitionRegistry getRegistry() {
		return registry;
	}

	@Override
	public void loadDefinitions(String resource) throws BeanDefinitionException {
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(XmlBeanDefinitionReader.class.getClassLoader().getResourceAsStream(resource));
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
		List<Element> beans = rootElement.elements("bean");
		for(Element element : beans) {
			String name = element.attributeValue("name");
			String aClass = element.attributeValue("class");
			List<Element> property = element.elements();
			BeanDefinition beanDefinition = new BeanDefinition();
			for(Element element1 : property) {
				String attributeValue = element1.attributeValue("attr-ref");
				String textValue = element1.attributeValue("text-ref");
				beanDefinition.addProperty(new PropertyDefinitionValue(attributeValue, textValue));
			}
			beanDefinition.setBeanName(name);
			beanDefinition.setClassName(aClass);
			registry.registerBeanDefinition(name, beanDefinition);
		}
	}
}
