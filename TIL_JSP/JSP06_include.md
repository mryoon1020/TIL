# JSP

### <%@ %> 지시자(Directive)

2. including

   - 여러 JSP 파일의 공통 내용을 파일로 저장하여 사용

   - including을 사용했을 때 처리 결과가 출력이 아닌 소스가 하나의 파일로 합쳐짐

     ex) a.jsp + b.jsp = c.jsp(a소스+b소스)

```
-----------------------------------------------------------------------------------------
a.jsp
-----------------------------------------------------------------------------------------
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
include 지시자의 Top부분입니다.
<hr>
</body>
</html>
-----------------------------------------------------------------------------------------
b.jsp
-----------------------------------------------------------------------------------------
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.Date" %>
    
<% Date date = new Date(); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<hr>
include 지시자의 Bottom부분입니다.</P>
<%= date.toLocaleString() %>
</body>
</html>
-----------------------------------------------------------------------------------------
c.jsp
-----------------------------------------------------------------------------------------
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Directive Example</h1>\
<%@ include file = "top.jsp"%>
include지시자의 Body 부분입니다.
<%@ include file = "bottom.jsp"%>
</body>
</html>
```



