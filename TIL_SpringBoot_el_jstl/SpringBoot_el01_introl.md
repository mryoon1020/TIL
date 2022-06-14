# EL(Expression Language)

### 특징

- 값이 없는 경우 null을 출력하지 않음
- `${}` 를 통해서 사용
- 코드가 단순해짐
- 사용하기 위해 build.gradle 수정 필요
  - dependencies에 하기 2줄 추가 후 refresh gradle

```gradle
dependencies {
	implementation 'javax.servlet:jstl'
    implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'
}
```

### JSP 내부 객체에 대한 EL 객체

| EL(Expression Language, 표현언어) | 속성 |
| --------------   | ------------------------------ |
| pageContext      | PageContext 객체               |
| pageScope        | page 영역에 포함된 객체        |
| requestScope     | request 영역에 포함된 객체     |
| sessionScope     | session 영역에 포함된 객체     |
| applicationScope | application 영역에 포함된 객체 |
| param            | HTTP의 파라미터들              |
| paramValues      | 하나의 파라미터의 값들         |
| header           | 헤더 정보들                    |
| headerValues     | 하나의 헤더의 값들             |
|cookie			|	쿠키들 					|
| initParam|컨텐츠의 초기화 파라미터들 |

 ### EL 객체의 사용예

| 예시                               | 의미                                                 |
| ---------------------------------- | ---------------------------------------------------- |
| ${pageContext.request.requestURI}  | request URI                                          |
| ${sessionScope.profile}            | session 영역에서 profile이라는 이름으로  저장된 객체 |
| ${param.productId}                 | productId라는 이름의 파라미터 값                     |
| ${paramValues.productId}           | productId라는 이름의 파라미터 값들                   |
| ${pageContext.request.contextPath} | Context Path 추출                                    |

