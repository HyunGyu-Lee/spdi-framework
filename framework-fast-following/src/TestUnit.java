
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.net.MalformedURLException;

import org.junit.Before;
import org.junit.Test;

import com.leeframework.beans.MyBeanConfig;
import com.leeframework.beans.RefTest;
import com.leeframework.beans.Student;
import com.leeframework.context.AnnotationConfigApplicationContext;
import com.leeframework.context.XmlApplicationContext;

public class TestUnit {
	
	private XmlApplicationContext context;
	private AnnotationConfigApplicationContext ctx;
	
	@Before
	public void setUp() {
		context = new XmlApplicationContext("lee-context.xml");
		ctx = new AnnotationConfigApplicationContext(MyBeanConfig.class);
	}
	
//	@Test
	public void main() {
		Student student = context.getBean("test", Student.class);
		RefTest t = context.getBean("rf", RefTest.class);
		System.out.println(student.toString()+","+t);
		context.close();
	}
	
	@Test
	public void reflectionUtilsTest() throws MalformedURLException {
		
	}
	
}
