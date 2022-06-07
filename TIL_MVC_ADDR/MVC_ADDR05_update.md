# MVC 게시판에 주소록 추가하기

### Update

- 순서:
  1. list.jsp 구동 후 등록 클릭 => 주소창 명령어확인
  2. config.properties 파일 수정
  3. UpdateAction.java파일 생성
  4. updateForm.jsp 파일 카피 및 수정
  5. UpdateAction.java 수정
  6. config.properties 파일 수정
  7. UpdateProcAction.java 파일 생성 및 수정
  8. updateProc.jsp 파일 카피 및 수정
  9. 실행 및 오류검토

- config.properties

```properties
# command = Action class Mapping List
/addr/list.do=action.ListAction
/addr/create.do=action.CreateAction
/addr/createProc.do=action.CreateProc
/addr/read.do=action.ReadAction
/addr/updateForm.do=action.UpdateAction
/addr/updateProc.do=action.UpdateProcAction
```

- UpdateAction.java

```
public class UpdateAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		int addressnum = Integer.parseInt(request.getParameter("addressnum"));
		AddrDAO dao = new AddrDAO();
		AddrDTO dto = dao.read(addressnum);
		
		request.setAttribute("dto", dto);
		
		return "/view/updateForm.jsp";
	}

}
```

- UpdateProcAction.java

```
public class UpdateProcAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		AddrDAO dao = new AddrDAO();
		AddrDTO dto = new AddrDTO();
		
		dto.setName(request.getParameter("name"));
		dto.setHandphone(request.getParameter("handphone"));
		dto.setZipcode(request.getParameter("zipcode"));
		dto.setAddress(request.getParameter("address"));
		dto.setAddress2(request.getParameter("address2"));
		dto.setAddressnum(Integer.parseInt(request.getParameter("addressnum")));
		
//		Map map = new HashMap();
//		map.put("addressnum", dto.getAddressnum());
		
		boolean flag = dao.update(dto);
		
		request.setAttribute("flag", flag);
		
		return "/view/updateProc.jsp";
	}

}
```

- updateProc.jsp

```
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
				out.print("수정 성공");
			}else{
				out.print("수정 실패");
			}
			
	%>
	</div>
	

	<button class='btn' onclick="location.href='createForm.do'">다시등록</button>
	<button class='btn' onclick="location.href='list.do'">목록으로</button>
</div>

</body>
</html>
```

