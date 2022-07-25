# FileUpload 기능 추가하기

### 사용법

> create.jsp

- form 태그안에 반드시 `enctype="multipart/form-data"` 속성을 추가해주어야 함
- ` <input type="file" name="fnameMF" id="fnameMF" class="form-control">`
- 파일 입력부분인 input 태그 타입에 file 속성추가

```jsp
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <title>notice</title>
  <meta charset="utf-8">
  <script type="text/JavaScript">

 function checkIn(f){
         if (f.wname.value == ""){
              alert("글쓴이를 입력하세요");
              f.wname.focus()
              return false;
         }
         if (f.title.value == ""){
              alert("제목를 입력하세요");
              f.title.focus();
              return false;
         }
         if (f.content.value == '') {
             window.alert('내용을 입력해 주세요.');
             f.content.focus();
             return false;
         }
         if (f.passwd.value == ""){
             alert("패스워드를 입력하세요");
             f.passwd.focus();
             return false;
         }
 }
 </script>
</head>
<body>
<div class="container">
<h2 class="col-sm-offset-2 col-sm-10">공지 생성</h2>
<form class="form-horizontal"
      action="./create"
      method="post"
      onsubmit="return checkIn(this)"
      enctype="multipart/form-data"
      >

  <div class="form-group">
    <label class="control-label col-sm-2" for="wname">작성자</label>
    <div class="col-sm-6">
      <input type="text" name="wname" id="wname" class="form-control">
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-2" for="title">제목</label>
    <div class="col-sm-8">
      <input type="text" name="title" id="title" class="form-control">
    </div>
  </div>

  <div class="form-group">
    <label class="control-label col-sm-2" for="content">내용</label>
    <div class="col-sm-8">
    <textarea rows="12" cols="7" id="content" name="content" class="form-control"></textarea>
    </div>
  </div>

    <div class="form-group">
      <label class="control-label col-sm-2" for="fname">첨부파일</label>
      <div class="col-sm-6">
        <input type="file" name="fnameMF" id="fnameMF" class="form-control">
      </div>
    </div>

  <div class="form-group">
    <label class="control-label col-sm-2" for="passwd">비밀번호</label>
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

> DTO.java

```java
@Data
public class NoticeDTO {
    private int    noticeno     ;
    private String title        ;
    private String content      ;
    private String wname        ;
    private String passwd       ;
    private String wdate        ;
    private String fname        ;
    private MultipartFile fnameMF;

}
```

> utility.java

- `else if (os.equals("windows 11")) { System.out.println("os: " + os); serverFullPath = basePath + "\\" + filename; }`
- 운영체제 반드시 정확히 기재해줄 것

```java
public static String saveFileSpring(MultipartFile mf, String basePath) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String filename = "";
        long filesize = mf.getSize();
        String originalFilename = mf.getOriginalFilename();
        try {
            if (filesize > 0) { // 파일이 존재한다면
                // 인풋 스트림을 얻는다.
                inputStream = mf.getInputStream();

                File oldfile = new File(basePath, originalFilename);

                if (oldfile.exists()) {
                    for (int k = 0; true; k++) {
                        // 파일명 중복을 피하기 위한 일련 번호를 생성하여
                        // 파일명으로 조합
                        oldfile = new File(basePath, "(" + k + ")" + originalFilename);

                        // 조합된 파일명이 존재하지 않는다면, 일련번호가
                        // 붙은 파일명 다시 생성
                        if (!oldfile.exists()) { // 존재하지 않는 경우
                            filename = "(" + k + ")" + originalFilename;
                            break;
                        }
                    }
                } else {
                    filename = originalFilename;
                }
                String os = System.getProperty("os.name").toLowerCase();
                System.out.println("os: " + os); // windows 10, linux, mac os x
                String serverFullPath = null;
                if (os.equals("mac os x")) { // Mac
                    System.out.println("맥");
                    serverFullPath = basePath + "/" + filename;
                } else if (os.equals("windows 11")) {
                    System.out.println("os: " + os);
                    serverFullPath = basePath + "\\" + filename;
                } else if (os.equals("linux")) {
                    System.out.println("리눅스");
                    serverFullPath = basePath + "/" + filename;
                }

                System.out.println("fileName: " + filename);
                System.out.println("serverFullPath: " + serverFullPath);

                outputStream = new FileOutputStream(serverFullPath);

                // 버퍼를 만든다.
                int readBytes = 0;
                byte[] buffer = new byte[8192];

                while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {
                    outputStream.write(buffer, 0, readBytes);
                }
                outputStream.close();
                inputStream.close();

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

        return filename;
    }


    public static void deleteFile(String upDir, String oldfile) {
        File file = new File(upDir, oldfile);
        if (file.exists())
            file.delete();

    }
```

> upload.java

- 업로드한 파일 저장위치 설정파일

```java
public class upload {
    /** 페이지당 출력할 레코드 갯수 */
    public static int RECORD_PER_PAGE = 3;

    // Windows, VMWare, AWS cloud 절대 경로 설정
    public static synchronized String getUploadDir() {
        String path = "";
        if (File.separator.equals("\\")) {
            path = "D:\\Github_upload\\projectSaveF\\uploaded";
            System.out.println("Windows 10: " + path);

        } else {
            // System.out.println("Linux");
            path = "/home/ubuntu/deploy/bbs/storage/";
        }

        return path;
    }

}
```

> controller.java

```java
  @GetMapping("/notice/create")
    public String create() {

        return "/notice/create";
    }

    @PostMapping("/notice/create")
    public String create(NoticeDTO dto) {

        String upDir = UploadNotice.getUploadDir();

        if (dto.getFnameMF().getSize() > 0) {
            dto.setFname(Utility.saveFileSpring(dto.getFnameMF(), upDir));

        }

        if(service.create(dto)==1) {
            return "redirect:list";
        }else {
            return"error";
        }

    }
```

> xml파일설정

- 현재는 파일이름만 출력하는 상태
- 추후 다운로드도 가능하게 업데이트 할예정

```xml
<insert id="create" parameterType="com.rentcar.notice.model.NoticeDTO">

        <choose>

        <when test="fname == null">
            INSERT INTO projectNotice(title, content, wname, passwd, wdate)
                VALUES(#{title}, #{content}, #{wname}, #{passwd}, NOW())

        </when>

            <otherwise>
                INSERT INTO projectNotice(title, content, wname, passwd, wdate, fname)
                VALUES(#{title}, #{content}, #{wname}, #{passwd}, NOW(), #{fname})

            </otherwise>

        </choose>

    </insert>
```
