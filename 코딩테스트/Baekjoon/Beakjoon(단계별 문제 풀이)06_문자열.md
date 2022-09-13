# Beakjoon(단계별 문제 풀이)

### 문자열

> 11654번(https://www.acmicpc.net/problem/11654)

- 선행공부(https://st-lab.tistory.com/41?category=830901)
  - 아스키코드
    - **ASCII** (**A**merican **S**tandard **C**ode for **I**nformation **I**nterchange, 미국 정보 교환 표준 부호)
    - 1963년 미국 ANSI에서 표준화한 정보교환용 7비트 부호체계
    - 000(0x00)부터 127(0x7F)까지 총 128개의 부호가 사용
    - 1바이트를 구성하는 8비트 중에서 7비트만 씀(1비트는 통신에러 검출용)
    - 한글 사용 불가 => UTF-8로 사용
- 정답
- 출처(https://st-lab.tistory.com/59)

```java
public class Main {
	public static void main(String[] args) throws Exception {
 
		int a = System.in.read();
		System.out.print(a);
 
	}
}
```

> 11720번(https://www.acmicpc.net/problem/11720)

- 입력값을 받은후 `charAt()` 을 사용하여 분리 및 배열에 저장
- 자바의 내장함수 `Character.getNumericValue()` 를 사용하여 char => int 변환 및 연산

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  
  public static void main(String[] args) throws IOException{
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int n = Integer.parseInt(br.readLine());
    
    String num = br.readLine();
    
    char [] number = new char[n];

    for(int i =0; i < n; i++) {
      number[i] = num.charAt(i);
    }
    
    int sum =0;
    
    for(int i =0; i < n; i++) {
      System.out.println(number[i]);
      sum += Character.getNumericValue(number[i]);
    }
    
    System.out.println(sum);
  }
  
}
```

- 또 다른 문제 풀이
- 출처(https://st-lab.tistory.com/61)
  - 아스키 코드 활용
  - 아스키코드번호 - 48 or - '0' 을 하게되면 아스키코드는 자신의 수가 됨
    - ex) 5의 아스키 코드값은 53, 0의 아스키 코드값은 48
    - 53 - 48 = 5 
    - 또는 `.charAt() - '0'` 을 사용해도 됨
  - 하기 코드에서는 배열도 사용하지 않아도 됨
    - getBytes() 는 문자열을 byte 배열로 반환함
    - for-each 문을 사용함
    - for-each문 구조
      - `for (타입 변수명 : 변수에 순차적으로 대입하여 for문을 실행시킬 객체) { 루프를 돌릴 내용 }`
      - `for (type var : iterate) {body-of-loop}` 

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
	public static void main(String[] args) throws IOException {		
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();	// N 은 필요없지만 문제에서 요구 하므로 입력만 받음
		
		int sum = 0;
		
		for(byte value : br.readLine().getBytes()) {
			sum += (value - '0');	// 또는 (value-48)
		}
		
		System.out.print(sum);
		
	}
}
```

- 상기 코드의 `getBytes()` 가 궁금해서 찍어 보았음

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
	public static void main(String[] args) throws IOException {		
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();	// N 은 필요없지만 문제에서 요구 하므로 입력만 받음
		
//		int sum = 0;
		
		for(byte value : br.readLine().getBytes()) {
            System.out.println(value);
//			sum += (value - '0');	// 또는 (value-48)
		}
		
//		System.out.print(sum);
		
	}
}
//================= 콘솔결과 ==================
6	----------> 최초 횟수 입력(상시코드에서는 기능 없는 부분)
654321 ----------> 입력 숫자
54 ----------> 6에 대한 아스키 코드
53 ----------> 5에 대한 아스키 코드
52 ----------> 4에 대한 아스키 코드
51 ----------> 3에 대한 아스키 코드
50 ----------> 2에 대한 아스키 코드
49 ----------> 1에 대한 아스키 코드
```