import com.spdiframework.context.AnnotationConfigApplicationContext;
import com.spdiframework.context.ApplicationContext;
import com.spdiframework.context.XmlApplicationContext;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spdi.MyBeanConfig;
import spdi.Student;

public class AnnotationConfigApplicationContextTestcase {

	private ApplicationContext ctx;

	private Class[] configurations;

	private static Logger log = LoggerFactory.getLogger(AnnotationConfigApplicationContextTestcase.class);

	@Before
	public void setUp() {
		this.configurations = new Class[]{MyBeanConfig.class};
	}

	@Test
	public void testAnnotationConfigApplicationContextLoad() {
		this.ctx = new AnnotationConfigApplicationContext(configurations);

		log.debug("{}", ctx.getBean("test", Student.class));
	}

	@Test
	public void testXmlApplicationContextLoad() {
		this.ctx = new XmlApplicationContext("test-context.xml");
	}


}
