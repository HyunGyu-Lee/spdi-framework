package com.mvcsupporter.di;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mvcsupporter.di.bean.Property;

public class ConfigurationParser {
	
	public static ContainerConfiguration parse(JSONObject containerConfig) {
		
		ContainerConfiguration containerConfiguration = new ContainerConfiguration();
		
		String containerId = (String)containerConfig.get(PropertyNamespace.CONTAINER_ID);
		String controllerScan = (String)containerConfig.get(PropertyNamespace.CONTROLLER_SCAN);
		
		containerConfiguration.setContainerId(containerId);
		containerConfiguration.setControllerScanPackage(controllerScan);
		
		JSONArray beans = (JSONArray)containerConfig.get(PropertyNamespace.BEANS);
		
		ArrayList<BeanConfiguration> beansConfig = new ArrayList<>();
		
		if(beans!=null)
		{
			for(Object element : beans)
			{
				JSONObject bean = (JSONObject) element;
				
				BeanConfiguration beanConfig = new BeanConfiguration();
				ArrayList<Property> propertiesConfig = new ArrayList<>();

				String beanName = (String)bean.get(PropertyNamespace.BEAN_NAME);
				String beanClass = (String)bean.get(PropertyNamespace.BEAN_CLASS);
				
				beanConfig.setName(beanName);
				beanConfig.setClazz(beanClass);
				
				JSONArray properties = (JSONArray)bean.get(PropertyNamespace.PROPERTIES);
				
				if(properties!=null)
				{
					for(Object element2 : properties)
					{
						JSONObject property = (JSONObject)element2;
						
						String propertyName = (String)property.get(PropertyNamespace.PROPERTIY_NAME);
						String propertyValue = (String)property.get(PropertyNamespace.PROPERTIY_VALUE);
						String propertyRef = (String)property.get(PropertyNamespace.PROPERTY_REF);
						propertiesConfig.add(new Property(propertyName, propertyValue, propertyRef));
					}
				}
				beanConfig.setProperties(propertiesConfig);
				beansConfig.add(beanConfig);
			}
			containerConfiguration.setBeanConfigurations(beansConfig);
		}
		
		return containerConfiguration;
	}
	
}
