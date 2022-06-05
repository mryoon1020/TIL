# MVC 게시판에 주소록 추가하기

### 프로젝트 기본셋팅 - 코드

- Action.java

```java
public interface Action {
  String execute(HttpServletRequest request, HttpServletResponse respons);
}
```

- NullAction.java

```java
public class NullAction implements Action {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse respons) {
    // TODO Auto-generated method stub
    return "/view/nullCommand.jsp";
  }

}
```

- Controller.java

```java
public class Controller extends HttpServlet { 
    private boolean usingTemplate = false; 
    private String templatePage = null; 
    
    //명령어=명령처리Action객체
    private Map map = new java.util.HashMap(); 
     
    public void init(ServletConfig config) throws ServletException { 
        String configFile = config.getInitParameter("configFile"); 
         
        Properties prop = new Properties(); 
        FileInputStream fis = null; 
        try { 
            fis = new FileInputStream(configFile); 
            prop.load(fis); 
        } catch (IOException e) { 
            throw new ServletException(e); 
        } finally { 
            if (fis != null) try { fis.close(); } catch(IOException ex) {} 
        } 
         
        Iterator keyIter = prop.keySet().iterator(); 
        while( keyIter.hasNext() ) { 
            String command = (String)keyIter.next(); 
            System.out.println("command: " + command); 
 
            String handlerClassName = prop.getProperty(command).trim();  
            System.out.println("handlerClassName: " + handlerClassName); 
             
            try { 
                //클래스를 JVM으로 로딩합니다. 
                Class handlerClass = Class.forName(handlerClassName); 
                 
                //읽어들인 클래의 객체를 생성합니다. 
                Object handlerInstance = handlerClass.newInstance(); 
                 
                //MAP에 키와 각 클래스별 객체가 저장합니다. 
                map.put(command, handlerInstance); 
                
            } catch (ClassNotFoundException e) { 
                throw new ServletException(e); 
            } catch (InstantiationException e) { 
                throw new ServletException(e); 
            } catch (IllegalAccessException e) { 
                throw new ServletException(e); 
            } 
        } 
      
        templatePage = config.getInitParameter("templatePage"); 
        
        if (templatePage != null && !templatePage.equals("")) { 
            usingTemplate = true; // 템플릿 페이지 존재 
        } 
    } 
 
    //1.http 요청받음
    public void doGet( 
        HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException { 
        process(request, response); 
    } 
 
    protected void doPost( 
        HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException { 
        process(request, response); 
    } 
     
    private void process( 
        HttpServletRequest request, HttpServletResponse response)  
    throws ServletException, IOException { 
        //2.요청기능 분석
        String command = request.getRequestURI(); 
        System.out.println("RequestURI: " + request.getRequestURI()); 
        
        //URI:/mvc_bbs/bbs/create.do
        if (command.indexOf(request.getContextPath()) == 0) { 
            command = command.substring(request.getContextPath().length()); 
            System.out.println("command: " + command); 
        } 
         
 
        Action action =  
            (Action)map.get(command); 
         
        // 핸들러가 없는 경우 
        if (action == null) { 
            action = new NullAction(); 
        } 
         
        String viewPage = null; 
        try { 
            //"/view/hello.jsp" 3.model사용,4.request 결과저장,5.view선택(리턴) 
            viewPage = action.execute(request, response); 
        } catch(Throwable e) { 
            throw new ServletException(e); 
        } 
         
        if (usingTemplate) { //false -> true
            request.setAttribute("CONTENT_PAGE", viewPage); 
        } 
        //forward 처리 <jsp:forward page=""/> 6.뷰포워드 처리
        RequestDispatcher dispatcher = 
           request.getRequestDispatcher( 
                   usingTemplate ? templatePage : viewPage); 
        dispatcher.forward(request, response); 
    } 
} 
```

- AddrDAO.java

```java
public class AddrDAO {
	
	public boolean delete(int addressnum) {
		boolean flag = false;
		Connection con = DBOpen.getConnection();
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from address ");
		sql.append(" where addressnum = ? ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, addressnum);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, con);
		}
		return flag;
	}
	
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
	
  public boolean update(AddrDTO dto) {
	  boolean flag = false;
	  Connection con = DBOpen.getConnection();
	  PreparedStatement pstmt = null;
		
	  StringBuffer sql = new StringBuffer();
	  sql.append("   UPDATE address  	");
	  sql.append("   SET name= ?,	");
	  sql.append("       handphone= ?,	");
	  sql.append("  	 address= 	?,	");
	  sql.append("  	 zipcode =  ?,	");
	  sql.append("       address2 = ?	");
	  sql.append(" WHERE addressnum = ?	");
	  
	  try {
		pstmt = con.prepareStatement(sql.toString());
		pstmt.setString(1, dto.getName());
		pstmt.setString(2, dto.getHandphone());
		pstmt.setString(3, dto.getAddress());
		pstmt.setString(4, dto.getZipcode());
		pstmt.setString(5, dto.getAddress2());
		pstmt.setInt(6, dto.getAddressnum());
		
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

  public int total(Map map) { //col,word
		int total = 0;
		Connection con = DBOpen.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String col = (String)map.get("col"); //검색컬럼
		String word = (String)map.get("word"); //사용자가 입력한 단어
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from address ");
		
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

- AddrDTO.java

```java
public class AddrDTO {

	private int     addressnum;
	private String  name;
	private String  handphone;
	private String  zipcode;
	private String  address;
	private String  address2;
	
	public AddrDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddrDTO(int addressnum, String name, String handphone, String zipcode, String address, String address2) {
		super();
		this.addressnum = addressnum;
		this.name = name;
		this.handphone = handphone;
		this.zipcode = zipcode;
		this.address = address;
		this.address2 = address2;
	}

	@Override
	public String toString() {
		return "AddrDTO [addressnum=" + addressnum + ", name=" + name + ", handphone=" + handphone + ", zipcode="
				+ zipcode + ", address=" + address + ", address2=" + address2 + "]";
	}

	public int getAddressnum() {
		return addressnum;
	}

	public void setAddressnum(int addressnum) {
		this.addressnum = addressnum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHandphone() {
		return handphone;
	}

	public void setHandphone(String handphone) {
		this.handphone = handphone;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}
		
}
```

- Utility.java

```java
public class Utility {

	 /**
     * 오늘,어제,그제 날짜 가져오기
     * @return List- 날짜들 저장
     * SimpleDateFormat("yyyy-MM-dd") 
     */
    public static List<String> getDay(){
        List<String> list = new ArrayList<String>();
        
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd"); 
        Calendar cal = Calendar.getInstance();
        for(int j = 0; j < 3; j++){
            list.add(sd.format(cal.getTime()));
            cal.add(Calendar.DATE, -1);
        }
        
        return list;
    }
    /**
     * 등록날짜와 오늘,어제,그제날짜와 비교
     * @param wdate - 등록날짜
     * @return - true:오늘,어제,그제중 등록날짜와 같음
     *           false:오늘,어제,그제 날짜가 등록날짜와 다 다름
     */
    public static boolean compareDay(String wdate){
        boolean flag = false;
        List<String> list = getDay();
        if(wdate.equals(list.get(0)) 
           || wdate.equals(list.get(1))
           || wdate.equals(list.get(2))){
            flag = true;
        }
          
        return flag;
    }
	
	public static String checkNull(String str) {
		if(str== null) {
			str = "";
		}
		return str;
	}

	/** 
	    * @param totalRecord 전체 레코드수 
	    * @param nowPage     현재 페이지 
	    * @param recordPerPage 페이지당 레코드 수  
	    * @param col 검색 컬럼  
	    * @param word 검색어
	    * @return 페이징 생성 문자열
	    */ 
	  public static String paging(int totalRecord, int nowPage, int recordPerPage, String col, String word){ 
	       int pagePerBlock = 5; // 블럭당 페이지 수 
	       int totalPage = (int)(Math.ceil((double)totalRecord/recordPerPage)); // 전체 페이지  
	       int totalGrp = (int)(Math.ceil((double)totalPage/pagePerBlock));// 전체 그룹 
	       int nowGrp = (int)(Math.ceil((double)nowPage/pagePerBlock));    // 현재 그룹 
	       int startPage = ((nowGrp - 1) * pagePerBlock) + 1; // 특정 그룹의 페이지 목록 시작  
	       int endPage = (nowGrp * pagePerBlock);             // 특정 그룹의 페이지 목록 종료   
	        
	       StringBuffer str = new StringBuffer(); 
	       str.append("<div style='text-align:center'>"); 
	       str.append("<ul class='pagination'> ");
	       int _nowPage = (nowGrp-1) * pagePerBlock; // 10개 이전 페이지로 이동 
	       if (nowGrp >= 2){ 
	         str.append("<li><a href='./list.do?col="+col+"&word="+word+"&nowPage="+_nowPage+"'>이전</A></li>"); 
	       } 
	 
	       for(int i=startPage; i<=endPage; i++){ 
	         if (i > totalPage){ 
	           break; 
	         } 
	 
	         if (nowPage == i){ 
	           str.append("<li class='active'><a href=#>"+i+"</a></li>"); 
	         }else{ 
	           str.append("<li><a href='./list.do?col="+col+"&word="+word+"&nowPage="+i+"'>"+i+"</A></li>");   
	         } 
	       } 
	           
	       _nowPage = (nowGrp * pagePerBlock)+1; // 10개 다음 페이지로 이동 
	       if (nowGrp < totalGrp){ 
	         str.append("<li><A href='./list.do?col="+col+"&word="+word+"&nowPage="+_nowPage+"'>다음</A></li>"); 
	       } 
	       str.append("</ul>"); 
	       str.append("</div>"); 
	        
	       return str.toString(); 
	    } 
	
}
```

- Constent.java

```java
public class Constant {

	public static String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://127.0.0.1:3306/webtest?useUnicode=true&characterEncoding=utf8";
	public static String USER = "javauser";
	public static String PASSWD = "1234";

}
```

- DBOpen.java

```java
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

```java
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

- index.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <jsp:include page="/template/top.jsp"/>
 <div class="container">
  <div class="row">
    <div class="col-sm-4">
      <h3>Column 1</h3>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>
      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris...</p>
    </div>
    <div class="col-sm-4">
      <h3>Column 2</h3>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>
      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris...</p>
    </div>
    <div class="col-sm-4">
      <h3>Column 3</h3>        
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>
      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris...</p>
    </div>
  </div>
</div>
</body>
</html>
```

- template.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% String CONTENT_PAGE = (String)request.getAttribute("CONTENT_PAGE"); %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="./top.jsp"/>

<div style="width:100%; padding-top:30px;">

<jsp:include page="<%= CONTENT_PAGE %>"/>

</div>

</body>
</html>
```

- top.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  String root = request.getContextPath(); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

</head>
<body>
 <!--상단메뉴-->
<div class="container">
  <div class="page-header row">
   <div class="col-sm-4">
    <img src="<%=root %>/images/img_chania.jpg" class="img-responsive img-thumbnail" alt="Cinque Terre" >
   </div>
   <div class="col-sm-8"><h1>Homepage</h1></div>     
  </div>
  <ul class="nav nav-tabs">
    <li class="active"><a href="<%=root%>/index.jsp">Home</a></li>
    <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown" href="#">MVC 실습<span class="caret"></span></a>
      <ul class="dropdown-menu">
        <li><a href="<%=root%>/mvc/hello.do">인삿말</a></li>
        <li><a href="<%=root%>/mvc/date.do">오늘의 날짜</a></li>
        <li><a href="<%=root%>/mvc/myinfo.do">나의정보</a></li>                       
        <li><a href="<%=root%>/mvc/team.do">팀정보</a></li>                       
      </ul>
    </li>
    <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown" href="#">게시판<span class="caret"></span></a>
      <ul class="dropdown-menu">
        <li><a href="<%=root%>/bbs/create.do">생성</a></li>
        <li><a href="<%=root%>/bbs/list.do">목록</a></li>                      
      </ul>
    </li>
    
     <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown" href="#">주소정보<span class="caret"></span></a>
      <ul class="dropdown-menu">
        <li><a href="<%=root%>/addr/create.do">생성</a></li>
        <li><a href="<%=root%>/addr/list.do">목록</a></li>                      
      </ul>
    </li>
    
    <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown" href="#">메모<span class="caret"></span></a>
      <ul class="dropdown-menu">
        <li><a href="<%=root%>/memo/createForm.jsp">생성</a></li>
        <li><a href="<%=root%>/memo/list.jsp">목록</a></li>                      
      </ul>
    </li>
    
    <li><a href="#">Menu 2</a></li>
    <li><a href="#">Menu 3</a></li>
  </ul> 
</div>
</body>
</html>
```

- nullCommand.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div align="center">
<br>
<h1>
일치하는 명령어가 없습니다
</h1>
</div>

</body>
</html>
```

- web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="https://jakarta.ee/xml/ns/jakartaee" xmlns:web="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee https://jakarta.ee/xml/ns/jakartaee/web-app_5_0.xsd" id="WebApp_ID" version="5.0">
  <display-name>mvc_addr</display-name>
  <welcome-file-list>
    <welcome-file>index.html</welcome-file>
    <welcome-file>index.jsp</welcome-file>
    <welcome-file>index.htm</welcome-file>
    <welcome-file>default.html</welcome-file>
    <welcome-file>default.jsp</welcome-file>
    <welcome-file>default.htm</welcome-file>
  </welcome-file-list>
  
  <servlet>
  
  <servlet-name>Controller</servlet-name>
  <servlet-class>controller.Controller</servlet-class>
  
  <init-param>
  
  	<param-name>configFile</param-name>
  	<param-value>D:/aistudy/web/workspace/mvc_addr/src/main/webapp/WEB-INF/config/config.properties</param-value>
  
  </init-param>
  
  <init-param>
  
  <param-name>templatePage</param-name>
  <param-value>/template/template.jsp</param-value>
  
  
  </init-param>
  
  </servlet>
  
  <servlet-mapping>
  
	<servlet-name>Controller</servlet-name>
	<url-pattern>*.do</url-pattern>
  
  </servlet-mapping>
  
</web-app>
```

- config.properties
  - #은 properties 파일의 주석처리
  - 기재되어있는 명령어는 명령어 예시
  - 확장자 .properties 오타주의

```properties
# command = Action class Mapping List
/addr/list.do=action.ListAction
```