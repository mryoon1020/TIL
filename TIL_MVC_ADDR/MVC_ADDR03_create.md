#  MVC 게시판에 주소록 추가하기

### Ceate

- 순서:
  1. list.jsp 구동 후 등록 클릭 => 주소창 명령어확인
  2. config.properties 파일 수정
  3. CreateAction.java파일 생성
  4. createForm.jsp 파일 카피 및 수정
  5. CreateAction.java 수정
  6. config.properties 파일 수정
  7. CreateProc.java 파일 생성 및 수정
  8. createProc.jsp 파일 카피 및 수정
  9. 실행 및 오류검토

- config.properties

```properties
# command = Action class Mapping List
/addr/list.do=action.ListAction
/addr/create.do=action.CreateAction
/addr/createProc.do=action.CreateProc
```

- CreateAction.java

```java
public class CreateAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		return "/view/createForm.jsp";
	}

}
```

- createForm.jsp(우편번호 검색기능은 오픈소스 사용했음)

```jsp
<%@ page contentType="text/html; charset=UTF-8" %> 
 
<!DOCTYPE html> 
<html> 
<head>
  <title>homepage</title>
  <meta charset="utf-8">
  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
               if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                   // document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                   // document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
</script>
</head>
<body> 

<div class="container">
<h1 class="col-sm-offset-2 col-sm-10">주소등록</h1>
<form class="form-horizontal" 
      action="createProc.do"
      method="post"
      >
 
  <div class="form-group">
    <label class="control-label col-sm-2" for="name">이름</label>
    <div class="col-sm-6">
      <input type="text" name="name" id="name" class="form-control">
    </div>
  </div>
  
  <div class="form-group">
    <label class="control-label col-sm-2" for="handphone">전화번호</label>
    <div class="col-sm-6">
      <input type="text" name="handphone" id="handphone" class="form-control">
    </div>
  </div>
  
   <div class="form-group">
    <label class="control-label col-sm-2" for="zipcode">우편번호</label>
    <div class="col-sm-2">
      	<input type="text" name="zipcode" class="form-control" 
      	id="sample6_postcode" placeholder="우편번호" > 
    </div>
    <div class='col-sm-4'>
    	<button class='btn' type="button" onclick="sample6_execDaumPostcode()">주소검색</button>
    </div>
  </div>
  
  <div class="form-group">
    <label class="control-label col-sm-2" for="address">주소</label>
    <div class="col-sm-6">
    <input type="text" name="address" id="sample6_address" placeholder="주소" class="form-control" >
    </div>
  </div>
    <div class="form-group">
    <label class="control-label col-sm-2" for="address2">상세주소</label>
    <div class="col-sm-6">
    <input type="text" name="address2" id="sample6_detailAddress" placeholder="상세주소" class="form-control" >
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

- CreateProc.java

```java
public class CreateProc implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		AddrDTO dto = new AddrDTO();
		dto.setName(request.getParameter("name"));
		dto.setHandphone(request.getParameter("handphone"));
		dto.setZipcode(request.getParameter("zipcode"));
		dto.setAddress(request.getParameter("address"));
		dto.setAddress2(request.getParameter("address2"));
		
		AddrDAO dao = new AddrDAO();
		boolean flag = dao.create(dto);
		
		request.setAttribute("flag",flag);
		
		return "/view/createProc.jsp";
	}

}
```

- createProc.jsp

```jsp
<%@ page contentType="text/html; charset=UTF-8" %> 
<% request.setCharacterEncoding("utf-8"); %>

<%

boolean flag = (boolean)request.getAttribute("flag");

%>

<!DOCTYPE html> 
<html> 
<head>
  <title>homepage</title>
  <meta charset="utf-8">
</head>
<body> 

<div class="container">
<div class="well well-lg">

<%

if(flag){
	out.print("등록 성공");
}else{
	out.print("등록실패");
}

%>

</div>
</div>
</body> 
</html> 
```