# 게시판 만들기

- create
  - 데이터 입력 양식 createForm.jsp 생성
    - 사용자 입력 데이터(서버로 전송할 데이터)를 form태그로 묶음
    - form안의 데이터 전송 경로지정(action 설정)
    - 등록 버튼, 취소 버튼 생성
  - Data Transfer Object (이하 DTO) 생성
    - BbsDTO.java
  - Data Access Object (이하 DAO) 생성
    - BbsDAO.java
  - createForm에서 전송된 파라미터는 DTO에 저장됨
  - 이후 해당 파라미터를 처리할 create 메소드 생성
  - createProc.jsp 생성
    - jsp:useBean 사용을 통해 dao, dto property값 세팅
  - DAO를 통해 처리된 데이터 존재 여부를 판단하여 성공, 실패 여부를 createProc.jsp에 출력

```
-------------------------------------------------------------------------------
createForm.jsp
-------------------------------------------------------------------------------
<%@ page contentType="text/html; charset=UTF-8" %> 
 
<!DOCTYPE html> 
<html> 
<head>
  <title>homepage</title>
  <meta charset="utf-8">
</head>
<body> 
<jsp:include page="/menu/top.jsp"/>
<div class="container">
<h1 class="col-sm-offset-2 col-sm-10">게시판생성</h1>
<form class="form-horizontal" 
      action="createProc.jsp"
      method="post"
      >
 
  <div class="form-group">
    <label class="control-label col-sm-2" for="wname">작성자</label>
    <div class="col-sm-6">
      <input type="text" name="wname" id="wname" class="form-control">
    </div>
  </div>
  
  <div class="form-group">
    <label class="control-label col-sm-2" for="title">제목</label>
    <div class="col-sm-6">
      <input type="text" name="title" id="title" class="form-control">
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
MemoDTO.java
-------------------------------------------------------------------------------
public class BbsDTO {
	/** 번호 */ 
	  private int bbsno; 
	  /** 글쓴이 */ 
	  private String wname; 
	  /** 제목 */ 
	  private String title; 
	  /** 내용 */ 
	  private String content; 
	  /** 패스워드 */ 
	  private String passwd; 
	  /** 조회수 */ 
	  private int viewcnt; 
	  /** 등록일 */ 
	  private String wdate; 
	  /** 그룹 번호 */ 
	  private int grpno; 
	  /** 답변 차수 */ 
	  private int indent; 
	  /** 답변 순서 */ 
	  private int ansnum;
	  
	public BbsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BbsDTO(int bbsno, String wname, String title, String content, String passwd, int viewcnt, String wdate,
			int grpno, int indent, int ansnum) {
		super();
		this.bbsno = bbsno;
		this.wname = wname;
		this.title = title;
		this.content = content;
		this.passwd = passwd;
		this.viewcnt = viewcnt;
		this.wdate = wdate;
		this.grpno = grpno;
		this.indent = indent;
		this.ansnum = ansnum;
	}
	
	@Override
	public String toString() {
		return "BbsDTO [bbsno=" + bbsno + ", wname=" + wname + ", title=" + title + ", content=" + content + ", passwd="
				+ passwd + ", viewcnt=" + viewcnt + ", wdate=" + wdate + ", grpno=" + grpno + ", indent=" + indent
				+ ", ansnum=" + ansnum + "]";
	}

	public int getBbsno() {
		return bbsno;
	}

	public void setBbsno(int bbsno) {
		this.bbsno = bbsno;
	}

	public String getWname() {
		return wname;
	}

	public void setWname(String wname) {
		this.wname = wname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public int getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public int getGrpno() {
		return grpno;
	}

	public void setGrpno(int grpno) {
		this.grpno = grpno;
	}

	public int getIndent() {
		return indent;
	}

	public void setIndent(int indent) {
		this.indent = indent;
	}

	public int getAnsnum() {
		return ansnum;
	}

	public void setAnsnum(int ansnum) {
		this.ansnum = ansnum;
	}
	  
	  
	  
}
```

```
-------------------------------------------------------------------------------
BbsDAO.java
-------------------------------------------------------------------------------
public class BbsDAO {	
	public boolean create(BbsDTO dto) {
		boolean flag = false;
		Connection con = DBOpen.getConnection();
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into bbs(wname, title, content, passwd, wdate, grpno) ");
		sql.append(" values(?,?,?,?,sysdate(), (select ifnull(max(grpno),0)+1 from bbs b)) ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getWname());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getPasswd());
			
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
}
```

```
-------------------------------------------------------------------------------
createProc.jsp
-------------------------------------------------------------------------------
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bbs.*" %>
    
<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="dao" class="bbs.BbsDAO"/>

<jsp:useBean id="dto" class="bbs.BbsDTO"/>

<jsp:setProperty name="dto" property="*"/>

<%

/** String wname = request.getParameter("wname");
String title = request.getParameter("title");
String content = request.getParameter("content");
String passwd = request.getParameter("passwd");

BbsDTO dto = new BbsDTO();
dto.setWname(wname);
dto.setTitle(title);
dto.setContent(content);
dto.setPasswd(passwd);

BbsDAO dao = new BbsDAO(); */

boolean flag = dao.create(dto);

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
	out.print("글 등록 성공입니다");
}else{
	out.print("글 등록 실패입니다");
}

%>

</div>

<button class ='btn' onclick="location.href='createForm.jsp'">다시등록</button>
<button class ='btn' onclick="location.href='list.jsp'">목록</button>

</div>
</body>
</html>
```



