# MVC 게시판에 주소록 추가하기

### Delete

- 순서:
  1. list.jsp 구동 후 삭제 클릭 => 주소창 명령어확인
  2. config.properties 파일 수정
  3. DeleteProcAction.java 파일 생성 및 수정
  4. deleteProc.jsp 파일 카피 및 수정
  5. 실행 및 오류검토
- config.properties

```properties
# command = Action class Mapping List
/addr/list.do=action.ListAction
/addr/create.do=action.CreateAction
/addr/createProc.do=action.CreateProc
/addr/read.do=action.ReadAction
/addr/updateForm.do=action.UpdateAction
/addr/updateProc.do=action.UpdateProcAction
/addr/deleteProc.do=action.DeleteProcAction
```

- DeleteProcAction.java

```java
public class DeleteProcAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		int addressnum = Integer.parseInt(request.getParameter("addressnum"));
		
		AddrDAO dao = new AddrDAO();
		
		boolean flag = dao.delete(addressnum);
		
		request.setAttribute("flag", flag);
		
		return "/view/deleteProc.jsp";
	}

}
```

- deleteProc.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<% 	

boolean flag = (boolean)request.getAttribute("flag");

%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="container">
	<div class="well well-lg">
	 <%

	 	if(flag){
	 		out.print("글 삭제 성공입니다.");
	 	}else{
	 		out.print("글 삭제 실패입니다.");
	 	}
	 %>
	</div>
	
	<% if(!flag) { %>
		<button class="btn" onclick="history.back()">다시시도</button>
	<% } %>
	
	<button class='btn' onclick="location.href='createForm.do'">다시등록</button>
	<button class='btn' onclick="location.href='list.do'">목록</button>
</div>

</body>
</html>
```