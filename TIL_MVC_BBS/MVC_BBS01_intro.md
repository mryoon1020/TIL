# MVC 활용

## 게시판 생성 및 MySQL연동

1. 기존의 모델1(jsp) 실습 중 게시판만들기 활용
2. 모델2 방식으로 변경
3. DAO, DTO 수정 없이 사용
4. web.xml 필요
5. config.properties 파일 필요
6. action파일 필요

### 프로젝트 기본셋팅

- mvc_bbs/src/main/java

  - package
    - action: Action.java, NullAction.java
    - controller: Controller.java
    - model: BbsDAO.java, BbsDTO.java
    - utility: Utility.java, Contant.java, DBClose.java, DBOpen.java

- mvc_addr/src/main/webapp

  - index.jsp

  - folder(자동생성 제외)

    - images: img_chania.jpg, new.gif, re.jpg

      => main page 이미지 및 답글, 최신글 이미지

    - template: template.jsp, top.jsp

      => controller.java에서 요청 처리 후 view에서 쓰게 될 기본 템플릿

    - view: nullCommand.jsp

      => 일치하는 명령어가 없을시 뜨게하는 에러메시지(404, 500방지)

    - WEB-INF: web.xml, config/config.properties, lib/mysql-connector-java.jar

      ​	web.xml => 서블릿 맵핑, *.do를 사용하기 위함

      ​	config.properties => *.do 명령 실행후 action을 처리하기 위한 페이지

      ​	mysql-connector-java.jar => MySql 연동 드라이버

