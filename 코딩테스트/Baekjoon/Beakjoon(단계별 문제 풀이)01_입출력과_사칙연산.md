# Beakjoon(단계별 문제 풀이)

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

