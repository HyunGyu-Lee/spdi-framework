package com.leeframework.context;

import com.leeframework.beans.BeanFactoryMetaData;

public class XmlApplicationContext extends ApplicationContext {
	
	private String[] xmls;
	
	public XmlApplicationContext(String...xmls) {
		this.xmls = xmls;
		super.initailize();
	}
	
	@Override
	public BeanFactoryMetaData createBeanFactoryMetaDataStrategy() {
		BeanFactoryMetaData metaData = new BeanFactoryMetaData();
		System.out.println("XML파일을 파싱 후 metaData 셋팅한 후 반환");
		for(String xml : xmls)
		{
			System.out.println(xml);
		}
		return metaData;
	}

	
	
}
