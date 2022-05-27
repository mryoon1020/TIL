# 게시판에 메모항목 추가하기

- create
  - createForm.jsp 생성후 주소 정보 입력양식 생성
  - MemoDAO.java 에 create 메소드 추가
  - createProc.jsp 생성후 메모 등록시 성공 실패여부 화면에 출력하는 html 작성

```
-------------------------------------------------------------------------------
createForm.jsp
-------------------------------------------------------------------------------
<%@ page contentType="text/html; charset=UTF-8" %> 
 
<!DOCTYPE html> 
<html> 
<head>
  <title>homepage</title>
  <meta charset="utf-8">
</head>
<body> 
<jsp:include page="/menu/top.jsp"/>
<div class="container">
<h1 class="col-sm-offset-2 col-sm-10">메모등록</h1>
<form class="form-horizontal" 
      action="createProc.jsp"
      method="post"
      >
 
 
 <div class="form-group">
    <label class="control-label col-sm-2" for="title">제목</label>
    <div class="col-sm-6">
      <input type="text" name="title" id="title" class="form-control">
    </div>
  </div>
 
  <div class="form-group">
    <label class="control-label col-sm-2" for="wname">작성자</label>
    <div class="col-sm-6">
      <input type="text" name="wname" id="wname" class="form-control">
    </div>
  </div>
  
  
  
  <div class="form-group">
    <label class="control-label col-sm-2" for="content">내용</label>
    <div class="col-sm-6">
    <textarea rows="5" cols="5" id="content" name="content" class="form-control"></textarea>
    </div>
  </div>
  
  <div class="form-group">
    <label class="control-label col-sm-2" for="title">비밀번호</label>
    <div class="col-sm-6">
      <input type="password" name="passwd" id="passwd" class="form-control">
    </div>
    </div>
  
   <div class="form-group">
   <div class="col-sm-offset-2 col-sm-5">
    <button class="btn">등록</button>
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

	public boolean create(MemoDTO dto) {
		boolean flag=false;
		
		Connection con = DBOpen.getConnection();
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append(" insert into memo (wname, title, content, wdate, grpno, passwd) ");
		sql.append(" values (?,?,?,sysdate(), ");
		sql.append(" (select ifnull(max(grpno),0) + 1 from memo m), ?) ");
		
		try {
			pstmt= con.prepareStatement(sql.toString());
			
			pstmt.setString(1, dto.getWname());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getPasswd());
			
			int cnt = pstmt.executeUpdate();
			if(cnt>0)flag=true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(pstmt, con);
		}
		
		return flag;
	}
	
}
```

```
-------------------------------------------------------------------------------
createProc.jsp
-------------------------------------------------------------------------------
<%@ page contentType="text/html; charset=UTF-8" %> 
<% request.setCharacterEncoding("utf-8"); %>
<%@ page import="memo.*" %>

<jsp:useBean id= "dto" class="memo.MemoDTO" />
<jsp:useBean id="dao" class="memo.MemoDAO" />

<jsp:setProperty name="dto" property="*"/>
 
 <%
 
 boolean flag = dao.create(dto);
 
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
<div class="well well-lg">
<%

if(flag){
	out.print("등록 성공");
}else{
	out.print("등록 실패");
}

%>
</div>

<button class ='btn' onclick="location.href='createForm.jsp'">다시등록</button>
<button class ='btn' onclick="location.href='list.jsp'">목록</button>

</div>
</body> 
</html> 
```

