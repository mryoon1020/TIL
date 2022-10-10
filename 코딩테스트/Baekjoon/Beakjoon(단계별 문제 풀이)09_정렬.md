# Beakjoon(단계별 문제 풀이)

### 정렬

> 2750번(https://www.acmicpc.net/problem/2750)

- 매우 간단한 문제였음
- 배열에 값을 저장한 후 자바 내부 Arrays클래스의 메소드 sort를 활용해 정렬후 출력 함

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
 
public class Main {

  public static boolean checkPrime[];
  
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		
		int [] arr = new int[t];
		
		for(int i =0; i<t; i++) {
		  arr[i]= Integer.parseInt(br.readLine());
		}
    
		Arrays.sort(arr);
		
		for(int i =0; i<t; i++) {
		  
		  System.out.println(arr[i]);
		  
		}
		
	}  
	
}
```

- 속도가 빠른(성능이 좋은) 정렬방법
- 카운트 정렬방법이라고 함
  - 기본 매커니즘은 데이터 값이 몇번 나왔는지 세주는것
  - 수열의 길이보다 수의 범위가 극단적으로 크면 메모리가 엄청 낭비 될 수 있음

- 출처(https://st-lab.tistory.com/105)
- 자세한 내용(https://st-lab.tistory.com/104)
- 정렬에는 다양한 방법이 있는듯 함, 정렬을 풀기전 다른 정렬알고리즘에 대한 이해도가 필요

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
	public static void main(String[] args) throws IOException {
    
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
        
		int N = Integer.parseInt(br.readLine());
        
		/*
		  range : -1000 ~ 1000
		  0 은 index[1000] 을 의미
		*/
		boolean[] arr = new boolean[2001];
		
		for(int i = 0; i < N; i++) {
			arr[Integer.parseInt(br.readLine()) + 1000] = true;
		}
 
		// 정렬 과정이 따로 필요 없음
		
		for(int i = 0; i < 2001; i++) {
			if(arr[i]) {
				sb.append(i - 1000).append('\n');
			}
		}
		System.out.println(sb);
	}
}
```

