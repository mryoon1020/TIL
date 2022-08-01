# 네이버 버킷에 업로드된 파일 다운로드 오류

### 목적

-  네이버 버킷에 업로드된 파일 다운로드
- 현재 상황 : 
  - 파일에 접근은 가능하지만 로컬에 저장할때 엑세스거부 오류
  - 파일경로설정이 맞게 되어있지만 파일을 찾을 수 없는 오류
  - 현재 상기 두가지 오류가 나타나고 있음

> 오류나는 코드

- jsp파일

```jsp
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="util" uri="/ELFunctions" %>
<!DOCTYPE html> 
<html> 
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
 <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
   <script type="text/javascript">
     function read(noticeno){
       var url = "read";
       url += "?noticeno="+noticeno;
       url += "&col=${col}";
       url += "&word=${word}";
       url += "&nowPage=${nowPage}";
       location.href=url;
 
     }

     function fileDown(noticeno,key){
             var url = "./fileDown";
             url += "?noticeno=" + noticeno;
             url += "&key="+key;
             alert(url);
             location.href=url;
          }

  </script>
 
</head>
<body>
<div class="container">
 
   <h2>공지 사항</h2>
  <form class="form-inline" action="./list">
    <div class="form-group">
      <select class="form-control" name="col">
        <option value="wname"
        <c:if test= "${col=='wname'}"> selected </c:if>
        >성명</option>
        <option value="title"
        <c:if test= "${col=='title'}"> selected </c:if>
        >제목</option>
        <option value="content"
        <c:if test= "${col=='content'}"> selected </c:if>
        >내용</option>
        <option value="title_content"
        <c:if test= "${col=='title_content'}"> selected</c:if>
        >제목+내용</option>
        <option value="total"
        <c:if test= "${col=='total'}"> selected </c:if>
        >전체출력</option>       
     </select>
    </div>
    <div class="form-group">
      <input type="text" class="form-control" placeholder="Enter 검색어" 
      name="word" value="${word}">
    </div>
    <button type="submit" class="btn btn-default" >검색</button>

    <button type="button" class="btn btn-default" onclick="location.href='/notice/create'">등록</button>

  </form>
  
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
    			    <a href="javascript:fileDown('${dto.noticeno}','${dto.key}')">
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
  <div>
      ${paging}
  </div>
</div>
</body> 
</html> 
```

- controller

```java
 @GetMapping("/notice/fileDown")
    public void fileDown(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 저장 폴더를 절대 경로로 변환
        String dir = UploadNotice.getUploadDir();
        // 파일명 받기
        //String fname = request.getParameter("fname");
        String noticeno = request.getParameter("noticeno");
//        NoticeDTO dto = service.read(Integer.parseInt(noticeno));

        String fname = request.getParameter("key");

        String downloadFilePath = "/";

        Runtime.getRuntime().exec("chmod -R 777 " + downloadFilePath);

        log.info("pathFile    :    " + fname);


// download object
        try {
            S3Object s3Object = amazonS3.getObject("imagetest", fname);
            S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();

            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFilePath));

            byte[] bytesArray = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = s3ObjectInputStream.read(bytesArray)) != -1) {
                outputStream.write(bytesArray, 0, bytesRead);
            }

            outputStream.close();
            s3ObjectInputStream.close();
            System.out.format("Object %s has been downloaded.\n", fname);

        } catch (AmazonS3Exception e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }

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

- WebMvcConfiguration

```java
package com.rentcar.config;


import com.rentcar.utility.UploadList;


import com.rentcar.utility.UploadCon;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Windows: path = "f:/AIstudy/deploy/shopping/contents/storage";
        // ▶ file:///f:/AIstudy/deploy/shopping/contents/storage
        // Ubuntu: path = "/home/ubuntu/deploy/shopping/contents/storage";
        // ▶ file:////home/ubuntu/deploy/shopping/contents/storage
        // JSP 인식되는 경로: http://localhost:8000/contents/storage";
        registry.addResourceHandler("/carinfo/storage/**")
                .addResourceLocations("file:///" + UploadCon.getUploadDir());
//        registry.addResourceHandler("/member/storage/**")
//                 .addResourceLocations("file:///" + UploadMem.getUploadDir());


        registry.addResourceHandler("/ckstorage/files/**")
                .addResourceLocations("file:///" + UploadList.getUploadDir() + "/files/");


        registry.addResourceHandler("/ckstorage/files/**")
                .addResourceLocations("file:///" + UploadList.getUploadDir() + "/files/");

        registry.addResourceHandler("/notice/**")
                .addResourceLocations("file:///" + UploadList.getUploadDir() + "/files/");
    }

}
```

- uploadList

```java
package com.rentcar.utility;

import java.io.File;

public class UploadList {
    /** 페이지당 출력할 레코드 갯수 */
    public static int RECORD_PER_PAGE = 3;

    // Windows, VMWare, AWS cloud 절대 경로 설정
    public static synchronized String getUploadDir() {
        String path = "";
        if (File.separator.equals("\\")) {
            path = "D:\\Github_upload\\projectSaveF\\download\\";
            System.out.println("Windows 10: " + path);

        } else {
            // System.out.println("Linux");
            path = "/home/ubuntu/deploy/bbs/storage/";
        }

        return path;
    }

}
```

- uploadNotice

```java
package com.rentcar.notice.model;

import java.io.File;

public class UploadNotice {
    /** 페이지당 출력할 레코드 갯수 */
    public static int RECORD_PER_PAGE = 3;
 
    // Windows, VMWare, AWS cloud 절대 경로 설정
    public static synchronized String getUploadDir() {
        String path = "";
        if (File.separator.equals("\\")) {
            path = "https://kr.object.ncloudstorage.com/imagetest/";
            System.out.println("Windows 10: " + path);
            
        } else {
            System.out.println("Linux");
            path = "/root/imagetest/";
        }
        
        return path;
    }
    
}
```

