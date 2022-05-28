# 게시판에 메모항목 추가하기

- update
  - updateForm.jsp 생성
  - 데이터 수정시 초기 입력 비밀번호가 일치해야 수정 가능하게 구현
  - MemoDAO.java update, 비밀번호 확인 메소드 추가
  - updateProc.jsp 생성(비밀번호 일치, 수정 성공, 실패 여부 출력)

```
-------------------------------------------------------------------------------
updateForm.jsp
-------------------------------------------------------------------------------
<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ page import="memo.*"%>
<jsp:useBean id="dao" class="memo.MemoDAO"/>

<%
	int memono = Integer.parseInt(request.getParameter("memono"));
	MemoDTO dto = dao.read(memono); 
%>

<!DOCTYPE html> 
<html> 
<head>
  <title>homepage</title>
  <meta charset="utf-8">
</head>
<body> 
<jsp:include page="/menu/top.jsp"/>
<div class="container">
<h1 class="col-sm-offset-2 col-sm-10">메모 수정</h1>
<form class="form-horizontal" 
      action="updateProc.jsp"
      method="post"
      >
 <input type="hidden" name='memono' value="<%=memono %>">
  <div class="form-group">
    <label class="control-label col-sm-2" for="wname">작성자</label>
    <div class="col-sm-6">
      <input type="text" name="wname" id="wname" class="form-control" value="<%=dto.getWname()%>">
    </div>
  </div>
   <div class="form-group">
    <label class="control-label col-sm-2" for="title">제목</label>
    <div class="col-sm-6">
      <input type="text" name="title" id="title" class="form-control" value="<%=dto.getTitle()%>">
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-2" for="content">내용</label>
    <div class="col-sm-6">
    <textarea rows="5" cols="5" id="content" name="content" class="form-control"><%=dto.getContent() %></textarea>
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-2" for="passwd">비밀번호</label>
    <div class="col-sm-6">
      <input type="password" name="passwd" id="passwd" class="form-control">
    </div>
  </div>
  
   <div class="form-group">
   <div class="col-sm-offset-2 col-sm-5">
    <button class="btn">수정</button>
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
	
	public boolean update(MemoDTO dto) {
		boolean flag = false;
		
		Connection con = DBOpen.getConnection();
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("  update memo ");
		sql.append("  set  ");
		sql.append(" 	wname = ? ,  ");
		sql.append(" 	title = ? ,  ");
		sql.append("    content = ?  ");
		sql.append(" where memono = ? ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setString(1,dto.getWname());
			pstmt.setString(2,dto.getTitle());
			pstmt.setString(3,dto.getContent());
			pstmt.setInt(4, dto.getMemono());
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0)flag = true;
			
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
updateProc.jsp
-------------------------------------------------------------------------------
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ page import ="memo.*,java.util.*" %>

<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="dao" class="memo.MemoDAO"/>
<jsp:useBean id="dto" class="memo.MemoDTO"/>
<jsp:setProperty name="dto" property="*"/>

<%

Map map = new HashMap();

map.put("memono", dto.getMemono());
map.put("passwd", dto.getPasswd());
boolean pflag = dao.passCheck(map);
boolean flag = false;

if(pflag){//올바른 비밀번호
    flag = dao.update(dto);//수정
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
	out.print("글 수정 성공입니다");
}else{
	out.print("글 수정 실패입니다");
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

