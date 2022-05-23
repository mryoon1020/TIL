# JSP

### 표현식

-  단순 출력기능
  - <% System.out.print(hap(10, 20)); %> 콘솔출력 
  - <% out.print(hap(10, 20)); %> JSP 출력(브라우저)
  - <%=hap(10, 20)%> JSP 출력(브라우저), ; 사용 불가, 값 1개만 출력



### 주석

- 주석, <% %>안에서 사용

  - <% %>안에서 사용

  - // : 한줄주석
  - /* */ : 여러줄 주석
  - -- --: JSP 주석

```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- 선언문 --%>
<%! String declaration = "Declaration";

	public String decMethod(){
		return declaration;
	}

%>

<%!

	public int hap(int kuk, int eng){
		return kuk+eng;
}

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Script Example</h1>

<%

String scriptlet= "Scriptlet";
String comment = "Comment";

out.print("내장객체를 이용한 출력:"+declaration+"</p>");


%>

	선언문의 출력1:<%=declaration %></p>
	선언문의 출력2:<%=decMethod() %></p>
	스크립트의 출력:<%=scriptlet %></p>
	<!-- JSP주석부분 -->
	<!-- JSP 주석1: <%=comment %> -->
	<p/>
	<%-- JSP 주석2: <%=comment%> --%>
	<%
	
	/* 주석(여러줄 주석)*/
	//한줄 주석
	
	%>

	표현식1 콘솔출력: <% System.out.println(hap(10,20));%></p>
	표현식2 브라우저(JSP)출력: <% out.print(hap(10,20)); %></p>
	표현식3 브라우저(JSP)출력: <%= hap(10,20)%></p>
</body>
</html>
```