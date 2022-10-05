# 소수 구하기 알고리즘

### 소인수분해

- 합성수를 소수들의 곱으로 나타내는 것
- 모든 합성수는 그 수의 제곱근 보다 작거나 같은 약수를 갖음
  - 증명: *n*을 합성수라 하자. 그러면 n=ab,\,1<a,b<n*n*=*a**b*,1<*a*,*b*<*n*이다. 만약 a,b*a*,*b*가 둘 다 \sqrt n*n*보다 크다면, n=\sqrt n\sqrt n<ab=n*n*=*n**n*<*a**b*=*n*이 되어 모순이다. 따라서 a,b*a*,*b*중 적어도 하나는 \sqrt n*n*보다 같거나 작다.

### 프로그래밍에서의 활용

-  RSA 암호화 방식
  - 소수를 활용한 암호화 방식
  - ***임의의 수들의 곱은 구하기 쉽지만 역으로 소인수 분해하는 것은 어렵다.*** 골자로 고안된 방법
  - 자리수가 커질수록 더 어려움
  - 공개키와 개인키 한쌍을 이룸
  - 키만들기
    - 두 소수 p, q 준비
    - p - 1, q - 1과 각각 서로소인 정수 e 준비
    - ed % (p - 1) (q - 1) = 1 인 정수 d를 찾음
    - N = pq 계산 후 N, e를 공개 ===> 공개키 => 일상어를 암호문으로 바꿀 때 씀
    - d를 숨김 ===> 개인키 => 공개키로 상대방이 만든 암호문을 해독할 때 씀
    - p, q, (p - 1) (q - 1)는 파기

## 소수 구하기 알고리즘

### 소인수분해

- N보다 작은 자연수 들로 모두 나누기

- 내 방법
  - 임의의 수 n입력시 2부터 시작하여 n%num == 0 일경우만 나눗셈 시행
  - n%num != 0 일경우에는 1씩 증가하면 다시 반복문 반복
  - 몫과 나누는 수가 같아질 경우 반복문 빠져나옴(마지막 약수이기 때문)

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

### 소수판별

- 2이상 n미만의 수 중에 나누어 떨어지는 수가 존재한다면 소수가 아님을 이용한 판별법
- 출처(https://st-lab.tistory.com/81)
- 반드시 `return;` 을 해주어야함

```java
import java.util.Scanner;
 
public class Prime {
	public static void main(String[] args) {
 
		Scanner in = new Scanner(System.in);
        
		is_prime(in.nextInt());
    
	}
 
	// 소수 판별 메소드 
	public static void is_prime(int number) {
 
		// 0 과 1 은 소수가 아니다
		if(number < 2) {
			System.out.print("소수가 아닙니다");
			return;
		}
		
		// 2 는 소수다
		if(number == 2) {
			System.out.print("소수입니다");
			return;
		}
		
        
		for(int i = 2; i < number; i++) {
        
			// 소수가 아닐경우
			if(number % i == 0) {
				System.out.print("소수가 아닙니다");
				return;
			}
		}
		// 위 반복문에서 약수를 갖고 있지 않는경우 소수다.
		System.out.print("소수입니다");
		return;
	}
 
 
}
```

### n이하의 모든 소수구하기

- 0~n까지의 반복문을 설정함
- 출처(https://st-lab.tistory.com/81)
- 소수판별 메소드를 작성하여 소수판별하여 출력함

```java
// 소수만 출력
 
import java.util.Scanner;
 
public class Prime {
	public static void main(String[] args) {
 
		Scanner in = new Scanner(System.in);
 
		int N = in.nextInt();
        
		// 0 ~ N 까지 수 중 소수를 구하는 반복문        
		for(int i = 0; i <= N; i++) {
			make_prime(i);
		}
    
	}
 
	// 소수 생성 메소드 
	public static void make_prime(int number) {
 
		// 0 과 1 은 소수가 아니므로 종료
		if(number < 2) {
			return;
		}
		
		// 2 는 소수다
		if(number == 2) {
			System.out.println(number);
			return;
		}
		
        
		for(int i = 2; i < number; i++) {
        
			// 소수가 아닐경우 종료
			if(number % i == 0) {
				return;
			}
		}
		// 위 반복문에서 약수를 갖고 있지 않는경우 소수다.
		System.out.println(number);
		return;
	}
 
 
}
```

### 상기 소수판별 및 소수 알고리즘의 시간 단축 방법

- n의 제곱근까지만 반복문을 돌려주면 됨
- 상기 소인수분해의 증명과 같은 이유임
- 좀 더 쉬운 설명은 하기 출처에 있음
- 출처(https://st-lab.tistory.com/81)
- 메소드내부의  `for(int i = 2; i < number; i++)` => `for(int i = 2; i < Math.squr(number);i++)` 

### 에라토스테네스의 체

- k=2부터 N의 제곱근 이하까지 반복하여 자연수들중 k를 제외한 k를 제외한 k의 배수들을 제외시킨다
  - 2의 배수 삭제 => 3의 배수 삭제 => 4의 배수 삭제 => ...... => k의 배수 삭제
- 출처(https://st-lab.tistory.com/81)
- 개인적으로 어려웠던 알고리즘
- 배수들을 제외하는 방법이라 시간이 많이 걸릴 것 같지만 오히려 줄어듬
  - 이미 검사한 내용은 검사하지 않기 때문
  - 예를 들어 6은 2의 배수이지만 3의 배수이기도 함, 2의배수의 단계에서 이미 지워졌으므로 검사하지 않고바로 다음 단계로 건너뜀

```java
import java.util.Scanner;
 
public class Prime {
 
    public static boolean[] prime;	// 소수를 체크할 배열
	public static void main(String[] args) {
 
		Scanner in = new Scanner(System.in);
        
		int N = in.nextInt();
		
		make_prime(N);
 
		for(int i = 0; i < prime.length; i++) {
			if(prime[i] == false) {	// 소수(false)일 경우 출력
				System.out.println(i);
			}
		}
	}
 
	// N 이하 소수 생성 메소드 
	public static void make_prime(int N) {
		
		prime = new boolean[N + 1];	// 0 ~ N
 
		/*
		소수가 아닌 index = true
		소수인 index = false
		*/
		
		// 2 미만의 N 을 입력받으면 소수는 판별할 필요 없으므로 바로 return
		if(N < 2) {
			return;
		}
        
		prime[0] = prime[1] = true;
		
        
		// 제곱근 함수 : Math.sqrt()
		for(int i = 2; i <= Math.sqrt(N); i++) {
        
			// 이미 체크된 배열이면 다음 반복문으로 skip
			if(prime[i] == true) {
				continue;
			}
        
			// i 의 배수들을 걸러주기 위한 반복문
			for(int j = i * i; j < prime.length; j = j+i) {
				prime[j] = true;
			}
		}
 
	}
 
}
```

