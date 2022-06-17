# Shopping application(website)제작

#### 초기 세팅

- 새로운 Spring project에서 시작
  - Name : shopping
  - Type : Gradle
  - Packaging : War
  - Java Version : 11
  - Language : Java
  - Group : com.study
  - Artifact: shopping
  -  Package : com.study.shop
  - 나머지 : defalult값
  - Next클릭후 : Spring Boot DevTools // Lombok /// JDBC API // MyBatisFramework//Spring Web 체크

- build.gradle 편집
  - 하기 내용 추가 후 gradle refresh

```gradle
implementation 'javax.servlet:jstl'  
implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'  
implementation 'org.springframework.boot:spring-boot-starter-validation' 
```

- application.properties 편집

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
spring.datasource.hikari.username=javauser
spring.datasource.hikari.password=1234
# All DBMS
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.connection-timeout=5000
```

- com.study.shop 패키지 설정

```java
ShoppingApplication.java
-----------------------------------------------------------------------------------------
package com.study.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.study.*"})
public class ShoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingApplication.class, args);
	}

}
```

- 관련폴더 생성(bbs에서 가져옴)

  - src/main/webapp/WEB-INF
    - WEB-INF폴더가 webapp폴더 밖에 있고 web.xml파일이 있다면 webapp폴더 속으로 넣고 web.xml파일은 반드시 삭제해줄  것
    - lib, sql, views,tlds
  - src/main/webapp/views
    - contents, member, template
  - src/main/resources
    - mybatis(package임)
    - templates(package지만 자동생성되어있음)

  - src/main/resources/static
    - css, images, js



