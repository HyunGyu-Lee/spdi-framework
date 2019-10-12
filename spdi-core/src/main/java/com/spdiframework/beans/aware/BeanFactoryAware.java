package com.spdiframework.beans.aware;

import com.spdiframework.beans.factory.OldAbstractBeanFactory;
import com.spdiframework.context.Aware;

/***
 * @author dlgusrb0808@gmail.com
 */
public interface BeanFactoryAware extends Aware {
	public void setBeanFactory(OldAbstractBeanFactory beanFactory);
}
