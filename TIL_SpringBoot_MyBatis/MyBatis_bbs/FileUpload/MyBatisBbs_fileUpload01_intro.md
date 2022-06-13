# BBS에 파일 업로드 추가

### 기존 MyBatis로 만든 BBS에 파일 업로드 기능 추가

- SQL 수정
  - mySQL에서 컬럼 추가

```mysql
use webtest;
alter table bbs
add (filename varchar(50),
      filesize int default 0);
```

- build.gradle 수정(Fileupload의존성 추가)
  - 추가후 반드시 refresh 잊지 말것

```gradle
 // https://mvnrepository.com/artifact/commons-io/commons-io       
  implementation group: 'commons-io', name: 'commons-io', version: '2.6'
  // https://mvnrepository.com/artifact/commons-fileupload/commons-fileupload
  implementation group: 'commons-fileupload', name: 'commons-fileupload', version: '1.3.3'
```

- BbsDTO 수정
  1. 매개변수가 있는 생성자 삭제
  2. toString 메소드 삭제
  3. `private String filename` `private String filename` (필드값) 추가
  4. `private MultipartFile filenameMF` (필드값)추가 후 import
  5. 매개변수가 있는 생성자 재생성
  6. toString 재생성
  7. 두필드에 대한 getter, setter 추가
- BbsDTO.java 완성본

```java
import org.springframework.web.multipart.MultipartFile;

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
  /** 파일 이름 */
  private String filename;
  /** 파일 크기 */
  private int filesize;
  /** form에서 서버로 보내지는 파일의 객체 타입 */
  private MultipartFile filenameMF;

  public BbsDTO() {
    super();
    // TODO Auto-generated constructor stub
  }

  public BbsDTO(int bbsno, String wname, String title, String content, String passwd, int viewcnt, String wdate,
      int grpno, int indent, int ansnum, String filename, int filesize, MultipartFile filenameMF) {
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
    this.filename = filename;
    this.filesize = filesize;
    this.filenameMF = filenameMF;
  }

  @Override
  public String toString() {
    return "BbsDTO [bbsno=" + bbsno + ", wname=" + wname + ", title=" + title + ", content=" + content + ", passwd="
        + passwd + ", viewcnt=" + viewcnt + ", wdate=" + wdate + ", grpno=" + grpno + ", indent=" + indent + ", ansnum="
        + ansnum + ", filename=" + filename + ", filesize=" + filesize + ", filenameMF=" + filenameMF + "]";
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

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public int getFilesize() {
    return filesize;
  }

  public void setFilesize(int filesize) {
    this.filesize = filesize;
  }

  public MultipartFile getFilenameMF() {
    return filenameMF;
  }

  public void setFilenameMF(MultipartFile filenameMF) {
    this.filenameMF = filenameMF;
  }

}
```

- Utility.java 수정
  - 운영체제 반드시 고쳐주어야함(윈도우 버전까지 맞아야함)

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
        } else if (os.equals("windows 11")) {//운영체제 반드시 고쳐주어야함
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

- UploadBbs.java 추가
  - if문의 path는 실제 내 storage의 폴더 경로 및 이름이 정확히 맞아야함
  - storage폴더는 내가 파일을 업로드 했을 경우 업로드한 파일이 저장될 위치임 

```java
import java.io.File;
 
public class UploadBbs {
    /** 페이지당 출력할 레코드 갯수 */
    public static int RECORD_PER_PAGE = 3;
 
    // Windows, VMWare, AWS cloud 절대 경로 설정
    public static synchronized String getUploadDir() {
        String path = "";
        if (File.separator.equals("\\")) {
            path = "D:/aistudy/deploy/bbs/storage/";
            //실제 내 storage폴더의 경로로 수정해주어야함
            System.out.println("Windows 11: " + path);
            
        } else {
            // System.out.println("Linux");
            path = "/home/ubuntu/deploy/bbs/storage/";
        }
        
        return path;
    }
    
}
```