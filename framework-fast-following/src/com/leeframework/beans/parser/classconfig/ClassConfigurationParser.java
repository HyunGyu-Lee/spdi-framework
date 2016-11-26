package com.leeframework.beans.parser.classconfig;

import com.leeframework.beans.metadata.BeanDependency;
import com.leeframework.beans.metadata.BeanEntry;
import com.leeframework.beans.metadata.BeanFactoryMetaData;
import com.leeframework.beans.metadata.BeanReference;
import com.leeframework.beans.streotype.Bean;

import static com.leeframework.utils.ReflectionUtils.*;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassConfigurationParser {

	private static final Logger logger = LoggerFactory.getLogger(ClassConfigurationParser.class);
	
	public static void parseAndApply(BeanFactoryMetaData metaData, Class<?> configurable) {
		Method[] beans = getAnnotatedMethods(configurable, Bean.class);
		
		for(Method bean : beans)
		{
			/* 빈 엔트리 생성 */
			BeanEntry entry = new BeanEntry();

			/* 메소드에 설정된 어노테이션 */
			Bean beanInfo = bean.getAnnotation(Bean.class);
			
			Class<?> beanType = bean.getReturnType();

			entry.setBeanType(beanType);
			entry.setBeanName(beanInfo.name().equals("")?bean.getName():beanInfo.name());
			entry.setInitMethod(beanInfo.initMethod().equals("")?null:beanInfo.initMethod());
			entry.setDestroyMethod(beanInfo.destroyMethod().equals("")?null:beanInfo.destroyMethod());
			
			/* 의존관계 추가 처리 */
			proceedingReference(metaData, entry, bean);
			/*
			 * 여기서 실행할 메소드를 빈 엔트리에 넣어두던지 해야함, Autowired정보가 계속 유지될지가 해봐야함
			 * */
			entry.setInvocable(bean);
			metaData.addEntry(entry.getBeanName(), entry);
		}
		
	}
	
	public static void proceedingReference(BeanFactoryMetaData metaData, BeanEntry entry, Method bean) {
		
		/* 여기서 빈에 대해 주입되는 의존성을 확인할 수 있으며 적합한 빈이 오토와이어링 되야한다.
		 * 
		 * 때문에 이건 모든 빈을 전부 읽어둔 다음 제일 마지막에 처리되어야한다.
		 *  */
		
		for(Parameter clazz : bean.getParameters())
		{
			metaData.addDefendencies(entry.getBeanName(), new BeanDependency(clazz.getType(), clazz.getName()));
		}
		
	}
	
}
