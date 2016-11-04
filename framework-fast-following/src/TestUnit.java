
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.net.MalformedURLException;

import org.junit.Before;
import org.junit.Test;

import com.leeframework.beans.RefTest;
import com.leeframework.beans.Student;
import com.leeframework.context.XmlApplicationContext;
import com.leeframework.utils.ReflectionUtils;

public class TestUnit {
	
	private XmlApplicationContext context;
	
	@Before
	public void setUp() {
		context = new XmlApplicationContext("lee-context.xml");
	}
	
	//@Test
	public void main() {
		Student student = context.getBean("test", Student.class);
		RefTest t = context.getBean("rf", RefTest.class);
		System.out.println(student.toString()+","+t);
		context.close();
	}
	
	@Test
	public void reflectionUtilsTest() throws MalformedURLException {
		assertThat(ReflectionUtils.scanPackage("com.leeframework.beans.xml"), not(nullValue()));
	}
	
}
