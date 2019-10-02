import com.spdiframework.webmvc.core.Container;
import com.spdiframework.webmvc.core.GeneralApplicationContainer;

public class ContainerTest {
	
	public static void main(String[] args) {
		Container container = new GeneralApplicationContainer("context-conf.json");
	}
	
}
