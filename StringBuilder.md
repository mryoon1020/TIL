# StringBuilder

### 사용이유

- 성능저하를 막기 위함

  - String은 내부의 문자열을 수정할 수없음

    ```
    ex)
    String a = "a";
    a += "b";
    상기 연산시 String 객체가 새로 생성되며 변수 a는 새로 생성된 String 객체를 참조하게 됨
    ```

  - +연산이 많아질수록 메모리 할당해제가 더 많이 일어 나므로 성능이 저하됨
  - StringBuffer/StringBuilder를 사용하면 새로운 String객체 생성없이 사용가능
  - StringBuffer : 멀티쓰레드 환경에서 사용할 수 있도록 동기화가 적용되어있음
  - StringBuilder: 단일쓰레드 환경에서만 사용할 수 있음

- 메소드

  - append() : 문자열 끝에 데이터 추가
  - insert() : 문자열 중간에 데이터 추가, insert(문자위치, "")
    - 0: 맨앞에 추가 1: 첫번째 문자 뒤에 추가 , 2: 두번째문자 뒤에 추가, 3: 세번째 문자 뒤에 추가 ...etc
  - 기존에 int값이 주어졌다면 StringBuilder 내부에서 연산도 가능

- 사용법

```java
public class Main {
 
  public static void main(String[] args) {

    StringBuilder sb = new StringBuilder();
    int a=1;
    int b =2;
    sb.append("abcd");
    sb.append("//efg").append("//hi");
    sb.append("\n");
    sb.append("jk").append("\n").append("lm").append("\n");
    sb.append(a+b);
    sb.insert(4, "\"추가\"");
    System.out.println(sb);
  }
}
//-------------- 콘솔창 출력 결과
abcd"추가"//efg//hi
jk
lm
3

```