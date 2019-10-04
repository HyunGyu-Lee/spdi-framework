package com.spdiframework.beans.parser.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.spdiframework.beans.metadata.BeanEntry;
import com.spdiframework.beans.metadata.BeanFactoryMetaData;
import com.spdiframework.beans.metadata.BeanProperty;
import com.spdiframework.beans.metadata.BeanReference;
import com.spdiframework.beans.metadata.Scope;

/***
 * @author dlgusrb0808@gmail.com
 */
public class XmlConfigurationParser {

	public static Document getDocument(File file) {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = null;
		Document document = null;

		// TODO / [개선] 명확한 예외처리 필요
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			try {
				document = documentBuilder.parse(file);
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return document;
	}

	private static void parseDocument(BeanFactoryMetaData dataTarget, Document document) throws ClassNotFoundException {
		Element root = document.getDocumentElement();
		root.normalize();
		NodeList beanNodes = root.getElementsByTagName(XmlConfigurationNamespace.BEAN);

		for (int idx = 0; idx < beanNodes.getLength(); idx++) {
			Element beanNode = (Element) beanNodes.item(idx);
			String beanName = beanNode.getAttribute(XmlConfigurationNamespace.NAME);
			Class<?> beanClass = Class.forName(beanNode.getAttribute(XmlConfigurationNamespace.CLASS));
			String scope = beanNode.getAttribute(XmlConfigurationNamespace.SCOPE).toUpperCase();
			String initMethod = beanNode.getAttribute(XmlConfigurationNamespace.INIT_METHOD);
			String destroyMethod = beanNode.getAttribute(XmlConfigurationNamespace.DESTROY_METHOD);

			BeanEntry entry = new BeanEntry();

			if (scope.length() == 0) {
				entry.setScope(Scope.SINGLETON);
			} else {
				entry.setScope(Scope.valueOf(scope));
			}

			NodeList propertiesNodes = beanNode.getElementsByTagName(XmlConfigurationNamespace.PROPERTY);

			for (int jdx = 0; jdx < propertiesNodes.getLength(); jdx++) {
				Element propertyNode = (Element) propertiesNodes.item(jdx);
				String propertyName = propertyNode.getAttribute(XmlConfigurationNamespace.NAME);
				String propertyValue = propertyNode.getAttribute(XmlConfigurationNamespace.VALUE);
				String refName = propertyNode.getAttribute(XmlConfigurationNamespace.REF);
				entry.setBeanName(beanName);
				entry.setBeanType(beanClass);
				entry.addProperty(new BeanProperty(propertyName, propertyValue, refName));
				entry.setInitMethod(initMethod.length() == 0 ? null : initMethod);
				entry.setDestroyMethod(destroyMethod.length() == 0 ? null : destroyMethod);
				if (refName.length() != 0) {
					dataTarget.addReference(beanName, new BeanReference(refName, propertyName));
				}
			}
			dataTarget.addEntry(beanName, entry);
		}

	}

	public static void parseAndApply(BeanFactoryMetaData dataTarget, String fileName) throws ClassNotFoundException {
		parseDocument(dataTarget, getDocument(new File(fileName)));
	}

}
