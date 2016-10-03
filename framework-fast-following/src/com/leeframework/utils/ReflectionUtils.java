package com.leeframework.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtils {
	
	public static Method findMethod(Class<?> clazz, String methodName, Class<?>...paramTypes) {		
		try
		{
			return clazz.getDeclaredMethod(methodName, paramTypes);
		}
		catch (NoSuchMethodException e)
		{
			e.printStackTrace();
		}
		catch (SecurityException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static Field findField(Class<?> clazz, String fieldName) {
		try
		{
			return clazz.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e)
		{
			e.printStackTrace();
		}
		catch (SecurityException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static void setField(Object instance, String fieldName, Object fieldValue) {
		Field field = findField(instance.getClass(), fieldName);
		field.setAccessible(true);
		try
		{
			field.set(instance, fieldValue);
		} catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
	}
	
	public static <T> T createInstance(Class<T> clazz) {
		try
		{
			return clazz.newInstance();
		}
		catch (InstantiationException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean isImplements(Class<?> clazz, Class<?> interfaze) {
		for(Class<?> inter : clazz.getInterfaces()) {
			if(inter.equals(interfaze))
			{
				return true;
			}
		}
		return false;
	}
	
}
