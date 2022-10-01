# Beakjoon(단계별 문제 풀이)

### 기본수학2

> 1978번(https://www.acmicpc.net/problem/1978)

- 내 답안
- 소수의 조건인 1와 자신의 수로만 나눠지는 수 내가만든 조건
  - 몫이 1인 경우
  - 몫이 1이면서 나머지가 0인경우
  - 제곱근이 자연수가 아닌경우
    - 제곱근을 구하는 자바 내부함수는 `Math.sqrt()`
    - 제곱근을 1로 나눈 나머지가 0 일때 제곱근은 자연수로 떨어짐
      - 실제 연산에서는 1로나누게 되면 나머지는 0임
      - 해당 조건이 성립하는 이유는 자바연산에서 int타입은 소수점 계산을 하지 못하기 때문으로 추정
      - int타입에서는 소수점은 버려짐

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;
 
public class Main {

	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[n];
		
		for(int i = 0; i < n; i++) {
		  arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int cnt = 0;
		
		for(int i = 0; i < n; i++) {

		  for(int j = 2; j<=arr[i];j++) {

		    if(arr[i]/j ==1 && arr[i]%j == 0 && arr[i]==j && Math.sqrt(arr[i])%1 != 0 ) {
		    
		      cnt++;
		      
		  }	    
		    
		  }
		}
    System.out.println(cnt);
	}
	
}
```

- 정답
- 출처(https://st-lab.tistory.com/80)
- 자연수 n의 소수판별은 `2 <= p <= "n 의 제곱근"` 범위안의 소수 p로 n을 나누어 떨어지지 않으면 소수

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		br.readLine();	// N 은 쓰지 않음
        				// st.nextToken()을 통해 남은 토큰이 없을때까지 while 연산할 것임
		int count = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		while(st.hasMoreTokens()) {
        
			// 소수인경우 true, 아닌경우 false   
			boolean isPrime = true;
			
			int num = Integer.parseInt(st.nextToken());
			
			if(num == 1) {	// 1에 대한 예외처리
				continue;
			}
            
			for(int i = 2; i <= Math.sqrt(num); i++) {	// 제곱근까지만 찾으면 됨
                
				if(num % i == 0) {
					isPrime = false;
					break;
				}
			}
			if(isPrime) {
				count++;
			}
		}
		System.out.println(count);
	}
 
}
```

- 에라토스테네스의 체 코드 버젼

```java
// 1 ~ Max 범위
 
// 소수인 수 = false
// 소수가 아닌 수 = true
 
public boolean[] make_prime(int Max) {
 
	boolean[] Prime = new boolean[Max + 1];	// 0 부터 시작하므로 +1
 
	// 0 과 1 은 소수가 아니므로 true
	Prime[0] = true;
	Prime[1] = true;
    
    
	for(int i = 2; i <= Math.sqrt(Max); i++) {
 
		// 이미 걸러진 배열일 경우 다음 반복문으로 건너뜀
        // 하단 for문을 if(!Prime[i]){}로 감싸주는 방법을 사용해도 됨
		if(Prime[i] = true) {
			continue;
		}
		
		/*
		정석대로라면 j = i * 2 부터 시작이지만 
		이미 2의 배수가 걸러졌기때문에
		i 의 제곱수부터 시작해도 된다.
		*/
        
		for(int j = i * i; j < Max + 1; j = j + i) {
            // 초기값이 i*i 인 이유는 i*i이하의 값은 이미 이전 작업에서 처리되었기 때문
			Prime[j] = true;
		}
	}
 
	// 배열 index 가 소수라면 false 로, 아니라면 true 로 완성됨
    
	return Prime;
}
```

