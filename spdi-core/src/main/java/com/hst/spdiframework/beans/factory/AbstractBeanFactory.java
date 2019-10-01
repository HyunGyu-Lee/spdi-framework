package com.hst.spdiframework.beans.factory;

import com.hst.spdiframework.beans.lifecycle.DisposableBean;
import com.hst.spdiframework.beans.lifecycle.InitailizingBean;
import com.hst.spdiframework.context.ApplicationContext;
import com.hst.spdiframework.context.ApplicationContextAware;
import com.hst.spdiframework.utils.ReflectionUtils;
import com.hst.spdiframework.beans.aware.BeanFactoryAware;
import com.hst.spdiframework.beans.aware.BeanNameAware;
import com.hst.spdiframework.beans.exception.NoSuchBeanException;
import com.hst.spdiframework.beans.metadata.BeanEntry;
import com.hst.spdiframework.beans.metadata.BeanEntryObjectMapper;
import com.hst.spdiframework.beans.metadata.BeanFactoryMetaData;
import com.hst.spdiframework.beans.metadata.BeanReference;
import com.hst.spdiframework.beans.metadata.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractBeanFactory extends BeanEntryObjectMapper implements ApplicationContextAware {

	private static final Logger logger = LoggerFactory.getLogger(AbstractBeanFactory.class);
	
	private ApplicationContext applicationContext;
	private BeanFactoryMetaData beanFactoryMetaData;
	private SingletonRegistry singletonRegistry = new SingletonRegistry(this);

	public AbstractBeanFactory() {}
	
	public AbstractBeanFactory(BeanFactoryMetaData beanFactoryMetaData) {
		load(beanFactoryMetaData);
	}
	
	private void load(BeanFactoryMetaData beanFactoryMetaData) {
		logger.info("Load metadata {}",beanFactoryMetaData);
		this.beanFactoryMetaData = beanFactoryMetaData;
	}
	
	public void refresh() {
		for(String beanName : beanFactoryMetaData.getBeanEntries().keySet())
		{
			BeanEntry entry = beanFactoryMetaData.getBeanEntries().get(beanName);
			if(entry.getScope()==null) beanFactoryMetaData.getBeanEntries().get(beanName).setScope(Scope.SINGLETON);
			
			if(entry.getScope().equals(Scope.SINGLETON))
			{
				logger.info("{}({}) registed at Singleton registry", beanName, entry.getBeanType().getSimpleName());
				singletonRegistry.registry(beanFactoryMetaData.getBeanEntries().get(beanName));
			}
		}
	}
	
	public BeanFactoryMetaData getBeanFactoryMetaData() {
		return beanFactoryMetaData;
	}
	public void setBeanFactoryMetaData(BeanFactoryMetaData beanFactoryMetaData) {
		this.beanFactoryMetaData = beanFactoryMetaData;
	}
	
	public <T> T getBean(String beanName, Class<T> clazz) {
		BeanEntry beanEntry = beanFactoryMetaData.getEntry(beanName);
		BeanReference reference = beanFactoryMetaData.getReference(beanName);
		
		if(beanEntry==null)throw new NoSuchBeanException(beanName);
		
		T beanObject = null;

		/* �� �ν��Ͻ� ȭ �� DI */
		beanObject = getBeanByScope(beanEntry);	// �� ��ü�� ���� �Ǵ� �̱��� ������Ʈ������ �����ϴ�.
	
		processBeanReference(beanObject, reference);	// Bean�� ������ ���⼭ �߻��մϴ�.
		
		/* Aware Ȯ�� */
		processAware(beanObject, beanEntry);		// Aware ���� ���ο� ���� �ʿ��� ��ü�� �������ݴϴ�.

		/* Lifecycle �ݹ� */
		processBeanInitiating(beanObject, beanEntry);	// BeanEntry�� ������ ��� �� �ʱ�ȭ ���� �޼ҵ� �����ŵ�ϴ�.
		
		return beanObject;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getBeanByScope(BeanEntry beanEntry) {
		if(beanEntry.getScope().equals(Scope.SINGLETON))
		{
			return (T)singletonRegistry.getBean(beanEntry.getBeanName(), beanEntry.getBeanType());
		}
		else if(beanEntry.getScope().equals(Scope.PROTOTYPE))
		{
			return (T) this.mapping(beanEntry, beanEntry.getBeanType());
		}
		// default singleton
		return (T)singletonRegistry.getBean(beanEntry.getBeanName(), beanEntry.getBeanType());
	}
	
	public <T> void processAware(T beanObject, BeanEntry beanEntry) {
		
		if(ReflectionUtils.isImplements(beanEntry.getBeanType(), BeanNameAware.class))
		{
			ReflectionUtils.invoke(beanObject, "setBeanName", beanEntry.getBeanName());
		}
		
		if(ReflectionUtils.isImplements(beanEntry.getBeanType(), BeanFactoryAware.class))
		{
			ReflectionUtils.invoke(ReflectionUtils.findMethod(beanEntry.getBeanType(), "setBeanFactory", AbstractBeanFactory.class), beanObject, this);
		}
		
		if(ReflectionUtils.isImplements(beanEntry.getBeanType(), ApplicationContextAware.class))
		{
			ReflectionUtils.invoke(ReflectionUtils.findMethod(beanEntry.getBeanType(), "setApplicationContext", ApplicationContext.class), beanObject, applicationContext);
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> void processBeanReference(T beanObject, BeanReference reference) {
		if(reference!=null)
		{
			BeanEntry refEntry = beanFactoryMetaData.getEntry(reference.getRefName());
			T refBean = (T)getBean(refEntry.getBeanName(), refEntry.getBeanType());
			ReflectionUtils.setField(beanObject, reference.getInjectField(), refBean);
		}
	}
	
	public <T> void processBeanInitiating(T beanObject, BeanEntry beanEntry) {
		
		Class<?> beanType = beanEntry.getBeanType();

		/* PostConstruct */
		ReflectionUtils.invoke(ReflectionUtils.getAnnotatedMethod(beanType, PostConstruct.class), beanObject);
		
		/* InitializingBean */
		if(ReflectionUtils.isImplements(beanType, InitailizingBean.class))
		{
			ReflectionUtils.invoke(ReflectionUtils.findMethod(beanType, "afterPropertiesSet"), beanObject);
		}
		
		/* Initiating-method */
		if(beanEntry.getInitMethod()!=null)
		{
			ReflectionUtils.invoke(ReflectionUtils.findMethod(beanType, beanEntry.getInitMethod()), beanObject);
		}
	}
	
	public <T> void processBeanDisposing(T beanObject, BeanEntry beanEntry) {
		
		Class<?> beanType = beanEntry.getBeanType();
		
		if(beanEntry.getDestroyMethod()!=null) {
			ReflectionUtils.invoke(ReflectionUtils.findMethod(beanType, beanEntry.getDestroyMethod()), beanObject);
		}
		
		if(ReflectionUtils.isImplements(beanType, DisposableBean.class))
		{
			ReflectionUtils.invoke(ReflectionUtils.findMethod(beanType, "destroy"), beanObject);
		}
		
		ReflectionUtils.invoke(ReflectionUtils.getAnnotatedMethod(beanType, PreDestroy.class), beanObject);
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void destroy() {
		if(singletonRegistry!=null) {
			for(String beanName : beanFactoryMetaData.getBeanEntries().keySet())
			{
				if(beanFactoryMetaData.getBeanEntries().get(beanName).getScope().equals(Scope.SINGLETON))
				{
					destroyBean(beanName);
				}
			}
		}
	}
	
	public void destroyBean(String beanName) {
		BeanEntry entry = beanFactoryMetaData.getEntry(beanName);
		processBeanDisposing(singletonRegistry.getBean(beanName, entry.getBeanType()), entry);
		//beanFactoryMetaData.getBeanEntries().remove(beanName);
		singletonRegistry.destroySingleton(beanName);
	}
	
}
