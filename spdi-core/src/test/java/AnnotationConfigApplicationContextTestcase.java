import com.hst.spdiframework.beans.Student;
import com.hst.spdiframework.context.AnnotationConfigApplicationContext;
import com.hst.spdiframework.context.XmlApplicationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hst.spdiframework.beans.MyBeanConfig;

public class AnnotationConfigApplicationContextTestcase {
	
	private XmlApplicationContext context;
	private AnnotationConfigApplicationContext ctx;
	
	private static Logger log = LoggerFactory.getLogger(AnnotationConfigApplicationContextTestcase.class);
	
	public void setUp() {
		//context = new XmlApplicationContext("lee-context.xml");
		ctx = new AnnotationConfigApplicationContext(MyBeanConfig.class);
	}
	

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MyBeanConfig.class);
		System.out.println(ctx.getBean("test", Student.class));
		log.trace("TRACE");
		log.debug("DEBUG");
		log.info("INFO");
		log.warn("WARN");
		log.error("ERROR");
	}
	

}
