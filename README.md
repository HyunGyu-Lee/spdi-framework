# spdi-framework

## spdi-framework 란?
Spring framework 학습목적으로 DI / IoC / AOP 를 직접 구현해보고자 시작한 프로젝트
처음 명명할 당시 DI 기능만이라도 온전히 구현해보고자 하여 이름을 Simple DI , spdi 로 명명
 
## spdi-framework 의 모듈 목록
- spdi-core 기본적으로, 사용자에게 프레임워크 환경을 제공해주기 위한 핵심 기능을 담당하는 모듈 

## TODO
- [ ] JspMvcAdapter (https://github.com/HyunGyu-Lee/JspMVCAdapter) 통합
    - [ ] 기존 기능 spdi-core 통합
        - [ ] JSON Context 설정 방식
        - [ ] 비슷하게 구현된 Beans 관련 기능
    - [ ] Web 관련 기능 (DispatcherServlet 등등) spdi-web 신규 생성
- [ ] 소스 전체적으로 리팩토링