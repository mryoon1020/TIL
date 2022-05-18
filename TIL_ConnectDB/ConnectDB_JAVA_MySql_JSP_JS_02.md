# JAVA , MySql, JSP,JS  연동-02

1. 연동은 DAO -> suggest.jsp -> ajax.js -> client.jsp 순서로 된다

   - suggest.jsp에서 dao를 사용
   - ajax.js에서 suggest.jsp를 사용
   - client.jsp에서 suggest.jsp를 사용

2. 코딩

   - DAO.java

   ```
   import java.sql.Connection;
   import java.sql.PreparedStatement;
   import java.sql.ResultSet;
   import java.sql.SQLException;
   import java.util.ArrayList;
   import java.util.List;
   
   import utility.DBClose;
   import utility.DBOpen;
   
   public class SuggestDAO {
   	
   	public int getCount(String keyword) {
   		int count = 0;
   		Connection con = DBOpen.getConnection();
   		PreparedStatement pstmt = null; //전송객체 선언, 생성아님(sql문을 -> mySQL server)역할
   		ResultSet rs = null; //MySQL server -> 결과를 받아옴
   		
   		//int cnt = pstmt.executeUpdate();//결과를 숫자로 받음. 몇개인지 ex) insert한 갯수 등
   		
   		StringBuffer sql = new StringBuffer();
   		
   	    sql.append(" SELECT COUNT(sqlquery) as cnt "); //sqlquery: 컬럼명
           sql.append(" FROM suggest ");
           sql.append(" WHERE sqlquery LIKE '"+keyword+"%'");
   		
   			
   		try {
   			pstmt = con.prepareStatement(sql.toString()); //전송객체 생성, 연결객체와 SQL문이 만들어져 있어야됨 
   			rs = pstmt.executeQuery();//sql문 전송, 결과를 받을 result객체 생성
   			
   			if(rs.next()) {//rs는 표로 되있음, 맨위에는 컬럼 명이기떄문에 데이터 못가져옴, 실제 데이터가 있는곳으로 한행씩 이동하여 가르키기 시키기위함
   				count = rs.getInt("cnt");//수량 산출(count의 값을 가져온다), 위에서 as cnt(alias)로 호출 했기 떄문에 "cnt"로 가져와야함
   			}
   			
   		} catch (SQLException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}finally {
   			DBClose.close(rs, pstmt, con);
   		}
   		
   		return count;
   	}
   	
   	public List<String> getSplquery(String keyword){
   		List<String> list = new ArrayList<String>();
   		Connection con = DBOpen.getConnection();
   		PreparedStatement pstmt = null;
   		ResultSet rs = null;
   		StringBuffer sql = new StringBuffer();
   		sql.append(" select sqlquery ");
   		sql.append("     from suggest ");
   		sql.append("     where sqlquery like '"+keyword+"%' ");
   		sql.append(" order by sqlquery ");
   		
   		try {
   			pstmt = con.prepareStatement(sql.toString());
   			
   			rs = pstmt.executeQuery();
   			
   			while(rs.next()) {
   				String str = rs.getString(1);//따로 쿼리문에 as 를 설정해주지 않았기 때문에 그냥 1을 넣어 1번 컬럼을 가져온다.
   				list.add(str); //데이터를 list에 저장해주는 역할
   			}
   			
   		} catch (SQLException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}	finally {
   			DBClose.close(rs, pstmt, con);
   		}
   		
   		return list;
   		
   	}
   }
   
   ```

   - suggest.jsp

   ```
   <%@ page language="java" contentType="text/html; charset=UTF-8"
       pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
    <%--  new suggestDAO  --%>   
   <jsp:useBean id="dao" class="suggest.SuggestDAO"/>
   
   <%
   	request.setCharacterEncoding("utf-8"); //요청지에서 post방식으로 보낸 한글처리 코드
   	String keyword = request.getParameter("keyword"); //"keyword" -> 파라미터 이름임
   	System.out.println(keyword);
   	int count = dao.getCount(keyword); //SQL문에 대한 결과를 리턴 받는 것임
   	List<String> list = dao.getSplquery(keyword); //파라메터로 받아온 데이터로 쿼리문의 like절로 할당해줌
   	//문장한개는 String으로 받지만 여러개는 list로 받아야됨
   	out.print(count + "|");
   	
   //	out.print(list); //리스트 확인 코드(코드 확인을 위해 적음)
   //	out.print(list.get(3));//리스트 인덱스로 값불러오는 코드(코드 확인을 위해 적음)
   	
   	for(int i=0;i<list.size();i++){
   		count = count -1;
   		
   //		out.print(count);//count값의 변화량을 보기위한 코드(코드 확인을 위해 적음)
   		String key = list.get(i);
   		out.print(key);
   		if( count > 0 ){
   			out.print(",");
   		}
   	}
   %>
   ```

   
