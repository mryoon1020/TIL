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

> 1193번(https://www.acmicpc.net/problem/1193)

- 풀지 못한 문제
- 정답 출처(https://st-lab.tistory.com/74)
- 알고리즘
  - 분모+분자 => T
  - 칸에 있는 분수의 갯수 => T-1
  - 지그재그 규칙
    - 분수갯수가 홀수일 때
      - (T-1) -- / 1 ++
    - 분수갯수가 짝수일 때
      - 1 ++ / (T-1) --
    - 홀수 짝수 판별식
      - `(T-1) % 2 == 0` =======> 짝수
      - `(T-1) % 2 == 1` =======> 홀수

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int X = Integer.parseInt(br.readLine());	// 입력받을 X의 값
 
		int cross_count = 1;	//	해당범위의 대각선 칸 갯수
        int prev_count_sum = 0;	//	해당 대각선 직전 대각선 까지의 칸의 누적 합
 
		while (true) {
			// 직전 대각선 누적합 + 해당 대각선 개수 이용한 범위 판별
			if (X <= prev_count_sum + cross_count) {	
				
				if (cross_count % 2 == 1) {	// 대각선의 개수가 홀수라면 
					// 분자가 큰 수부터 시작
					// 분자는 대각선상 블럭의 개수 - (X 번째 - 직전 대각선까지의 블럭 개수 - 1) 
					// 분모는 X 번째 - 직전 대각선까지의 블럭 개수
					System.out.print((cross_count - (X - prev_count_sum - 1)) + "/" + (X - prev_count_sum));
					break;
				} 
				
				else {	// 대각선의 개수가 짝수라면 
					// 홀수일 때의 출력을 반대로 
					System.out.print((X - prev_count_sum) + "/" + (cross_count - (X - prev_count_sum - 1)));
					break;
				}
 
			} else {
				prev_count_sum += cross_count;
				cross_count++;
			}
		}
	}
}
```

> 2869번(https://www.acmicpc.net/problem/2869)

- 답이 나오지 않는 내 답안
- 정점에 도달하였을때는 미끄러지지 않는다 조건을 달아주지 못함
  - a = 2, b = 1, v = 5 일 때
  - 4일 차에서 남은 높이를 다 올라감

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
 
		int d = 0;
		
		while(v>0) {
		  v = v - a + b;
		  d++;
		}
		
		System.out.println(d);
		
	}
}
```

- 이후 수정한 내 답안
- 정답을 구하는데 문제는 없지만 시간초과됨

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken()); 
		int b = Integer.parseInt(st.nextToken()); 
		int v = Integer.parseInt(st.nextToken());
 
		int d = 0;
		
		while(v>0) {
		  
		  if(v == b) {
		    
		    break;
		    
		  }else {
        v = v - a + b;
        d++;
		  }
		}
		
		System.out.println(d);
		
	}
}
```

- 정답

- 출처(https://st-lab.tistory.com/75)

- 참고(https://lordofkangs.tistory.com/104)

- 수학연산으로 반복문없이 시간을 줄였음

  - 최소 일수 : n , 높이 : v , 올라간 거리 : a , 밤사이 미끄러진 거리 : b

  - 최소 일수 전날 : n-1

  - 최소 일수 전날까지는 올라가고 미끄러지고를 계속 반복했음

    ==> `(n-1) * (a-b)`

  - 최소 일수 당일의 경우 올라가기만 함

    ==> `+a` 

  - 상기 식을 이용하여 부등식을 세운다면

    - **(n-1) * (a-b) + a >= v**
    - 부등식을 풀어 본다면

  - na - nb - a + b + a >= v

  - n(a - b) >= v - b

  - **n >= (v - b) / (a - b)**

    - 부등식을 보기 쉽게 풀어본다면

      1.  **n = (v - b) / (a - b)**

      2.  **n > (v - b) / (a - b)**

    - 두가지 경우가 나옴

      - 1의 경우 : 나머지가 없는(0인) 경우 

      - 2의 경우 : 나머지가 0 아닌 경우

        => 2 의 경우 조건문 처리하여 n에 1을 처리함

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
 
		int n = (v - b) / (a - b);
		if ((v - b) % (a - b) != 0)
			n++;
 
		System.out.println(n);
	}
}
```

- 상기 부등식과 같은 내용이지만 본인이 이해하기 어렵웠던 내용
  - 하루동안 올라간 거리 : 3, 밤사이 미끄러진 거리 : 1
  - 하기 표에서 높이가 8인 경우와 9인 경우를 비교해봤을 때
    - 높이가 9인 경우 4일 필요
    - 높이가 8인 경우 4일 필요
    - 두경우 모두 4일 필요함을 알수 있음
    - 높이가 9인 경우 `높이 / (올라간거리 - 미끄러진거리)` 로 최소 일수가 나오지 않음
      - 4회 등반했을 때 딱 떨어지기 때문
      - 그러므로 `(높이 - 미끄러진거리) / (올라간거리 - 미끄러진거리)` 할 경우
        - 높이: 8  몫: 3  나머지: 1
        - 높이: 9  몫: 4
        - 나머지가 있는경우 일수에 +1 해줌으로 최소 일수를 걸러줄 수 있음

```

												10
									
4회등반							  9			  9
------------------------------------------------------------4					
4회추락			 	  8			  8			  8
			
3회등반	  7			  7			  7			  7
------------------------------------------------------------3
3회추락 	  6			  6			  6			  6

2회등반 	  5			  5			  5			  5
------------------------------------------------------------2
2회추락	  4			  4			  4		      4

1회등반	  3   		  3			  3			  3
------------------------------------------------------------1
1회추락	  2			  2			  2			  2
	        
		    1		    1			1		    1
------------------------------------------------------------0
			0			0			0			0

```

> 10250번(https://www.acmicpc.net/problem/10250)

- 삼중 for 반복문을 이용하려 하였으나 실패한 내 코드
- 배열의 값이 누적으로 저장되는 오류가 있음

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
		int t = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		int[] room = new int[h*w];
		
		for(int i =0; i<room.length; i++) {
		  
//		  for(int j = 0; j<w; j++) {
		    
		    room[i] = 1;
		    
		    if(i<w) {
		      room[i] ++;
		    }
		    
//		    for(int k =0; k<h; k++) {
		      
//		      if(k<h) {
//		        
//		        room[i] = (k+1)*100;
		        
//		        System.out.println("room "+j+" : "+room[j]);
		        
//		      }else if(k<=(j+1)*h) {
//		        k=0;
//		        break;
//		      }
		      
//		    }
		    
//		  }
		  
		  
		}
		  for(int i = 0; i<room.length;i++) {
		System.out.println("room "+i+" : "+room[i]);
		  }
	}
}
```

- 정답출처(https://st-lab.tistory.com/77)

- 입력값: 

  - H: 높이(층수)
  - W: 가로길이(한층당 방갯수)
  - N: n번째 손님

- 높이 y : 손님이 위치한 층수

  - **y = N % H**

  - 예시: 3층 6열 10번째 손님의 층수

  - 10/3 = 3 ...1  ===> 1층에 위치, 104호에 있을 것임

  - 층별 호실

    - ```
      301		302		303		304		305		306
      201		202		203		204		205		206
      101		102		103		104		105		106
      ```

  - 풀이: 

    - 문제 조건은 101호 201호 301호를 채운후 다시 102호로 다시 채움
    - 즉, 한줄씩 채움 => 채워지는 방수는 층수와 같음
    - 주의 할점은 나머지가 없는경우 ==> 따로 조건문 처리

- 거리(방) x: 엘리베이터와의 거리

  - **x = N / H +1**
  - 예시: 3층 6열 10번째 손님의 층수
  - 10/3 = 3 ...1  ===> 3+1 = 4호에 위치
  - 엘리베이터와의 거리는 1부터 시작이므로 +1을 해주어야함
  - 풀이:
    - 2번째 손님의 예시:
    - 2/3 = 0...2 ===> 2층에 있지만 엘리베이터와의 거리는 0이 아닌 1임 ===> 201호에 있음

```java
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
 
public class Main {
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());	// 테스트 케이스
 
		for (int i = 0; i < T; i++) {
            
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

//==================================================================            
            
			int H = Integer.parseInt(st.nextToken());	// 층수
            
			st.nextToken();			// W 는 필요없는 값이므로 버림
            
			int N = Integer.parseInt(st.nextToken());	// n번째 손님
 
			if (N % H == 0) {
                
				sb.append((H * 100) + (N / H)).append('\n');
 
			} else {
                
				sb.append(((N % H) * 100) + ((N / H) + 1)).append('\n');
			}
            
		}// for end
        
		System.out.print(sb);
 
	}
}
```

> 2775번(https://www.acmicpc.net/problem/2775)

- 풀지 못한 문제

- 정답 출처(https://st-lab.tistory.com/78)

- 2차원배열 활용한 문제

- **2차원 배열**

  - 행렬과 비슷

  - ```java
    int[][] arr = new int[rows][columns];
    					//[행크기][열크기];
    int[][] arr = { {1, 2, 3}, {4, 5, 6} };
    			// 선언과 동시에 초기화도 가능함
    		// { 
    		// {[0][0]의 요소, [0][1]의 요소, [0][2]의 요소},
    		// {[1][0]의 요소, [1][1]의 요소, [1][2]의 요소} 
    		// };
    ```

  - `int[][] arr = new int[2][3];` 의 도식화

  - |   arr\[0][0]   |   arr\[0][1]   |   arr\[0][2]   |
    | :------------: | :------------: | :------------: |
    | **arr\[1][0]** | **arr\[1][1]** | **arr\[1][2]** |

  - 참고사이트 : http://www.tcpschool.com/java/java_array_twoDimensional

- 해설

  - |         | 0호  | 1호  | 2호  | 3호  | 4호  | 6호  |
    | ------- | ---- | ---- | ---- | ---- | ---- | ---- |
    | **4층** | X    | 1    | 6    | 21   | 56   | 126  |
    | **3층** | X    | 1    | 5    | 15   | 35   | 70   |
    | **2층** | X    | 1    | 4    | 10   | 20   | 35   |
    | **1층** | X    | 1    | 3    | 6    | 10   | 15   |
    | **0층** | X    | 1    | 2    | 3    | 4    | 5    |

  - 상기 표를 참조 한다면

  - 2층 3호 => 2층2호 + 1층3호 => 4 + 6 임을 알수있음

  - k층 n호 => k층 n-1호 + k-1층 n호 

  - 0호는 0명으로 취급해도 괜찮음 

  - 하기 코드는 아파트를 만드는 것을 가독성을 위해 메소드로 분리 했음

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
 
	public static int[][] APT = new int[15][15];
 
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		make_APT();	// 아파트 만들기 함수 
		
		int T = Integer.parseInt(br.readLine());	// 시행횟수
 
		for (int i = 0; i < T; i++) {
            
			int k = Integer.parseInt(br.readLine());
			int n = Integer.parseInt(br.readLine());
            
			sb.append(APT[k][n]).append('\n');	// StringBuilder에 출력결과 저장
            
		}
        
		System.out.println(sb);
        
	}
 
	
	public static void make_APT() {
		// 아파트 생성
 
		for (int i = 0; i < 15; i++) {
			APT[i][1] = 1; // i층 1호
			APT[0][i] = i; // 0층 i호
		}
 
		for (int i = 1; i < 15; i++) { // 1층부터 14층까지
 
			for (int j = 2; j < 15; j++) { // 2호부터 14호까지, 1호부터 해도 상관없지만
                							// 1호는 항상 1로 위에서 초기화 해주었음
                							// 반복문을 돌려도 항상 1만 나옴
				APT[i][j] = APT[i][j - 1] + APT[i - 1][j];
			}
		}
	}
 
}
```

> 2839번(https://www.acmicpc.net/problem/2839)

- 최초 내 답
  - 11과 같은 수는 완벽한 봉지가 나옴에도 걸러내지 못하는 오류가 있음

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
 
	public static int[][] APT = new int[15][15];
 
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		
		int cal = n%5;
		
		int cal2 = cal/3;
		
		if(cal%3 == 0) {
		  System.out.println(n/5+cal2);
		}else {
		  
		  if(n%3 == 0) {
		    System.out.println(n/3);
		  }else {
		    System.out.println(-1);
		  }
  
		}
    
	}
 
	
}
```

- while문으로 다시 제작했으나 18대입시 3이 정답으로 나옴
- 5의 배수 뺀값이 3, 6, 9, 12인 경우만 최소봉지 갯수라고 생각했음

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {

	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		
		int cnt = 0;
		
		if(n%5 != 0) {
		
		while(n!=0) {
		
		  n = n-(5*cnt);		  
		  
		  if(n>0) {
		  
		    if(n==3|n==6|n==9|n==12) {
		    
		    cnt=cnt+n/3;
		    break;
		    
		    }
		  
		  cnt++;
		  
		  }else {
		    cnt=-1;
		    break;
		  }
		}
		
		System.out.println(cnt);
		
//		}else if(n%3 != 0) {
//		  cnt=n/3;
//      System.out.println(cnt);
    }
		
	}
	
}
```

- 정답
- 출처(https://st-lab.tistory.com/72)
- 수학적규칙을 찾아 한 풀이 방법

```java
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
 
		if (N == 4 || N == 7) {
			System.out.println(-1);
		}
		else if (N % 5 == 0) {
			System.out.println(N / 5);
		}
		else if (N % 5 == 1 || N % 5 == 3) {
			System.out.println((N / 5) + 1);
		}
		else if (N % 5 == 2 || N % 5 == 4) {
			System.out.println((N / 5) + 2);
		}
	}
}
```

- 또 다른 정답
- 출처(https://yongku.tistory.com/1434)
- while문 사용
- 조건에 맞는 while문을 사용하려고 했던 나와 달리 5의 배수가 아닌경우 계속 -3씩 빼주면서 5로 나누어줬음
- -3이 되는 경우 계속 봉지갯수를 추가함

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {

	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		
        int cnt = 0;
        
        while(true){
            
            if(n%5 == 0){
                
                cnt += n/5;
                System.out.println(cnt);
                break;
                
            } else{
                n -= 3;
                cnt++;
            }
            
            if(n<0){
                System.out.println(-1);
                break;
            }
            
        }
		
	}
	
}
```

