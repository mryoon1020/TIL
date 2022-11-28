# Kakao map api 사용 연습 프로젝트

### 목적

- 팀프로젝트에서 티원이 사용했던 kakao map api를 연습해보기위함
- 연습하며 일어났던 모든 오류 및 해결방안, 참조사이트를 기록하기 위함
- 추후에 편집예정

>  2022-11-27

- 시작이 반이다 라는 말이 있다 하지만 시작부터 에러가 났다

- gradle 의존성 빌드 중에 나타난 오류

  - Could not resolve all files for configuration ':classpath'.
  - No matching variant of org.springframework.boot:spring-boot-gradle-plugin:3.0.0 was found.

  ```gradle
  plugins {
  	id 'java'
  	id 'org.springframework.boot' version '3.0.0'
  	id 'io.spring.dependency-management' version '1.1.0'
  }
  ```

  - 기존 프로젝트에는 플러그인 부분이 다른점을 발견하였음
  - 하기와 같이 변경하자 바로 실행되었다

  ```gradle
  plugins {
  	id 'java'
  	id 'org.springframework.boot' version '2.7.5'
  	id 'io.spring.dependency-management' version '1.0.15.RELEASE'
  }
  ```

  - 원인 파악을 위해구글링했으나 원인파악은 안되었음
  - 많은 사람들이 spring initialaizr에서  gradle 버전 3.0.0 버전을 사용할경우 같은 오류가 나는것을 발견했으며 2.7.1버전으로 변경하면 된다고 하였음
  - spring initializr에는 2.7.6 버전이 제일 낮은 버전이라 해당버전으로 다시 프로젝트 생성하였더니 성공했음
  - java 버전은 앞으로도 계속 11로 하는것이 기존 자료들 참고 하는데 오류가 없을 듯하다

- 새프로젝트 오류없이 빈화면 까지 띄우기 완료

> 2022-11-29

- 빈화면에 map띄우기까지 성공하였음
- 생각보다 별거 없어서 놀랐음
- AWS S3를 사용하며 생긴 access-key 보안이슈 때문에 카카오톡 개발자에 가입하면서 준 보안키를 직접 자바스크립트에 쓰지 않고 properties 파일에 작성하고 사용할대마다 빼서 사용할 수 있도록 수정이 필요함
  - 정말 S3는 이글을 작성하고 있는 이시점에도 해결이 되지 않고 있음
  - 보안에 대해서 다시한번 생각해보는 계기가 되었으며 ignorelist 작성과 함께 보안키를 잘관리할 방법을 강구해야함
  - 프로퍼티에 작성하고 configuration 폴더에서 value 로 호출한뒤 controler에서 JSP로 넘어가는 방법이 어떨지 괜찮을 듯함
  - JSP에서 지도를 쓸때 비동기로 상시요청하는방식으로 개발을 해보면 어떨까 싶음

- 보안과 별개로 지도 카카오 지도 API는 마킹을 지원하여 위도, 경도, 이름 등의 값을 저장할 수 있는 기능이 있음