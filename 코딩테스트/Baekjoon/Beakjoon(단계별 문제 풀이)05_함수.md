# Beakjoon(단계별 문제 풀이)

### 함수

> 15596번(https://www.acmicpc.net/problem/15596)

- 컴파일 에러난 내정답

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
 
  public static long sum() throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int n = Integer.parseInt(br.readLine());
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    long [] a = new long[n];
    
    long sum = 0;
    
    for(int i=0;i<n;i++) {
      a[i]= Integer.parseInt(st.nextToken());
    }
    
    for(int i=0;i<n;i++) {
      sum+=a[i];
    }
    
    return sum; 
  }
}
```

- 정답
- 출처(https://st-lab.tistory.com/52)
  - 정말 말그대로 합만 구하면 되는 것이 었다.
  - 특별히 정수를 입력해주는 부분을 구현할 필요는 없었음

```java
class Test {
    long sum(int[] a) {
		long sum = 0;
        
		for(int i = 0; i < a.length; i++) {
			sum += a[i];
		}
		return sum;
	}
}
```

