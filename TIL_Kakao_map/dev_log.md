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