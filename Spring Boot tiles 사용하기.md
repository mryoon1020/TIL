# Spring Boot "tiles" 사용하기

### 개념

- 웹페이지의 반복적으로 사용 되는 부분을 반복된 코드 사용없이 관리 할수 있게해주는 프레임워크

  ex) 상단 네비게이션바, 하단부 회사 정보 등

### 사용법

> build.gradle
>
> - 하기코드 추가 및 Gradle -> Refresh Gradle Project

```gradle
// https://mvnrepository.com/artifact/org.apache.tiles/tiles-jsp
implementation group: 'org.apache.tiles', name: 'tiles-jsp', version: '3.0.8'
```

> TilesConfiguration.java
>
> - 사용할 xml파일을 등록해주는 역할
> - ServletInitializer.java, 프로젝트명Application.java 가 있는 곳에 생성해주면 됨

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
 
@Configuration
public class TilesConfiguration {
  @Bean
  public TilesConfigurer tilesConfigurer() {
      final TilesConfigurer configurer = new TilesConfigurer();
      //해당 경로에 tiles.xml 파일을 넣음
      configurer.setDefinitions(new String[]{"classpath:/tiles/tiles.xml"});
      configurer.setCheckRefresh(true);
      return configurer;
  }
 
  @Bean
  public TilesViewResolver tilesViewResolver() {
      final TilesViewResolver tilesViewResolver = new TilesViewResolver();
      tilesViewResolver.setViewClass(TilesView.class);
      return tilesViewResolver;
  }
}
```

> tiles.xml
>
> - src/main/resources/ + TilesConfiguration.java에 등록된 주소를 넣어주면 됨
> - xml파일도 마찬가지로 TilesConfiguration.java에 등록된 이름으로 생성해주면 됨
> - 최초 name을 main으로 설정할경우 extends를 사용해서 밑으로 덧 붙일수 있음
> - template 파일에서 insertAttribute 된 name중 한개라도 빠지게 되면 오류가 남
> - 같은 패키지 내의 다른 tiles.xml파일에서 main을 썼다면 중복해서 사용할 필요없음(단, 최초1회는 사용해주어야함)

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE tiles-definitions PUBLIC
       "-//Apache Software Foundation//DTD Tiles Configuration 3.0//EN"
       "http://tiles.apache.org/dtds/tiles-config_3_0.dtd">
 
<tiles-definitions>
<!--
  <definition name="호출 할 명령어(ex> /home, /bbs/list)"
    template="/WEB-INF/views/템플릿으로 사용할 jsp파일 위치">
    <put-attribute name="tiles를 표시할 위치(ex> header, body 등 template에서 작성한 name을 입력하면 됨")
      value="/WEB-INF/views/jsp뷰페이지 위치 및 파일이름입력 하면 됨" />
  </definition>
-->  
    <!-- main -->
  <definition name="main"
    template="/WEB-INF/views/template/template.jsp">
    <put-attribute name="header"
      value="/WEB-INF/views/template/top.jsp" />
  </definition>
    
  <definition name="/home" extends="main">
    <put-attribute name="title" value="기본페이지"></put-attribute>
    <put-attribute name="body"
      value="/WEB-INF/views/index.jsp" />
  </definition>
 
</tiles-definitions>
```

> template.jsp
>
> - 뷰페이지의 표시 위치를 설정해주는 파일
> - taglib를 반드시 사용해야함
> - tiles:getAsString : 뷰페이지 대신 text value값을 채워줌

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title"/></title>
</head>
<body>
<tiles:insertAttribute name="header"/>

<tiles:insertAttribute name="body"/>
</body>
</html>
```

> Controller.java
>
> - controller에서는 extends에 해당하는 뷰페이지를 xml에 등록한 명령어를 리턴해주면 됨

```java
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
 
@Controller
public class BbsController {
  
  @GetMapping("/")
  public String home() {
    return "/home";
  }
}
```

