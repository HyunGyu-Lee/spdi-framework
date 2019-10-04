package com.spdiframework.beans.parser.classconfig;

import com.spdiframework.beans.metadata.BeanDependency;
import com.spdiframework.beans.metadata.BeanEntry;
import com.spdiframework.beans.metadata.BeanFactoryMetaData;
import com.spdiframework.beans.streotype.Bean;
import com.spdiframework.utils.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/***
 * @author dlgusrb0808@gmail.com
 */
public class ClassConfigurationParser {

	private static final Logger logger = LoggerFactory.getLogger(ClassConfigurationParser.class);

	public static void parseAndApply(BeanFactoryMetaData metaData, Class<?> configurable) {
		Method[] beans = ReflectionUtils.getAnnotatedMethods(configurable, Bean.class);

		for (Method bean : beans) {
			BeanEntry entry = new BeanEntry();
			Bean beanInfo = bean.getAnnotation(Bean.class);

			Class<?> beanType = bean.getReturnType();

			entry.setBeanType(beanType);
			entry.setBeanName(beanInfo.name().equals("") ? bean.getName() : beanInfo.name());
			entry.setInitMethod(beanInfo.initMethod().equals("") ? null : beanInfo.initMethod());
			entry.setDestroyMethod(beanInfo.destroyMethod().equals("") ? null : beanInfo.destroyMethod());

			proceedingReference(metaData, entry, bean);

			entry.setInvocable(bean);
			metaData.addEntry(entry.getBeanName(), entry);
		}

	}

	private static void proceedingReference(BeanFactoryMetaData metaData, BeanEntry entry, Method bean) {
		for (Parameter clazz : bean.getParameters()) {
			metaData.addDefendencies(entry.getBeanName(), new BeanDependency(clazz.getType(), clazz.getName()));
		}

	}

}
