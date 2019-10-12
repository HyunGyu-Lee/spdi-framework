import com.spdiframework.context.AnnotationConfigOldApplicationContext;
import com.spdiframework.context.OldApplicationContext;
import com.spdiframework.context.XmlOldApplicationContext;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spdi.MyBeanConfig;
import spdi.Student;

public class AnnotationConfigOldApplicationContextTestcase {

	private OldApplicationContext ctx;

	private Class[] configurations;

	private static Logger log = LoggerFactory.getLogger(AnnotationConfigOldApplicationContextTestcase.class);

	@Before
	public void setUp() {
		this.configurations = new Class[]{MyBeanConfig.class};
	}

	@Test
	public void testAnnotationConfigApplicationContextLoad() {
		this.ctx = new AnnotationConfigOldApplicationContext(configurations);

		log.debug("{}", ctx.getBean("test", Student.class));
	}

	@Test
	public void testXmlApplicationContextLoad() {
		this.ctx = new XmlOldApplicationContext("test-context.xml");
	}


}
