# Intro.

들어가기 앞서...

 본 기록은 현재 진행중인 프로젝트에서 JWT를 통한 인증을 구현하며

생긴 여럿오류들의 원인파악을 좀 더 수월하게 하기위해 ChatGPT와 여러 무료 강좌와 블로그들을

참고하여 Toy Project보다 더 작은 연습용(테스팅용) 프로젝트를 만들어 보았습니다.

# JWT 란?

https://jwt.io/introduction 인용 글

- JSON Web Token 의 약자로 당사자 간에 정보를 JSON 개체로 안전하게 전송하기 위한 간결하고 독립적인 방법을 정의하는 개방형 표준( RFC 7519 )입니다.
- Secret(**HMAC** 알고리즘을 사용) 또는 RSA 또는 ECDSA를 사용한 공개키/개인키 를 활용하여 서명할수 있습니다.
- 인증 및 정보교환의 용도로 사용할 수 있습니다.
- Header \/ Payload \/ Signature 총 세부분으로 구성 되어있습니다.

# JWT 구조?

https://jwt.io/introduction 인용 글

- Header
  - 어떤 알고리즘이 사용되었는지 기록합니다. (HMAC SHA256 또는 RSA 등)
- Payload
  - Claim을 담는 공간입니다.
  - Claim은 Entity에 대한 설명으로 entity(보통은 유저)와 추가 데이터를 담습니다.
  - Claim 은 registered, public, private 세가지로 구분됩니다.
    - registered claim
      - 필수는 아니지만 미리 정의된 클레임 집합
      - 발급처, 만료시간, 주제, 대상 등의 정보를 담음
    - public claim
      - JWT를 사용하는 사람들이 원하는 대로 정의할 수 있는 클레임
      - 충돌방지가 필요(URI 정의 또는  IANA JSON Web Token Registry 등의 도구 사)
    - private claim
      - 커스텀 클레임으로 정보공유를 위해 생성
      - 구성원간의 약속에 따라 정의됨
- Signature
  - 토큰이 변경되지 않았음을 확인할 때 사용
  - Signature를 생성하려면 인코딩된 header, payload, secret 그리고 헤더에 사용된 알고리즘 필요
  - 메시지가 도중에 변경되지 않았는지 확인하는 데 사용
  - 개인 키로 서명한 토큰의 경우 JWT의 발신자가 자신이 말하는 사람인지 확인 가능
  - 예를들어  HMAC SHA256 algorithm를 사용하고 싶다면 다음과 같이 하면 됨

```jwt
HMACSHA256(
  base64UrlEncode(header) + "." +
  base64UrlEncode(payload),
  secret)
```

- 이렇게 Signature까지 추가되면 JWT가 완성이 됩니다.

# Setting

- 라이브러리를 추가합니다.
- https://mvnrepository.com/ 여기서 찾으면 됩니다.
- Spring에서 가장 많이 쓰이는 것은 jjwt-api 라고 합니다.(ChatGPT 피셜)

```gredle
    // jwts 라이브러리
    implementation group: 'io.jsonwebtoken', name: 'jjwt-api', version: '0.11.5'
    runtimeOnly group: 'io.jsonwebtoken', name: 'jjwt-impl', version: '0.11.5'
    runtimeOnly group: 'io.jsonwebtoken', name: 'jjwt-jackson', version: '0.11.5'
```

- application.properties 에서 secret-key를 작성합니다
  - ChatGPT 피셜 : class에 하드코딩하면 노출될 수도 있으므로 설정 파일에서 읽어오는 것을 추천한다고 합니다.
  - 여기서 `주의할 점`은 절대로 `""` 또는 `''` 안에 작성하면 `안됩니다.`
    - 나중에 BASE64로 인코딩할 때 문자열로 인식하지 못합니다. 
  - secret-key는 256 bit 보다 길어야 합니다.
    - HMAC-SHA256 과 같은 암호화 알고리즘을 사용해야 하기 때문입니다.
    - 각 알고리즘에서 권장하는 글자 수를 맞추면 됩니다. (256, 384, 512)
      - 각 알고리즘보다 길어지면 예기치 못한 오류가 발생할 수 있다고 합니다.
    - 256 bit \/ 8bit(알파벳, 숫자(ASCII 문자 집합 포함 글자 들) = 8bit = 1byte) => 32 byte => 32 글자입니다.

```properties
jwt.secret=비밀키
```

- properties 값은 다음과 같이 꺼내서 쓰면 된다

```java
 @Value("${jwt.secretKey}")
    String accessSecret;
```
