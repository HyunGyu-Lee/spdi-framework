package com.mvcsupporter.utils;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mvcsupporter.web.annotation.Service;
import com.mvcsupporter.web.annotation.Service.RequestMethod;

public class ReflectionUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);
	
	public static ArrayList<Class<?>> scanPackage(String packagePath) {
		// 사용할 리스트 초기화
		ArrayList<String> classNames = new ArrayList<>();
		ArrayList<Class<?>> classes = new ArrayList<>();

		// 전달받은 경로 기준 URL 생성
		URL url = ReflectionUtils.class.getResource("/"+packagePath.replace(".", "/"));
		// URL이 null이면 null반환 후 종료
		if(url==null)return null;
		File root = new File(url.getFile());
		
		// root디렉토리 기준으로 탐색 시작
		searchDirectory(root, packagePath, classNames);
		
		ReflectionUtils.class.getClassLoader();
		
		for(String className : classNames) {
			try
			{
				classes.add(Class.forName(className));
				logger.info("Load Controller - [{}]", className);
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
	
	@SuppressWarnings("unchecked")
	public static <T> T createInstance(Class<?> clazz) {
		try
		{
			return (T)clazz.newInstance();
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
	
	public static Class<?> findClass(String className) {
		try
		{
			return Class.forName(className);
		}
		catch (ClassNotFoundException e)
		{
			return null;
		}
	}
	
	public static void set(Object instance, String fieldName, Object value) {
		Class<?> clazz = instance.getClass();
		Field field = findField(clazz, fieldName);
		try
		{
			field.set(instance, value);
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
	}
	
	public static Field findField(Class<?> clazz, String fieldName) {
		Field field = null;
		try
		{
			field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
		}
		catch (NoSuchFieldException e)
		{
			e.printStackTrace();
		}
		catch (SecurityException e)
		{
			e.printStackTrace();
		}
		return field;
	}
	
	public static void set(Object instance, String fieldName, String value) {
		Class<?> clazz = instance.getClass();
		Field field = findField(clazz, fieldName);
		try
		{
			if(isDigit(value))
			{
				field.setInt(instance, Integer.parseInt(value));				
			}
			else
			{
				field.set(instance, value);
			}
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
	}
	
	public static boolean isDigit(String value) {
		try
		{
			Integer.parseInt(value);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	public static ArrayList<Method> findAnnotatedMethodsInClass(Class<?> clazz, Class<? extends Annotation> annotation) {
		
		Method[] methods = clazz.getDeclaredMethods();
		ArrayList<Method> returnVals = new ArrayList<>();
		
		for(Method method : methods)
		{
			if(method.getAnnotation(annotation)!=null)
			{
				returnVals.add(method);
			}
		}
		
		return returnVals;		
	}
	
	public static Method findMethodByServiceName(ArrayList<Method> methods, String serviceName, RequestMethod m) {
		
		for(Method method : methods)
		{
			Service service = method.getAnnotation(Service.class);
			if(service.name().equals(serviceName)&&service.method().equals(m))
			{
				return method;
			}
		}
		return null;
	}
	
}
