# JSP

### 사용전 기본 셋팅

- 무료 서버 Tomcat 을 설치한다

  - 홈페이지: http://tomcat.apache.org/

  - Tomcat 10 zip파일 다운로드 및 설치(최신버전)

    - 최신버전이 항상 좋은 것만은 아님, 호환성 오류가 날 수도 있음
    - tomcat-10.0/tomcat-10.0 가 되지 않게 주의

  - tomcat-10.0/conf/server.xml 파일수정(한글처리를 위해 UTF-8로 변경해줘야함)

    ```
    <Connector port="8000" protocol="HTTP/1.1" 
            connectionTimeout="20000" 
            redirectPort="8443" URIEncoding="UTF-8"/> 
    ```

- STS셋팅

  - Help -> install New Software
  - Latest Eclipse Release - https://download.eclipse.org/releases/latest 선택 ->   Web, XML, Java EE and OSGi Enterprise Development 확장 -> Eclipse로 시작되는것들 전부 체크 -> 동의 및 설치

  - 설치후 재시작 -> Dynamic Web Project 생성 -> Window -> Preferences -> General -> Workspace -> Text file encoding -> other -> UTF-8 설정
  - Window -> Preferences -> General -> Web Browser -> use external web browser -> 원하는걸로 선택(본인은 Chrome으로 함)
  - Window -> Preferences -> Web -> JSP Files -> Encoding -> UTF-8선택
  - File -> New -> Other -> Server -> Tomcat v10.0 -> next -> finish



### JSP 태그

1.  <% %>   스크립트릿 : JAVA코드 삽입

2. <%= %>  표현식: 결과값출력

3. <%! %>   선언: 변수, 메소드 선언

4. <%@ %> 지시자: 페이지 속성 지정

5.  주석

   - // : 한줄주석

   - /*  */ : 여러줄 주석\

   - <%--  --%> :  JSP 주석

6. <<jsp:action>>   <</jsp:action>> 액션태그: 페이지 삽입, 공유, 빈 사용 등

