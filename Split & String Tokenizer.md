# Split & String Tokenizer

### 개념

- 문자열이 특정 구분자로 연결되어 있을 경우 구분자를 기준으로 분리를 할수있음
  - String의 split()메소드를 사용하거나 java.util.StringTokenizer 클래스 이용

### Spilt

- 정규표현식을 구분자로 해서 문자열을 분리 및 배열에 저장,리턴함

  - ex) 띄어쓰기를 구분자로 사용할 경우

    `String[] result = "길길동 박길동 홍길동".split(" ");`

  - ex) & , 쉼표(,) ,- 를 구분자로 사용할 경우

  - `|` 를 통해 연결해주면 됨

    `String[] result = "길길동&박길동,홍길동-가길동".split("&|,|-");`

### String Tokenizer

- 문자열이 한 종류의 구분자로 연결되어있을 경우 토큰으로 분리

  - ex) 띄어쓰기를 구분자로 사용할 경우 구분자를 생략하면됨

    `StringTokenizer st = new StringTokenizer("홍길동 김길동 박길동");`

  - ex) /가 구분자로 지정되어있는경우

    `StringTokenizer st = new StringTokenizer("홍길동/김길동/박길동","/");`

  - for문 돌릴때 주의사항

    - for문의 조건 에 직접 `st.countTokens()` 사용 불가
    - token이 꺼내지게 되면 갯수가 줄어들어 분리된 문자열이 출력되지 않음
    - 잘못 된 조건:  `for(int i=0; i<st.countTokens() ;i++)`
    - for문이 끝까지 잘돌아서 분리된 문자열이 출력된 예제

  ```java
  import java.util.StringTokenizer;
  
  public class Main {
   
    public static void main(String[] args) {	
  
  	StringTokenizer st = new StringTokenizer("홍길동 김길동 박길동");
  
      int ct = st.countTokens();
      
      for(int i=0; i< ct;i++) {
      	System.out.println(st.nextToken());
      }
    }
  }
  ```

  - String Tokenizer의 메소드
    - int : countTokens() : 꺼내지 않고 남아있는 토큰의 수
    - boolean : hasMoreTokens() : 남아있는 토큰이 있는지 확인 (O : true // X : false)
    - String : nextToken() : 토큰을 한개씩 꺼내옴
  - while문을 사용하여 토큰꺼내기

  ```java
  import java.util.StringTokenizer;
  
  public class Main {
   
    public static void main(String[] args) {	
   
        StringTokenizer st = new StringTokenizer("홍길동/김길동/박길동","/");
        while(st.hasMoreTokens()){
            System.out.println(st.nextToken());
        }
    }
  }
  ```

  