package com.leeframework.context;

import com.leeframework.beans.metadata.BeanFactoryMetaData;
import com.leeframework.beans.parser.xml.XmlConfigurationParser;

public class XmlApplicationContext extends ApplicationContext {
	
	private String[] configurables;
	
	public XmlApplicationContext(){}
	
	public XmlApplicationContext(String...configurables) {
		this.configurables = configurables;
		load();
		refresh();
	}
	
	public String[] getConfigurables() {
		return configurables;
	}

	public void setConfigurables(String...configurables) {
		this.configurables = configurables;
	}

	@Override
	protected BeanFactoryMetaData createBeanFactoryMetaDataStrategy() {
		BeanFactoryMetaData metaData = new BeanFactoryMetaData();
		
		for(String configurable : configurables)
		{
			try
			{
				XmlConfigurationParser.parseAndApply(metaData, "./resource/"+configurable);
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}		
		return metaData;
	}
	
}
