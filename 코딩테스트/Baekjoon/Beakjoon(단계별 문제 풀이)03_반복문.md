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
      예제입력			예제출력		 |		1
      1 1					2			  |		 1
      2 3					5			  |		 2  (1+1의 연산결과)
      0 0								  |		 2
      								  |		 3
      								  |		 5	(2+3의 연산결과)
      								  |		 0
      								  |		 0
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

> 10951번(https://www.acmicpc.net/problem/10951)

- 내 오답
  - while문이 끝나는 시점이 없었기에 공백입력시 오류가 남

```java
import java.util.Scanner;

public class Main {
 
  public static void main(String[] args) throws Exception {

    Scanner sc = new Scanner(System.in);
    

    while(true) {
      
      int a = sc.nextInt();
      int b = sc.nextInt();

      System.out.println(a+b);
    }
    
  }
}
```

- 정답
- 출처: https://st-lab.tistory.com/40

```java
import java.util.Scanner;

public class Main {
 
  public static void main(String[] args) throws Exception {

    Scanner sc = new Scanner(System.in);
    

    while(sc.hasNextInt()) {
      
      int a = sc.nextInt();
      int b = sc.nextInt();

      System.out.println(a+b);
    }
    
  }
}
```

- BufferedReader사용
- 출처: https://st-lab.tistory.com/40 && 댓글질문
- 정답처리는 되었지만 IDE상에서는 NoSuchElementException과 함께 합이 계산되지 않음
- 입력을 끝내고 싶을 때에는 enter키를 누르는 것 대신 ctrl+z를 눌렀을때 결과가 잘 도출이됨
- 윈도우 계열 : ctrl + z   //   리눅스 계열 : ctrl + d
- StringTokenizer에서 오류가 났을 확률이 높음
  - `""` 입력은 null이 아닌 빈문자열의 데이터
  - null은 메모리도 할당되지 않은 상태
  - StringTokenizer는 공백 `" "` 단위로 구분
  - 공백이 없는 빈문자열에서 검사할 수 있는 문자가 없기 때문에 뽑아낼수 없는 토큰이 없음
  - NoSuchElementException발생

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
 
  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringBuilder sb = new StringBuilder();
    
    String str;
    
    while((str = br.readLine()) != null) {
      StringTokenizer st = new StringTokenizer(str," ");
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      sb.append(a + b).append("\n");
    }
    
    System.out.println(sb);
  }   
}
```

- ctrl+z를 누를 필요 없이 enter만 써도 되는경우

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
 
  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringBuilder sb = new StringBuilder();
    
    String str;
    
    while((str = br.readLine()) != null) {
      StringTokenizer st = new StringTokenizer(str," ");
      
      if(st.hasMoreTokens()) {

      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      sb.append(a + b).append("\n");
      }else {
        break;
      }
    }
    System.out.println(sb);
  }
}
```

> 1110번(https://www.acmicpc.net/problem/1110)

- 잘못된 나의 코드
- 입력값을 subString으로 쪼갠뒤 int로 변환 후 stringBuilder에 저장하는 연산을 구동했음
- 하기 코드는 while문이 돌지 않으며 굉장히 복잡함

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
 
  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    String str = br.readLine();    
    int cnt = 0;
    
    while(str == sb.toString()){

      int a = Integer.parseInt(str.substring(0, 1));
      int b = Integer.parseInt(str.substring(1));
    
      if(a+b >=10) {
       String c = Integer.toString(a+b);
       sb.append(b);
       sb.append(Integer.parseInt(c.substring(1)));      
       
      }else {
      
      sb.append(b);
      sb.append((a+b));
      }

      cnt++;
      
   }
    System.out.println(str);
    System.out.println(sb);
    System.out.println(cnt);
  }
}
```

- 정답코드
- 출처(https://st-lab.tistory.com/42)
- scanner 사용 코드

```java
import java.util.Scanner;

public class Main {
 
  public static void main(String[] args){
    
    Scanner in = new Scanner(System.in);
    
    int N = in.nextInt(); //입력
    in.close();
        
    int cnt = 0;  //루프 횟수
    int copy = N; //변수 복사
        
    while (true) {
      N = ((N % 10) * 10) + (((N / 10) + (N % 10)) % 10);
      
      //N을 10으로 나눈 나머지 * 10 => 새로운 수의 10의 자리
      //(N을 10으로 나눈 몫 + 나머지)를 10으로 나눈 나머지 => 새로운 수의 1의 자리
      
      cnt++;  //루프가 돌때마다 1씩증가
 
      if (copy == N) {
        break;
      }
    }
    System.out.println(cnt);
    
  }
}
```

- bufferedReader 사용 코드
- while 문도 가능하지만 do-while문을 통해 if를 생략하였음

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
 
  public static void main(String[] args) throws IOException{
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int N = Integer.parseInt(br.readLine());
    
    int cnt = 0;
    int copy = N;
        
    do {
      N = ((N % 10) * 10) + (((N / 10) + (N % 10)) % 10);
      cnt++;
    } while (copy != N);
    
    System.out.println(cnt);

  }
}
```

