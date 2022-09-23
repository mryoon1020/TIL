# Beakjoon(단계별 문제 풀이)

### 손익분기점

> 1712번(https://www.acmicpc.net/problem/1712)

- 컴파일 에러나는 내정답
- 하기정답과 같은 로직임에도 불구하고 컴파일에러가 남
- 원인은 계산을 if문 밖에서 변수로 설정해준 것
  - 변수를 계산식으로 설정하게 될경우 분모가 0이 되는 경우가 생기게되어 오류가 발생함
  - 굳이 변수로 설정해주려면 `else{ int cal = a/(c-b); System.out.println(cal+1); }`
  - 백준에서 클래스명에 숫자 들어가면 컴파일 에러가남
- 로직은 간단함
  - 판매가격 = 가변비용+이익
  - 이익 = 판매가격 - 가변비용
  - 비용 = 고정비용 + (가변비용*생산량)
  - 손익분기점(break point)
    - 판매가격\*판매량 = 고정비용 + (가변비용\*판매량 )
      - 생산량을 구해야 하므로 양변의 생산량을 하나로 묶어줌
    - 판매가격\*판매량 - (가변비용\*판매량 ) = 고정비용
    - (판매가격 - 가변비용) \* 판매량 = 고정비용
    - 판매량 = 고정비용/(판매가격 - 가변비용)
      - 상기 판매량은 수익이 같아진 지점임
      - 판매량+1을 해줘야 이익이 나는 지점

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  
  public static void main(String[] args) throws IOException{
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int a = Integer.parseInt(st.nextToken());
    
    int b = Integer.parseInt(st.nextToken());
    
    int c = Integer.parseInt(st.nextToken());

    int cal = a/(c-b);
    
    if(c <=b) {
      
      System.out.println(-1);
      
    }else {
      
      System.out.println(cal+1);
      
    }

  }
  
}
```

- while문을 사용한 내 정답
- 굉장히 느림

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
  
  public static void main(String[] args) throws IOException{
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int a = Integer.parseInt(st.nextToken());
    int b = Integer.parseInt(st.nextToken());    
    int c = Integer.parseInt(st.nextToken());
    
    int cal = c-b;
    
    int cnt = 0;
    
    if(cal <=0) {
      
      System.out.println(-1);
      
    }else {
      
      while(a >= cal*cnt) {
        cnt++;
      }
      
      System.out.println(cnt);
      
    }

  }
  
}
```

- 정답
- 출처(https://st-lab.tistory.com/71)

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
		StringTokenizer st = new StringTokenizer(br.readLine());
 
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
 
		if (C <= B) {
			System.out.println("-1");
		} 
		else {
			System.out.println((A/(C-B))+1);
		}
	}
}
```

> 2292번(https://www.acmicpc.net/problem/2292)

- 풀지 못한 문제

- 정답출처(https://st-lab.tistory.com/73)

- 알고리즘

  - 1을 기점으로 둘러싸는 갯수

    - 1겹    =>    2겹    =>    3겹       =>   4겹

    - 1        =>	6	    =>	12	    =>	18		=======>	6씩 증가하고 있음

    - 1        =>    2~7   =>    8~19    =>    20~37  

      =======>    최단거리(count)는 범위(겹)가 늘어남에 따라 1씩증가

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
		int N = Integer.parseInt(br.readLine());
		int count = 1; // 겹 수(최소 루트)
		int range = 2;	// 범위 (최솟값 기준) 
 
		if (N == 1) {
			System.out.print(1);
		}
 
		else {
			while (range <= N) {	// 범위가 N보다 커지기 직전까지 반복 
				range = range + (6 * count);
				count++;
			}
			System.out.print(count);
		}
	}
}
```

- 비슷한 풀이
- 출처(https://youtu.be/rIH8_7zdJFc)
- 해당영상은 Scanner를 사용함
- 상기 블로그 답과 차이는 반복문 안에 조건문을 넣어 준 것

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
		int N = Integer.parseInt(br.readLine());
		int count = 1;
		int sum = 1;
 
		while(true){
            if(N<= sum){
                break;
            }
            sum+= count*6;
            count++;
        }
        System.out.println(count);
	}
}
```

