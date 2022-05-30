# 게시판에 주소록 항목 추가하기

- read
  - 조회수가 없는 주소록 조회

```
-------------------------------------------------------------------------------
read.jsp
-------------------------------------------------------------------------------
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="addr.*"%>
<jsp:useBean id="dao" class="addr.AddrDAO" />
<%
int addressnum = Integer.parseInt(request.getParameter("addressnum"));
AddrDTO dto = dao.read(addressnum);//한건의 레코드조회
%>
<!DOCTYPE html>
<html>
<head>
<title>homepage</title>
<meta charset="utf-8">
<script>
	function update(addressnum) { //수정 페이지로 이동
		//alert(addressnum);
		let url = 'updateForm.jsp?addressnum=' + addressnum;
		location.href = url;
	}
	function del(addressnum) { //삭제 페이지로 이동
		//alert(addressnum);
		let url = 'deleteProc.jsp?addressnum=' + addressnum;
		location.href = url;
	}
</script>
</head>
<body>
	<jsp:include page="/menu/top.jsp" />
	<div class="container">
		<h1>주소록 조회</h1>
		<div class="panel panel-default">
			<div class="panel-heading">이름</div>
			<div class="panel-body"><%=dto.getName()%></div>
			<div class="panel-heading">전화번호</div>
			<div class="panel-body"><%=dto.getHandphone()%></div>
			<div class="panel-heading">우편번호</div>
			<div class="panel-body"><%=dto.getZipcode()%></div>
			<div class="panel-heading">주소</div>
			<div class="panel-body"><%=dto.getAddress()%></div>
			<div class="panel-heading">상세주소</div>
			<div class="panel-body"><%=dto.getAddress2()%></div>
		</div>
		<div>
			<button onclick="location.href='createForm.jsp'">등록</button>
			<button onclick="update('<%=addressnum%>')">수정</button>
			<button onclick="del('<%=addressnum%>')">삭제</button>
			<button onclick="location.href='list.jsp'">목록</button>
		</div>
		<br>
	</div>
</body>
</html>
```

```
-------------------------------------------------------------------------------
AddrDTO.java
-------------------------------------------------------------------------------
public class AddrDAO {

	public AddrDTO read(int addressnum) {
		AddrDTO dto = null;
		Connection con = DBOpen.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT addressnum, name, handphone, zipcode, address, address2  ");
		sql.append(" FROM address ");
		sql.append(" WHERE addressnum = ? ");

		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, addressnum);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto = new AddrDTO();
				dto.setAddressnum(rs.getInt("addressnum"));
				dto.setName(rs.getString("name"));
				dto.setHandphone(rs.getString("handphone"));
				dto.setZipcode(rs.getString("zipcode"));
				dto.setAddress(rs.getString("address"));
				dto.setAddress2(rs.getString("address2"));
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

