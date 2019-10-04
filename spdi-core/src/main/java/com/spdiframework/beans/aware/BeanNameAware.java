package com.spdiframework.beans.aware;

import com.spdiframework.context.Aware;

/***
 * @author dlgusrb0808@gmail.com
 */
public interface BeanNameAware extends Aware {
	public void setBeanName(String beanName);
}
