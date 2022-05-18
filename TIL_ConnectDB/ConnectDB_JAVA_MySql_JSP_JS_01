# JAVA , MySql, JSP,JS  연동-01

1. mysql-connector-java 파일 설치(연동을 위한 드라이버)

   - src\main\webapp\WEB-INF\lib 에 jar 파일만 붙여 넣기

2. utility만들기

   - src\main\java에 폴더 생성

   - Constant.java , DBOpen.java 클래스 만들기

   - ```
     Const.java
     //변수를 선언해주고 드라이버 및 아이피 주소접근
     public class Constant {
     
     	public static String DRIVER = "com.mysql.cj.jdbc.Driver";
     	public static final String URL = "jdbc:mysql://아이피 주소: 포트번호 4자리/webtest?useUnicode=true&characterEncoding=utf8";
     	public static String USER = "MySQL ID";
     	public static String PASSWD = "MySQL 비밀번호";
     
     }
     -------------------------------------------------------------
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

   - DBClose.java 클래스 만들기

     - 데이터 연동을 끊어 주어야 함, 트래픽부하를 막기위함

   - ```
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
     
   - 연결 테스트 코드
   
   - ```
     import java.sql.Connection;
     
     import utility.DBClose;
     import utility.DBOpen;
      
     public class DriverTestMySQL {
      
       public static void main(String[] args) {
         Connection con = null;
         
         try {
           con = DBOpen.getConnection();
           System.out.println("데이터베이스 접속이 성공했습니다.");    
           
         } catch (Exception e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
         } finally {
           
           DBClose.close(con);
           
         }
      
       }
      
     }
     ```
   
     
