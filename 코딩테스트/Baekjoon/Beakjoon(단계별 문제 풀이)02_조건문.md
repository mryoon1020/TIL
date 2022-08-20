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