# 게시판에 주소록 항목 추가하기

- delete
  - 비밀번호 체크 기능 없이 삭제
  - list에서 바로 삭제하는 기능
  - 삭제경고 문구 및 비밀번호 입력 없으므로 deleteForm.jsp 필요없음

```
-------------------------------------------------------------------------------
AddrDAO.java
-------------------------------------------------------------------------------
public class AddrDAO {
	
	public boolean delete(int addressnum) {
		boolean flag = false;
		Connection con = DBOpen.getConnection();
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from address ");
		sql.append(" where addressnum = ? ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, addressnum);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, con);
		}
		return flag;
	}
}
```

```
-------------------------------------------------------------------------------
deleteProc.jsp
-------------------------------------------------------------------------------
<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="addr.*,java.util.*"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="dao" class="addr.AddrDAO" />

<% 	
	int addressnum = Integer.parseInt(request.getParameter("addressnum"));
	
	Map map = new HashMap();
	map.put("addressnum",addressnum);


	boolean flag = false;

	flag = dao.delete(addressnum); //수정처리

%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/menu/top.jsp"></jsp:include>
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
	
	<button class='btn' onclick="location.href='createForm.jsp'">다시등록</button>
	<button class='btn' onclick="location.href='list.jsp'">목록</button>
</div>

</body>
</html>
```



