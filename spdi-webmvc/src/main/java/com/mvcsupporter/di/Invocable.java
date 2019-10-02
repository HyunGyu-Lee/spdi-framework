package com.mvcsupporter.di;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Invocable {
	private Object instance;
	private Method target;
	private Object[] parameters;
	
	public Invocable(Object instance, Method target)
	{
		this.instance = instance;
		this.target = target;
	}
	
	public Object getInstance() {
		return instance;
	}
	public void setInstance(Object instance) {
		this.instance = instance;
	}
	public Method getTarget() {
		return target;
	}
	public void setTarget(Method target) {
		this.target = target;
	}
	public Object[] getParameters() {
		return parameters;
	}
	public void setParameters(Object...parameters) {
		this.parameters = parameters;
	}
	public Object invoke() {
		try
		{
			return target.invoke(instance, parameters);
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		catch (InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
