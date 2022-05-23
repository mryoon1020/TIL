# JSP

### 액션태그

- jsp 문법, 코드를 줄일 수 있음

| 액션태그        | 내용                                                         |
| ----------- | ----------------------------------------------------------- |
| jsp:forward | jsp 페이지에서 다른 jsp 페이지로 요청을 이동 할 때 사용 |
| jsp:include | include 지시자 처럼 다른페이지를 현재 페이지에 포함(처리결과도 포함) |
| jsp:param | forward, include 되는 페이지에서 값을 전달하는 목적 |
| jsp:useBean | java bean을 jsp페이지에서 참조할 때 사용 |
| jsp:setProperty | java bean을 property에 값을 저장할 때 사용 |
| jsp:getProperty | java bean을 property의 값을 읽을 때 사용 |

  

1. jsp:forward

   - 다른 jsp 페이지로 요청을 이동 할 때 사용

     ex) forwardTag1.jsp 에서 id,password 입력 -> 보내기 클릭 -> forwardTag1_1.jsp 

     <jsp:forward page="forwardTag1_2.jsp" /> 실행 -> forwardTag1_2.jsp 에서 출력

     (cf. button의 기본타입은 submit임)

```
-----------------------------------------------------------------------------------------
forwardTag1.jsp
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
<h1>Forward Tag Example1</h1>

<form method=post action='forwardTag1_1.jsp'>

	아디디: <input name ='id'></p>
	패스워드: <input name='pwd' type='password'></p>
	<button>보내기</button>
</form>
</body>
</html>
-----------------------------------------------------------------------------------------
forwardTag1_1.jsp
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

<h1>Forward Tag Example1</h1>
Forward Tag의 포워딩 되기 전의 페이지입니다.
<jsp:forward page="forwardTag1_2.jsp" />

</body>
</html>
-----------------------------------------------------------------------------------------
forwardTag1_2.jsp
-----------------------------------------------------------------------------------------
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

String id = request.getParameter("id");
String pwd = request.getParameter("pwd");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Forward Tag Example1</h1>
당신의 아이디는<b><%=id%></b>이고<p/>
패스워드는 <b><%=pwd%></b> 입니다.

</body>
</html>
```

