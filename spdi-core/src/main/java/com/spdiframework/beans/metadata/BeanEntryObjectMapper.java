package com.spdiframework.beans.metadata;

import com.spdiframework.utils.ReflectionUtils;

import java.util.Set;

/***
 * @author dlgusrb0808@gmail.com
 */
public class BeanEntryObjectMapper {

	public Object mapping(BeanEntry entry) {
		Object newObject = ReflectionUtils.createInstance(entry.getBeanType());

		Set<String> propertyNames = entry.getProperties().keySet();

		for (String propertyName : propertyNames) {
			BeanProperty property = entry.getProperty(propertyName);

			if (property.getValue().length() != 0 && property.getRef().length() == 0) {
				if (isNumber(property.getValue())) {
					ReflectionUtils.setField(newObject, property.getName(), Integer.parseInt(property.getValue()));
				} else {
					ReflectionUtils.setField(newObject, property.getName(), property.getValue());
				}
			}
		}
		return newObject;
	}

	@SuppressWarnings("unchecked")
	protected <T> T mapping(BeanEntry entry, Class<T> clazz) {
		return (T) mapping(entry);
	}

	private boolean isNumber(String target) {
		try {
			Integer.parseInt(target);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
