import com.leeframework.beans.RefTest;
import com.leeframework.beans.Student;
import com.leeframework.context.ApplicationContext;
import com.leeframework.context.XmlApplicationContext;

public class TestUnit {
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		
		ApplicationContext context = new XmlApplicationContext("lee-context.xml");
		
		Student test = context.getBean("test", Student.class);
		RefTest ref = context.getBean("refBean", RefTest.class);
		
		System.out.println("testºó : "+test);
		System.out.println(ref.getStudent());
	}
	
}
