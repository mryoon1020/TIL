# ORACLE DATABASE 21c express edition

- 오라클 데이터 베이스는 21c 기업용 버전(enterprise edition)과 express edition(이하 xe) 이 있음
- xe버전은 기업용 버전보다 가벼우며(일부기능만 제공) 완전 무료제품
- 설치시 반드시 경로에 한글이 없어야함
  - 때에 따라서는 윈도우 사용자 계정명이 한글일경우에도 오류가 난다고 함
- 본인은 실수로 기업용 버전을 설치하여 쓰고있었음
  - 오라클 실습목적으로 설치한거라 매우 적은 데이터를 입력했음
  - 혹시나 싶어서 삭제후 xe 버전으로 재설치함
  - 자료를 찾으며 정상구동까지 약 4시간정도 시간이 소모되어 기록을 남김 

### 재설치 과정에서 나타난 다양한 이슈들 및 해결

- 윈도우 11기준
- 재설치를 위해서는 완벽한 삭제의 과정이 필요함
- 오라클 자체에서는 uninstall 툴을 제공하지 않는것으로 보임
- 참조 사이트들
  - https://www.youtube.com/watch?v=z4ZowjA0p5k
    - step by step 으로 따라했음
    - 주요 내용 골자는 다음과 같음
      1. 시작메뉴 우클릭 또는 실행을 통해 컴퓨터관리를 켬
      2. 좌측 제일 하단의 서비스 및 응용프로그램 텝에 들어있는 서비스 클릭
      3. oracle로 시작하는 모든 것을 전부 마우스 우클릭 후 중지처리 함
      4. 시스탬 => 시스탬 고급설정 => 고급 텝 => 환경변수 설정 => 시스템 변수안에 있는 Path더블클릭
      5. ORACLE 경로 삭제
      6. 실행 ==> regedit
      7. 여기서부터는 주의가 필요함(레지스트리를 직접건드리기 떄문에 엉뚱한거 삭제시 **컴퓨터가 망가질수 있음**)
      8. 레지스트리 편집기 좌측 에서 HKEY_LOCAL_MACHINE => SYSTEM => CurrentControlSet => Services로 들어감
      9. Oracle로 시작하는 모든폴더 확인
      10. 폴더 확인하면서 db_home 과 SID의 위치도 확인할 것
      11. Oracle 로 시작하는 모든 폴더 삭제후 레지스트리 편집기 닫기
      12. 재부팅(레지스트리를 건드렸으므로 반드시 재부팅 해야한다고함)
      13. 아까 확인한 Oracle DB_HOME 과 SID 위치 확인하여 삭제하면 끝.
      14. 참고로 D:에 설치했었어도 C:의 Program Files에도 oracle이 들어 있으므로 삭제해야함
      15. C: => user(사용자) => 현재 계정 폴더 더블클릭 하면 Oracle이라는 폴더가 존재할수도 있음, 필자는 있어서 삭제 해줬음
      16. 혹시나 싶어서 상기 과정 완료후 휴지통 비우기 후 재부팅 해줬으나 딱히 필요없는 과정인듯함
  - https://hello-ming.tistory.com/entry/Oracle-oracle-database-express-edition-%EC%84%9C%EB%B9%84%EC%8A%A4%EA%B0%80-%EC%9D%B4%EB%AF%B8-%EC%9E%88%EC%8A%B5%EB%8B%88%EB%8B%A4-%ED%95%B4%EA%B2%B0%EB%B2%95
    - 재설치과정에서 **이 시스템에는 oracle database express edition 서비스가 이미 있습니다** 라는 오류가 뜨며 설치가 되지 않을 때 다음과 같이 함 상기 동영상을 보며 오라클을 삭제했음을 전제로 함
    - cmd 관리자 권한 실행 ==> `sc delete OracleServiceXE` 

### 재설치 완료후 처리했던 것들

- 쿼리문 마지막에 `;` 반드시 붙여줄 것
- 시스탬 계정을 사용하는 것은 너무 위험하다고 하여 새로운 user 계정을 만듬
- cmd => sqlplus 입력 후 엔터 => 사용자 ID: SYSTEM 비밀번호: 설치할 때 입력했던 비밀번호 로그인
- `create user c##하고싶은이름 indentified by 비밀번호;`
  - 12c 이상부터는 계정에 c##이 반드시 포함되어야된다고 함, oracle sql developer도 마찬가지
- 상기 쿼리의 한글로 된부분에 원하는 걸로 바꿔서 하면 됨
- `grant connect, resource to c##생성된 계정명;`
  - resource 권한은 개체생성, 변경, 제거 권한임
  - 참조사이트(https://viera.tistory.com/9)
- 참조사이트(https://sosocodingday.tistory.com/36)

- resource 권한후에도 insert문에서 오류가 난다면?
  - 오류코드 ORA-01950 일경우
    - cmd => sqlplus 입력 후 엔터 => 사용자 ID: SYSTEM 비밀번호: 설치할 때 입력했던 비밀번호 로그인
    - `alter user [유저명] default tablespace users quota unlimited on users;` 실행
    - 참조사이트(https://javacatcher.tistory.com/23)

### 컴퓨터 종료후 Oracle SQL Developer로 접속이 되지 않을 때

- 상태: 실패 -테스트 실패: IO 오류: The Network Adapter could not establish the connection

- 상기와 같은 오류코드가 날 수도 있음

- 해결방법

  - 시작메뉴 우클릭 또는 실행을 통해 컴퓨터관리를 켬
  - 좌측 제일 하단의 서비스 및 응용프로그램 텝에 들어있는 서비스 클릭

  - OracleJobScheduler, OracleVssWriterXE 제외 전부 실행으로 바꿔주면 됨

- 해당오류의 경우 다양한 해결방법이 있었으나 본인은 다 안되고 이방법만 됬음