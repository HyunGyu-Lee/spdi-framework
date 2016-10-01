import com.leeframework.context.ApplicationContext;
import com.leeframework.context.XmlApplicationContext;

public class TestUnit {
	
	public static void main(String[] args) {
		ApplicationContext context = new XmlApplicationContext("a.xml","b.xml");
		
	}
	
}
