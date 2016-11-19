# framework-dev-myself

## 프로젝트 기본 골격 생성
- 1. ApplicationContext는 하위 클래스에서 정의한 빈팩토리 메타 데이터 생성 전략에 따라 메타 데이터를 얻은 후 BeanFactory 생성
- 2. BeanFactory는 전달받은 메타데이터에서 스코프가 Singleton(Default)인 빈에 대해 SingletonRegistry에게 해당 BeanEntry을 넘겨주고 처리 위임
- 3. SingletonRegistry는 전달받은 빈의 엔트리정보를 이용해 오브젝트를 생성 후 적재

###TODO 리스트
- <i>BeanEntry 에 속성정보 추가 (추가 - BeanProperty)-----------------완료</i>
- <i>XML 설정파일 파싱해 BeanFactoryMetaData로 만들어야함 (XmlConfigurationParser)-----------------완료</i>
- <i>Class의 Aware implements 여부를 확인 후 필요한 오브젝트 넣어줘야함-----------------완료</i>
- <i>Annotation을 이용해 자바 파일로 BeanFactoryMetaData 만들 수 있어야함 ----------------------- 완</i>
- <b>LifeCycle 구현 , Bean LifeCycle은 구현, Context LifeCycle은 미약함 </b>
- <b>Annotation 설정 후 Bean을 얻기 위해 invoke할 메소드를 저장할 필요가 있음</b>
