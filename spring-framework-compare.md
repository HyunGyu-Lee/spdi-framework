# Reference of Spring Framework Core 

## BeanDefinition
Spring Bean Container 에서 관리되는 빈에 대한 정의 (BeanDefinition)로 클라이언트가 빈 요청 시 BeanDefinition 을 보고 빈 응답 방식을 결정한다.
<br>

### BeanDefinition 이 포함하고 있는 정보
- Class
- Name
- Scope
- Constructor arguments
- Properties
- Autowiring mode
- Lazy initialization mode
- Initialization / Destruction method

## BeanFactory
- Spring Bean Container 에 접근하는 방법을 정의한 최상위 인터페이스
- Bean 획득에 대한 책임
- Bean 정보를 관리하고, Client 호출 시 필요한 Bean 을 반환해주는 역할

## ApplicationContext
