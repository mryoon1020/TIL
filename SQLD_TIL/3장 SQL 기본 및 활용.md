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
