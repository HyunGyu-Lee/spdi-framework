package com.leeframework.context;

import com.leeframework.beans.metadata.BeanFactoryMetaData;
import com.leeframework.beans.xml.XmlConfigurationParser;

public class XmlApplicationContext extends ApplicationContext {
	
	private String[] xmls;
	
	public XmlApplicationContext(String...xmls) {
		this.xmls = xmls;
		super.initailize();
	}
	
	@Override
	public BeanFactoryMetaData createBeanFactoryMetaDataStrategy() {
		BeanFactoryMetaData metaData = new BeanFactoryMetaData();
		
		for(String xml : xmls)
		{
			try
			{
				XmlConfigurationParser.parseAndApply(metaData, "./resource/"+xml);
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}		
		return metaData;
	}

	
	
}
