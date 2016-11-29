package com.leeframework;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.leeframework.beans.MyBeanConfig;
import com.leeframework.beans.RefTest;
import com.leeframework.beans.Student;
import com.leeframework.beans.exception.NoSuchBeanException;
import com.leeframework.context.AnnotationConfigApplicationContext;
import com.leeframework.context.XmlApplicationContext;

public class TestUnit {
	
	private XmlApplicationContext context;
	private AnnotationConfigApplicationContext ctx;
	
	Logger log = LoggerFactory.getLogger(TestUnit.class);
	
	@Before
	public void setUp() {
		//context = new XmlApplicationContext("lee-context.xml");
		ctx = new AnnotationConfigApplicationContext(MyBeanConfig.class);
	}
	
	@Test
	public void main() {
		//System.out.println(ctx.getBean("test", Student.class));
//		log.trace("TRACE 레벨");
//		log.debug("DEBUG 레벨");
//		log.info("INFO 레벨");
//		log.warn("WARN 레벨");
//		log.error("ERROR 레벨");
		
		
		
	}
	
//	@Test
//	public void reflectionUtilsTest() throws MalformedURLException {
//		
//	}
	
}
