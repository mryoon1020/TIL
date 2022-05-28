# 게시판에 메모항목 추가하기

- read

  - list.jsp에서 제목을 클릭했을때 해당하는 데이터를 읽어오는 기능
  - 양식 하단에 등록, 수정, 삭제, 답변, 목록 버튼 생성
  - MemoDAO.java에 read 메소드 추가
  - 조회수 표시(upViewcnt 메소드 추가)
  - 각버튼에 해당하는 기능 구현

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
  MemoDAO.java
  -------------------------------------------------------------------------------
  
  public class MemoDAO {
  	
  	public void upViewcnt(int memono) {
  		Connection con = DBOpen.getConnection();
  		PreparedStatement pstmt = null;
  		
  		StringBuffer sql = new StringBuffer();
  		
  		sql.append(" UPDATE memo  ");
  		sql.append(" SET viewcnt = viewcnt + 1  ");
  		sql.append(" WHERE memono= ?  ");
  		
  		try {
  			pstmt = con.prepareStatement(sql.toString());
  			
  			pstmt.setInt(1, memono);
  			
  			pstmt.executeUpdate();
  			
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} finally {
  			DBClose.close(pstmt, con);
  		}
  		
  	}
  	
  	public MemoDTO read(int memono) {
  		
  		MemoDTO dto = null;
  		Connection con = DBOpen.getConnection();
  		PreparedStatement pstmt = null;
  		ResultSet rs = null;
  		
  		StringBuffer sql = new StringBuffer();
  		
  		sql.append(" select memono,wname,title,viewcnt,wdate, content from memo ");
  		sql.append(" where memono = ? ");
  		
  		try {
  			pstmt = con.prepareStatement(sql.toString());
  			
  			pstmt.setInt(1, memono);
  			
  			rs= pstmt.executeQuery();
  			
  			if(rs.next()) {
  				dto = new MemoDTO();
  				dto.setMemono(rs.getInt("memono"));
  				dto.setWname(rs.getString("wname"));
  				dto.setTitle(rs.getString("title"));
  				dto.setViewcnt(rs.getInt("viewcnt"));
  				dto.setWdate(rs.getString("wdate"));
  				dto.setContent(rs.getString("content"));
  
  			}
  			
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}finally {
  			DBClose.close(rs, pstmt, con);
  		}
  		
  		return dto;
  	}
  }
  ```

  