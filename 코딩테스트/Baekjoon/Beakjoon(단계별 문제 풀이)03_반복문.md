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

> 15552번(https://www.acmicpc.net/problem/15552)

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
 
public class Main {
 
  public static void main(String[] args) throws Exception, IOException {
 
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
    int n = Integer.parseInt(br.readLine());
 
    for (int i = 0; i < n; i++) {
      String str = br.readLine();
      int a = Integer.parseInt(str.split(" ")[0]);
      int b = Integer.parseInt(str.split(" ")[1]);
      bw.write(a+b+"\n");
      
    }
   
    bw.flush();
 
  }
}
```

> 11021번(https://www.acmicpc.net/problem/11021)

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
 
public class Main {
 
  public static void main(String[] args) throws Exception, IOException {
 
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
    int n = Integer.parseInt(br.readLine());
 
    for (int i = 1; i < n+1; i++) {
      String str = br.readLine();
      int a = Integer.parseInt(str.split(" ")[0]);
      int b = Integer.parseInt(str.split(" ")[1]);
      int sum = a+b;
      bw.write("Case #"+i+": "+sum+"\n");
      
    }
   
    bw.flush();
 
  }
}
```

> 11022번(https://www.acmicpc.net/problem/11022)

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
 
public class Main {
 
  public static void main(String[] args) throws Exception, IOException {
 
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
    int n = Integer.parseInt(br.readLine());
 
    for (int i = 1; i < n+1; i++) {
      String str = br.readLine();
      int a = Integer.parseInt(str.split(" ")[0]);
      int b = Integer.parseInt(str.split(" ")[1]);
      int sum = a+b;
      bw.write("Case #"+i+": " +a+" + "+b+" = "+sum+"\n");
      
    }
   
    bw.flush();
 
  }
}
```

> 2438번(https://www.acmicpc.net/problem/2438)

```java
import java.util.Scanner;

public class Main {
 
  public static void main(String[] args) {
 
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
      
    for(int i=1;i<=n;i++) {
      for(int j=1; j<=i;j++) {
        System.out.print("*");
      }
      System.out.println();
    }
  }
}
```

- 연습삼아 해본 역순으로 별찍기

```java
import java.util.Scanner;

public class Main {
 
  public static void main(String[] args) {
 
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    for(int i = n; i>0 ; i--) {
      for(int j = i; j>0;j--) {
        System.out.print("*");
      }
      System.out.println("");
    }  
  }
}
```

> 2439번

- 내가 헤메던 코드

  - 아래코드를 실행하면 다음과 같이 출력됨

  - 공백이 줄어 들지 않음

  - for문이 돌며 공백이 프린트 되지만 제동을 해줄 장치가 없음

  - i 가 j 를 제동해주지만 k까지 제동해주지 못함

    ```
    5
        *
        **
        ***
        ****
        *****
    ```

```java
import java.util.Scanner;

public class Main {
 
  public static void main(String[] args) {
 
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
      
    for(int i =1; i<=n ; i++) {
      
      for(int k=n-1; k>0;k--) {
      
        System.out.print(" ");
      }
      for(int j=1;j<=i;j++) {
        
        System.out.print("*");
        }
        
      System.out.println("");
    }
 
  }
}
```

- 정답코드
  - 출처: https://st-lab.tistory.com/36

```java
import java.util.Scanner;

public class Main {
 
  public static void main(String[] args) {
 
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
      
    for(int i =1; i<=n ; i++) {
      
      for(int k=1; k<=n-i;k++) {
      
        System.out.print(" ");
      }
      for(int j=1;j<=i;j++) {
        
        System.out.print("*");
        }

      System.out.println("");
    }
 
  }
}
```

> 10871번(https://www.acmicpc.net/problem/10871)

- 내정답

```java
import java.util.Scanner;

public class Main {
 
  public static void main(String[] args) {
 
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();//정수갯수
    int x = sc.nextInt();//크기비교용숫자
    
    int[] num = new int[n];
    
    for(int i = 0; i< num.length;i++) {
      num[i]=sc.nextInt();
    }
    
    for(int i = 0; i< num.length;i++) {
      if(num[i]<x) {
        System.out.print(num[i] + " ");
     
      }
    } 
  }
}
```

- 빠른연산 및 배열을 사용하지 않는 풀이법
- 출처("https://st-lab.tistory.com/38")

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
 
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
 
		StringBuilder sb = new StringBuilder();
 
		st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 0; i < N; i++) {
			int value = Integer.parseInt(st.nextToken());
 
			if (value < X)
				sb.append(value).append(' ');
		}
		System.out.println(sb);
	}
}
```

> 10952번

- 출처 : https://st-lab.tistory.com/39

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
 
  public static void main(String[] args) throws Exception {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringBuilder sb = new StringBuilder();
    
    while(true) {

      StringTokenizer st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      
      if(a==0 && b ==0) {
        break;
      }
      sb.append((a+b)).append('\n');
    }
    System.out.println(sb);
  }
}
```

- 좀더 간단한 방법(Scanner 사용)

  - 예제 입력

    - ```
      ※ 문제의 예제에는 다음과 같이표기			Scanner 사용시 콘솔 출력
      예제입력			예제출력		 |		1 1
      1 1					2			  |		 2
      2 3					5			  |		 2 3
      0 0								  |		 5
      								  |		 0 0
      ```

    - Console에 어떻게 나와도 상관 없이 정답처리가 됨

```java
import java.util.Scanner;

public class Main {
 
  public static void main(String[] args) throws Exception {

    Scanner sc = new Scanner(System.in);
    

    while(true) {
      
      int a = sc.nextInt();
      int b = sc.nextInt();
      
      if(a == 0 && b ==0) {
        break;
      }
      System.out.println(a+b);
    }    
  }
}
```

