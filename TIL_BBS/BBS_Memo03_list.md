# 게시판에 메모항목 추가하기

- list
  - MemoDAO.java에 list메소드 추가
  - list.jsp생성 및 저장되어있는 DB 출력
  - list.jsp 에 검색기능 추가
    - 게시판 만들때 Utility.java 에 생성한 메소드이용
    - MemoDAO.java의 list메소드에 검색기능 구현
    - list.jsp에 스크립트릿 이용하여 검색기능 추가
  - list.jsp paging기능 추가
    - 게시판 만들때 Utility.java 에 생성한 메소드이용
    - MemoDAO.java에 total 메소드 추가
    - list.jsp에 스크립트릿 이용하여 paging기능 추가

```
-------------------------------------------------------------------------------
MemoDAO.java
-------------------------------------------------------------------------------
public class MemoDAO {

	public int total(Map map) {
		int total = 0;
		Connection con = DBOpen.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String col = (String)map.get("col");
		String word = (String)map.get("word");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from memo ");
		
		if(word.trim().length() > 0) {
			sql.append(" where "+ col +" like concat('%',?,'%')" );
		}
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			
			if(word.trim().length() > 0) {
				pstmt.setString(1, word);
			}
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, con);
		}
		
		return total;
	}
	
	public List<MemoDTO> list(Map map){
		
		List<MemoDTO> list = new ArrayList<MemoDTO>();
		Connection con = DBOpen.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String col = (String)map.get("col");
		String word = (String)map.get("word");
		int sno = (int)map.get("sno"); //레코드 시작위치
		int eno = (int)map.get("eno"); //가져올 건수
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select memono, wname, title, wdate, grpno, indent, ansnum ");
		sql.append(" from memo ");
		
		if(word.trim().length() >0 && col.equals("title_content")) {
			sql.append(" where title like concat('%',?,'%') ");
			sql.append(" or content like concat('%',?,'%') ");
		}else if(word.trim().length() > 0) {
			sql.append(" where "+ col +" like concat('%',?,'%')" );
		}
		
		sql.append(" order by grpno desc, ansnum  ");
		sql.append(" limit ?, ? ");
		
		int i = 0;
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			if(word.trim().length() >0 && col.equals("title_content")) {
				pstmt.setString(++i, word);
				pstmt.setString(++i, word);
			}else if(word.trim().length() > 0) {
				pstmt.setString(++i, word);
			}
			
			pstmt.setInt(++i, sno);
			pstmt.setInt(++i, eno);
			
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				MemoDTO dto = new MemoDTO();
				dto.setMemono(rs.getInt("memono"));
				dto.setWname(rs.getString("wname"));
				dto.setTitle(rs.getString("title"));
				dto.setWdate(rs.getString("wdate"));
				dto.setGrpno(rs.getInt("grpno"));
				dto.setIndent(rs.getInt("indent"));
				dto.setAnsnum(rs.getInt("ansnum"));
				
				list.add(dto);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, con);
			
		}
		
		return list;
			
	}
}
```

```
-------------------------------------------------------------------------------
list.jsp
-------------------------------------------------------------------------------
<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ page import="java.util.*, memo.MemoDTO, utility.*" %>
<jsp:useBean id="dao" class="memo.MemoDAO"/>

<% 

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

Map map = new HashMap();
map.put("col",col);
map.put("word",word);
map.put("sno",sno);
map.put("eno",eno);

List<MemoDTO> list = dao.list(map);
int total = dao.total(map);
String paging = Utility.paging(total, nowPage, recordPerPage, col, word);
 %>
<!DOCTYPE html> 
<html> 
<head>
  <title>homepage</title>
  <meta charset="utf-8">
  <script type="text/javascript">
  	function read(memono){
  	   let url = 'read.jsp?memono='+memono;
  	   location.href = url;
  	}
  </script>
</head>
<body> 
<jsp:include page="/menu/top.jsp"/>
<div class="container">
<h1 class="col-sm-offset-2 col-sm-10">메모</h1>

<form action="list.jsp" class='form-inline'>
<div class='form-group'>
 <select class='form-control' name='col'>
 	<option value="wname" <%if(col.equals("wname")) out.print("selected");%>>성명</option>
 	<option value="title" <%if(col.equals("title")) out.print("selected");%>>제목</option>
 	<option value="content" <%if(col.equals("content")) out.print("selected");%> >내용</option>
 	<option value="title_content" <%if(col.equals("title_content")) out.print("selected");%>>제목+내용</option>
 	<option value="total" <%if(col.equals("total")) out.print("selected");%>>전체출력</option>
 </select>
</div>
<div class="form-group">
	<input type='text' class='form-control' placeholder='Enter 검색어' name='word' value="<%=word%>">
</div>
<button class='btn btn-default'>검색</button>
<button class='btn btn-default' type='button' onclick="location.href='createForm.jsp'">등록</button>
</form>

<table class="table table-striped">
	<thead>
		<tr>
			<th>메모번호</th>
			<th>메모제목</th>
			<th>작성자</th>
			<th>등록날짜</th>
			<th>grpno</th>
			<th>indent</th>
			<th>ansnum</th>
			
		</tr>
	</thead>
	<tbody>
	<% if(list.size()==0){ %>
		<tr> <td colspan='7'>등록된 글이 없습니다.</td></tr>
		<%}else{
			for(int i=0;i<list.size();i++){
				MemoDTO dto= list.get(i);
		%>	
			<tr>
			<td><%=dto.getMemono() %></td>
			
			<td>
			
			<%			
			
			for(int j=0; j<dto.getIndent();j++){
				out.print("&nbsp;&nbsp");
			}
			
			if(dto.getIndent()>0)out.print("<img src='../images/re.jpg'>");
			%>
			
			<a href="javascript:read('<%=dto.getMemono() %>')"><%=dto.getTitle() %></a>
			
			<% if(Utility.compareDay(dto.getWdate())){ %>	
				
				<img src="../images/new.gif">
			
			<%} %>	
			
			</td>
				<td><%=dto.getWname() %></td>
				<td><%=dto.getWdate() %></td>
				<td><%=dto.getGrpno() %></td>
				<td><%=dto.getIndent() %></td>
				<td><%=dto.getAnsnum() %></td>	
			
			</tr>
		<% } //for end%>
	<% } //if end%>
	
	</tbody>
</table>
<div>
	<%=paging %>
</div>
</div>
</body> 
</html> 
```

