# RestController를 이용한 댓글 목록

### REST 개요

- REST => Representational State Transfer

- URI는 하나의  고유한 리소스(Resource)를 대표하도록 설계된다는 개념에 전송방식을
    결합해서 원하는 작업을 지정함

- REST서비스에서는 CRUD에 해당하는 4개의 HTTP메소드가 있음

  | Http Method | CRUD   | 기능 |
  | ----------- | ------ | ---- |
  | Post        | Create | 생성 |
  | Get         | Read   | 조회 |
  | Put         | Update | 갱신 |
  | Delete      | Delete | 삭제 |

- RestController는 기존 Controller 와 ResponseBody를 합친 모습과 같음

- RestController는 기존 Controller가 기존 JSP경로를 리턴 했다면 RestController는 순수한 데이터를 반환

- 일반 문자열, JSON, XML등의 다양한 포맷의 데이터를 전송함

- RestController에서 사용가능한 요청 annotation

  - @RequestMapping // @GetMapping // @PostMapping // @PutMapping // @DeleteMapping

- @RequestBody : json 파일로 넘어온것을 우리가 원하는 타입으로 받을수 있게해줌

  

### 댓글처리 구현 셋팅

#### MySQL 테이블 생성 및 쿼리 작성

- ##### 부모테이블: bbs(외래키(foreign key) 제공))

```mysql
create database webtest;
use webtest;
CREATE TABLE bbs ( 
  bbsno     int NOT NULL auto_increment primary key,   -- 글 일련 번호
  wname     VARCHAR(20)      NOT NULL,   -- 글쓴이 
  title     VARCHAR(100)        NOT NULL,   -- 제목(*) 
  content   VARCHAR(4000)    NOT NULL,   -- 글 내용 
  passwd    VARCHAR(15)      NOT NULL,   -- 비밀 번호 
  viewcnt   int                    DEFAULT 0,  -- 조회수, 기본값 사용 
  wdate     DATE                NOT NULL,   -- 등록 날짜,  
  grpno     int                   DEFAULT 0,  -- 부모글 번호 
  indent    int                   DEFAULT 0,  -- 답변여부,답변의 깊이
  ansnum    int                 DEFAULT 0  -- 답변 순서 
);   
```

- 자식테이블: reply(외래키 사용(포함))

  - bbsno 컬럼(외래키)은 bbs table의 bbsno의 레코드를 갖음

     => `foreign key(bbsno) references bbs(bbsno)` 

```mysql
use webtest;
 
create table reply(
   rnum int not null auto_increment primary key,
   content varchar(500) not null,
   regdate date not null,
   id varchar(10) not null,
   bbsno int(7) not null,
   foreign key(bbsno) references bbs(bbsno)
);
 
select * from bbs;
 
insert into reply(content, regdate, id, bbsno)
values('의견입니다.',sysdate(),'user1',34);
 
 
-- list(목록)
select rnum, content, regdate, id, bbsno
from REPLY
where bbsno = 34
order by rnum DESC
limit 0, 3;
 
 
-- total(목록)
select count(*) from reply
where bbsno = 34;
```

##### Model 생성 및 reply.xml 생성

경로: **spring_bbs/src/main/java/com.study.model**

- replyDTO.java 생성

```java
public class ReplyDTO {
  private int rnum;
  private String content;
  private String regdate;
  private String id;
  private int bbsno;
 
  public int getRnum() {
    return rnum;
  }
 
  public void setRnum(int rnum) {
    this.rnum = rnum;
  }
 
  public String getContent() {
    return content;
  }
 
  public void setContent(String content) {
    this.content = content;
  }
 
  public String getRegdate() {
    return regdate;
  }
 
  public void setRegdate(String regdate) {
    this.regdate = regdate;
  }
 
  public String getId() {
    return id;
  }
 
  public void setId(String id) {
    this.id = id;
  }
 
  public int getBbsno() {
    return bbsno;
  }
 
  public void setBbsno(int bbsno) {
    this.bbsno = bbsno;
  }
}
```

- ReplyMapper.java 생성

```java
public interface ReplyMapper{
}
```

- BbsService.java 생성

```
public interface BbsService {
}
```

- ReplyServiceImpl.java 생성

```java
@Service("com.study.model.ReplyServiceImpl")
public class ReplyServiceImpl implements ReplyService {
  @Autowired
  private ReplyMapper mapper;
}
```

경로: **spring_bbs/src/main/resources/static/js**

- producer.js 생성
- consumer.js 생성
