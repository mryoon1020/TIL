# Beakjoon(단계별 문제 풀이)

### 문자열

> 11654번(https://www.acmicpc.net/problem/11654)

- 선행공부(https://st-lab.tistory.com/41?category=830901)
  - 아스키코드
    - **ASCII** (**A**merican **S**tandard **C**ode for **I**nformation **I**nterchange, 미국 정보 교환 표준 부호)
    - 1963년 미국 ANSI에서 표준화한 정보교환용 7비트 부호체계
    - 000(0x00)부터 127(0x7F)까지 총 128개의 부호가 사용
    - 1바이트를 구성하는 8비트 중에서 7비트만 씀(1비트는 통신에러 검출용)
    - 한글 사용 불가 => UTF-8로 사용
- 정답
- 출처(https://st-lab.tistory.com/59)

```java
public class Main {
	public static void main(String[] args) throws Exception {
 
		int a = System.in.read();
		System.out.print(a);
 
	}
}
```

> 11720번(https://www.acmicpc.net/problem/11720)

- 입력값을 받은후 `charAt()` 을 사용하여 분리 및 배열에 저장
- 자바의 내장함수 `Character.getNumericValue()` 를 사용하여 char => int 변환 및 연산

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  
  public static void main(String[] args) throws IOException{
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int n = Integer.parseInt(br.readLine());
    
    String num = br.readLine();
    
    char [] number = new char[n];

    for(int i =0; i < n; i++) {
      number[i] = num.charAt(i);
    }
    
    int sum =0;
    
    for(int i =0; i < n; i++) {
      System.out.println(number[i]);
      sum += Character.getNumericValue(number[i]);
    }
    
    System.out.println(sum);
  }
  
}
```

- 또 다른 문제 풀이
- 출처(https://st-lab.tistory.com/61)
  - 아스키 코드 활용
  - 아스키코드번호 - 48 or - '0' 을 하게되면 아스키코드는 자신의 수가 됨
    - ex) 5의 아스키 코드값은 53, 0의 아스키 코드값은 48
    - 53 - 48 = 5 
    - 또는 `.charAt() - '0'` 을 사용해도 됨
  - 하기 코드에서는 배열도 사용하지 않아도 됨
    - getBytes() 는 문자열을 byte 배열로 반환함
    - for-each 문을 사용함
    - for-each문 구조
      - `for (타입 변수명 : 변수에 순차적으로 대입하여 for문을 실행시킬 객체) { 루프를 돌릴 내용 }`
      - `for (type var : iterate) {body-of-loop}` 

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
	public static void main(String[] args) throws IOException {		
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();	// N 은 필요없지만 문제에서 요구 하므로 입력만 받음
		
		int sum = 0;
		
		for(byte value : br.readLine().getBytes()) {
			sum += (value - '0');	// 또는 (value-48)
		}
		
		System.out.print(sum);
		
	}
}
```

- 상기 코드의 `getBytes()` 가 궁금해서 찍어 보았음

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
	public static void main(String[] args) throws IOException {		
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();	// N 은 필요없지만 문제에서 요구 하므로 입력만 받음
		
//		int sum = 0;
		
		for(byte value : br.readLine().getBytes()) {
            System.out.println(value);
//			sum += (value - '0');	// 또는 (value-48)
		}
		
//		System.out.print(sum);
		
	}
}
//================= 콘솔결과 ==================
6	----------> 최초 횟수 입력(상시코드에서는 기능 없는 부분)
654321 ----------> 입력 숫자
54 ----------> 6에 대한 아스키 코드
53 ----------> 5에 대한 아스키 코드
52 ----------> 4에 대한 아스키 코드
51 ----------> 3에 대한 아스키 코드
50 ----------> 2에 대한 아스키 코드
49 ----------> 1에 대한 아스키 코드
```

> 10809번(https://www.acmicpc.net/problem/10809)

- 오류나는 내코드
  - 입력된 글자와 상관없이 -1만 출력함
  - 이중 for문에서 문제가 발생한것으로 추정됨
  - for문대신 while 문으로 바꾸는 것이 더 합리적으로 보임
  - `String.valueOf()` 는 char을 String으로 변경해줌

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  
  public static void main(String[] args) throws IOException{
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String word = br.readLine();  // N 은 필요없지만 문제에서 요구 하므로 입력만 받음
    String[] wordArr = new String[word.length()];
    String[] alpha = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    
    for(int i =0;i<word.length();i++) {
      wordArr[i] = String.valueOf(word.charAt(i));
    }
    
    for(int i =0;i<wordArr.length;i++) {
      
      for(int j =0; j<alpha.length;j++) {
        
        if(wordArr[i] == alpha[j]) {
          
          alpha[j] = Integer.toString(i);
          
        }else {
          
          alpha[j] = "-1";
          
        }
      }

    }
    
    for(int i =0; i<alpha.length;i++) {
    
    System.out.print(alpha[i]+" ");
    }
  }
  
}
```

- 정답
- 출처(https://st-lab.tistory.com/62)
  - 아스키코드 활용
  - 알파벳 총 갯수와 동일한 26개의 -1로 이루어진 배열생성
  - 알파벳 배열에서 각 알파벳의 배열의 자리수는 `'알파벳' - 'a'`
    - 'a'의 아스키 코드 값은 97
    - ex) 'f'의 배열 인덱스
      - 'f'-'a' = 102 - 97  => 5  ==> 6번째에 있음(인덱스가 5이므로)

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
		int[] arr = new int[26];
		
		for(int i = 0; i < arr.length; i++) {
            
			arr[i] = -1;	//배열 -1로 초기화
            
		}
        
//================================================================ 
        
		String S = br.readLine();	//단어 입력
 
//===============================================================       
        
		for(int i = 0; i < S.length(); i++) {
            
			char ch = S.charAt(i);	// 단어의 각자리별 알파벳 뽑아내기
    
			if(arr[ch - 'a'] == -1) {	// 해당 원소 값이 -1 인 경우에만 변경
				arr[ch - 'a'] = i;
			}
		}

//===============================================================              
        
		for(int val : arr) {	// 배열 출력
			System.out.print(val + " ");
		}
	}
}
```

> 2675번(https://www.acmicpc.net/problem/2675)

- 내 오답
  - 입력받은 값을 charAt()을 이용하여 뽑아낸뒤 StringBuilder에 저장하여 출력하려 했음
  - 곱셈연산에서 오류나는 것으로 추정됨

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int n = Integer.parseInt(br.readLine());
    
    int m = 0;
    
    String w = "";
    
    StringBuilder sb = new StringBuilder();
    
    for(int i = 0; i<n; i++) {
      m=Integer.parseInt(br.readLine());
      
      for(int j =0; j<m; j++) {
      
      w = br.readLine();
      sb.append(w.charAt(i))*m);
      }
      sb.append("\n");
    }
    
    System.out.println(sb);
    
  }

}
```

- 정답
- 출처(https://st-lab.tistory.com/63)
- 생각보다 간단한 해법이었음

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());	//테스트 횟수

//==========================================        
        
		for(int i = 0; i < T; i++) {
	
            //데이터 입력 및 숫자와 글자 부분을 분리해주는 부분
            
			String[] str = br.readLine().split(" ");	// 공백 분리
			
			int R = Integer.parseInt(str[0]);	// 반복 횟수
			String S = str[1];	//글자
            
//==========================================			
            
			for(int j = 0; j < S.length(); j++) {
                
				for(int k = 0; k < R; k++) {
                    
					System.out.print(S.charAt(j));
                    
				}	//for k end
			}	//for j end
			System.out.println();
		}	//for i end
	}
}
```

> 1157번(https://www.acmicpc.net/problem/1157)

- 손 못댄 문제
- 정답 출처 (https://st-lab.tistory.com/64)
- 해설
  - 아스키 코드 값이용:  
    - 대문자: `A = 65 ~ Z = 90` 
    - 소문자:  `a = 97 ~ z = 122` 
    - ? : `? = 63`
  - 알고리즘
    - 문자열 입력 받음
    - 알파벳 갯수 만큼 정수 배열을 선언해줌(26개)
    - 입력받은 문자열을 검사하기 위핸 반복문 설정
    - 반복문을 돌며 배열의 요소 증가
    - 조건문 최대값 출력 또는 ? 출력 판단
    - 최종값 출력

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
 
	public static void main(String[] args) throws IOException{
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] arr = new int[26];	// 알파벳 갯수 만큼 배열 선언(대소문자 구분 없으므로 26)
		String s = br.readLine();	// 문자열 입력
		
//===================================================================
        
		for (int i = 0; i < s.length(); i++) {	// 입력 받은 문자열의 갯수만큼 반복
            
        // 대소문자 구분 없애는 파트
            
			if ('a' <= s.charAt(i) && s.charAt(i) <= 'z') {
                // 'a'의 아스키 코드 값 : 97 , 'z'의 아스키 코드값 : 122
                // 97 <=  s.charAt(i)  <= 122
                // 소문자 범위
                
				arr[s.charAt(i) - 97]++;
                // 'a'를 빼주어 해당 인덱스 요소찾기 및 ++연산시행
                
			} else {	// 대문자
                
				arr[s.charAt(i) - 65]++;
                // 'A'를 빼주어 해당 인덱스 요소찾기 및 ++연산시행
                
			}
            
		} // for end
        
//==================================================================        
        
		int max = -1;	// 최대값 초기화, 0으로 초기화해도 문제 없으나 관행상 -1로 초기화
		char ch = '?';
        
		for (int i = 0; i < 26; i++) {	// 알파벳 갯수 많큼 for문 돌림
            
        // 최대값 구하기
 
			if (arr[i] > max) {	// 최대값 찾는 구문
                
				max = arr[i];
				ch = (char) (i + 65);	// 최대값의 인덱스를 알파벳으로 치환
                						// 대문자로 출력해야하므로 65('A')를 더함
                
			} // if end
            
       // 가장 많이 사용 된 알파벳이 여러개 존재하는 경우
            
			else if (arr[i] == max) {
                
				ch = '?';
                
			} // else if end
            
		} // for end
        
		System.out.print(ch);
        
	} // main end
 
} //class end
```

> 1152번(https://www.acmicpc.net/problem/1152)

- 생각보다 아주 간단한 문제였으나 생각이 꼬여서 시간이 많이 걸렸음
- 본인도 StringTokenizer를 이용했음
- 출처 블로그의 필자와 달리 배열에 값을저장한 후 다시 출력하는 비효율적인 코드를 짰었음
- 이후 정답 참조 블로그의 필자와 비슷하게 수정했었으나 공백오류가 났음
- 글을쓰고 있는 지금 다시 돌려보니 오류가 나지 않았음
- 간혹 STS 자체에서 오류가 나는 듯 함

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test {

  public static void main(String[] args) throws IOException {

    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int n = st.countTokens();
    
//    String [] word = new String[n];
//    
//    for(int i = 0; i < n; i++) {
//      word[i]= st.nextToken();
//    }
    
    System.out.println(n);
    
  }

}
```

- 이후 다음과 같이 코드를 짰으나 실행이 되지 않았음

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  
  public static void main(String[] args) throws IOException{
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    String word = br.readLine();
    
    int n = word.length();
    
    int [] cnt = new int [n];
    
    //아스키 코드 스페이스바(공백) = 32
    
    for(int i = 0; i<n; i++) {
      cnt[i]=0;
    }
    
    for(int i = 0; i<n; i++) {
      if(word.charAt(i)!= 32) {
        cnt[i] = 1;
      }

    }
    
    //{1 1 0 0 0 1 1 1 0 0}
    int cntWord = 0;
    int realCnt = 0;
    for(int i = 0; i<n; i++) {
//      int cntWord = 0;
      int cntAlpha = 0;
      
      if(cnt[i] != 0) {
        cntAlpha++;
        
        if(cntAlpha !=0) {
          cntWord++;
        } else {
          cntWord =0;
        }
        System.out.println("cntWord : " + cntWord);
      }
      
      
      
      if(cntWord != 0) {
//        cntWord =0;
        cntAlpha =0;
        realCnt++;
      }
      
      System.out.println(realCnt);
    }
    
    System.out.println(realCnt);    
    
  }
  
}
```

- 정답코드
- 출처(https://st-lab.tistory.com/65)

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		System.out.print(st.countTokens());
	}
}
```

