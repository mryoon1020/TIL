# 게시판에 메모항목 추가하기

- 답글기능 개요

  - 기존에 작성된 글 하위 항목으로 표기
  - 작성된 grpno와 답글의 grpno를 같게 표기
  - 본글의 답변 들여쓰기, 답변의 답변은 들여쓰기 구현
  - 들여쓰기 수준 구현(최초답변:1, 그다음:2, 그다음:3)
  - 답변의 번호 표기, 답변의 들여쓰기 수준과 상관없이 갯수를 표기

- 순서

  - read.jsp
    - 답변 버튼 생성 및 클릭시 답변페이지로 이동하는 함수 설정
  - replyForm.jsp 생성
    - 게시판 답변 양식 만들기(create.jsp)와 동일
  - MemoDAO.java 메소드추가
    - readReply 
    - createReply
    - upAnsnum
  - createProc.jsp 생성

  ```
  -------------------------------------------------------------------------------
  read.jsp
  -------------------------------------------------------------------------------
  <%@ page contentType="text/html; charset=UTF-8" %> 
  <%@ page import="memo.*" %>
  <jsp:useBean id="dao" class="memo.MemoDAO"/>
  <%
  
  int memono = Integer.parseInt(request.getParameter("memono"));
  dao.upViewcnt(memono);
  MemoDTO dto = dao.read(memono);
  
  %>
   
  <!DOCTYPE html> 
  <html> 
  <head>
    <title>homepage</title>
    <meta charset="utf-8">
    
     <script>
    	function update(memono){ //수정페이지 이동
    		//alert(memono);
    		let url = 'updateForm.jsp?memono='+memono;
    		location.href = url;
    	}
    	
    	function del(memono){ //삭제페이지 이동
    		//alert(memono);
    		let url = 'deleteForm.jsp?memono='+memono;
    		location.href = url;
    	}
    	
    	function reply(memono){ //답변페이지 이동
    		//alert(memono);
    		let url = 'replyForm.jsp?memono='+memono;
    		location.href = url;
    	}
    	
    </script>
    
  </head>
  <body> 
  <jsp:include page="/menu/top.jsp"/>
  <div class="container">
  <h1 class="col-sm-offset-2 col-sm-10">메모조회</h1>
  
  <div class="panel panel-default">
  	<div class="panel-heading">작성자</div>
  	<div class="panel-body"><%=dto.getWname() %></div>
  	<div class="panel-heading">제목</div>
  	<div class="panel-body"><%=dto.getTitle() %></div>
  	<div class="panel-heading">내용</div>
  	<div class="panel-body" style='height:170px'><%=dto.getContent() %></div>
  	<div class="panel-heading">조회수</div>
  	<div class="panel-body"><%=dto.getViewcnt() %></div>
  	<div class="panel-heading">등록일</div>
  	<div class="panel-body"><%=dto.getWdate() %></div>
  </div>
  <div>
  	<button onclick="location.href='createForm.jsp'">등록</button>
  	<button onclick="update('<%=memono%>')">수정</button>
  	<button onclick="del('<%=memono%>')">삭제</button>
  	<button onclick="reply('<%=memono%>')">답변</button>	
  	<button onclick="location.href='list.jsp'">목록</button>
  </div>
  <br>
  
  </div>
  </body> 
  </html> 
  ```

  ```
  -------------------------------------------------------------------------------
  replyForm.jsp
  -------------------------------------------------------------------------------
  <%@ page contentType="text/html; charset=UTF-8" %> 
  <%@ page import="memo.*" %>
  <jsp:useBean id="dao" class="memo.MemoDAO"/>
  <%
  
  int memono = Integer.parseInt(request.getParameter("memono"));
  MemoDTO dto = dao.readReply(memono); //부모의 grpno, indent, ansnum
  
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
  <h1 class="col-sm-offset-2 col-sm-10">메모 답변</h1>
  <form class="form-horizontal" 
        action="replyProc.jsp"
        method="post"
        >
   
   <input type="hidden" name="memono" value="<%=memono %>">
    <input type="hidden" name="grpno" value="<%=dto.getGrpno() %>">
    <input type="hidden" name="indent" value="<%=dto.getIndent() %>">
    <input type="hidden" name="ansnum" value="<%=dto.getAnsnum() %>">
   
    <div class="form-group">
      <label class="control-label col-sm-2" for="wname">작성자</label>
      <div class="col-sm-6">
        <input type="text" name="wname" id="wname" class="form-control">
      </div>
    </div>
    
    <div class="form-group">
      <label class="control-label col-sm-2" for="title">제목</label>
      <div class="col-sm-6">
        <input type="text" name="title" id="title" class="form-control" value="<%=dto.getTitle() %>">
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
  	
  	public void upAnsnum(Map map) {
  		Connection con = DBOpen.getConnection();
  		PreparedStatement pstmt = null;
  		StringBuffer sql = new StringBuffer();
  		
  		sql.append(" update memo ");
  		sql.append(" set ");
  		sql.append(" ansnum = ansnum + 1 ");
  		sql.append(" where grpno = ? and ansnum > ? ");
  		
  		int grpno = (int)map.get("grpno");
  		int ansnum = (int)map.get("ansnum");
  		
  		try {
  			pstmt = con.prepareStatement(sql.toString());
  			pstmt.setInt(1,grpno);
  			pstmt.setInt(2,ansnum);
  			
  			pstmt.executeUpdate();
  			
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}finally {
  			DBClose.close(pstmt, con);
  		}
  		
  	}
  	
  	public boolean createReply(MemoDTO dto) {
  		boolean flag = false;
  		
  		Connection con = DBOpen.getConnection();
  		PreparedStatement pstmt = null;
  		StringBuffer sql = new StringBuffer();
  		sql.append(" insert into memo(wname, title, content, passwd, wdate, grpno, indent, ansnum) ");
  		sql.append(" values(?,?,?,?,sysdate(),?,?,?) ");
  				
  		try {
  			pstmt = con.prepareStatement(sql.toString());
  			pstmt.setString(1, dto.getWname());
  			pstmt.setString(2, dto.getTitle());
  			pstmt.setString(3, dto.getContent());
  			pstmt.setString(4, dto.getPasswd());
  			
  			pstmt.setInt(5, dto.getGrpno());
  			pstmt.setInt(6, dto.getIndent()+1);
  			pstmt.setInt(7, dto.getAnsnum()+1);
  			
  			int cnt = pstmt.executeUpdate();
  			
  			if(cnt>0) flag = true;
  			
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} finally {
  			DBClose.close(pstmt, con);
  		}
  		
  		return flag;
  	}
  	
  	public MemoDTO readReply(int memono) {
  		MemoDTO dto = null;
  		
  		Connection con = DBOpen.getConnection();
  		PreparedStatement pstmt = null;
  		ResultSet rs = null;
  		
  		StringBuffer sql = new StringBuffer();
  		sql.append(" SELECT memono, title, grpno, indent, ansnum ");
  		sql.append(" FROM memo   ");
  		sql.append(" WHERE memono = ?  ");
  		
  		try {
  			pstmt = con.prepareStatement(sql.toString());
  			
  			pstmt.setInt(1, memono);
  			rs = pstmt.executeQuery();
  			
  			if(rs.next()) {
  				dto = new MemoDTO();
  				dto.setMemono(rs.getInt("memono"));
  				dto.setTitle(rs.getString("title"));
  				dto.setGrpno(rs.getInt("grpno"));
  				dto.setIndent(rs.getInt("indent"));
  				dto.setAnsnum(rs.getInt("ansnum"));
  			}
  						
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} finally {
  			DBClose.close(rs, pstmt, con);
  		}
  		
  		return dto;
  	}
  }
  ```

  ```
  <%@ page language="java" contentType="text/html; charset=UTF-8"
      pageEncoding="UTF-8" import="memo.*, java.util.*" %>
  
  <% request.setCharacterEncoding("utf-8"); %>
  
  <jsp:useBean id="dao" class="memo.MemoDAO"/>
  <jsp:useBean id="dto" class="memo.MemoDTO"/>
  <jsp:setProperty name="dto" property="*"/>
  
  <%
  
  Map map = new HashMap();
  
  map.put("grpno", dto.getGrpno());
  map.put("ansnum", dto.getAnsnum());
  dao.upAnsnum(map);
  boolean flag = dao.createReply(dto);
  
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
  	out.print("답변성공");
  
  }else{
  	out.print("답변 실패");
  }
  
  %>
  
  </div>
  
  <button class ='btn' onclick="location.href='createForm.jsp'">다시등록</button>
  <button class ='btn' onclick="location.href='list.jsp'">목록</button>
  
  </div>
  </body>
  </html>
  ```