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

> 4673번(https://www.acmicpc.net/problem/4673)

- 정답출처(https://st-lab.tistory.com/53)
- 손도 못댄 문제
- 순수 코드

```java
package cal;


public class Main {
  
  public static void main(String[] args){
    
    boolean[] check = new boolean[10001];
    
    for(int i =0; i<10001;i++) {
      int n = d(i);
      
      if(n<10001) {
        check[n]=true;
      }
      
    }
    
    StringBuilder sb =new StringBuilder();
    
    for(int i =1; i<10001; i++) {
      if(!check[i]) {
        sb.append(i).append('\n');
      }
    }
      System.out.println(sb);
  }
  
  public static int d(int number) {
    int sum = number;
    
    while(number !=0) {
      sum = sum + (number % 10);
      number = number/10;
    }
    
    return sum;
  }
  
}
```

- 코드 쪼개서 해석해보기

​	**※	함수출력 결과 대입부분**

```java
public class Main {
  
  public static void main(String[] args){
    
    boolean[] check = new boolean[10001];	// 1부터 10000까지 임 
      										// 배열의 index는 0부터 시작이므로 배열의 크기는 10001임
    
    for(int i =0; i<10001;i++) {	//d()함수에 계속 i를 넣어줌
      int n = d(i);	//int n = d(1) / int n = d(2) / int n = d(3) /......
      
      if(n<10001) {		//10000 까지 boolean type의 배열에 true 저장
        check[n]=true;	//문제의 조건에서 10000이하의 숫자만 요구하였음
          				//for문이 계속 돌면서 int n이 10000이하인 값이라면 배열에 true를 저장함
          				//만약 n = 1234 일때 check[1234] = true; 가 되는 것임
      }//if end
      
    }//for end
     
  }//main end
  
}//class end
```

​	**※	함수 d(i)**

- 10진법의 원리

- 예시) 

  ※ While문 내부 순서

  - **1234** 라는 숫자가 number로 입력되었을 때 일의자리 더하기
    - 1234 => 1234 % 10 => 1234 + **4** `1234의 1의자리수` 
  - 10의 자리수 숫자 출력을 위해 자리수 줄여주기
    - 1234 => 1234 / 10 => 123
  - 123 != 0 이므로 다시 반복
    - 123 => 123 %10 => (1234 + **4**) + **3** `1234의 10의 자리수` 
    - 123 => 123 / 10 => 12
  - 12 != 0 이므로 다시 반복
    - 12 => 12 % 10 => ((1234+ **4**) +**3**) + **2** `1234의 100의 자리수` 
    - 12 => 12 / 10 => 1
  - 1 != 0 이므로 다시 반복
    - 1 => 1 % 10 => (((1234+ **4**) +**3**) + **2**) + **1** `1234의 1000의 자리수` 
    - 1 => 1 / 10 => 0
  - 0 != 0 과 맞지 않으므로 while문 탈출
  - 함수 실행결과 리턴 : sum = (((1234+ **4**) +**3**) + **2**) + **1** => sum = **1244**

```java
  public static int d(int number) {
    int sum = number;
    
    while(number !=0) {
      sum = sum + (number % 10);	//첫째 자리수를 입력된 정수에 더해주는 식
      number = number/10;		//10을 나누어 이미 더해진 첫째자리수 삭제

    }//while end
    
    return sum;
  }//function d() end
```

​	**※	출력부분**

- 함수를 실행했을 때 true 값이 저장되지 않은 인덱스를 출력(false 인덱스 출력)
  - 함수 `d()` 의 출력 결과 = index , 배열에 저장하는 값 = true, 루프를 돌아 true를 저장했다는 것은 셀프넘버가 아니라는 뜻임

```java
 StringBuilder sb =new StringBuilder();	//숫자 출력을 위함
    
    for(int i =1; i<10001; i++) {
        
      if(!check[i]) {	//boolean이므로 !true는 false임
          				//배열요소의 값이 false인 인덱스만 출력
          
        sb.append(i).append('\n');
          
      }//if end
        
    }//for end
      System.out.println(sb);
```

> 1065번(https://www.acmicpc.net/problem/1065)

- 답이 도출되지 않는 내정답
  - 등차수열 공식인 `n(초항+마지막항)/2` or `n{(2a+(n-1)d}/2` 을 활용하고 싶었음
  - 하지만 1234 와 1243과 같은 수는 걸러내지 못하는 오류가 있음

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  
  public static void main(String[] args) throws IOException{
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int n = Integer.parseInt(br.readLine());
    
    boolean [] arr = new boolean[n+1];
    
    for(int i=0; i<arr.length;i++) {
      int num = ap(i);
      
      if(num<n+1) {
        arr[num]=true;
      }
    }
    
    int cntTrue = 0;
    for(int i =0; i<arr.length; i++) {
      if(arr[i]) {
        cntTrue++;
      }
    }
    System.out.println(cntTrue-1);
  }
  
  /*
   * 
   *생각 정리
   * 321이 들어온다면
   * 321을 쪼개서 배열에 저장
   * 자리수를 알기 위해서는 int => string.length
   * 
   */
  
  public static int ap(int number) {
    
    String index = Integer.toString(number);
    int [] arr = new int[index.length()];
    
    int eap = number;
    int cnt = 0;
    
    while(number!=0) {
      
      arr[cnt]= number%10;
      cnt++;
      number=number/10;
    }
    
    int sum = 0;
    
    for(int i=0;i<arr.length;i++ ) {
    
      sum+=arr[i];
      
    }
    
 //   int cal = arr.length * (arr[0]+arr[index.length()-1]) / 2;
    int cal = arr.length * ((2*arr[0])+((arr.length-1)*(arr[1]-arr[0]))) / 2;
 
    if(sum == cal) {
      
      return eap;
    }else {
      return 0;
    }

  }
  
}
```

- 정답
- 출처(https://st-lab.tistory.com/54)
- 순수코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  
  public static void main(String[] args) throws IOException{
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    System.out.println(as(Integer.parseInt(br.readLine())));

  } // main end
  
  public static int as(int num) {
    int cnt = 0;
    
    if(num <100) {
      return num;
    }else {
      cnt = 99;
      
      for(int i = 100; i<= num; i++) {
        int hun = i/100;
        int ten = (i/10)%10;
        int one = i%10;
        
        if((hun - ten) == (ten - one)) {
          cnt++;
        } //for안에 if end
      } //for end
    } //else end
    
    return cnt;
    
  } //as() end
  
} //class end
```

- 코드 뜯어 보기

※ main

- 단순 숫자 입력 부분
  - 숫자가 입력되면 as()에 넣어주고 연산결과를 도출해주는 역할

```java
  public static void main(String[] args) throws IOException{
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    System.out.println(as(Integer.parseInt(br.readLine())));

  } // main end
```

※as()

- 등차수열의 일반항 n = 초항 + (n-1)*공차
  - 두자리숫자 및 한자리 숫자는 그 자체로 등차수열임의 유의 할것
    - ex: 1 : 초항이 1 인 등차수열
    - ex: 18 : 초항이 1 공차는 7인 등차수열
  - 세자리 숫자부터는 각 항간의 공차를 확인 하여야함
- 문제에서의 범위 : 1 이상 1000 이하의 숫자(자연수)
  - 검사해야할 숫자 : 100이상 1000 이하 사이의 숫자

```java
public static int as(int num) {
    int cnt = 0;	//한수 카운트용
    
//---------------------------------------------------    
    
    if(num <100) {
      return num;	//그자체로 등차수열인 숫자들
        			//입력된 num의 값이 99라면
        			//99개의 한수를 가지고 있는 것임
        			//반복문을 돌아도 되지만 굳이 필요없음
        			//as()의 리턴값임
    }
    
//---------------------------------------------------    
    else {
        
      cnt = 99;		//입력숫자가 100 이상일 경우
        			//1~99는 한수 이므로 카운트해주기 위함
        
//---------------------------------------------------   
        
      for(int i = 100; i<= num; i++) {	//99까지는 이미 한수 이므로 100부터 시작
          
        int hun = i/100;		//백의자리수
        int ten = (i/10)%10;	//십의 자리수
        int one = i%10;			//일의 자리수
          
//--------------------------------------------------- 
          
        if((hun - ten) == (ten - one)) {	//각자리수의 차가 공차 => 등차수열
            
          cnt++;	//한수 카운트
            
        } //for안에 if end
          
//---------------------------------------------------  
          
      } //for end
        
    } //else end
    
    return cnt;	//for문을 빠져나온 결과 리턴, as()의 리턴 값임
    
  } //as() end
```

