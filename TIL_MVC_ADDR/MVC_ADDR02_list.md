# MVC게시판에 주소록 추가하기

### List

- 순서: 
  1. top.jsp:  .jsp => .do 변경 및 서버구동
  2. 주소정보 => 목록 클릭 => 주소창의 명령어 확인
  3. config.properties 파일 수정
  4. ListAction.java파일 생성
  5. list.jsp 파일 카피 및 수정
  6. ListAction.java 수정
  7. 실행 및 오류검토

- config.properties

```properties
# command = Action class Mapping List
/addr/list.do=action.ListAction
```

- list.jsp

```jsp
<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ page import="java.util.*, model.AddrDTO, utility.*" %>

<%

String col = (String) request.getAttribute("col");
String word = (String) request.getAttribute("word");
int nowPage =(int)request.getAttribute("nowPage");
List<AddrDTO> list = (List<AddrDTO>)request.getAttribute("list");
String paging = (String)request.getAttribute("paging");
%>

<!DOCTYPE html> 
<html> 
<head>
  <title>homepage</title>
  <meta charset="utf-8">
  <script>
  	function del(addressnum){
  		if(confirm("정말 삭제하시겠습니까?")){
  			let url = "deleteProc.do?addressnum="+addressnum;
  			location.href=url;
  		} else{
  			alert("실패");
  		}
  	}
  	function read(addressnum){
  		let url = 'read.do?addressnum='+addressnum;
  		location.href = url;
  	}
  
  </script>
</head>
<body> 

<div class="container">
<h2 class="col-sm-offset-2 col-sm-6">주소록</h1>

<table class="table table-striped">
	<thead>
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>주소</th>
			<th>삭제</th>
		</tr>
	</thead>
	<tbody>
<%  if(list.size()==0){ %>	
	<tr><td colspan='7'>등록된 글이 없습니다.</td></tr>
<%  } else {
		for(int i=0; i < list.size(); i++) { 
	   	AddrDTO dto = list.get(i);	
%>		<tr>
		
		<td><%=dto.getAddressnum() %></td>
		<td><a href="javascript:read('<%=dto.getAddressnum() %>')"><%=dto.getName() %></a></td>
		<td><%=dto.getHandphone() %></td>
		<td><%=dto.getAddress() %></td>
		<td><a href="javascript:del('<%=dto.getAddressnum() %>')">삭제</a></td>
		

		</tr>	
<% 		}			
	}
%></tbody>
</table>
<form action="list.do" class='form-inline'>
<div class='form-group'>
 <select class='form-control' name='col'>
 	<option value="name" <%if(col.equals("name")) out.print("selected");%>>이름</option>
 	<option value="handphone" <%if(col.equals("handphone")) out.print("selected");%>>핸드폰</option>
 	<option value="address" <%if(col.equals("address")) out.print("selected");%> >주소</option>
 	<option value="total" <%if(col.equals("total")) out.print("selected");%>>전체출력</option>
 </select>
</div>
<div class="form-group">
	<input type='text' class='form-control' placeholder='Enter 검색어' name='word' value="<%=word%>">
</div>
<button class='btn btn-default'>검색</button>
<button class='btn btn-default' type='button' onclick="location.href='create.do'">등록</button>
</form>
<div><%=paging %></div>
</div>

</body> 
</html> 
```

- ListAction.java

```java
public class ListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
	    //검색관련--------------
	    String col = Utility.checkNull(request.getParameter("col"));
	    String word = Utility.checkNull(request.getParameter("word"));

	    if(col.equals("total"))word = "";

	    //페이징 관련---------------
	    int nowPage = 1;
	    if(request.getParameter("nowPage") != null){
	        nowPage = Integer.parseInt(request.getParameter("nowPage"));
	    }

	    int recordPerPage = 5;

	    int sno = ((nowPage -1) * recordPerPage);
	    int eno = recordPerPage;

	    //1.model 사용
	    Map map = new HashMap();//sno,eno,col,word
	    map.put("sno", sno);
	    map.put("eno", eno);
	    map.put("col", col);
	    map.put("word", word);
	    AddrDAO dao = new AddrDAO();
	    
	    List<AddrDTO> list = dao.list(map);
	    int total = dao.total(map);
	    String paging = Utility.paging(total, nowPage, recordPerPage, col, word);
	    //2.request 저장 (view페이지에서 사용할 내용을 저장)
	    request.setAttribute("list", list);
	    request.setAttribute("paging", paging);
	    request.setAttribute("col", col);
	    request.setAttribute("word", word);
	    request.setAttribute("nowPage", nowPage);
	    
	    //3.view 선택(리턴)
	    return "/view/list.jsp";
	  
	}

}
```