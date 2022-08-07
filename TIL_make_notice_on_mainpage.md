# 메인페이지에 공지사항 일부 추출하기

### 목적

- 메인페이지에 최신 공지사항글을 노출시킴

> Controller

- 요청주소는 메인페이지를 호출하는 주소로 처리
- 메인페이지에서 el(jstl)을 통한 호출사용시 반드시 컨트롤러(서버)에서 처리가 되어야함 

```java
 @GetMapping("/")
    public String home(HttpServletRequest request) {

        List<NoticeDTO> mainNoticeList = service.mainNoticeList();

        log.info("mainNoticeList : "+mainNoticeList);

        request.setAttribute("mainNoticeList", mainNoticeList);

        return "/home";
    }
```

> Xml(myBatis)

```xml
<select id="mainNoticeList" resultType="com.rentcar.notice.model.NoticeDTO">

		select noticeno,title, wdate from notice
		order by noticeno desc limit 4

</select>
```

> ViewPage(jsp)

```jsp
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 생략 -->
<div>
    <div>
        <div>
            <div>
                <p>알려드려요</p>
                <h2>따끈한 공지사항</h2>
                <a href="./notice/list" class="">전체보기</a>
            </div>

            
            <ul>
 <c:forEach var="dto" items="${mainNoticeList}">
                    <li>
                        <input type='hidden' value="${dto.noticeno}">
                        <a href="javascript:read('${dto.noticeno}')">${dto.title}</a>
                    </li>
 </c:forEach>
            </ul>
<!-- 생략 -->
```

