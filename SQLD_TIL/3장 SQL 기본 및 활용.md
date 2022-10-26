# 3장

###  SQL 기본 및 활용

- DML
  - select, update, insert, delete
- DDL
  - create, drop, alter, rename
- DCL
  - revoke : 권한제거
    - revoke 권한 on 객체명 from 계정명
    - `revoke` select `on` tableA `from` adminA;
  - grant : 권한부여
    - grant 권한 on 객체명 to계정명
    - `grant` create `on` tableA `from` adminA;

- TCL
  - commit : 컷밋
    - 커밋을 하게되면 rollback 해도 지워지거나 복구되지 않음
    - DML의 작업 결과만 변경됨
  - rollback : 마지막 커밋 직후로 돌림