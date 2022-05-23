# JSP

### <%@ %> 지시자(Directive)

- JSP페이지가 실행될때 JSP 컨테이너에 알리는 역할
- page, include, taglib 3종류가있음



1. page

   - JSP 컨테이너에게 해당페이지를 어떻게 처리할 것인가에 대한 페이지 정보를 알려줌

     - info: 페이지 설명,  JSP페이지에 제목 붙이는것과 같음

     - language: JSP페이지의 스크립트 언어 지정, 기본값은 JAVA

     - contentType: JSP의 출력 형식 지정

       ex) <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

     - import: 자바에서 import와 같음, 중복사용가능

       ex) <%@ page import="java.util.Date" %> 

   ```
   ex> import 연습(java.util.Date//java.text.DecimalFormat)
   
   <%@ page language="java" contentType="text/html; charset=UTF-8"
       pageEncoding="UTF-8"%>
       
   <%@ page import= "java.util.Date" %>
   <%@ page import= "java.text.DecimalFormat" %>
   
   <%! public String comma(long val){
   	
   	DecimalFormat df = new DecimalFormat("W ###,###,### 원");
   	String str = df.format(val); //지정된 형식으로 val를 가져옴
   	return str;
   		
   }
   	%>
   
   
   <!DOCTYPE html>
   <html>
   <head>
   <meta charset="UTF-8">
   <title>Insert title here</title>
   
   <style>
   
   #date{
   	font-size: 36 px;
   	color: #ffffff;
   	background-color: #FF3399;
   }
   
   #df{
   
   	font-size: 24px;
   	color:#ffffff;
   	background-color: #000055;
   
   }
   
   #no{
   
   	color: #ff0000;
   
   }
   
   #thx{
   	
   	color: #00ff00;
   
   }
   
   </style>
   
   </head>
   <body>
   
   <h1>현재 시간</h1>
   
   <div id="date">
   
   <%
   
   Date date = new Date();
   
   out.print(date);
   out.print("<br>");
   out.print(date.toLocaleString());
   //toLocaleString()은 현지 표기법에 맞는 문자열로 리턴해줌
   
   %>
   
   </div>
   
   <br>
   
   <h1>6월 급여 명세서</h1>
   
   <div id="df">
   본봉:<%= comma(1800000) %>
   <br>
   수당:<%= comma(200000) %>
   <br>
   세금:
   <span id="no">
   <%= comma(200000) %>
   </span>
   <br>
   실수령액:
   <%= comma(1800000) %>
   <br>
   <span id="thx">
   
   수고하셨습니다!
   
   </span>
   
   </div>
   
   </body>
   </html>
   ```

   