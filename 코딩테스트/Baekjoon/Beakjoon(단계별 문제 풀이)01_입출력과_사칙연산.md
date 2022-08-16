# Beakjoon(단계별 문제 풀이)

### 시작하기 전에

- 본격적으로 알고리즘 공부 시작전 공부
- 백준 홈페이지의 단계별 문제(https://www.acmicpc.net/step) 1~11번 풀기
- 이후 강의에서 다루는 기초 1 ,2, 중급 1의 문제까지 공부할 예정

### 입출력과 사칙연산

> 2557번 (https://www.acmicpc.net/problem/2557)

```java
public class Main {
    public static void main(String[] args){
        System.out.println("Hello World!");
    }
}
```

- 아주 간단한 문제지만 시간이 걸렸음(출력문의 문장부호 및 대소문자 구분을 안했기 때문)
- 출력되는 문자가 대소문자 및 문장부호 갯수까지 정확해야 정답으로 인정됨

>10718번(https://www.acmicpc.net/problem/10718)

```java
public class Main{
    public static void main(String[] args){
        System.out.println("강한친구 대한육군");
        System.out.println("강한친구 대한육군");
    }
}
```

> 1000번(https://www.acmicpc.net/problem/1000)

- 처음 입력한 코드
- 오답처리되었음
- 군더더기 없이 주어진 문제만 구현 해야함

```java
import java.util.Scanner;

public class Main{
    
    public static void main(String[] args){
        
        Scanner scan = new Scanner(System.in);
        System.out.println("A와B를 순서대로 입력해주세요.(단, A>0, B<10)");
        
        int A = scan.nextInt();
        int B = scan.nextInt();
        scan.close();
        if(A>0 & B<10){
            System.out.println(A+B);
        }else{
            System.out.println("A가 0보다 작거나 B가 10보다 큽니다.");
        }
        
    }
    
}
```

- 정답처리된 코드

```java
import java.util.Scanner;

public class Main{
    
    public static void main(String[] args){
        
        Scanner scan = new Scanner(System.in);

        int A = scan.nextInt();
        int B = scan.nextInt();
            System.out.println(A+B);

    }
    
}
```

> 1001번(https://www.acmicpc.net/problem/1001)

```java
import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int A = scan.nextInt();
        int B = scan.nextInt();
        System.out.println(A-B);
    }
}
```

> 10998번(https://www.acmicpc.net/problem/10998)

- 상기 예제들 처럼 scanner를 사용해도 됨
- Scanner는 속도가 느리다는 단점이 있음

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int A = 0;
            int B = 0;
        String[] str = br.readLine().split(" ");
        A = Integer.parseInt(str[0]);
        B = Integer.parseInt(str[1]);
        
        System.out.println(A*B);
    }
}
```

> 1008번(https://www.acmicpc.net/problem/1008)

- 중요조건: 실제 정답과 출력값의 절대오차 또는 상대오차가 10<sup>-9 </sup>이하이면 정답이다
- `Double` 로 처리해주어야함
  - 소수점을 처리해주어야 하기 때문
  - 참조사이트: https://devlog-wjdrbs96.tistory.com/254
  - `float` : 유효자리수는 7자리 까지 → 문제의 조건에 부합하지 않음
  - `double`: 유효자리수는 16자리 까지 → 문제의 조건에 부합
- `<sup></sup>` : 윗첨자 `<sub></sub>` : 아래첨자
  - 예시. `10<sup>9</sup>`: 10<sup>9</sup>  , `H<sub>2</sub>O` : H<sub>2</sub>O

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main{
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double A = 0;
        double B = 0;
        String[] str = br.readLine().split(" ");
        A = Double.parseDouble(str[0]);
        B = Double.parseDouble(str[1]);
        System.out.println(A/B);
        
    }
}
```

