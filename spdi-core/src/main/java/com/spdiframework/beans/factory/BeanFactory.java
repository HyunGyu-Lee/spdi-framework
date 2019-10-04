package com.spdiframework.beans.factory;

import com.spdiframework.beans.metadata.BeanFactoryMetaData;

/***
 * @author dlgusrb0808@gmail.com
 */
public class BeanFactory extends AbstractBeanFactory {
	BeanFactoryMetaData beanFactoryMetaData;
	
	public BeanFactory(BeanFactoryMetaData beanFactoryMetadata) {
		super(beanFactoryMetadata);
	}

}
