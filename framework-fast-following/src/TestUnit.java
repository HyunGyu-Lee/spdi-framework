import com.leeframework.beans.BeanEntry;
import com.leeframework.context.ApplicationContext;
import com.leeframework.context.XmlApplicationContext;

public class TestUnit {
	
	public static void main(String[] args) {
		ApplicationContext context = new XmlApplicationContext("a.xml","b.xml");

		BeanEntry e = context.getBean("test", BeanEntry.class);
		
		System.out.println(e.getBeanType()+","+e.getBeanName()+","+e.getScope());
	}
	
}
