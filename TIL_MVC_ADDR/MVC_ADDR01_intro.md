# MVC 게시판에 주소록 추가 하기

### 개요

1. 기존의 모델1(jsp) 실습 중 게시판에 주소록 추가 활용
2. 모델2 방식으로 변경
   - 브라우저 요청 -> 컨트롤러  -> 뷰
3. 새로운 DynamicProject에서 구현
4. DAO, DTO 수정없이 사용
5. config.properties 파일 필요
   - web.xml 파일의 Servlet mapping 해야 사용가능
6. action파일 필요

### 프로젝트 기본셋팅

- mvc_addr/src/main/java
  - package
    - action: Action.java, NullAction.java
    - controller: Controller.java
    - model: AddrDAO.java, AddrDTO.java
    - utility: Utility.java, Contant.java, DBClose.java, DBOpen.java
- mvc_addr/src/main/webapp
  - index.jsp
  - folder(자동생성 제외)
    - images: img_chania.jpg, new.gif, re.jpg
    - template: template.jsp, top.jsp
    - view: nullCommand.jsp
    - WEB-INF: web.xml, config/config.properties, lib/mysql-connector-java.jar