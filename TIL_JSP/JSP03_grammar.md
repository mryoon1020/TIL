# JSP

### <% %>(스크립트릿)

- <% 자바 코드 %>

```
ex)

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

String name="왕눈이";
int kuk = 90;
int eng = 95;
int tot =kuk +eng;
int avg = tot/2;

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>성적표</h1>
<div style= 'font-size:24px'></div>
...............................<br>
성명 : <% out.println(name); %><br>
국어 : <% out.println(kuk); %><br>
영어 : <% out.println(eng); %><br>
총점 : <%=tot %><br>
총점 : <%=avg %><br>

<% System.out.println(">>>>>>>> name:"+name); %>

</body>
</html>

```

### <%! %>선언문(권장되지 않음, 빈즈로 대체하여 사용함)

- <%! 메소드(함수)선언 %>

```
ex)

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%! public int tot(int kuk, int eng, int mat){
		return kuk+eng+mat;
	}

	public int avg(int tot){
		return tot/3;
	}

%>

<%

	String name="아로미";
	int kuk = 90;
	int eng = 85;
	int mat = 100;
	int tot = tot(kuk, eng, mat);
	int avg = avg(tot);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>성적표</h1>
<ul style = 'font-size:24px'>

	<li> 성명: <%=name %>
  	<li> 국어: <%=kuk %>
  	<li> 영어: <%=eng %>
  	<li> 수학: <%=mat %>
  	<li> 총점: <%=tot %>
 	<li> 평균: <%=avg %>

</ul>

</body>
</html>
```
