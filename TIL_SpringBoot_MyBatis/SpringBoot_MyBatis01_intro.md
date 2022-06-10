# Spring Boot MyBatis 활용

### 특징

- 짧은 자바 코드로 DB연동을 함 
- SQL 명령어를 자바코드에서 분리하여 XML 파일에 따로 관리
- 이식성이 좋아 어떤 프로그래밍 언어로도 구현 가능
- 무료
- DAO가 필요없음

### 구성

- Configuration 파일(SqlMapConfig.xml)
  - Mybatis 메인 환경설정을 정의
  - DB설정및 mapper 설정등을 함
  - DB 설정은 별도의 properties 파일로 분리가능
  - mapper는 SQL query를 xml문서로 분리한 것 

- 매퍼(Mapper)

  - 매퍼를 정의방법 2가지

    - SQL을 XML에 정의된 XML파일로 생성

    - SQL을 메소드에 어노테이션으로 정의한 인터페이스로 생성 

- 매핑구문(Mapped Statements)
  - SQL을 DB에 실행할 구문
  - 매핑 구문은 두가지(어노테이션 정의, XML정의)

- Mybatis Java API

  - SqlSession 는 Mapper xml에 등록된 SQL구분을 실행

  - SqlSession 객체는 SQL구문 실행을 위한 여러가지 메소드 제공

    ex) selectOne(), selectList(), insert(), update(), delete() 등