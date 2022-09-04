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

> 1546번(https://www.acmicpc.net/problem/1546)

- 런타임 오류나는 내 답안
- 결과는 잘나옴
- 추후에 알게된 사실
  - 문제에서 모든 점수를 바꿔서 계산했다고 언급 되있으므로 굳이 100을 빼줄필요가없었음
  - 배열의 요소를 확인하기위해 만들었던 출력문이 그대로 정답으로 제출 되었었음

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
 
  public static void main(String[] args) throws IOException{
    
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      int n = Integer.parseInt(br.readLine());
      
      double[] num = new double[n];
      
      for(int i =0 ; i<num.length;i++) {
        
        num[i]= Double.parseDouble(br.readLine());
      }
      
      Arrays.sort(num);
      
      double max = num[n-1];
      
      for(int i=0;i<num.length;i++) {
       num[i] = num[i]/max*100;
      }
      
      double cal = 0;
      
      for(int i=0;i<num.length;i++) {
        cal += num[i];
      }
      
      for(int i=0;i<num.length;i++) {
      
      System.out.println(num[i]);
      }
      System.out.println((cal-100)/n);
      
  }
}
```

- 정답코드 참조 후 수정해서 정답처리된 코드
  - StringTokenizer를 사용하여 한줄로 입력받은 후 처리해야 정답처리되는 것으로 보임
  - StringTokenizer 미사용시 IDE상에서 정답 출력에는 이상 없으나 홈페이지에서 런타임오류가 뜸 

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
      
      double[] num = new double[n];
      
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      for(int i =0 ; i<num.length;i++) {
        num[i]= Double.parseDouble(st.nextToken());
      }
      
      Arrays.sort(num);
      for(int i=0;i<num.length;i++) {
       num[i] = num[i]/num[n-1]*100;
      }
      
      double cal = 0;
      for(int i=0;i<num.length;i++) {
        cal += num[i];
      }

      System.out.println((cal)/n);  
  }
}
```

- 정답 코드
- 출처(https://st-lab.tistory.com/47)

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
 
  public static void main(String[] args) throws IOException{
    
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      double[] num = new double[Integer.parseInt(br.readLine())];
      
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      for(int i =0 ; i<num.length;i++) {
        num[i]= Double.parseDouble(st.nextToken());        
      }
      
      double sum = 0;
      
      Arrays.sort(num);
      
      for(int i=0;i<num.length;i++) {
        sum += ((num[i]/num[num.length-1])*100);
      }
      
      System.out.println(sum/num.length);
      
  }
}
```

> 8958번(https://www.acmicpc.net/problem/8958)

- 풀지 못한문제
- 출처(https://st-lab.tistory.com/50)
- OX는 대문자로 입력해야 정답처리
- chartAt()을 사용하여 문자열에서 한개씩 문자를 뽑아냄

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
 
  public static void main(String[] args) throws IOException{
    
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      StringBuilder sb = new StringBuilder();
      
      int n = Integer.parseInt(br.readLine()); //테스트 횟수
      
      String str[] = new String[n];
      
      for(int i =0; i<n;i++) {
        str[i] =br.readLine();	//배열에 문자열 저장
      }

      for(int i =0; i<n;i++) {

        int cnt = 0;	//매회 초기화 하기 위함
        int sum = 0;
        
        for(int j =0;j<str[i].length();j++){	//str배열의 요소(문자열)한개의 길이만큼
												//반복문돌리기
          if(str[i].charAt(j) == 'O') {
            cnt++;	//O일 경우 cnt+1, O가 연속으로 나올경우 2, 3, 4 계속 증가할 것임
          }else {
            cnt=0;	//O가 아닐 경우 cnt 초기화
          }
          sum+=cnt;	//총합, 루프1개당 계속 더해중
        }
        sb.append(sum).append('\n');	//루프가 1개끝날때 마다 sum과 줄바꿈은 sb에 저장
      }
      System.out.println(sb);
  }
}
```

> 4344번(https://www.acmicpc.net/problem/4344)

- 출처(https://st-lab.tistory.com/51)
- String.format() 을 활용하여 n번째 자리에서 반올림
  - String.format("%.3f",(cnt/stu)*100) : 소수점 3번째 자리까지 반올림 (결과: 00.000)
  - String.format("%.2f",(cnt/stu)*100) : 소수점 2번째 자리까지 반올림 (결과: 00.00)
  - 반올림방법중에 Math.round() 도 있음. 이 방법은 소수점 아래가 0일때 표기하지 않음

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
 
  public static void main(String[] args) throws IOException{
    
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      int[] score;
      
      int n = Integer.parseInt(br.readLine());	//시행횟수
      StringTokenizer st;
      
      for(int i =0; i<n;i++) {
        st =new StringTokenizer(br.readLine());	//학생수 및 성적입력
        
        int stu = Integer.parseInt(st.nextToken());	//학생수 저장
        score = new int[stu];	//위에서 저장된 학생수 만큼의 성적 입력욕 배열생성
        
        double sum =0;	//성적 총합 변수 및 초기화
        
        for(int j=0;j<stu;j++) {
          int val = Integer.parseInt(st.nextToken());	//성적 변수에 저장
          score[j] = val;	//성적 배열에 저장
          sum += val;	//성적끼리 합
        }
        
        double avg = sum/stu;	//성적 평균
        double cnt = 0;	//평균성적 넘는 학생수 카운트변수 초기화
        
        for(int j =0;j<stu;j++) {	//학생수만큼 반복
          if(score[j]>avg) {
            cnt++;
          }
        }
        System.out.println(String.format("%.3f",(cnt/stu)*100)+"%");
      }
      
  }
}
```

