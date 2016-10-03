import com.leeframework.beans.Student;
import com.leeframework.context.ApplicationContext;
import com.leeframework.context.XmlApplicationContext;

public class TestUnit {
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		
		ApplicationContext context = new XmlApplicationContext("lee-context.xml");
		Student student = context.getBean("test", Student.class);
		
		System.out.println(student);
	}
	
}
