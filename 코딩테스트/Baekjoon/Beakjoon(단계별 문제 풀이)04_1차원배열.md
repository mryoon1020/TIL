# Beakjoon(단계별 문제 풀이)

### 1차원 배열

> 10818번(https://www.acmicpc.net/problem/10818)

- 오류난 내답안
- 정답은 제대로 도출되지만 런타임 오류가남

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
 
  public static void main(String[] args) throws IOException{
    
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      int n = Integer.parseInt(br.readLine());
      int[] num= new int[n];
      
      for(int i = 0; i<n;i++) {
        num[i] = Integer.parseInt(br.readLine());
      }
      Arrays.sort(num);
      System.out.println(num[0]);
      System.out.println(num[n-1]);
  }
}
```

- 정답
- 참조 (https://st-lab.tistory.com/43)
- 상기사이트에서는 while문을 사용했지만 for으로 사용해봄

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
 
  public static void main(String[] args) throws IOException{
    
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      int n = Integer.parseInt(br.readLine());
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      int[] num= new int[n];
      
      for(int i = 0; i<n;i++) {
        num[i] = Integer.parseInt(st.nextToken());
      }
      Arrays.sort(num);
      System.out.println(num[0]);
      System.out.println(num[n-1]);
  }
}
```

- while문을 사용한 정답
- for문과 비슷
- 조건: 토큰이 남아있을 때까지 반복, 배열의 첫번째인 [0]에 저장을 위해 index를 따로 0으로 설정해줌

```java
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
 
 
public class Main {
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int index = 0;
		int[] arr = new int[N];
        
		while(st.hasMoreTokens()) {
			arr[index] = Integer.parseInt(st.nextToken());
			index++;
		}
		
		Arrays.sort(arr);
		System.out.print(arr[0] + " " + arr[N - 1]);
	}
}
```

> 2562번(https://www.acmicpc.net/problem/2562)

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
 
  public static void main(String[] args) throws IOException{
    
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      int[] num= new int[9];
      
      for(int i = 0; i<9;i++) {
        num[i] = Integer.parseInt(br.readLine());
      }
      
      int max = num[0];
      int maxIndex = 0;
      
      for(int i =0; i<9; i++) {
        
        if(max<num[i]) {
         max=num[i];
         maxIndex= i;
        }
      
      }
      System.out.println(max);
      System.out.println(maxIndex+1);
  }
}
```

> 3052번(https://www.acmicpc.net/problem/3052)

- 잘못된 정답
- 같은 값을 인식하지 못함

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
 
  public static void main(String[] args) throws IOException{
    
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      int[] num= new int[10];
      
      for(int i =0; i<10;i++) {
        num[i] = Integer.parseInt(br.readLine())%42;
      }
      int ct = 0;
      for(int i =0; i<10;i++) {
        if(num[0]!=num[i]) {
          ct++;
        }
      }
      
      System.out.println(ct+1);
      
  }
}
```

- 정답코드
- 출처(https://dlagusgh1.tistory.com/489)

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
 
  public static void main(String[] args) throws IOException{
    
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      int[] num= new int[10];
      
      for(int i =0; i<10;i++) {
        num[i] = Integer.parseInt(br.readLine())%42;
      }
      
      int ct = 0;
      int temp =0;
      
      for(int i =0; i<10;i++) {
        temp = 0;
        for(int j =i+1;j<10;j++) {
        
          if(num[i]==num[j]) {
          temp++;
          }
        }
        if(temp == 0) {
          ct++;
        }
      }
      
      System.out.println(ct);
      
  }
}
```

- 이해가 잘안되서 찍어본 코드
  - 같은숫자의 갯수와 다른숫자의 갯수를 변수로 선언함
  - 같은숫자를 먼저 찾음과 동시에 갯수를 찍음
  - 같은숫자의 갯수가 0 일 경우 갯수를 찍음

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
 
  public static void main(String[] args) throws IOException{
    
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      int[] num= new int[10];
      
      for(int i =0; i<10;i++) {
        num[i] = Integer.parseInt(br.readLine())%42;
      }
      
      int ct = 0;
      int temp =0;
      
      System.out.print("{ ");
      for(int i =0; i<10;i++) {
      System.out.print(num[i]+" ");
      }
      System.out.print(" }");
      System.out.println("");
      
      for(int i =0; i<10;i++) {
        temp = 0;

        for(int j =i+1;j<10;j++) {
        
          if(num[i]==num[j]) {
          temp++;
          
          System.out.println("num i"+"["+i+"] :"+num[i]);
          System.out.println("num k"+"["+j+"] :"+num[j]);
          System.out.println("temp : "+temp);
          }
        }
        if(temp == 0) {
          ct++;
        }
      }
      
      System.out.println(ct);
      
  }
}
```

- 상기코드에 대한 결과값

```
39
40
41
42
43
44
82
83
84
85
================결과값===============
{ 39 40 41 0 1 2 40 41 0 1  }
num i[1] :40
num k[6] :40
temp : 1
num i[2] :41
num k[7] :41
temp : 1
num i[3] :0
num k[8] :0
temp : 1
num i[4] :1
num k[9] :1
temp : 1
6
```

- 또다른 풀이
- 출처(https://st-lab.tistory.com/46)
- 본인은 생각해보지 못한 방법
  - 배열길이다 42인 이유 => 나머지의 범위는 0~41이기 때문
  - boolean 배열의 default값은 false임
  - 

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
    public static void main(String[] args) throws IOException {
 
        boolean[] arr = new boolean[42];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for(int i = 0 ; i < 10 ; i++) {
            arr[Integer.parseInt(br.readLine()) % 42] = true;
        }
        
        int count = 0;
        for(boolean value : arr) {
            if(value){    // value 가 true 라면
                count++;
            }
        }
        System.out.println(count);
    }
}
```

