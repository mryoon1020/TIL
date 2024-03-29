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


- 2022-11-11

  - test project 이므로 tiles를 통해 뷰를나눌 필요가없어서 index를 url 없이 직접 호출하고 싶었음

    - `src/main/resources/static` 에 index.html을 넣어주면 아주 간단하게 해결이 됨

  - jsp사용을 위해서는 프로젝트 구조변경이 필요함

    - jsp없이 자바스크립트만으로 처리해보고 싶었지만 일단 궁극적 목표인 aws추가 할 프로젝트는 jsp여서 기존방식으로 처리하기로함

  - `main/webapp/WEB-INF/views` 폴더 추가

  - application.properties

    - 하기항목추가

    ```properties
    # jsp 설정
    spring.mvc.view.prefix=/WEB-INF/views/
    spring.mvc.view.suffix=.jsp
    ```

  - build.gradle

    - dependency 내부에 하기 항목추가
    - jsp는 별도의 템플릿엔진이므로 별도의 라이브러리를 추가해야함
    - jstl사용을 위해서 추가로 한줄 더 넣었으나 (jstl적힌 부분)굳이 필요하진 않음

    ```gradle
    	implementation "org.apache.tomcat.embed:tomcat-embed-jasper"
    	implementation 'javax.servlet:jstl'
    ```

  - controller 를 통해 index페이지 바로 호출되게끔 처리

    ```java
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;
    
    @Controller
    public class webTestControler {
    
    @GetMapping("/")
        public String home(){
        return "index";
    }
    ```

  - 우선 DB는 s3 적용전이므로 간단하게 짰음

  ```sql
  create database fileupload;
  
  use fileupload;
  
  create table filename(
  
  fileNo int not null primary key auto_increment,
  fileName varchar(100) not null
  
  );
  
  -- sample data
  insert into filename (fileName) values("aa.jpg");
  insert into filename (fileName) values("bb.jpg");
  insert into filename (fileName) values("cc.jpg");
  insert into filename (fileName) values("dd.jpg");
  ```

  - index.jsp 호출까지는 성공했으나 dto.fileNo에 데이터가 들어오진 않음

    ```jsp
                <input type="hidden" value="${dto.fileNo}">
                <input type="text" value="${dto.fileNo}">&nbsp;&nbsp;
                <input type="text" value="${dto.fileName}">
    ```

- 2022-11-12

  - dto.fileNo에 데이터가 들어오진 않았던 건
    - 생각보다 많은 문제가 있었음
    - index.jsp는 정말 index페이지로서 인식이 되었음을 확인(컨트롤러를 거치지 않는것으로 보임, log, System.out.prinln()등 전부 출력이 되지 않음)
    - 과거자료 및 인터넷 검색을 해보니 다음 두파일이 누락되었음

  ```java
  import javax.sql.DataSource;
   
  import org.apache.ibatis.session.SqlSessionFactory;
  import org.mybatis.spring.SqlSessionFactoryBean;
  import org.mybatis.spring.SqlSessionTemplate;
  import org.mybatis.spring.annotation.MapperScan;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.boot.context.properties.ConfigurationProperties;
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.annotation.Bean;
  import org.springframework.context.annotation.Configuration;
  import org.springframework.context.annotation.PropertySource;
   
  import com.zaxxer.hikari.HikariConfig;
  import com.zaxxer.hikari.HikariDataSource;
   
  @Configuration
  @PropertySource("classpath:/application.properties")  // 설정 파일 위치
  @MapperScan(basePackages= {"spring.test.*"})
  public class DatabaseConfiguration {
    @Autowired
    private ApplicationContext applicationContext;
    
    @Bean
    @ConfigurationProperties(prefix="spring.datasource.hikari") // 설정 파일의 접두사 선언 
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }
    
    @Bean
    public DataSource dataSource() throws Exception{
        DataSource dataSource = new HikariDataSource(hikariConfig());
        System.out.println(dataSource.toString());  // 정상적으로 연결 되었는지 해시코드로 확인
        return dataSource;
    }
    
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mybatis/**/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
    
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
  }
  ```

  ```java
  import org.springframework.boot.WebApplicationType;
  import org.springframework.boot.builder.SpringApplicationBuilder;
  import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
  import spring.test.webtest.WebtestApplication;
  
  public class ServletInitializer extends SpringBootServletInitializer {
  
  	@Override
  	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
  		return application.sources(WebtestApplication.class);
  	}
  
  }
  ```

  - 추가적으로 applicartion.properties 파일의 mysql부분 수정했더니 정상적으로 작동

  ```properties
  #mysql
  spring.datasource.hikari.driver-class-name=com.mysql.cj.jdbc.Driver
  spring.datasource.hikari.jdbc-url=jdbc:mysql://localhost:3306/fileupload?serverTimezone=Asia/Seoul&useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true
  spring.datasource.hikari.username=아이디
  spring.datasource.hikari.password=비밀번호
  spring.datasource.hikari.connection-test-query=SELECT NOW() FROM dual
  ```

  - 스프링 환경셋팅후 다음 파일을 통해 문제가 없는지 체크 할 수 있음

  ```java
  import org.junit.jupiter.api.Test;
  import org.mybatis.spring.SqlSessionTemplate;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.boot.test.context.SpringBootTest;
  
  @SpringBootTest
  class WebtestApplicationTests {
  
  	@Autowired
  	private SqlSessionTemplate sqlSession;
  	@Test
  	void contextLoads() {
  	}
  
  	@Test
  	public void testSqlSession() throws Exception {
  		System.out.println(sqlSession.toString());
  	}
  }
  ```

  - 상기와 같이 수정후 정상적으로 데이터를 읽는 것을 확인 하였음

- 2022-11-14

  - 정말 다양한 시행착오 끝에 upload기능 구현 성공하였음

  - 참조사이트

    - https://www.youtube.com/watch?v=NbOBIexAyjA
    - https://github.com/adityajoshi12/S3-springboot-example
    - 해당영상은 maven 기반으로 제작되었음
    - gradle의 의존성주입은 다음과 같이 작성하면됨
    - `implementation 'org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE'`

  - 에러들

    - **java.lang.IllegalStateException: There is no EC2 meta data available, because the application is not running in the EC2 environment. Region detection is only possible if the application is running on a EC2 instance**

      - application.properties 파일에 aws Region 관련 정보가 없음에서 나타나게되는 오류임
      - 본인은 region 정보가 있었음에도 오류가 나게 되어 살짝 다른 코드로 변경하였음
      - `cloud.aws.region.static=ap-northeast-2`를 추가하여 해결

    - 오류인듯 오류아닌 오류같은 로그들

      - 스프링 부트 구동시 생기며 서버가 올라가지 않음

      - 기능에 이상이 생겨서 나는 오류는 아님 

      - ```
        Error starting ApplicationContext. To display the conditions report re-run your application with 'debug' enabled.
        2022-11-14 18:33:17.875 ERROR 30484 --- [  restartedMain] o.s.boot.SpringApplication               : Application run failed
        
        org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'org.springframework.cloud.aws.core.env.ResourceIdResolver.BEAN_NAME': Invocation of init method failed;
        
        Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'stackResourceRegistryFactoryBean' defined in class path resource [org/springframework/cloud/aws/autoconfigure/context/ContextStackAutoConfiguration.class]: Unsatisfied dependency expressed through method 'stackResourceRegistryFactoryBean' parameter 1; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'autoDetectingStackNameProvider' defined in class path resource [org/springframework/cloud/aws/autoconfigure/context/ContextStackAutoConfiguration.class]: Bean instantiation via factory method failed; nested exception is org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.cloud.aws.core.env.stack.config.StackNameProvider]: Factory method 'autoDetectingStackNameProvider' threw exception; nested exception is java.lang.IllegalArgumentException: No valid instance id defined
        
        Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'autoDetectingStackNameProvider' defined in class path resource [org/springframework/cloud/aws/autoconfigure/context/ContextStackAutoConfiguration.class]: Bean instantiation via factory method failed; nested exception is org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.cloud.aws.core.env.stack.config.StackNameProvider]: Factory method 'autoDetectingStackNameProvider' threw exception; nested exception is java.lang.IllegalArgumentException: No valid instance id defined
        
        Caused by: org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.cloud.aws.core.env.stack.config.StackNameProvider]: Factory method 'autoDetectingStackNameProvider' threw exception; nested exception is java.lang.IllegalArgumentException: No valid instance id defined
        
        Caused by: java.lang.IllegalArgumentException: No valid instance id defined
        ```

      - 다음구문을 application.properties에 추가해주면 간단하게 해결됨

      - `cloud.aws.stack.auto=false`

    - JavaScript오류

      - **Cannot read properties of null (reading 'classList')**

      - 해당오류는 스크립트 로드 우선순위가 잘못되거나 로드가 되지 않았거나 스크립트가 로드될 당시 페이지에 아이디나 클래스에 해당하는 요소가 없는 경우에 남

      - 본인은 다음과 같이 작성하여 오류남

      - ```JS
        <script>
            
        const formData = new FormData();
        const fileField = document.querySelector('input[type="file"]');
        
        function addFile(){
        
            if(fileField.files[0] != null){
                formData.append('file',fileField.files[0]); //오류가 난부분
            }
        //--------------------중략------------------------------
            
         }//function end
        
        </script>
        ```

      - 해결

      - ```js
        <script>
        
        function addFile(){
        
         const formData = new FormData();
         const fileField = document.querySelector('input[type="file"]');
            
            if(fileField.files[0] != null){
                formData.append('file',fileField.files[0]); //오류가 난부분
            }
        //--------------------중략------------------------------
            
         }//function end
        
        </script>
        ```

    - fetch 비동기통신이 성공적으로 수행이 되었으나 404에러가 날경우

      - controller 주소 받는 부분에 `@Responsebody` 추가로 해결
      -  스프링에서 `@Responsebody` 없는 리턴값은 view의 이름으로 받아들인다고함
      - 비동기통신이므로 따로 이동할 뷰가 없음

- 2022-11-15
  - 업로드 기능이후 버켓 내부에있는 파일의 리스트를 보여주고 클릭시 다운로드 할수 있는기능을 추가 하고자 했음
  - 참조 자료 : https://kmhan.tistory.com/505
  - 현재 500에러 해결중 아직 원인이 파악되지 않음
  
- 2022-11-16


    - 골자는 같지만 다른 방법으로 다시 코딩하였음


    - 참조자료: https://www.techgeeknext.com/cloud/aws/amazon-s3-springboot-list-all-files-in-s3-bucket#google_vignette


    - 코드를 뜯어보며 aws 객체 사용기능은 같음을 알수있었음 추가적으로 list에 저장하여 리턴을 했음


    - list 형태로 데이터를 받는데 성공하였음


    - 비동기통신 요청으로 다시 응답하려면 json 형태로 변환해서 출력만 해주면 됨


    - 하지만 다운로드 기능도 필요하기 때문에 다운로드 링크를 같이 처리를 할 수 있게끔 반환하는 방법이 필요함 

- 2022-11-17

  - 다운로드는 파일명 출력시 a태그를 사용해서 /download/파일명으로 서버로 요청을 보내는 방식으로 처리
  - 생각보다 시간이 많이 걸렸음
  - 우선 li 태그를 파일 목록의 갯수만큼 자동 생성 해야함
    - 하기 코드에서 `'fileList'`는 ul 또는 ol의 id임

  ```js
  let fileList = document.getElementById('fileList');
  
              let li = document.createElement('li');
              
              fileList.appendChild(li)
  ```

  - 이후 자식 노드를 생성하고 추가
    - 텍스트 내용이 필요하다면 `document.createTextNode()` 를 사용하면됨
    - 원하는 태그의 하위 요소로 입력을 위해서는 `appendChild()` 를 통해 추가해주면됨
    - ul이나 ol에 li를 추가한후 a태그 추가시 자동으로 li태그 안으로 a태그가 들어가짐

  ```js
              let fileList = document.getElementById('fileList');
  
              let li = document.createElement('li');
              let downLoadLink = document.createElement('a');
  
              let linkText = document.createTextNode(result[i]);
  
              downLoadLink.href = "/download/"+result[i];
  			// a 태그에 주소를 추가해줌
  
              downLoadLink.appendChild(linkText);
  
              fileList.appendChild(li).appendChild(downLoadLink)
  
  ```

  - 같은 방법으로 삭제 버튼도 추가 해주었음
    - a태그에서는 `.href` 를 통해 주소를 추가해주었지만 button에서는 되지 않음
    - button tag는 `setAttribute("속성명", "값")` 을 통해서 추가해주어야함
      - 이 기능을 사용할시에 값에 해당하는 부분에는 ``${result[i]}``,  `location.href='/deleteFile/+${result[i]}'` 사용했을때 result[i] 부분에 값이 들어오지 않으므로 따로 변수를 선언해서 넣어 주어야함
      - 이부분 해결을 위해 정말 많은 시간이 걸렸음
    - ` deleteButton.setAttribute("onclick", `deleteFile(${result})`);`

  ```js
          for(let i = 0; i<result.length;i++){
  
              let fileList = document.getElementById('fileList');
  
              let li = document.createElement('li');
              let downLoadLink = document.createElement('a');
              let deleteButton = document.createElement('button');
  
              let linkText = document.createTextNode(result[i]);
              let buttonText = document.createTextNode("파일삭제");
  
              let deleteLink = "location.href=";
              deleteLink+="'/deleteFile/"+result[i]+"'";
  
              downLoadLink.href = "/download/"+result[i];
              deleteButton.setAttribute("onclick", deleteLink);
  
              downLoadLink.appendChild(linkText);
              deleteButton.appendChild(buttonText);
  
              fileList.appendChild(li).appendChild(downLoadLink)
              li.appendChild(deleteButton);
          }
  ```

  - 이제 정말 끝나간다. Spring 쪽에서 해당 url에 해당하는 기능만 매치해주면 된다

- 2022-11-15

  - 다운로드 기능 오류없이 완료

  - 삭제기능 405에러가 뜬다

    - 처음삭제 기능 어노테이션을 `@GetMapping` `@PostMapping` 두개 다 사용했으나 오류가남

      - 두경우 모두 하기 에러가 출력됨

        ```
        There was an unexpected error (type=Method Not Allowed, status=405).
        
        Request method 'GET' not supported
        ```

    - aws 콘솔창으로 확인했을 때 파일이 삭제 되었으므로 기능에 문제는 없음

    - `@RequestMapping(value="/deleteFile/{filename}", method = {RequestMethod.GET, RequestMethod.POST})` 로 다시 코딩하여 해결하였음

    - 바꿨을 때도 같은 에러가 발생하였음

      - 처음에는 비동기 통신 부분 특히 button에 onclick 이벤트 추가하는 setAttrubute 파트안에 result[i]를 매개 변수로 받는 함수를 넣으려고 햇음
        - 함수는 들어갈수 있지만 매개변수를 받을수가 없었음
      - 원인은 url을 통해 처리를 하였으나 돌아갈 뷰페이지가 없었음 따라서 `redirect:/` 를 통해 원래 페이지로 돌아가게 하여 완성하였음

- 해당 실습을 마치며

  - 생각했던 기간보다 매우 오래 걸렸다
  - 처음에는 DB에 파일리스트를 저장하고 출력하려고 하였으나 javascript도 같이 연습하기 위해 list에 저장해서 바로 출력하는 방법으로 코딩을 했다
  - 이번 연습용 프로젝트를 작성해보며 많은 공부가 되었다
  - 특히, 예제들을 참조하고 변형해서 내가 원하는 방향으로 흘러가게 하는 것은 정말 재밌는 것 같다
  - 이제 aws S3 관련 연습 프로젝트를 완성하였으니 기존에 했던 팀프로젝트에서 해당기능에 대해 리펙토링작업을 하여 완성도를 높여야 겠다
