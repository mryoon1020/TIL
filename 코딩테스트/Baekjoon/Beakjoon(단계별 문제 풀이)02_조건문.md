# Beakjoon(단계별 문제 풀이)

### 입출력과 사칙연산

> 1330번(https://www.acmicpc.net/problem/1330)

```java
import java.util.Scanner;

public class Main {

  public static void main(String[] args){
    
    Scanner sc = new Scanner(System.in);
    
    int A = sc.nextInt();
    int B = sc.nextInt();
    
    if(A>B) {
      System.out.println(">");
    }else if(A<B){
      System.out.println("<");
    }else if(A == B) {
      System.out.println("==");
    }
    
  }

}
```

> 9498번(https://www.acmicpc.net/problem/9498)

```java
import java.util.Scanner;

public class Main {

  public static void main(String[] args){
    
    Scanner sc = new Scanner(System.in);
    
    int a = sc.nextInt();
    
    if(100>=a & a>=90) {
      System.out.println("A");
    }else if (89>=a & a>=80) {
      System.out.println("B");
    }else if(79>=a & a>=70) {
      System.out.println("C");
    }else if(69>=a & a>=60) {
      System.out.println("D");
    }else {
      System.out.println("F");
    }    
    
  }

}
```

>2753번(https://www.acmicpc.net/problem/2753)

```java
import java.util.Scanner;

public class Main {

  public static void main(String[] args){
    
    Scanner sc = new Scanner(System.in);
    
    int a = sc.nextInt();
    
    if(a%4 == 0 & a%100 != 0 | a%400 == 0) {
      System.out.println(1);
    }else {
      System.out.println(0);
    }
  }

}
```

> 14681번(https://www.acmicpc.net/problem/14681)

```java
import java.util.Scanner;

public class Main {

  public static void main(String[] args){
    
    Scanner sc = new Scanner(System.in);
    
    int x = sc.nextInt();
    int y = sc.nextInt();
    
    if(x>0 & y>0) {
      System.out.println(1);
    }else if(x<0 & y>0) {
      System.out.println(2);
    }else if(x<0 & y<0) {
      System.out.println(3);
    }else if(x>0 & y<0) {
      System.out.println(4);
    }
    
  }

}
```

> 2884번(https://www.acmicpc.net/problem/2884)

```java
import java.util.Scanner;

public class Main {

  public static void main(String[] args){
    
    Scanner sc = new Scanner(System.in);
    
    int h = sc.nextInt();
    int m = sc.nextInt();
    
    if(m-45 <0) {
      if(h == 0) {
        System.out.println(24-1+" "+(m+15));
      }else {
      System.out.println(h-1+" "+(m+15));
      }
    }else {
      System.out.println(h+" "+(m-45));
    }
    
  }

}
```

> 2525번(https://www.acmicpc.net/problem/2525)

- 결과는 정확히 나오지만 틀린 내 답안

```java
import java.util.Scanner;

public class Main {

  public static void main(String[] args){
    
    Scanner sc = new Scanner(System.in);
    
    int h = sc.nextInt();
    int m = sc.nextInt();
    int cm = sc.nextInt();
    
    if(m+cm > 60) {
      
      if(h+((cm+m)/60)>24) {
        System.out.println(h+((cm+m)/60)-24+" "+(m+cm)%60);
      }else {
        System.out.println(h+((cm+m)/60)+" "+(m+cm)%60);
      }
    }else {
      System.out.println(h+" "+(m+cm));
    }
    
  }

}
```

- 정답처리된 답안
- 코드가 훨씬 깔끔함
- 입력값을 '분'으로 전부 변환한뒤 처리
- 0시 처리부분: `int hour = (min/60)%24`

- 출처: https://st-lab.tistory.com/292

```java
import java.util.Scanner;

public class Main {

  public static void main(String[] args){
    
    Scanner sc = new Scanner(System.in);
    
    int h = sc.nextInt();
    int m = sc.nextInt();
    int cm = sc.nextInt();
    
    int min = 60*h+m;
    min += cm;

    int hour = (min/60)%24;
    int minute = min % 60;
    
    System.out.println(hour+ " " + minute);
  }

}
```

- 스캐너 보다 성능이 훨씬 빠른 BufferedReader 사용
- BufferedReader는 한줄씩
- 출처: https://st-lab.tistory.com/292

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException{
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    
    int a = Integer.parseInt(st.nextToken());
    int b = Integer.parseInt(st.nextToken());
    
    int c = Integer.parseInt(br.readLine());
    
    int min = 60*a+b;
    min += c;
    
    int hour = (min/60)%24;
    int minute = min%60;
    
    System.out.println(hour + " " + minute);
    
  }

}
```

> 2480번(https://www.acmicpc.net/problem/2480)

```java
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    int a = sc.nextInt();
    int b = sc.nextInt();
    int c = sc.nextInt();

    if (a == b && a == c) {
      System.out.println(10000 + a * 1000);
    } else if (a == b || a == c || b == c) {
      
      if (a == b || a == c) {
        System.out.println(1000 + a * 100);

      } else {
        System.out.println(1000 + b * 100);
      }
    } else {
      
      int[] num = {a,b,c};
      int max = num[0];
      
      for(int i=0; i<num.length;i++) {
        if(max<num[i]) {
          max =num[i];
        }
      }
     System.out.println(max*100); 
    }

  }

}
```