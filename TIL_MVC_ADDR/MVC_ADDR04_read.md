# MVC 게시판에 주소록 추가하기

### Read

순서:

1. list.jsp 구동 후 데이터 클릭 => 주소창 명령어확인
2. config.properties 파일 수정
3. ReadAction.java파일 생성
4. read.jsp 파일 카피 및 수정
5. ReadAction.java파일 수정
6. 실행 및 오류검토

- config.properties

```properties
# command = Action class Mapping List
/addr/list.do=action.ListAction
/addr/create.do=action.CreateAction
/addr/createProc.do=action.CreateProc
/addr/read.do=action.ReadAction
```

- read.jsp

```jsp
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="model.*"%>

<%
int addressnum = Integer.parseInt(request.getParameter("addressnum"));
AddrDTO dto = (AddrDTO)request.getAttribute("dto");//한건의 레코드조회
%>
<!DOCTYPE html>
<html>
<head>
<title>homepage</title>
<meta charset="utf-8">
<script>
	function update(addressnum) { //수정 페이지로 이동
		//alert(addressnum);
		let url = 'updateForm.do?addressnum=' + addressnum;
		location.href = url;
	}
	function del(addressnum){
  		if(confirm("정말 삭제하시겠습니까?")){
  			let url = "deleteProc.do?addressnum="+addressnum;
  			location.href=url;
  		} else{
  			out.print("실패");
  		}
  	}
</script>
</head>
<body>

	<div class="container">
		<h1>주소록 조회</h1>
		<div class="panel panel-default">
			<div class="panel-heading">이름</div>
			<div class="panel-body"><%=dto.getName()%></div>
			<div class="panel-heading">전화번호</div>
			<div class="panel-body"><%=dto.getHandphone()%></div>
			<div class="panel-heading">우편번호</div>
			<div class="panel-body"><%=dto.getZipcode()%></div>
			<div class="panel-heading">주소</div>
			<div class="panel-body"><%=dto.getAddress()%></div>
			<div class="panel-heading">상세주소</div>
			<div class="panel-body"><%=dto.getAddress2()%></div>
		</div>
		<div>
			<button onclick="location.href='createForm.do'">등록</button>
			<button onclick="update('<%=addressnum%>')">수정</button>
			<button onclick="del('<%=addressnum%>')">삭제</button>
			<button onclick="location.href='list.do'">목록</button>
		</div>
		<br>
	</div>
</body>
</html>
```

- ReadAction.java

```java
public class ReadAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		int addressnum = Integer.parseInt(request.getParameter("addressnum"));
		
		AddrDAO dao = new AddrDAO();
		
		AddrDTO dto = dao.read(addressnum);
		
		request.setAttribute("dto", dto);
		
		return "/view/read.jsp";
	}

}
```

