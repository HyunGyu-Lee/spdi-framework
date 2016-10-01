# framework-dev-myself

## 프로젝트 기본 골격 생성
- 1. ApplicationContext는 하위 클래스에서 정의한 빈팩토리 메타 데이터 생성 전략에 따라 메타 데이터를 얻은 후 BeanFactory 생성
- 2. BeanFactory는 전달받은 메타데이터에서 스코프가 Singleton(Default)인 빈에 대해 SingletonRegistry에게 해당 BeanEntry을 넘겨주고 처리 위임
- 3. SingletonRegistry는 전달받은 빈의 엔트리정보를 이용해 오브젝트를 생성 후 적재

###TODO 리스트
- BeanEntry 에 속성정보 추가
- XML 설정파일 파싱해 BeanFactoryMetaData로 만들어야함
