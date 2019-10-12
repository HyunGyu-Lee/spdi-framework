package com.spdiframework.beans.factory;

/**
 * @author dlgusrb0808@gmail.com
 */
public interface BeanFactory {

	public <T> T getBean(String name);

	public <T> T getBean(String name, Class<T> type);

	public <T> T getBean(Class<T> type);

}
