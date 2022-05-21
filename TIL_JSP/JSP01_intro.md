# JSP

### 소개

1. 백앤드(back-end) 부분
2. 서블릿의 단점을 보완하여 JAVA 에서 만든 web관련 언어

3. 모든 운영체제 지원
4. JAVA기반
5. Servlet Container(AP서버)를 설치해야 실행가능

   6. http 프로토콜을 이용한 요청과 응답의 서버환경

   - Tomcat Server 기준
   - Tomcat Server에는 Web Server와 Servlet Container가 포함되어있음
   - Servlet Container에는 JSP , JAVA Servlet , class, Byte code , JRE Load 이있음
   - 요청과 응답과정
     - 클라이언트(브라우저)에서 요청(request) -> web server로 들어감
       - 이과정에서 HTML 요청일경우 브라우저 내에 컴파일 이 내장되어있으므로 바로 출력함
     - JSP 요청의 경우(기본언어가 JAVA)
       - JAVA Servelet 형태로 변환을 통해 class, Byte code로 컴파일 됨
       - 컴파일 이후 JRELoad를 통해 DB(Oracle,MySQL,DB2 etc.)에 접근하여 인터프리터 방식으로 읽어 옴
       - 출력된 결과를 web Server를 통해 다시 응답(response)함, 이때 HTML로 변환하여 응답하게 됨
     - Servlet 요청의 경우
       - JAVA언어 컴파일 과정없이 바로 JRE Load 과정으로 들어감,Servelet 자체가 class 형태이기 때문
       - 이후 과정의 JSP 요청과 동일함
