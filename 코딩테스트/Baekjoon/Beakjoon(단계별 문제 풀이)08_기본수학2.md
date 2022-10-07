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
  
    ```java
    public class asd {
    
      public static void main(String[] args) {
        
        int a = 7;
        
        if(Math.sqrt(a)%1 != 0) {
          System.out.println("자연수가 아님");
        }else {
          System.out.println("자연수임");
        }
    
        System.out.println(a/4);
      }
    
    }
    // 출력결과---------------------------------
    자연수가 아님
    1   
    ```
  
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

> 2581번(https://www.acmicpc.net/problem/2581)

- 내답안
- 에라토스테네스의 채를 활용하며 구해 보고자 했으나 구해지지 않음

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;
 
public class Main {

	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int m = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		
		int min = 0;
		int sum = 0;
		
		boolean[] prime = new boolean[n-m+1];
		
		if(m == 0) {
		  prime[0]= true;
		  prime[1]= true;
		}
		
		if(m == 1) {
		  prime[0]= true;
		}
		
		for(int i = 0; i <= Math.sqrt(n); i++) {
		  
		  if(prime[i]=true) {
		    continue;
		  }
		  
		  for(int j = i*i; j< n-m+1; j +=i) {
		    prime[j] = true;
		  }
		  System.out.println(prime[i]);
		}
		
		for(int i=0; i<prime.length; i++) {
		  if(prime[i]== false) {

		    sum += i+m;
		  }
		}
		
		for(int i=0; i<prime.length; i++) {
	    if(prime[i]== false) {
	        min= i+m;
	        break;
	      }  
		  
		}
		
		if(sum == 0 || min == 0) {
		  System.out.println(-1);
		}else {
		
		System.out.println(sum);
		System.out.println(min);
		
		}
		
	}
	
}
```

- 정답
- 출처(https://st-lab.tistory.com/83)
- 에라토스테네스의채 개념설명(https://st-lab.tistory.com/81)

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
 
	public static boolean prime[];
	
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int M = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		
		prime = new boolean[N + 1];	// 배열 생성 
		get_prime();
		
		
		// 소수 합 및 최솟값 
		int sum = 0;
		int min = Integer.MAX_VALUE;
		for(int i = M; i <= N; i++) {
			if(prime[i] == false) {	// false = 소수 
				sum += i;
				if(min == Integer.MAX_VALUE) {	// 첫 소수가 최솟값임  
					min = i;
				}
			}
		}
		
		if(sum == 0) {	// 소수가 없다면 
			System.out.println(-1);
		}
		else {
			System.out.println(sum);
			System.out.println(min);
		}
		
	}
 
	
	// 에라토스테네스 체 알고리즘 
	public static void get_prime() {
		prime[0] = true;
		prime[1] = true;
		
		for(int i = 2; i <= Math.sqrt(prime.length); i++) {
			if(prime[i]) continue;	// 이미 체크된 배열일 경우 skip
			for(int j = i * i; j < prime.length; j += i) {
				prime[j] = true;
			}
		}
		
	}
}
```

> 11653번(https://www.acmicpc.net/problem/11653)

- 내정답
- 오랜만에 혼자 풀어서 맞췄음
- 백준 홈페이지상 하기 코드를 실행했을때 시간이 매우 오래 걸렸음
- 매우 간단하게 짰음
  - 입력받을 숫자 n 설정
  - 최초로 나눌 숫자 num 설정
  - 나머지가 남을경우 num으로는 소인수 분해 되지 않는 경우이므로 num에 1을 더해서 다시 실행
  - 나누는 숫자와 입력받은 숫자 혹은 몫이 동일할경우 더 이상 소인수 분해되지 않으므로 while문을 끝내고 n을 출력
  - 소인수 분해 이므로 몫이 아닌 나누는 수(약수)를 출력해야함 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Main {

	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		
    	int num = 2;
		
		while(n != 1) {
		  
      if(n == num) {
        System.out.println(n);
        break;
        
      } if(n%num != 0) {
        
        num++;

      }else {
		    
		    n = n/num;
		    System.out.println(num);	    
		    
		  }
		  
		}
		
	}
	
}
```

- 다른 정답
- 출처(https://st-lab.tistory.com/152)
- 내 정답보다 시간이 훨씬 줄어듬
  - 내정답 : 156 ms
  - 하기답안: 124 ms
- 알고리즘
  - 2~n 까지 모든수를 나눠보며 나머지가 0일 경우 그값을 출력하는 것
  - 1은 소수가 아니므로 제외
  - 반복문의 범위를 n의 제곱근 까지로 설정
    - 어떤 n이 두개이상 소인수분해를 했을때 인수중 한개 이상은 반드시 n의 제곱근보다 작거나 같음
  - while문과 for문 종료후 마지막 인수 출력을 위한 조건문출력 조건을 설정(1은 소수가 아님)
    - n = 34일경우
    - for문은 34의 제곱근의 근사값인 5.83이하의 정수까지만 대입될것임
    - 17 % 2 != 0이므로 for문이 종료 되지만 17도 하나의 약수 이므로 출력해주어야함

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
 
		for (int i = 2; i <= Math.sqrt(N); i++) {
			while (N % i == 0) {
				sb.append(i).append('\n');
				N /= i;
			}
		}
		if (N != 1) {	
			sb.append(N);
		}
		System.out.println(sb);
	}
}
```

>1929번(https://www.acmicpc.net/problem/1929)

- 내정답
- 에라토스테네스의 체를 활용
- 0~n까지의 수를 모두 걸러낸뒤 0~m까지의 수를 모두 true 로 변경
- 단, 0과 1의 경우는 고정으로 true로 설정해주어야함
  - 설정해주지 않으면 0과 2입력시 0, 1, 2를 모두 출력함
  - 하단 m이 0이므로 0, 1을 true로 바꿔주지 못하기 때문
  - 나중에 알게된 사실
    - prime 메소드의 ` for(int i =0; i<m; i++) {arr[i] = true;}` 는 필요 없음
    - 메인의 `for(int i =m; i<arr.length; i++)` 에서 이미 걸러주기 때문

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {

  public static boolean arr[];
  
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		prime(m,n);
		
		for(int i =m; i<arr.length; i++) {
		  if(arr[i]==false) {
		    System.out.println(i);
		  }
		}
		
	}
	
	public static void prime(int m, int n) {
	  arr = new boolean [n+1];
	  
	  arr[0] = true;
	  arr[1] = true;
	  
	  for(int i = 2; i<=Math.sqrt(n); i++) {
	    
	    if(arr[i]==true) {
	      continue;
	    }
	    
	    for(int j=i*i;j<arr.length; j+=i) {
	      
	      arr[j]=true;
	      
	    }
	    
	  }
	  
//	   for(int i =0; i<m; i++) {
//	      arr[i] = true;
//	    }
	     
	}
	
}
```

- 비슷하지만 다른 풀이
- 출처(https://st-lab.tistory.com/84)

- 에라토스테네스의 체를 활용함
- 시간 면에서 나의 풀이보다 훨씬 빠름
  - 나의풀이 : 808ms
  - 하기풀이: 224ms
- 성능향상을 위해 StringBuilder를 활용

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
 
 
public class Main {
	public static boolean[] prime;
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		prime = new boolean[N + 1];	// 내 풀이와 다른 부분
		get_prime();
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = M; i <= N; i++) {
			// false = 소수 
			if(!prime[i]) sb.append(i).append('\n'); // 내 풀이와 다른부분
		}
		System.out.println(sb);
	}
 
	public static void get_prime() {
		// true = 소수아님 , false = 소수 
		prime[0] = prime[1] = true;
		
		for(int i = 2; i <= Math.sqrt(prime.length); i++) {
			if(prime[i]) continue;
			for(int j = i * i; j < prime.length; j += i) {
				prime[j] = true;
			}
		}
	}
}
```

- 소수판별과 동시에 출력
- 출처(https://st-lab.tistory.com/84)
- 최대값(n)까지 반복해야 가능(제곱근까지 불가)
  - 소수 판별 후 출력의 경우 배열의 false 값만 출력하므로 범위의 소수가 다 포함 되어있음
  - 하지만 하기방식은 false 값을 출력하지 못하므로 소수를 모두 찾기 위해 끝까지 가야함
  - 예시(0 16 입력시)
  - true 인 인덱스
    - 2의 배수 : 4 6 8 10 12 14 16
    - 3의 배수:  6 9 12 15
    - 5의 배수: 10 15
    - 7의 배수: 14
    - 11의 배수: 없음
    - 13의 배수: 없음
  - false 인 인덱스 : 2, 3, 5, 7, 11, 13 : 굳이 n까지 돌지 않아도 최대값의 제곱근까지에서 판별됨 
  - i를 StringBuilder에 직접저장하는 하기 방식에서는 최대값의 제곱근까지 하게되면 2, 3만 저장됨
- 이중 반복문의 내부 반복문이 수정되었음
  - `i*i` 가 될경우 int형 범위를 넘어 버릴수 있음
  - i 가 최대 1,000,000 가 된다면 j 의 경우 1,000,000,000,000 으로 int 형 범위초과

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
 
public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        
        boolean[] prime = new boolean[N + 1];
        
        for(int i = 2; i <= N; i++) {
        	if(prime[i]) continue;
        	
        	if(i >= M) sb.append(i).append('\n');
        	
        	for(int j = i + i; j <= N; j += i) {
        		prime[j] = true;
        	}
        }
        
        System.out.println(sb);
    }
 
}
```

> 4948번(https://www.acmicpc.net/problem/4948)

- 내 답안
- 내가 생각한 알고리즘
  - 변수 n 을 최초로 입력 받음
  - while문을 통해 0이 입력될때까지 반복함
  - 소수의 갯수를 세는 변수 cnt 초기화
  - 에라토스테네스의 체를 활용하여 소수를 구함
  - 소수일 경우 cnt를 1씩 올려줌
- 2가지 오류가 있음
  - 첫번째 열을 읽지 못함 => 두번째열 0 입력시 nullpointException이 나타나게됨
  - 소수를 세는 변수 cnt가 0 다음 차수에 0으로 초기화 되지 않음

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {


  public static boolean checkPrime[];
  
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		while(n!=0){
		  
		  n = Integer.parseInt(br.readLine());
		  
		  int cnt = 0;
		  
		  prime(n);
		  
		  for(int i = n; i<checkPrime.length; i++) {
		    
	      if(checkPrime[i]==false) {
	        
	        cnt++;

	      }
	             
	    }		
		  sb.append(cnt).append("\n");
		}
		System.out.println(sb);
    
	}
	
	public static void prime(int n) {
        
	  if(n==0) {
	    return;
	  }
        
	  checkPrime = new boolean [(2*n)+1];
	  
	  checkPrime[0] = checkPrime[1] = true;
    
    for(int i = 2; i <= Math.sqrt(checkPrime.length); i++) {
      
      if(checkPrime[i]==true) {
        
        continue;
      }
      
      for(int j = i * i; j < checkPrime.length; j += i) {
        
        checkPrime[j] = true;
        
      }
    }
	  
	}
	
}
```

- 정답
- 출처(https://st-lab.tistory.com/85)
- 정답을 보니 정말 아쉽다는 생각 부터 듬
- 동일한 알고리즘이지만 while문에 조건을 두지 않고 while문 내부에서 조건을 잡음
  - `while(true)` 라는 조건을 숙지할 것

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
	/*
	n > 123456 이므로 2n 은 최대 246912 이다.
	0 부터 시작하므로 246912 + 1
	*/
	public static boolean[] prime = new boolean[246913];
    
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		get_prime();	// 소수를 얻는 메소드 실행
		
		StringBuilder sb = new StringBuilder();
 
		while(true) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0) break;	// n 이 0 일경우 종료
            
			int count = 0;	// 소수 개수를 셀 변수
            
			for(int i = n + 1; i <= 2 * n; i++) {
				if(!prime[i]) count++;
			}
			sb.append(count).append('\n');	// 문자열로 이어준다
		}
		System.out.print(sb);
	}
	
	// 소수를 얻는 메소드
	public static void get_prime() {
		// 0 과 1 은 소수가 아니므로 ture
		prime[0] = prime[1] = true;
		
		for(int i = 2; i <= Math.sqrt(prime.length); i++) {
			if(prime[i]) continue;
			for(int j = i * i; j < prime.length; j += i) {
				prime[j] = true;
			}
		}
	}
 
}
```

> 9020번(https://www.acmicpc.net/problem/9020)

- 내답안
- 고민의시간이 많이 길었음
- 10000까지의 모든 소수를 배열에 저장하였음
- 이후 입력값으로 나누는 코드만 짜면 되지만 좀더 연구가 필요함

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Main {

  public static boolean checkPrime[];
  
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		
		int [] arr = new int[t];
		
		StringBuilder sb= new StringBuilder();
		
		checkPrime = new boolean[10001];
		
		for(int i = 0; i<arr.length; i++) {
		  arr[i]= Integer.parseInt(br.readLine());
		}
		
		prime();
		
		int cnt = 0;
		
		for(int i =0; i<checkPrime.length; i++) {
		  if(checkPrime[i]==false) {
		    cnt++;
		  }
		}
		
		int []primeNo = new int[cnt];
		
		int j =0;
		
    for(int i =0; i<checkPrime.length; i++) {
            
      if(checkPrime[i]==false) {
          
         primeNo[j]=i;
         
         j++;
                  
        }      
      
    }
      
	}//main end
	
	public static void prime() {

	  checkPrime[0] = checkPrime[1] = true;
    
    for(int i = 2; i <= Math.sqrt(checkPrime.length); i++) {
      if(checkPrime[i]) continue;
      for(int j = i * i; j < checkPrime.length; j += i) {
        checkPrime[j] = true;
      }
    }
    
	}  
	
}
```

