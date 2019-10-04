package spdi;

import com.spdiframework.beans.streotype.Bean;
import com.spdiframework.beans.streotype.Configuration;

// TODO / [개선] 테스트 패키지로 이동
@Configuration
public class MyBeanConfig {

	@Bean(initMethod="init", destroyMethod="destory")
	public Student student(String t, Student test) {
		Student student = new Student();
		return student;
	}
	
	@Bean
	public Student test() {
		return new Student();
	}
	
}
