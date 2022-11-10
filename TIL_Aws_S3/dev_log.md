# 우선 두서없이 적어보는 TIL

- Rent_car_project에 문제가 생겼음

  - NCL 기반으로 처리한 OCL기능, object storage가 기한 만료로 인해 사용불가됨

- 우선 Amazon에서 12개월간 프리티어를 제공하므로 NCL object storage를 aws S3로 변경

  - 사실 NCL object storage 도 Amazon aws S3 기반으로 제작(내가보기에는 똑같은거 같다)되었으므로 비슷할 것으로 추정

- 추후에 이문서는 다시 README 파일로 정리 될것임

- 우선 aws S3만 따로 구현해서 파일 업로드, 다운로드 기능을 사용한후 프로젝트에 적용해볼예정

- 시작일 2022-11-05

  - 관련 자료검색 완료
    - https://docs.aws.amazon.com/ko_kr/sdk-for-java/v1/developer-guide/examples-s3-objects.html#upload-object
    - https://antdev.tistory.com/93

  - 해당자료는 `application.yml` 파일을 사용중
  - `application.properties` 파일과 `application.yml` 과의 차이점
    - https://sowon-dev.github.io/2021/08/17/210818Spring-applicationyml/
    - 입력방식 차이로 보임
    - 가독성은 yml 파일 형식이 훨씬 좋으나 Rent_car_project는 properties 파일을 사용하므로 properties로 변경하여 사용하겠음

- 2022-11-07
  - Application.run 명령문 오류가 남
  - 원인파악이 잘 안됨
  
- 2022-11-08
  - Application.run 명령문 오류는 아주 심플한 오류였음
    - 부끄럽지만 sysntax에러 였음
    - 오타주의할 것!!
  - 아주 심플한 뷰페이지를 만들었음
  
- 2022-11-09

  - main페이지와 비동기통신 처리를 미리 해주었음
  - 컨트롤러부분이 작성되지 않았어서 실행확인은 하지 못함

- 2022-11-10

  - 문제가 생김
  - 대략 4시간동안 헤맨듯함
  - view페이지를 만든후 서버를 올렷는데 서버가 전혀 올라가지 않음
  - 다양한 곳에서 오류가나는데 두서없이 하다보니 꼬인것 같아 새로운 프로젝트 `webtest`생성함
  - 파일명저장을 위해 mysql로 새로운 DB를 생성하였음
    - DB설계는 aws S3에서 필요한 키값과 함께 파일명과 용량을 저장하는 테이블을 만들예정(아직 안만듬)
  - 이후 순차적으로 뷰페이지 => 컨트롤러 => 기능추가 순서로 시행하기러함
  - 새프로젝스 생성후 서버가 올라가는지 테스트를 해봄
    - 기존에는 테스트도 하지 않고 그저 코딩만 했음을 반성함...
  - 오류메시지

  ```
  Error starting ApplicationContext. To display the conditions report re-run your application with 'debug' enabled.
  2022-11-10 18:14:41.632 ERROR 19992 --- [  restartedMain] o.s.b.d.LoggingFailureAnalysisReporter   : 
  
  ***************************
  APPLICATION FAILED TO START
  ***************************
  
  Description:
  
  Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.
  
  Reason: Failed to determine a suitable driver class
  
  
  Action:
  
  Consider the following:
  	If you want an embedded database (H2, HSQL or Derby), please put it on the classpath.
  	If you have database settings to be loaded from a particular profile you may need to activate it (no profiles are currently active).
  
  
  Deprecated Gradle features were used in this build, making it incompatible with Gradle 8.0.
  
  You can use '--warning-mode all' to show the individual deprecation warnings and determine if they come from your own scripts or plugins.
  
  See https://docs.gradle.org/7.5.1/userguide/command_line_interface.html#sec:command_line_warnings
  
  BUILD SUCCESSFUL in 3s
  3 actionable tasks: 2 executed, 1 up-to-date
  오후 6:14:41: Execution finished ':WebtestApplication.main()'.
  ```

  - 해결 방법
    - spring 프로 젝트를 처음 시작할경우 생기는 오류로 application.properties파일의 수정이 필요
  - 다음과 같이 기존에 사용하던 내용을 복붙을 해보았지만 여전히 같은 오류가 생김
  - .hikari로 시작되는 부분이 문제인듯하지만 원인을 찾지 못했음
    - 기존에 수업때 했던 프로젝트를 전부 다 뒤져보고 실행해봤지만 그것들은 오류가 없음

  ```properties
  server.port=8000
  # DEVTOOLS (DevToolsProperties)
  spring.devtools.livereload.enabled=true
  # jsp 설정
  spring.mvc.view.prefix=/WEB-INF/views/
  spring.mvc.view.suffix=.jsp
  #mysql
  spring.datasource.hikari.driver-class-name=com.mysql.cj.jdbc.Driver
  spring.datasource.hikari.jdbc-url=jdbc:mysql://127.0.0.1:3306/webtest?useUnicode=true&characterEncoding=utf8
  spring.datasource.hikari.username=내가사용하는DB아이디
  spring.datasource.hikari.password=비밀번호
  # All DBMS
  spring.datasource.hikari.maximum-pool-size=10
  spring.datasource.hikari.minimum-idle=5
  spring.datasource.hikari.connection-timeout=5000
  ```

  - `#mysql` 파트를 다음과 같이 변경하니 서버가 잘 올라감

  ```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/스키마명?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC
  spring.datasource.username=아이디
  spring.datasource.password=비밀번호
  spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
  ```

  
