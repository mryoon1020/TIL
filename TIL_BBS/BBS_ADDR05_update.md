# 게시판에 주소록 항목 추가하기

- update
  - 비밀번호 체크 기능 없이 업데이트

```
-------------------------------------------------------------------------------
updateForm.jsp
-------------------------------------------------------------------------------
<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ page import="addr.*" %>
<jsp:useBean id="dao" class="addr.AddrDAO" />
<%
	int addressnum = Integer.parseInt(request.getParameter("addressnum"));
	AddrDTO dto = dao.read(addressnum);
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
<h1 class="col-sm-offset-2 col-sm-10">주소지 수정</h1>
<form class="form-horizontal" 
      action="updateProc.jsp"
      method="post"
      >
 
  <input type="hidden" name="addressnum" value="<%=addressnum %>">
  
  <div class="form-group">
    <label class="control-label col-sm-2" for="name">update name</label>
    <div class="col-sm-6">
      <input type="text" name="name" id="name" class="form-control" value="<%=dto.getName()%>">
    </div>
  </div>
  
  <div class="form-group">
    <label class="control-label col-sm-2" for="handphone">update handphone</label>
    <div class="col-sm-6">
      <input type="text" name="handphone" id="handphone" class="form-control" value="<%=dto.getHandphone()%>">
    </div>
  </div>
  
  
  <div class="form-group">
    <label class="control-label col-sm-2" for="address">update address</label>
    <div class="col-sm-6">
    <input type="text" name="address" id="address" class="form-control" value="<%=dto.getAddress()%>">
    </div>
  </div>

  <div class="form-group">
    <label class="control-label col-sm-2" for="zipcode">update zipcode</label>
    <div class="col-sm-6">
    <input type="text" name="zipcode" id="zipcode" class="form-control" value="<%=dto.getZipcode()%>">
    </div>
  </div>
  
  <div class="form-group">
    <label class="control-label col-sm-2" for="address2">update address2</label>
    <div class="col-sm-6">
    <input type="text" name="address2" id="address2" class="form-control" value="<%=dto.getAddress2()%>">
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
AddrDAO.java
-------------------------------------------------------------------------------
public class AddrDAO {

	public boolean update(AddrDTO dto) {
	  boolean flag = false;
	  Connection con = DBOpen.getConnection();
	  PreparedStatement pstmt = null;
		
	  StringBuffer sql = new StringBuffer();
	  sql.append("   UPDATE address  	");
	  sql.append("   SET handphone= ?,	");
	  sql.append("  	 address= 	?,	");
	  sql.append("  	 zipcode =  ?,	");
	  sql.append("       address2 = ?	");
	  sql.append(" WHERE addressnum = ?	");
	  
	  try {
		pstmt = con.prepareStatement(sql.toString());
		pstmt.setString(1, dto.getHandphone());
		pstmt.setString(2, dto.getAddress());
		pstmt.setString(3, dto.getZipcode());
		pstmt.setString(4, dto.getAddress2());
		pstmt.setInt(5, dto.getAddressnum());
		
		int cnt = pstmt. executeUpdate();
		if (cnt > 0) flag = true;
		
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="addr.*, java.util.* "%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="dao" class="addr.AddrDAO" />
<jsp:useBean id="dto" class="addr.AddrDTO" />
<jsp:setProperty name="dto" property="*" />
<% 
	Map map = new HashMap();
	map.put("addressnum", dto.getAddressnum());

	boolean flag = false;
	flag = dao.update(dto);

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
				out.print("수정 성공");
			}else{
				out.print("수정 실패");
			}
			
	%>
	</div>
	

	<button class='btn' onclick="location.href='createForm.jsp'">다시등록</button>
	<button class='btn' onclick="location.href='list.jsp'">목록으로</button>
</div>

</body>
</html>
```

