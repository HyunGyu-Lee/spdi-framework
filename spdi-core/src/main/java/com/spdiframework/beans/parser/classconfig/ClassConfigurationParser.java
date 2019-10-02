package com.spdiframework.beans.parser.classconfig;

import com.spdiframework.beans.streotype.Bean;
import com.spdiframework.utils.ReflectionUtils;
import com.spdiframework.beans.metadata.BeanDependency;
import com.spdiframework.beans.metadata.BeanEntry;
import com.spdiframework.beans.metadata.BeanFactoryMetaData;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassConfigurationParser {

	private static final Logger logger = LoggerFactory.getLogger(ClassConfigurationParser.class);
	
	public static void parseAndApply(BeanFactoryMetaData metaData, Class<?> configurable) {
		Method[] beans = ReflectionUtils.getAnnotatedMethods(configurable, Bean.class);
		
		for(Method bean : beans)
		{
			/* �� ��Ʈ�� ���� */
			BeanEntry entry = new BeanEntry();

			/* �޼ҵ忡 ������ ������̼� */
			Bean beanInfo = bean.getAnnotation(Bean.class);
			
			Class<?> beanType = bean.getReturnType();

			entry.setBeanType(beanType);
			entry.setBeanName(beanInfo.name().equals("")?bean.getName():beanInfo.name());
			entry.setInitMethod(beanInfo.initMethod().equals("")?null:beanInfo.initMethod());
			entry.setDestroyMethod(beanInfo.destroyMethod().equals("")?null:beanInfo.destroyMethod());
			
			/* �������� �߰� ó�� */
			proceedingReference(metaData, entry, bean);
			/*
			 * ���⼭ ������ �޼ҵ带 �� ��Ʈ���� �־�δ��� �ؾ���, Autowired������ ��� ���������� �غ�����
			 * */
			entry.setInvocable(bean);
			metaData.addEntry(entry.getBeanName(), entry);
		}
		
	}
	
	public static void proceedingReference(BeanFactoryMetaData metaData, BeanEntry entry, Method bean) {
		
		/* ���⼭ �� ���� ���ԵǴ� �������� Ȯ���� �� ������ ������ ���� ������̾ �Ǿ��Ѵ�.
		 * 
		 * ������ �̰� ��� ���� ���� �о�� ���� ���� �������� ó���Ǿ���Ѵ�.
		 *  */
		
		for(Parameter clazz : bean.getParameters())
		{
			metaData.addDefendencies(entry.getBeanName(), new BeanDependency(clazz.getType(), clazz.getName()));
		}
		
	}
	
}
