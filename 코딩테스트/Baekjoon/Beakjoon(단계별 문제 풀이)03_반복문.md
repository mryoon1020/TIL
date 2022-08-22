# Beakjoon(단계별 문제 풀이)

### 반복문

> 2739번(https://www.acmicpc.net/problem/2739)

```java
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();
    
    for(int i= 1; i <= 9; i++) {
      System.out.println(N+" "+"*"+" "+i+" "+"="+" "+N*i);
    }

  }

}
```

> 10950번(https://www.acmicpc.net/problem/10950)

```java
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    
    for(int i= 1; i <= T; i++) {
      int A = sc.nextInt();
      int B = sc.nextInt();
      System.out.println(A+B);
    }

  }

}
```

> 8393번(https://www.acmicpc.net/problem/8393)

- 반복문 문제였지만 본인은 등차수열의 합공식을 활용하였음

```java
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();

    System.out.println(n*(n+1)/2);

  }

}
```

- 반복문사용 문제 풀이답안
- 출처: https://st-lab.tistory.com/29

```java
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();

    int sum = 0;
    
    for(int i =1; i<= n ; i++) {
      sum +=i;
    }
    System.out.println(sum);
  }

}
```

> 25304번(https://www.acmicpc.net/problem/25304)

```java
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    int X = sc.nextInt(); //총금액
    int N = sc.nextInt(); //물건종류
    int sum = 0;
    
    for(int i=1; i<=N;i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      sum += a*b;
    }
    if(X == sum) {
      System.out.println("Yes");
    }else {
      System.out.println("No");
    }

  }

}
```

