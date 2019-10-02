# JspMVCAdapter
- JSP환경에서 MVC모델을 쉽게 적용할 수 있게 도와줍니다.
- json기반 설정을 통해 DI, IOC 활용이 가능합니다.

# 개발일지
## 2016-09-19
- 아주 기본적인 MVC기능 구현
- DispatcherServlet에서 요청 받은 후 @Controller의 @Service를 찾아 서비스한 후 view 리턴
- 라이브러리화 완료

## 2016-09-21
- Log4J 라이브러리 적용, Fat jar를 통해 Export
- - Container 로드 시기 변경 - Context 초기화 시 Container 로드

## 2016-09-23
- 설정파일에서 읽어온 내용으로 컨테이너 제작

## 2016-09-25
- Container(최상위), WebApplicationContainer 추가
- Container가 JSON을 읽어 bean 적재 및 bean ref matching 수행
- MVC 모듈에 DI, IOC 적용, DispatcherServlet이 container를 갖고 있게 변경

## 2016-09-28
- Dispatcher - RequestMapping - ViewResolving - foward 사이클 적용

# 개선사항
### 설정파일의 BeanConfiguration, Property를 이용해 DI를 통해 Container에 컨트롤러 및 Bean 적재
- reflection관련 코드는 전부 ReflectionUtils에서 처리하자
- DI에 Injector 만들고 Injector가 ReflectionUtils사용해 생성한 Class<?>를 Container에 적재하는 스토리로 간다
- Injector에 Container 전달 (Fix 2016-09-25)

### 현재 MappingHandler의 mapping메소드에서 적재된 컨트롤러의 메소드를 찾아 invoke하는 기준이 명확치 않음
- invoke 후 반환값에 따른 viewResolver의 역할도 정의하자(현재는 String만 반환)
- 메소드를 찾았다면 인자가 무엇인지 알아낸 후 handler가 인자를 넣어줄 수 있어야함

### @Service에 enum RequestMethod 추가

### @Controller에서 view로 데이터 보낼 방법 필요
- 매핑부터 제대로 하고 생각하자

# 설치방법
#### 1) WEB-INF/lib에 라이브러리 위치시킴
#### 2) WEB-INF 폴더 아래 컨테이너 설정파일 생성<br>
example) WEB-INF/conf/context-conf.json
```json
{
	"container-id" : "defaultContainer",
	"controller-scan" : "com.mvcsupporter.test.controller",
	"beans" : [
		{
			"bean-name" : "mappingHandler",
			"bean-class" : "MappingHandler"
		},
		{
			"bean-name" : "viewResolver",
			"bean-class" : "ViewResolver",
			"properties" : [
				{
					"property-name" : "prefix",
					"property-value" : "/"
				},
				{
					"property-name" : "suffix",
					"property-value" : ".jsp"
				}
			]
		}
	]
}
```
- container-id : 미구현<br>
- controller-scan : 컨테이너가 컨트롤러를 찾을 범위를 지정해준다.<br>
- beans : 컨테이너 내 적재될 빈들을 정의<br>
- bean-name : 빈 이름<br>
- bean-class : 빈 클래스<br>
- properties : 빈의 속성들을 정의<br>
- property-name : 속성명(해당 Bean클래스의 필드명과 일치해야함)<br>
- property-value : 값(Integer.parseInt메소드가 예외를 발생하지 않는 자료는 int형으로 저장, 이외에는 String형으로 저장)<br>

이 설정법은 JSP환경에 MVC패턴을 적용하기 위한 설정으로 <b>mappingHandler</b>빈과 <b>viewResolver</b>빈을 선언해줘야합니다.<br>
즉 고정 설정이라는 뜻.. 바뀔 수 있는 것은 <b>controller-scan<b>값과 위 설정파일의 위치입니다.

#### 3) web.xml에 DispatcherServlet 매핑, ContextLoaderListener 등록<br>
```xml
<!-- Container cofiguration file -->
<context-param>
  	<param-name>container-config</param-name>
  	<param-value>confs/context-conf.json</param-value>
</context-param>	

<!-- Default ContextLoaderListener -->	  
<listener>
	<listener-class>com.mvcsupporter.core.ContextLoaderListener</listener-class>
</listener>

<!-- Default DispatcherServlet -->	    
<servlet>
	<servlet-name>dispatcherServlet</servlet-name>
	<servlet-class>com.mvcsupporter.core.DispatcherServlet</servlet-class>
</servlet>
  
<servlet-mapping>
	<servlet-name>dispatcherServlet</servlet-name>
	<url-pattern>/</url-pattern>
</servlet-mapping>
```
* ContextLoaderListener에서 Container를 생성하고 json설정파일에 따라 Controller를 적재함.
* context-param에 containerConfiguration에 value값으로 json 설정파일 경로입력 (ContainerFactory는 <b>WEB-INF</b> 가리키고있음)
* url패턴은 /로 해줘 모든 요청을 DispatcherServlet이 받을수있게 한다

#### 4) 예제 컨트롤러 구현
```java
@Controller
public class ExampleController {
	@Service(name="main", method=RequestMethod.GET)
	public String example(HttpServletRequest request) {
		return "index"
	}
}
```
- return값은 WEB-CONTENT내 jsp파일 이름을 의미한다. (여러가지 값을 return할 수 있게 변경 예정)
- method의 경우 아직 미구현 (참고로 default 는 GET)

#### 5) ip:port/context-path/name 으로 요청한 후 view 페이지가 보여짐을 확인한다
