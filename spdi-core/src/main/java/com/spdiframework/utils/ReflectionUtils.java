package com.spdiframework.utils;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;

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
	
	@SuppressWarnings("unchecked")
	public static <T> T invoke(Method method, Class<T> clazz, Object invokeTarget, Object...params) {
		try
		{
			return (T) method.invoke(invokeTarget, params);
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
	
	public static void invoke(Method method, Object invokeTarget, Object...params) {
		try
		{
			if(method!=null)
			{
				method.invoke(invokeTarget, params);
			}
			else
			{
				// ����
			}
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
	}
	
	public static <T> T invoke(Object invokeTarget, Class<T> returnType, String methodName, Object...params) {
		Class<?>[] paramTypes = getObjectTypes(params);
		Method m = findMethod(invokeTarget.getClass(), methodName, paramTypes);		
		return invoke(m, returnType, invokeTarget, params);		
	}
	
	public static void invoke(Object invokeTarget, String methodName, Object...params) {
		Class<?>[] paramTypes = getObjectTypes(params);
		Method m = findMethod(invokeTarget.getClass(), methodName, paramTypes);				
		invoke(m, invokeTarget, params);
	}
	
	public static Class<?>[] getObjectTypes(Object...objs) {
		Class<?>[] paramTypes = new Class<?>[objs.length];
		
		for(int i=0;i<objs.length;i++) {
			paramTypes[i] = objs[i].getClass();
		}
		
		return paramTypes;
	}
	
	public static boolean hasMethod(Class<?> clazz, String methodName) {
		if(findMethod(clazz, methodName, Void.class)!=null)
		{
			return true;
		}
		
		return false;
	}
	
	public static boolean isAnnotatedOnClass(Class<?> clazz, Class<? extends Annotation> annotation)
	{
		if(clazz.getDeclaredAnnotation(annotation)!=null)
		{
			return true;
		}
		
		return false;			
	}
	
	public static Method getAnnotatedMethod(Class<?> clazz, Class<? extends Annotation> annotation)
	{
		for(Method m : clazz.getDeclaredMethods())
		{
			if(m.getAnnotation(annotation)!=null)
			{
				return m;
			}
		}
		return null;
	}
	
	public static Method[] getAnnotatedMethods(Class<?> clazz, Class<? extends Annotation> annotation) {
		ArrayList<Method> methodArray = new ArrayList<>();
		
		for(Method m : clazz.getDeclaredMethods())
		{
			if(m.getAnnotation(annotation)!=null)
			{
				methodArray.add(m);
			}
		}
		
		return methodArray.toArray(new Method[methodArray.size()]);
	}
	
	public static ArrayList<Class<?>> scanPackage(String packagePath) {
		// ����� ����Ʈ �ʱ�ȭ
		ArrayList<String> classNames = new ArrayList<>();
		ArrayList<Class<?>> classes = new ArrayList<>();

		// ���޹��� ��� ���� URL ����
		URL url = ReflectionUtils.class.getResource("/"+packagePath.replace(".", "/"));

		// URL�� null�̸� null��ȯ �� ����
		if(url==null)return null;
		File root = new File(url.getFile());
		
		// root���丮 �������� Ž�� ����
		searchDirectory(root, packagePath, classNames);

		for(String className : classNames) {
			try
			{
				classes.add(Class.forName(className));
				System.out.println(className);
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}
		
		return classes;
	}
	
	public static void searchDirectory(File root, String packagePath, ArrayList<String> classes) {
		if(root.isDirectory())
		{
			for(File child : root.listFiles())
			{
				if(child.isDirectory())
				{
					searchDirectory(child, packagePath+"."+child.getName(), classes);
				}
				else
				{

					classes.add(packagePath+"."+child.getName().split("\\.")[0]);
				}
			}
		}
	}
}
