# FileDownload 기능 추가하기

### 사용법

> list.jsp

- javascript 추가

```javascript
<script>
     function fileDown(fname){
             var url = "./fileDown";
             url += "?fname="+fname;
             location.href=url;
          }

  </script>
```

- 파일 링크 추가

```jsp
  <table class="table table-striped">
   <thead>
    <tr>
    <th>번호</th>
    <th>제목</th>
    <th>작성자</th>
    <th>등록날짜</th>
    <th>첨부파일</th>
    </tr>
   </thead>
   <tbody>

<c:choose>   
<c:when test="${empty list}">
   <tr><td colspan="6">등록된 글이 없습니다.</td>
</c:when>
<c:otherwise>
  
   <c:forEach var="dto" items="${list}">
   
   <tr>
    <td>${dto.noticeno}</td>
    <td>
    <a href="javascript:read('${dto.noticeno}')">${dto.title}</a>
 
    <c:if test="${util:newImg(fn:substring(dto.wdate,0,10)) }">
         <img src="/images/new.gif">
    </c:if>
 
    </td>
    <td>${dto.wname}</td>
    <td>${dto.wdate}</td>
    <td>
             <c:choose>
                 <c:when test="${empty dto.fname}">파일없음</c:when>
                 <c:otherwise>
                 <a href="javascript:fileDown('${dto.fname}')">
                 ${dto.fname}
                 </a>
                 </c:otherwise>
                 </c:choose>
    </td>
   </tr>
   </c:forEach>
   </c:otherwise>
   </c:choose>
 
   </tbody>
  
  </table>
```

> read.jsp

- list.jsp에서 사용한 javascript 동일하게 추가하며
- 테이블 대신 `div` 를 사용하였음

```html
<div class="panel-heading">첨부파일</div>
<div class="panel-body">
    			<c:choose>
    			    <c:when test="${empty dto.fname}">파일없음</c:when>
    			    <c:otherwise>
    			    <a href="javascript:fileDown('${dto.fname}')">
    			    ${dto.fname}
    			    </a>
    			    </c:otherwise>
    			</c:choose>
</div>
```

>controller.java

- ` response.setHeader("Content-disposition", "attachment; fileName=\"" + URLEncoder.encode(fname, "UTF-8") + "\";");` 
- 상기구문에 `attachment; fileName=\` 변경하게 될경우 파일명과 확장자를 가져오지 못하는 오류가 발생하게 됨

```java
 @GetMapping("/notice/fileDown")
    public void fileDown(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 저장 폴더를 절대 경로로 변환
        String dir = UploadNotice.getUploadDir();
        // 파일명 받기
        String fname = request.getParameter("fname");
        byte[] files = FileUtils.readFileToByteArray(new File(dir, fname));
        response.setHeader("Content-disposition", "attachment; fileName=\"" + URLEncoder.encode(fname, "UTF-8") + "\";");

        // Content-Transfer-Encoding : 전송 데이타의 body를 인코딩한 방법을 표시함.
        response.setHeader("Content-Transfer-Encoding", "binary");
        /**
         * Content-Disposition가 attachment와 함게 설정되었다면 'Save As'로 파일을 제안하는지 여부에 따라 브라우저가
         * 실행한다.
         */
        response.setContentType("application/octet-stream");
        response.setContentLength(files.length);
        response.getOutputStream().write(files);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }
```

