# JSP

### 액션태그

2. jsp:include
   - <%@ include file="" %>와 차이가 있음
     - 소스를 그대로 복사하여 추가, 결과 미포함
   - <jsp:include page="" flush=""/>
     - 경로의 링크에 있는 jsp파일이 처리되고 나서 HTML로 응답, 변경된 결과 포함
     - flush는 true, false 기입, true: 포함할 페이지의 내용을 삽입하기 이전에 현재 페이지가 지금까지 버퍼에 저장한 내용을 출력