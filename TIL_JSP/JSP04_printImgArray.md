# JSP

### 브라우저에 이미지 출력하기

- request.getContextPath() 사용

  - 특정 JSP나 폴더 경로를 잡아줌(최상위 경로를 표시함)

    ex) http://localhost:8000/jsptest/jsp/ex3.jsp  ==> /jsp 리턴

  - <% %> 안에 사용함

- 방법1

```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% String root = request.getContextPath(); 

String[] images = {"tu01.jpg","tu02.jpg","tu03.jpg","tu04.jpg",
		"tu05.jpg","tu06.jpg","tu08.jpg","tu09.jpg","tu10.jpg"
};




%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
out.print("<h1>튤립 축제</h1><br>");


for(int i=0; i<images.length;i++){
	out.print("<a href='"+root+"/tulip/" + images[i]+"'>");
	out.print("<img src='"+root+"/tulip/" + images[i]+"' width = '200px' height= '160px' border='0'>");
	out.print("</a>");
}

%>

</body>
</html>
```

- 방법2

```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% String root = request.getContextPath(); 

	String[] images = {"tu01.jpg", "tu02.jpg", "tu03.jpg", "tu04.jpg", "tu05.jpg", "tu06.jpg", "tu07.jpg", "tu08.jpg",
		    "tu09.jpg", "tu10.jpg"};

%>

    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

img{
width:200px;
height:160px;
birder:0;
}

</style>
</head>
<body>

<h1>튤립 축제</h1><br>

<% for(int i=0; i< images.length;i++){ %>

	<a href='<%=root%>/tulip/<%=images[i]%>'>
	<img src='<%=root%>/tulip/<%=images[i]%>'></a>
	
<% } %>

</body>
</html>
```