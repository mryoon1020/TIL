# Spring Boot 사용하여 게시판 주소록 추가하기

### 개요

1. 기존 MVC방식으로 제작된 주소록 활용
2. .do 를 전부 /명령어 형태로 변경
3. 어노테이션을 통해 프레임사용
4. web.xml 사용하지 않음
5. gradle사용(수정후 반드시 refresh 해주어야함)

### 프로젝트 기본셋팅

- spring_addr/src/main/java
  - package
    - com.study.addr
      - ServletInitializer.java
      - SpringAddrApplication.java
      - TilesConfiguration.java
    - com.study.controller
      - AddrController.java
    - com.study.model
      - AddrDAO.java
      - AddrDTO.java
    - com.study.utility
      - Utility.java
      - Contant.java
      - DBClose.java
      - DBOpen.java
- spring_addr/src/main/resources
  - package
    - templates
      - tiles.xml
  - folder
    - static/images
      - img_chania.jpg
  - file
    - application.properties
- spring_addr/src/main/webapp/WEB-INF
  - folder
    - lib
      - mysql-connector-java.jar
    - views(list, read, create, update, delete 뷰페이지 들어갈 예정)
    - views/template
      - template.jsp
      - top.jsp