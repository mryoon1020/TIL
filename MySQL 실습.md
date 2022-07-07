# MySQL 실습

```mysql
-- 설문 제목 저장 테이블

use webtest;
CREATE TABLE research (                        
       researchnum  int(5) NOT NULL auto_increment,        -- 설문 일련 번호
       content      VARCHAR(200) NOT NULL,     -- 설문 제목
       PRIMARY KEY (researchnum)                     -- PK 지정
);
 
 
-- 설문 선택 항목 저장
CREATE TABLE researchitem (                    
       researchitemnum     int(5) NOT NULL auto_increment,                   -- 일련번호
       num                 int(1) NULL,                               -- 설문 선택 번호
       content             VARCHAR(100) NULL,                        -- 항목 내용
       count               int(5) DEFAULT 0 NOT NULL,     -- 선택 수
       researchnum         int(5) NOT NULL ,                  -- FK 컬럼
       PRIMARY KEY (researchitemnum) ,                              -- PK 지정
       FOREIGN KEY (researchnum)  REFERENCES research(researchnum)                       -- FK 지정
);

-- 에러나야 정상, researchnum 컬럼에 1이 등록되어 있어야 함

INSERT INTO researchitem(num,content,researchnum) 
VALUES(1, '20~24 ', 1);

-- PK테이블에 레코드 등록
 
   INSERT INTO research(content)
   VALUES('당신이 결혼하고자하는 연령대는 언제쯤입니까?');
 
   SELECT * FROM research;

 -- 부모키가 등록이 되어 있음으로 FK테이블에 레코드를 추가할 수 있음 
      
   INSERT INTO researchitem(num,content,researchnum)
   VALUES(1, '25세이하', 1);
 
   INSERT INTO researchitem(num,content,researchnum)
   VALUES(2, '26~29', 1); 
   
   INSERT INTO researchitem(num,content,researchnum)
   VALUES(3, '30~32', 1);
 
   INSERT INTO researchitem(num,content,researchnum)
   VALUES(4, '33~36', 1);   
 
   INSERT INTO researchitem(num,content,researchnum)
   VALUES(5, '독신 주의', 1);     
     
   SELECT * FROM researchitem ORDER BY num;
   
   -- 또다른 설문의 등록, PK테이블에 레코드 등록

  INSERT INTO research(content)
  VALUES('자바를 이용하여 개발하고 싶은 분야는?');
 
  SELECT * FROM research ORDER BY researchnum;

 -- 부모키가 등록이 되어 있음으로 FK테이블에 레코드를 추가할 수 있음 
     
   INSERT INTO researchitem(num,content,researchnum)
   VALUES(1, 'Web Content', 2);
   INSERT INTO researchitem(num,content,researchnum)
   VALUES(2, 'Network', 2);     
   INSERT INTO researchitem(num,content,researchnum)
   VALUES(3, 'RCP, Swing, C/S', 2);
   INSERT INTO researchitem(num,content,researchnum)
   VALUES(4, 'Graphics', 2);     
   INSERT INTO researchitem(num,content,researchnum)
   VALUES(5, 'Game', 2);     
   INSERT INTO researchitem(num,content,researchnum)
   VALUES(6, 'Mobile', 2);     
     
   SELECT * FROM researchitem ORDER BY researchnum, num;

  
 -- 또다른 설문의 등록, PK테이블에 레코드 등록

   INSERT INTO research(content)
   VALUES('하루 코스로 주말에 가장 가고 싶은 유원지는?');
 
   SELECT * FROM research ORDER BY researchnum;

 -- 부모키가 등록이 되어 있음으로 FK테이블에 레코드를 추가할 수 있음 
   
   INSERT INTO researchitem(num,content,researchnum)
   VALUES(1, '석모도', 3);
   INSERT INTO researchitem(num,content,researchnum)
   VALUES(2, '대부도', 3);     
   INSERT INTO researchitem(num,content,researchnum)
   VALUES(3, '월미도', 3);
   INSERT INTO researchitem(num,content,researchnum)
   VALUES(4, '영흥도', 3);     
   INSERT INTO researchitem(num,content,researchnum)
   VALUES(5, '강화도', 3);     
   INSERT INTO researchitem(num,content,researchnum)
   VALUES(6, '영종도', 3);     
     
   SELECT * FROM researchitem ORDER BY researchnum, num;
   
 -- 1번 설문조사만 검색하는 경우
 
   SELECT * FROM researchitem 
   WHERE researchnum = 1
   ORDER BY num ASC;
   
 -- JOIN
 
 -- 기본형
 
 SELECT r.researchnum, r.content, i.researchitemnum, i.num, i.content, 
   i.count 
FROM research r inner join researchItem i
WHERE r.researchnum = i.researchnum
ORDER BY researchnum DESC, researchitemnum ASC;

-- 같은결과 출력됨

SELECT r.researchnum, r.content, i.researchitemnum, i.num, i.content, 
   i.count 
FROM research r, researchItem i
WHERE r.researchnum = i.researchnum
ORDER BY researchnum DESC, researchitemnum ASC;

-- JOIN과 함께 추가적인 조건이 동반되는 경우 "AND"를 이용
-- 주말' 이란 단어가 들어간 레코드만 전부 출력

SELECT r.researchnum, r.content, i.researchitemnum, i.num, i.content, 
   i.count 
FROM research r inner join researchItem i
WHERE (r.researchnum = i.researchnum) and (r.content LIKE '%주말%'); 

-- 1번 질문에 대한 항목만 출력

SELECT r.researchnum, r.content, i.researchitemnum, i.num, i.content, 
   i.count 
FROM research r inner join researchItem i
WHERE (r.researchnum = i.researchnum) and (r.researchnum=1); 

-- Delete

-- 오류나야 정상, 자식 레코드가 있기 때문에 부모 먼저 삭제 불가
   
   DELETE FROM research WHERE researchnum=1;
   
 -- researchnum이 1인 자식 레코드(자식)를 먼저 삭제
   
   DELETE FROM researchitem WHERE researchnum=1;
 
   SELECT * FROM researchitem ORDER BY num;
   
-- 부모 레코드를 삭제합니다.  
   
   DELETE FROM research WHERE researchnum=1;   
   
   SELECT * FROM research;
   SELECT * FROM researchitem;
```

