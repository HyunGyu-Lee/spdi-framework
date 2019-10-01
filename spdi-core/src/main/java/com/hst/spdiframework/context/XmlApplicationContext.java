package com.hst.spdiframework.context;

import com.hst.spdiframework.beans.metadata.BeanFactoryMetaData;
import com.hst.spdiframework.beans.parser.xml.XmlConfigurationParser;

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
