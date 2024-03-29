# 3장

###  SQL 기본 및 활용

1. 2. 3. 4. 5. 

- DML
  - 데이터베이스 사용자가 응용프로그램이나 질의어를 통하여 저장된 데이터베이스를 실질적으로 접근하는데 사용
  - 호스트 프로그램속에 삽입되어 사용되는 DML 명령어들은 데이터 부속어(Data Sub Language)라고 함
  - select, update, insert, delete
- DDL
  - 스키마, 도메인, 테이블, 뷰, 인덱스를 정의하거나 변경 또는 제거할때 사용
  - create, drop, alter, rename
  - truncate
    - 테이블 내의 모든 행을 없앰
    - 로그를 남기지 않음
- DCL
  - revoke : 권한제거
    - revoke 권한 on 객체명 from 계정명
    - `revoke` select `on` tableA `from` adminA;
  - grant : 권한부여
    - grant 권한 on 객체명 to계정명
    - `grant` create `on` tableA `from` adminA;

- TCL
  - 논리적인 작업의 단위를 묶어 DML에 의해 조작된 결과를 작업단뒤(트랜젝션) 별로 제어하는 명령어
  - commit : 컷밋
    - 커밋을 하게되면 rollback 해도 지워지거나 복구되지 않음
    - DML의 작업 결과만 변경됨
    - 커밋을 하게 되면 dirty버퍼가 clear 됨
  - rollback : 마지막 커밋 직후로 돌림
  - savepoint : 저장점을 지정하여 롤백할때 저장 점까지만 롤백할수 있게 함
    - `savepoint 포인트명`
    - `rollback to 포인트명` 


6. 

- constraint 제약조건명
  - 테이블 생성시 마지막 컬럼에서 `,` 찍고 쓰면 됨
  - `alter table 테이블명 constraint 제약조건명 컬럼명;` 이렇게도 가능
- 제약조건 종류
  - primary key
    - not null && unique
    - 주키로 테이블당 한개만 생성가능
  - unique
    - 칼럼값이 해당테이블 전체에서 유일한값
    - null 가능
  - foreign key
    - 입력되어야 할 값이 다른 테이블 칼럼의 값 참조
    - 외래키로 테이블당 여러개 생성 가능
  - check
    - 해당조건을 만족하는 값으로만 입력을 제한
  - not null
    - 칼럼값이 null 값을 포함 하지 못하도록 지정
- 정답
  - mySQL의 vachar 와 Oracle의 varchar 와 varchar2는 같은 것
  - 추후에 오라클에서 varchar는 다른용도로 사용될 예정이라 varchar2에 저장하는 것이 좋다고 함 

```sql
create table product(

    prod_id varchar2(10) not null,
    prod_nm varchar2(100) not null,
    reg_dt date not null,
    regn_no number(10),
    constraint product_pk primary key(prod_id)

);
```

7. 

- 테이블 칼럼에 대한 정의 변경

  - 오라클

  ```sql
  alter table 테이블명 modify(
  
      컬럼명1 데이터유형(varchar2(), number() 등),
      컬럼명1 데이터유형(varchar2(), number() 등)    
  );
  ```

  - sql server
    - 한줄씩 만가능

  ```sql
  alter table 테이블명 alter column
  
  	컬럼명 데이터유형(varchar2(), number() 등);
  ```

8. 

- null
  - 공백문자, 0 과는 전혀다른 문자
    - ASCII코드
      - 0 ==> 48
      - blank ==> 32
  - 모르는 값(아직 정의되지 않은 미지의 값)
  - 값의 부재(조건에 맞는 데이터가 없을 때의 공집합과도 다름)
  - null은 is null, is not null로만 비교가능 이외에는 알수 없음을 반환함
  - null을 포함한 사칙연산의 결과는 null

9. 10. 

- 책필기 참조

11.

- 테이블명 지정 규칙
  - 반드시 영문으로 시작해야함
  - A~Z
  - a~z
  - 0~9
  - 특수 문자 `$, #, _`  세개만 가능
- 주의사항
  - 가능한 단수형으로 명명 할것
  - 다른테이블명과 중복되지 않을것
  - 한 테이블내에서는 컬럼명이 중복될 수 없음
  - 테이블 이름을 지정하고 컬럼들은 `()` 로 묶어서  지정
  - 컬럼들은 `,` 로 구분
  - 테이블 생성문은 `;` 로 끝남
  - 컬럼에 대해서는 다른 테이블까지 고려하여 데이터베이스 내에서는 일관성 있게 사용하는 것이 좋음
    - 데이터 표준화 관점
  - 컬럼뒤에 데이터 유형은 꼭지정되야함
  - 벤더별로 길이에 대한 한계가 있음
  - 벤더에서 사전에 정의한 예약어는 쓸수 없음

12. 

- index
  - 개념
    - 검색 속도를 높이기 위해 사용하난 하나의 기술
    - 인덱스 생성시 인덱스 테이블을 생성하여 관히
    - B-tree 인덱스가 일반적임
      - 인덱스 키(인덱스로 만들 테이블의 컬럼 값)
      - 인덱스 키에 해당하는 컬럼 값을 가진 테이블의 로우가 저장된 주소값으로 구성
  - `create [unique] index [스키마명] 인덱스명 on 테이블명(컬럼명,[컬럼명, 컬럼명...])`
  - select 사용 가능
  - 오라클에서는 alter사용 불가 수정시에는 삭제후 생성이 필요
  - 대괄호 부분은 생량가능
  - drop을 통해 삭제가능
  - 쿼리문이 빨리 동작하도록 할수는 있지만 전체적인 데이터 베이스의 성능 부하를 초래함
  - SQL문을 최대한 효율적으로 짠후 최후의 수단으로 사용 할수 있도록 할것
- 올바른 코드

```sql
create table EMP (

    EMP_NO varchar2(10) not null primary key,
    EMP_NM varchar2(30) not null,
    DEPT_CODE varchar2(4) not null,
    JOIN_DATE date not null,
    REGIST_DATE date null
);

create index IDX_EMP_01 on EMP(JOIN_DATE);
```

- 올바른 코드2

```sql
create table EMP (

    EMP_NO varchar2(10) not null,
    EMP_NM varchar2(20) not null,
    DEPT_CODE varchar2(4) DEFALUT '0000' not null,
    JOIN_DATE DATE not null,
    REGIST_DATE date
    
);

alter table EMP ADD CONSTRAINT EMP_PK primary key(EMP_NO);

create index IDX_EMP_01 on EMP(JOIN_DATE);
```

13. 

```sql
create table 학생 (
    
    학번 char(8) primary key,
    장학금 integer
                 
);

-- SQL1
select count(*) from 학생;
-- SQL2
select count(학번) from 학생;
```

- SQL1, SQL2 는 항상 같은 결과가나옴(유효한 튜플 삽입되었을 때)

14. 

- 외래키
  - 테이블 생성시 가능, 생성후에도 가능함
  - 외래키가 특정 PK에 참조되면 null값을 가질 수 없음
  - 한테이블에 여러개 존재가능
  - 외래키 값은 참조 무결성 제약을 받을수 있음

15. 

- 테이블 제약조건(constraint)
  - check 제약조건은 데이터베이스에서 데이터 무결성을 유지하기 위해 테이블의 특정 컬럼에 설정하는 제약
  - 기본키는 반드시 테이블당 한개만 가능
  - 고유키로 지정된 모든 컬럼들은 null값을 가질 수 있음
  - 외래키는 테이블간의 관계를 정의하기 위해 기본키를 다른테이블의 외래키가 참조하도록 생성한다

16.

- 테이블의 불필요한 컬럼 삭제
  - `alter table 테이블명 drop column 삭제할 칼럼명;`
  - 오라클에서는 테이블의 데이터가 많을때 많은 시간이 필요함

17. 

- `on update cascade`
  - 외래키로 참조 되어질때 값을 수정 할 수 있게 해주는 것
- `on delete cascade`
  - 외래키로 참조 되어 질 때 값을 삭제 할 수 있게 해주는 것

18. 

- 테이블명 변경 쿼리
  - `rename 테이블명 to 바꿀 테이블명;`

19.

- on delete : 부모를 삭제할때
  - cascade
    - 부모 삭제시 자식도 삭제
  - set null
    - 부모 삭제시 자식은 null 설정
  - set default
    - 부모삭제시 자식은 기본값 설정
  - restrict
    - 자식이 없는 경우만 부모 삭제
- on insert : 자식을 입력 할때
  - automatic
    - 부모 입력값이 없을 때 부모 입력후 자식입력
  - set null
    - 부모가 입력값이 없는 경우, 자식의 FK를 null로 입력
  - set default
    - 부모가 입력값이 없을때 FK를 기본값으로 입력
  - set dependent
    - 부모의 PK가 있는 경우만 자식입력
    - 역으로 말하면 부모에 PK가 없는경우 자식테이블에 데이터 입력을 허용하지 않음

20.

- int = integer ==> 정수
- number = 소수점 가능
- 오라클에서는 integer를 잘 사용하지 않음
  - 사용시 자체적으로 number로 변환해서 사용된다함
- 입력방법
  - `insert into 테이블명(컬럼명1, 컬럼명2, 컬럼명3...) values(값, 값, 값...);`
  - `insert into 테이블명 values(값, 값, 값...);`
    - 이경우에는 values에 모든 컬럼에 대한 값이 들어가야함

```sql
create table TBL(

    ID number primary key,
    AMT number not null,
    DEGREE varchar2(1)

);

-- 1번
insert into TBL values(1,100);
-- 2번
insert into TBL(ID, AMT, DEGREE) values (2, 200, 'AB');
-- 3번
insert into TBL(ID, DEGREE) values (4, 'X');
-- 4번
insert into TBL(ID, AMT) values (3, 300);
-- 5번
insert into TBL values (5, 500, null);
```

- 1번 
  - `insert into TBL(ID, AMT) values(1,100);` 로 변경해서 실행

- 2번
  - DEGREE자리의 입력 밧이 너무 큼(`varchar2(1)` 초과)
- 3번
  - AMT는 not null이며 누락됨

- 4번, 5번 맞는 문장

- 추가적으로 테스트

```sql
alter table TBL add test_alias varchar2(5);
-- 테스트 구문
insert into TBL(ID,AMT,test_alias)  values(2, 300, '안');
```

- 오류 없음
  - DEGREE는 null을 허용하기 때문

21. 

- 입력된 데이터 수정
  - `update 테이블명 set 수정을 원하는 컬럼명 = 수정되길 원하는 새로운 값;` 

- 테이블 만들기 및 구문 연습

```sql
BOARD
+---------------------------------+
| BOARD_ID: VARCHAR2(10) NOT NULL |
|---------------------------------|
| BOARD_NM: VARCHAR2(50) NOT NULL |
| USE_YN: VARCHAR2(1) NOT NULL    |
| REG_DATE: DATE NOT NULL         |
| BOARD_DESC: VARCHAR2(100) NULL  |
+---------------------------------+

create table BOARD(

    BOARD_ID: varchar2(10) not null primary key,
    BOARD_NM: varchar2(50) not null,
    USE_YN: varchar2(1) not null,
    REG_DATE: date not null,
    BOARD_DESC: varchar2(100) null
);

-- 1번
insert into BOARD values(1, 'Q&A', 'Y', sysdate, 'Q&A게시판');
-- 2번
insert into BOARD(BOARD_ID, BOARD_NM, USE_YN, BOARD_DESC) values('100', 'FAQ', 'Y', 'FAQ게시판');
-- 3번
update BOARD set USE_YN = 'N' where BOARD_ID ='1';
-- 4번
update BOARD set BOARD_ID = 200 where BOARD_ID = '100';
```

- 2번은 오류가 남
  - REG_DATE 컬럼은 null을 허용하지 않음

22.

```sql
고객                                                         주문
+-------------------------------+                            +--------------------------------+
| 고객_ID: VARCHAR2(20) NOT NULL |                 	  /		| 주문번호: VARCHAR2(20) NOT NULL |
|-------------------------------| --+-- -- -- -- -- --o+--   |--------------------------------|
| 고객명: VARCHAR2(20) NULL      |               	     \     | 고객_ID: VARCHAR2(20) NOT NULL  |
| 가입일시: DATE NOT NULL        |                            | 주문일시: DATE NOT NULL          |
+-------------------------------+                            +--------------------------------+

표: 주문데이터                                               표: 고객데이터
+----------- -+------------+-------------+				+------------+-------+-------------+
| 주문번호(PK) | 고객ID(FK)  | 주문일시      |              | 고객ID(PK)  | 고객명 | 가입일시	   |
+-------------+------------+-------------+              +------------+-------+-------------+
| 001         | C001       | 2013-12-24  |              | C001       | 홍길동 | 2013-12-12  |
+-------------+------------+-------------+              +------------+-------+-------------+
| 002         | C001       | 2013-12-25  |              | C002       | 이순신 | 2013-12-13  |
+-------------+------------+-------------+              +------------+-------+-------------+
| 003         | C002       | 2013-12-26  |
+-------------+------------+-------------+
| 004         | C002       | 2013-12-27  |
+-------------+------------+-------------+

-- SQL문 연습
CREATE TABLE 고객(

    고객_ID VARCHAR2(20) NOT NULL PRIMARY KEY,
    고객명 VARCHAR2(20) NULL,
    가입일시 DATE NOT NULL
    
);

CREATE TABLE 주문(

    주문번호 VARCHAR2(20) NOT NULL PRIMARY KEY,
    고객_ID VARCHAR2(20) NOT NULL,
    주문일시 DATE NOT NULL,
    
    CONSTRAINT FK_고객_ID FOREIGN KEY (고객_ID)
    REFERENCES 고객(고객_ID)
);

ALTER TABLE 주문 DROP CONSTRAINT FK_고객_ID;

ALTER TABLE 주문 ADD CONSTRAINT FK_001 FOREIGN KEY(고객_ID)
REFERENCES 고객(고객_ID) ON DELETE SET NULL;

-- 부모: 고객 // 자식: 주
INSERT INTO 고객 VALUES('C001', '홍길동', '2013-12-12');
INSERT INTO 고객 VALUES('C002', '이순신', '2013-12-13');

INSERT INTO 주문 VALUES('0001', 'C001', '2013-12-24');
INSERT INTO 주문 VALUES('0002', 'C001', '2013-12-25');
INSERT INTO 주문 VALUES('0003', 'C002', '2013-12-26');
INSERT INTO 주문 VALUES('0004', 'C002', '2013-12-27');

-- 문제의 조건
-- 1번
INSERT INTO 고객 VALUES ('C003', '강감찬', '2014-01-01'); -- 가능
-- 2번
INSERT INTO 주문 VALUES ('0005', 'C003','2013-12-28'); -- 불가
-- 3번
DELETE FROM 주문 WHERE 주문번호 IN('0001','0002'); --  가능
-- 4번
DELETE FROM 고객 WHERE 고객_ID = 'C002'; -- 불가
```

- 고객 테이블은 삭제에 있어 자유롭지 않음
- 주문 테이블은 입력에 있어 자유롭지 않음
- 상기사유는 주문테이블이 고객테이블의 고객ID를 외래키로 참조하며 기본키로 지정하고있기 때문
- 2번
  - 'C003' 고객ID가 고객테이블에 존재하지않음
  - 1번 조건 실행시 가능함
- 4번
  - 'C002' 고객ID가 주문테이블에서 참조되어 지고 있음
  - 주문테이블의 'C002' 참조 데이터 삭제시 가능

23. 

- `DELETE FROM STADIUM;`
  - 삭제 데이터에 대한 로그를 남겨줌
  - 복구가능(ROLLBACK)
- `DELETE * FROM STADIUM;`
  - sysntax error
- `TRUNCATE TABLE STADIUM;`
  - 테이블 초기화로 복구가 불가능함
- `DROP TABLE STADIUM;`
  - DDL명령어로 복구 불가능(ROLLBACK불가)

24.

- select 관련 옵션
  - `SELECT ALL/DISTINCT 컬럼명1, 컬럼명2,... FROM 해당컬럼이 있는 테이블명`
  - ALL = *
    - 사용시 컬럼명 입력 안해도됨
    - 원하는 컬럼만 보고 싶다면 사용하면 안됨
  - DISTINCT
    - 중복된 데이터가 있는 경우 1건으로 처리해서 출력

25. 

- TRUNCATE TABLE
  - 테이블에 들어 있는 모든 행들이 삭제됨
  - 테이블삭제가 아님
  - 데이터를 삭제하므로 디스크 사용량도 초기화됨
  - 테이블 구조까지 완전 삭제를 위해서는 `DROP` 을 사용해야함
  - UNDO를 위한 데이터를 생성하지 않음
  - DELETE보다 빠름
  - AUTO 커밋됨

26. 

- DELETE
  - 데이터 삭제
  - 사용자가 커밋해야 됨
- DROP
  - AUTO 커밋됨

27. 

- 트랜젝션 특성(목표)
  - 원자성(Atomicity)
    - 트랜젝션에서 정의된 연산들은 모두 성공적으로 실행되던지 전혀 실행이 되지 않은 상태로 남아있어야함
    - ALL OR NOTHING
  - 일관성(Consistency)
    - 트랜젝션이 실행되기 전의 데이터 베이스 내용이 잘못되어 있지 않다면 트랜잭션이 실행된 이후에도 데이터베이스의 내용에 잘못이 있으면 안됨
  - 고립성(Isolation)
    - 트랜젝션이 실행도중에 다른 트랜잭션의 영향을 받아 잘못된 결과를 만들어서는 안됨
  - 지속성(Durability)
    - 트랜잭션이 성공적으로 수행되면 그트랜잭션이 갱신한 데이터베이스의 내용은 영구적으로 저장됨

28. 

- 트랜잭션에 대한 격리성이 낮다
  - 다른 트랜잭션의 영향을 많이 받음

- Dirty-Read
  - 다른 트랜잭션에 의해 수정되었고 커밋전 데이터를 읽는것
- Non-Repeatable read
  - 한 트랜잭션 내에서 같은 쿼리를 두번 수행했는데,  그사이에 다른 트랜재션이 값을 수정 또는 삭제하는 바람에 두 쿼리 결과가 다르게 나타나는 현상
- Phantom Read
  - 한 트랜잭션 내에서 같은 쿼리를 두번 수행 했는데, 첫번째 쿼리에서 없던 유령 레코드가 두번째 쿼리에서 나타나는 현상

29. 

- COMMIT
  - 테이블내 데이터 변경사항(입력, 수정, 삭제)에 대하여 확정
- ROLLBACK
  - 테이블내 데이터 변경사항(입력, 수정, 삭제)에 대한취소
  - 마지막 COMMIT 직후의 상태로 돌아감
- SQL SERVER에서는 ROLLBACK 시 UPDATE도 취소 됨
- SQL SERVER에서는 CREATE도 ROLLBACK 가능함
  - 오라클에서는 CREATE는 ROLLBACK 안됨

30. 

- 트랜잭션
  - 데이터베이스의 논리적 연산단위로 밀접히 관련되어 분리될수 없는 한개 이상의 데이터베이스 조작
- COMMIT
  - 트랜잭션의 종료를 위한 대표적 명령어로 데이터에 대한 변경사항을 영구적으로 반영하는 명령어
- ROLLBACK
  - 데이터에 대한 변경사항을 모드 폐기하고 변경전의 상태로 되돌림

31. 

- BEGIN TRANSACTION
  - BEGIN TRAN 도 가능
  - 트랜잭션시작
  - COMMIT / ROLLBACK 으로 종료

32. 

- SAVE POINT
  - SAVE POINT 사용시 ROLLBACK 을 만나게되면 현시점에서 SAVE POINT 까지만 ROLLBACK 가능
  - 오라클
    - `SAVEPOINT 세이브포인트명;`
    - `ROLLBACK TO 세이브포인트명;`
  - SQL SERVER
    - `SAVE TRANSACTION 세이브포인트명;`
    - `ROLLBACK TRANSACTION 세이브포인트명;`

33.  34.

- WHERE

  - FROM절 다음에 위치함
  - 컬럼명
    - 보통 WHERE 조건절의 좌측에 위치
  - 비교연산자
  - 문자, 숫자, 표현식
    - 보통 WHERE 조건절의 우측에 위치
  - 비교 칼럼 명(JOIN 사용시)

  - 예시

```sql
EMP_TBL                                            
+-------+-------+
| EMPNO |  SAL  | 
+-------+-------+
| 100   |  1500 |
+-------+-------+
| 200   |  3000 | 
+-------+-------+
| 300   |  2000 |
+-------+-------+

SELECT COUNT(*) 
FROM EMP_TBL 
WHERE EMPNO > 100 AND SAL >= 3000 OR EMPNO = 200;

-- 실행시 1이 출력됨
-- EMPNO > 100 AND SAL >= 3000 ==> 0개
-- EMPNO = 200 ==> 1개
```

35. 

```SQL
TAB_A(레코드 3건)
+--------+--------+--------+
|  COL1  |  COL2  |  COL3  |
+--------+--------+--------+
|   30   |  NULL  |   20   |
+--------+--------+--------+
|  NULL  |   10   |   40   |
+--------+--------+--------+
|   50   |  NULL  |  NULL  |
+--------+--------+--------+

SELECT COL1 + COL3 FROM TAB_A;
-- 결과값은
-- 50
-- NULL
-- NULL
-- 연산은 같은 행끼리만 이루어짐
```

- NULL값과의 모든 연산의 결과는 모두 NULL 임
- NULL은 크다 작다를 표현 할 수 없음

36. 

- 부정 비교연산자
  - != : 같지 않다
  - ^= : 같지 않다
  - <> : 같지않다
    - ISO 표준, 모든 운영체제에서 사용가능
  - NOT 컬럼명 = : ~와 같지 않다
  - NOT 컬럼명 > : ~와 크지 않다
- 비교대상  IS NOT NULL
  - NULL이 아니다

37. 

```SQL
CREATE TABLE 서비스 (

    서비스번호 VARCHAR2(10) PRIMARY KEY,
    서비스명 VARCHAR2(100) NULL,
    개시일자 DATE NOT NULL
);

-- DML
INSERT INTO 서비스 VALUES ('999', '', '2015-11-11');
SELECT * FROM 서비스 WHERE 서비스명 IS NULL;
```

- SQL SERVER에서 해당 INSERT문은 데이터가 조회 되지 않음
- ORACLE 에서는 공백 `''` 은 NULL 로 처리하므로 데이터가 조회됨
  - 단, `' '` 은 공백으로 처리함

38. 

- BETWEEN a AND b
  - a와 b 사이의 값
  - a, b 도 포함됨
- IN
  - WHERE 절에서 사용 쓰임
  - 리스트값 중에서 어느하나라도 일치하면 됨
  - `SELECT * FROM 테이블명 WHERE 컬럼명 IN (값1, 값2, 값3, ...);`

39. 

- 형변환 함수
  - TO_CHAR
    - 날짜형 또는 숫자형을 문자형으로 변환
  - TO_NUMBER
    - 문자형을 숫자형으로 변환
  - TO_DATE
    - 문자형을 날짜형으로 변환

- 각종 형변환 함수 참조 사이트
  - https://blog.naver.com/jiae7634/222857895703
