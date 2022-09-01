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

