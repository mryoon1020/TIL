# 게시판에 주소록 항목 추가하기

- list

  - 페이징 기능 추가
  - 검색기능 추가
  - 리스트화면에서 삭제 기능 추가

  ```
  -----------------------------------------------------------------------------------------------------------------------------
  AddrDAO.java
  -----------------------------------------------------------------------------------------------------------------------------
  public class AddrDAO {
  
  public List<AddrDTO> list(Map map){
  		List<AddrDTO> list = new ArrayList<AddrDTO>();
  		Connection con = DBOpen.getConnection();
  		PreparedStatement pstmt = null;
  		ResultSet rs = null;
  		
  		String col = (String)map.get("col");
  		String word = (String)map.get("word");
  		int sno = (int)map.get("sno"); //레코드 시작위치
  		int eno = (int)map.get("eno"); //가져올 건수
  		
  		StringBuffer sql = new StringBuffer();
  		sql.append(" select addressnum, name,handphone,address  ");
  		sql.append(" from address ");
  		
  		if(word.trim().length() > 0) {
  			sql.append(" where "+ col +" like concat('%',?,'%')" );
  		}
  		
  		sql.append(" order by name desc  ");
  		sql.append(" limit ?, ? ");
  		
  		int i = 0;
  		
  		try {
  			pstmt = con.prepareStatement(sql.toString());
  			if(word.trim().length() > 0) {
  				pstmt.setString(++i, word);
  			}
  			pstmt.setInt(++i, sno);
  			pstmt.setInt(++i, eno);
  			
  			rs = pstmt.executeQuery();
  			
  			while(rs.next()) {
  				AddrDTO dto = new AddrDTO();
  				dto.setAddressnum(rs.getInt("addressnum"));
  				dto.setName(rs.getString("name"));
  				dto.setHandphone(rs.getString("handphone"));
  				dto.setAddress(rs.getString("address"));
  				
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
    
  	public boolean create(AddrDTO dto) {
  		boolean flag = false;
  		Connection con = DBOpen.getConnection();
  		PreparedStatement pstmt = null;
  		StringBuffer sql = new StringBuffer();
  		sql.append(" INSERT INTO address(name, handphone, address, zipcode, address2) ");
  		sql.append(" VALUES( ? , ? , ? , ? , ?) ");
  		
  		try {
  			pstmt = con.prepareStatement(sql.toString());
  			
  			pstmt.setString(1, dto.getName());
  			pstmt.setString(2, dto.getHandphone());
  			pstmt.setString(3, dto.getAddress());
  			pstmt.setString(4, dto.getZipcode());
  			pstmt.setString(5, dto.getAddress2());
  			
  			int cnt = pstmt.executeUpdate();
  			
  			if(cnt>0) flag = true;
  			
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} finally {
  			DBClose.close(pstmt, con);		}
  		
  		return flag;
  
  	}
  	
  		
  }
  ```

  ```
  -----------------------------------------------------------------------------------------------------------------------------
  list.jsp
  -----------------------------------------------------------------------------------------------------------------------------
  <%@ page contentType="text/html; charset=UTF-8" %> 
  <%@ page import="java.util.*, addr.AddrDTO, utility.*" %>
  <jsp:useBean id = "dao" class="addr.AddrDAO" />
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
  
  List<AddrDTO> list = dao.list(map); 
  int total = dao.total(map);
  String paging = Utility.paging(total, nowPage, recordPerPage, col, word);
  
  %>
  <!DOCTYPE html> 
  <html> 
  <head>
    <title>homepage</title>
    <meta charset="utf-8">
    <script>
    	function del(addressnum){
    		if(confirm("정말 삭제하시겠습니까?")){
    			let url = "deleteProc.jsp?addressnum="+addressnum;
    			location.href=url;
    		} else{
    			out.print("실패");
    		}
    	}
    	function read(addressnum){
    		let url = 'read.jsp?addressnum='+addressnum;
    		location.href = url;
    	}
    
    </script>
  </head>
  <body> 
  <jsp:include page="/menu/top.jsp"/>
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
  <form action="list.jsp" class='form-inline'>
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
  <button class='btn btn-default' type='button' onclick="location.href='createForm.jsp'">등록</button>
  </form>
  <div><%=paging %></div>
  </div>
  
  </body> 
  </html> 
  ```

  