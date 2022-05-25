# 게시판 만들기

## 순서

1. 게시판 생성전 새로운 프로젝트생성 및 SQL연동
2. 게시판에 목록 및 게시판 생성기능 구현
3. 게시판 생성시 작성자, 제목, 내용, 비밀번호 사용자 입력란 만들기
4. 등록버튼, 취소 버튼 만들기
5. 등록 버튼 클릭시 DB 저장
6. 목록 클릭시 게시판 목록 출력, DB나열(번호, 제목,작성자, 등록날짜, grpno, indent, ansnum)
7. 목록하단 페이지 번호 구현
8. 제목 클릭시 조회화면으로 전환
9. 조회화면 작성자, 제목, 내용, 조회수, 등록일 구현
10. 조회화면 하단 등록, 수정, 삭제 ,답변, 목록 버튼 기능 활성화
11. 게시판 수정시 작성자, 제목, 내용, 비밀번호 입력란 만들기
12. 게시판 수정, 삭제는 비밀번호 입력해야 가능하게 구현
13. 목록 클릭시 게시판 목록으로 이동
14. 답변하기 클릭시 답변 작성 및 게시글의 하위 항목으로 들어가게 구현
15. 답변이 된글이 맨위로 정렬되게 하며 new표시 및 화살표이미지 표기
16. new 표시는 3일 경과 후 사라지게 함 

16. 현재 진행중이며 완성될때 현재 파일내용 계속 수정 및 커밋할 예정



#### 01 게시판 만들기전 셋팅

- Spring Tool Suite -> File -> new -> dynamic web project -> webtest(프로젝트명) 생성
- webtest -> src/main/java(이름이 이렇게 되어있음 경로X) 폴더내 utility package 성성
- utility package에 Constant.java , DBOpen.java , DBClose.java , Utility.java 생성
-  Constant.java

```
public class Constant {

	public static String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://127.0.0.1:3306/webtest?useUnicode=true&characterEncoding=utf8";
	public static String USER = "sql아이디";
	public static String PASSWD = "비밀번호";

}
```

- DBOpen.java

```
package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBOpen {
	
	private static Connection con;
	
	public static Connection getConnection() {
		
		try {
			Class.forName(Constant.DRIVER);
			
			con=DriverManager.getConnection(Constant.URL,Constant.USER,Constant.PASSWD);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;//연결객체 생성되어있으면 연결객체 리턴
	}

}
```

- DBClose.java

```
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBClose {
	public static void close(Connection con) {
		
		try {
			if(con!=null)con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void close(PreparedStatement pstmt,Connection con) {
		
		try {
			if(pstmt!=null)pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}try {
			if(con!=null)con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void close(ResultSet rs, PreparedStatement pstmt,Connection con) {
		try {
			if(rs!= null)rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}try {
			if(pstmt!=null)pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}try {
			if(con!=null)con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
```

- Utility.java(특정 기능을 하는 메소드 꺼내쓰기 위한 클래스, 계속 추가될 예정)

```
public class Utility {

}
```

- JDBC_Test.java(SQL연동 테스트용 클래스, 없어도 됨)

```
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC_Test {

	public static void main(String[] args) {
		Connection con = DBOpen.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql="SELECT COUNT(*) cnt FROM information_schema.tables "
				+ "WHERE table_schema = 'webtest';";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("현재 webtest DB에 생성된 테이블 갯수: "+ rs.getInt("cnt"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	finally {
			DBClose.close(rs, pstmt, con);
		}
	}

}
```