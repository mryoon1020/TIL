# Counting Sort(카운팅 정렬)

- 출처(https://st-lab.tistory.com/104)
- 시간복잡도가 O(n) 으로 엄청난 성능을 보여줌
- 기타 빠른 정렬 알고리즘 종류
  - 퀵 정렬(Quick Sort), 힙 정렬(Heap Sort), 합병 정렬(Merge Sort) 등
  - 얘네들은 시간복잡도가 O(nlogn)
- **단점:** 
  - counting이라는 새로운 배열을 선언해주어야 한다는 점
  - 정렬하고자 하는 배열의 가장 큰값에 따라 counting배열의 크기가 달라짐
  - 예를 들어 10개의 원소를 정렬하지만 수의 범위가 0~1억이라면 메모리가 매우 낭비됨

### 원리

- 배열1 예시

| index     | 0    | 1    | 2    | 3    | 4    | 5    | 6    | 7    | 8    | 9    | 10   | 11   |
| --------- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| **value** | 7    | 2    | 3    | 5    | 7    | 1    | 4    | 6    | 7    | 5    | 0    | 1    |

- 상기 배열을 0~11번 index까지 순회함
- 새로운 배열을 생성함(counting)
- counting 배열

| index     | 0    | 1    | 2    | 3    | 4    | 5    | 6    | 7    |
| --------- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| **value** |      |      |      |      |      |      |      |      |

- 배열1 예시의 값에 해당하는 인덱스의 값을 1씩 증가함
- 배열1의 5번 index까지 갔을 때

| index     | 0    | 1    | 2    | 3    | 4    | 5    | 6    | 7    |
| --------- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| **value** |      |      | 1    | 1    |      | 1    |      | 2    |

- 배열 1의 모든 인덱스를 다 순회 했을때

| index     | 0    | 1    | 2    | 3    | 4    | 5    | 6    | 7    |
| --------- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| **value** | 1    | 2    | 1    | 1    | 1    | 2    | 1    | 3    |

- 즉, 0의 갯수 = 1개, 1의 갯수 = 2개, 2의 갯수 =1게 ...... 이라는 뜻
- 이후 counting배열의 각 원소들을 누적합으로 설정해줌

| index     | 0    | 1    | 2        | 3            | 4                | 5                     | 6    | 7    |
| --------- | ---- | ---- | -------- | ------------ | ---------------- | --------------------- | ---- | ---- |
| **value** | 1    | 1+2  | (1+2) +1 | ((1+2)+1) +1 | (((1+2)+1)+1) +1 | ((((1+2)+1)+1) +1) +2 | ...  | ...  |

- 설정 결과

| index     | 0    | 1    | 2    | 3    | 4    | 5    | 6    | 7    |
| --------- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| **value** | 1    | 3    | 4    | 5    | 6    | 8    | 9    | 12   |

- 상기 블로그에서는 시작점 -1을 알려준다고 했음
  - 그냥 이해하기 편하게 각 counting 배열의 값에서 -1씩 빠진다고 생각해도 될듯함

- 정렬이 되는 과정
  - 안정적 순회를 위해 배열의 마지막 idex부터 순회하는 것이 좋음

**순회 1단계**

- 배열1 예시

| index     | 0    | 1    | 2    | 3    | 4    | 5    | 6    | 7    | 8    | 9    | 10   | 11   |
| --------- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| **value** | 7    | 2    | 3    | 5    | 7    | 1    | 4    | 6    | 7    | 5    | 0    | 1    |

- counting

| index     | 0    | 1    | 2    | 3    | 4    | 5    | 6    | 7    |
| --------- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| **value** | 1    | 3    | 4    | 5    | 6    | 8    | 9    | 12   |

- 정렬 중인 배열1
  - 배열1[11] = 1
  - counting[1] = 3
  - 3 -1 = 2
  - 배열1[11]의 값은 ======> 배열1[2]의 값으로 변경

| index     | 0    | 1    | 2     | 3    | 4    | 5    | 6    | 7    | 8    | 9    | 10   | 11   |
| --------- | ---- | ---- | ----- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| **value** |      |      | **1** |      |      |      |      |      |      |      |      |      |

**순회 2단계**

- 배열1 예시

| index     | 0    | 1    | 2    | 3    | 4    | 5    | 6    | 7    | 8    | 9    | 10   | 11   |
| --------- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| **value** | 7    | 2    | 3    | 5    | 7    | 1    | 4    | 6    | 7    | 5    | 0    | 1    |

- counting

| index     | 0    | 1    | 2    | 3    | 4    | 5    | 6    | 7    |
| --------- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| **value** | 1    | 3    | 4    | 5    | 6    | 8    | 9    | 12   |

- 정렬 중인 배열1
  - 배열1[10] = 0
  - counting[0] = 1
  - 1 -1 = 0
  - 배열1[10]의 값은 ======> 배열1[0]의 값으로 변경

| index     | 0     | 1    | 2    | 3    | 4    | 5    | 6    | 7    | 8    | 9    | 10   | 11   |
| --------- | ----- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| **value** | **0** |      | 1    |      |      |      |      |      |      |      |      |      |

**순회 3단계**

- 배열1 예시

| index     | 0    | 1    | 2    | 3    | 4    | 5    | 6    | 7    | 8    | 9    | 10   | 11   |
| --------- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| **value** | 7    | 2    | 3    | 5    | 7    | 1    | 4    | 6    | 7    | 5    | 0    | 1    |

- counting

| index     | 0    | 1    | 2    | 3    | 4    | 5    | 6    | 7    |
| --------- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| **value** | 1    | 3    | 4    | 5    | 6    | 8    | 9    | 12   |

- 정렬 중인 배열1
  - 배열1[9] = 5
  - counting[5] = 8
  - 8 -1 = 7
  - 배열1[9]의 값은 ======> 배열1[7]의 값으로 변경

| index     | 0    | 1    | 2    | 3    | 4    | 5    | 6    | 7     | 8    | 9    | 10   | 11   |
| --------- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ----- | ---- | ---- | ---- | ---- |
| **value** | 0    |      | 1    |      |      |      |      | **5** |      |      |      |      |

- 모든 순회를 마치게 되면 배열1은 다음과 같이 정렬이 됨

| index     | 0    | 1    | 2    | 3    | 4    | 5    | 6    | 7    | 8    | 9    | 10   | 11   |
| --------- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| **value** | 0    | 1    | 1    | 2    | 3    | 4    | 5    | 5    | 6    | 7    | 7    | 7    |

### 코드로 구현하기

- 조건
  - 배열의 크기 100개, 수의 범위 0~30, 숫자는 무작위 입력

```java
public class Main {
  
	public static void main(String[] args) {
 
	  int arr[] = new int[100];
	  
	  int counting[] = new int[31];
	  
	  int sort[] = new int[100];
	  
	  // 배열에 무작위 숫자 입력
	  
	  for(int i =0; i<arr.length; i++) {
	    arr[i] = (int) (Math.random()*31); // 0~30까지의 수
	        // Math.random()은 0.0~1.0 사이의 임의의 double을 나타내는 함수
	        // 0.999999는 나올수 있지만 1.0은 나오지 않음
	        // 30까지의 int를 나타 내려면 31을 곱해주어야함
	        // 5~30 까지의 int는 ((Math.random())*31)+5로 처리해주면 됨
	  }
	  
	  // counting 배열
	  
	  for(int i =0; i<arr.length; i++) {
	    counting[arr[i]]++;  // arr을 순회하며 해당 인덱스의 값을 1씩 증가시킴
	  }
	  
	 // 누적합 설정

	  for(int i=1; i<counting.length; i++) {
	    counting[i]= counting[i]+counting[i-1];
	  }
	  
	 // 정렬
	  
	  for(int i =arr.length -1; i>=0; i--) {
	    int value = arr[i];
	    counting[value]--;
	    sort[counting[value]] = value;
	  }
	
	// 비교출력  
	  
	// 초기 배열 
    System.out.println("arr[]");
    for(int i = 0; i < arr.length; i++) {
      if(i % 10 == 0) System.out.println();
      System.out.print(arr[i] + "\t");
    }
    System.out.println("\n\n");
    
    
    // Counting 배열 
    System.out.println("counting[]");
    for(int i = 0; i < counting.length; i++) {
      if(i % 10 == 0) System.out.println();
      System.out.print(counting[i] + "\t");
    }
    System.out.println("\n\n");
    
    
    // 정렬된 배열
    System.out.println("sort[]");
    for(int i = 0; i < sort.length; i++) {
      if(i % 10 == 0) System.out.println();
      System.out.print(sort[i] + "\t");
    }
    System.out.println();
	  
	}  
	
}
```

- count 정렬을 사용하여 푼 2751번(https://www.acmicpc.net/problem/2751)

- 런타임에러는 나지만 정렬하는데 문제없음

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  
	public static void main(String[] args) throws IOException {
 
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
	
	int t = Integer.parseInt(br.readLine());
	
	int[] arr = new int[t];
	
	int[] count = new int[1000000];
	
	int[] sort = new int[t];
	
	for(int i =0; i<arr.length; i++) {
	  arr[i]= Integer.parseInt(br.readLine());
	}
	
	for(int i =0; i<arr.length; i++) {
	  count[arr[i]]++;
	}
	
	for(int i =1; i<count.length; i++) {
	  count[i]= count[i]+count[i-1];
	}

	for(int i = arr.length -1; i>=0; i--) {
	  
	  int value = arr[i];
	  count[value]--;
	  sort[count[value]]=value;
	  
	}
	
	for(int i =0; i<sort.length; i++) {
	  
	  System.out.println(sort[i]);
	}
	
	}  
	
}
```

- 해당 블로거의 정답
- 출처(https://st-lab.tistory.com/106)
- 좀더 간결함
  - 배열을 3개씩이나 생성하지 않음
  - StringBuilder를 통해 좀더 시간을 단축함
  - 수가 중복되지 않으므로 boolean 배열 사용 가능
  - 문제에서 절대값 1000000이하인 정수라고 하였음
    - 수의 범위는 -1000000 ~ 1000000
    - arr [1000000] = 0 이 해당하는 곳이라고 생각함
      - 숫자 0은 배열의 1000000번 index에 해당한다고 생각하고 문제를 품 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Main {
	public static void main(String[] args) throws IOException {
    
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
        
		/*
		  -1000000 ~ 1000000
		  기준점 0 = index 100000 으로 생각
		*/
		boolean[] arr = new boolean[2000001];	
        
		int N = Integer.parseInt(br.readLine());
        
		for(int i = 0; i < N; i++) {
			arr[Integer.parseInt(br.readLine()) + 1000000] = true;
		}
 
		for(int i = 0; i < arr.length; i++) {
			if(arr[i]) {
				sb.append((i - 1000000)).append('\n');
			}
		}
		System.out.print(sb);
	}
}
```

