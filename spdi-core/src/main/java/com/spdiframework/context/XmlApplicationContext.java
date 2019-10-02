package com.spdiframework.context;

import com.spdiframework.beans.metadata.BeanFactoryMetaData;
import com.spdiframework.beans.parser.xml.XmlConfigurationParser;

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
				XmlConfigurationParser.parseAndApply(metaData, "./resources/"+configurable);
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}		
		return metaData;
	}
	
}
