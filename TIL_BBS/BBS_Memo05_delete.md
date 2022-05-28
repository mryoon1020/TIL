# 게시판에 메모항목 추가

- delete
  - deleteForm.jsp 생성
  - 데이터 삭제시 초기입력 비밀번호가 일치해야 삭제
  - 비밀번호 안내창과 함께 삭제 경고문 구현
  - MemoDAO.java delete 메소드 추가
  - deleteProc.jsp 생성(비밀번호 일치, 삭제 성공,실패 여부 출력)

```
-------------------------------------------------------------------------------
deleteForm.jsp
-------------------------------------------------------------------------------
<%@ page contentType="text/html; charset=UTF-8" %> 
 
<!DOCTYPE html> 
<html> 
<head>
  <title>homepage</title>
  <meta charset="utf-8">
  <style>
  
  #red{
  
  	color: red;
  	
  }
  
  </style>
</head>
<body> 
<jsp:include page="/menu/top.jsp"/>
<div class="container">
<h1 class="col-sm-offset-2 col-sm-10">삭제</h1>
<form class="form-horizontal" 
      action="deleteProc.jsp"
      method="post"
      >
 
 <input type="hidden" name="memono" value="<%= request.getParameter("memono") %>" />
  <div class="form-group">
    <label class="control-label col-sm-2" for="passwd">비밀번호</label>
    <div class="col-sm-6">
      <input type="password" name="passwd" id="passwd" class="form-control">
    </div>
  </div>
  
  <p id="red" class=" col-sm-offset-2 col-sm-6">삭제하면 복구할 수 없습니다</p>
  
   <div class="form-group">
   <div class="col-sm-offset-2 col-sm-5">
    <button class="btn">삭제</button>
    <button type="reset" class="btn">취소</button>
   </div>
 </div>
</form>
</div>
</body> 
</html> 
```

```
-------------------------------------------------------------------------------
MemoDAO.java
-------------------------------------------------------------------------------
public class MemoDAO {
	public boolean delete(int memono) {
		
		boolean flag = false;
		
		Connection con = DBOpen.getConnection();
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" delete from memo ");
		sql.append(" where memono = ? ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setInt(1,memono);
			
			int cnt = pstmt.executeUpdate();
			if(cnt>0) flag=true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, con);
		}
		return flag;
	}
	
	public boolean passCheck(Map map) {
		boolean flag = false;
		
		Connection con = DBOpen.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int memono = (int)map.get("memono");
		String passwd = (String)map.get("passwd");
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select count(memono) as cnt ");
		sql.append("  from memo ");
		sql.append("  where memono = ? ");
		sql.append("  and passwd = ? ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setInt(1, memono);
			pstmt.setString(2, passwd);
			
			rs = pstmt.executeQuery();
			rs.next();
			int cnt = rs.getInt("cnt");
			
			if(cnt>0)flag = true;// 올바른 패스워드
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, con);
		}
		
		return flag;
	}
}
```

```
-------------------------------------------------------------------------------
deleteProc.jsp
-------------------------------------------------------------------------------
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ page import ="memo.*,java.util.*" %>

<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="dao" class="memo.MemoDAO"/>

<%

int memono = Integer.parseInt(request.getParameter("memono"));
String passwd = request.getParameter("passwd");

Map map = new HashMap();

map.put("memono", memono);
map.put("passwd", passwd);
boolean pflag = dao.passCheck(map);
boolean flag = false;

if(pflag){//올바른 비밀번호
    flag = dao.delete(memono);//수정
}
%>

<!DOCTYPE html> 
<html> 
<head>
  <title>homepage</title>
  <meta charset="utf-8">
</head>
<body> 
<jsp:include page="/menu/top.jsp"></jsp:include>
<div class="container">
<div class="well well-lg">

<% 

if(!pflag){
	out.print("잘못된 비밀번호 입니다.");
}else if(flag){
	out.print("글 삭제 성공입니다");
}else{
	out.print("글 삭제 실패입니다");
}

%>
</div>

<% if(!pflag){ %>
	<button class='btn' onclick="history.back()">다시시도</button>
<% } %>
<button class ='btn' onclick="location.href='createForm.jsp'">다시등록</button>
<button class ='btn' onclick="location.href='list.jsp'">목록</button>

</div>
</body> 
</html> 
```

