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
- 오류가 난다면 너무 콘솔창을 믿지 말것
- 최소한 한번 껏다가 다시 시도해볼것

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

> 2908번(https://www.acmicpc.net/problem/2908)

- 내정답
- 설명
  - 최초 입력 n
  - StringTokenizer를 통해 a 와 b로 분리
  - 각 자리수를 바꾼 a, b를 저장하기 위한 빈문자열 변수 changeA, changeB 선언
  - for 반복문을 이용하여 자리수 변경
    - charAt()의 인덱스 번호를 맞춰주기 위해 `i ` 가 아닌 `i-1` 로 설정하여야 함
    - chatAt(0)이 첫번째 자리이기 때문
  - if 조건문을 이용하여 큰수 출력

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  
  public static void main(String[] args) throws IOException{
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    String n = br.readLine();    
    
    StringTokenizer st = new StringTokenizer(n);
    
    String a = st.nextToken();
    String b = st.nextToken();
    
    String changeA = "";
    String changeB = "";
    
    for(int i = a.length() ;i>0;i--) {

      changeA+=a.charAt(i-1);
      
    }
    
    for(int i = b.length();i>0;i--) {
      
      changeB += b.charAt(i-1);
      
    }
    
    if(Integer.parseInt(changeA)>Integer.parseInt(changeB)) {
      System.out.println(changeA);
    }else{
      System.out.println(changeB);
    }
  
  }
  
}
```

- 훨씬 더 깔끔한 정답

- 출처(https://st-lab.tistory.com/66)

- StringBuilder 의 reverse() 메소드를 이용

- 비교구문은 삼항연산자를 이용

  - 삼항 연산자 :

    -  `(조건문) ? 참 인경우 : 거짓인 경우;` 
    - 객체에 담는 것도 가능, 모든항의 타입은 맞춰 주어야함
    - ex) 

    ```java
    int max = Integer.parseInt(changeB) < Integer.parseInt(changeA) ? Integer.parseInt(changeA):Integer.parseInt(changeB);
    //=======================================================
    String ex = changeA==changeB ? "같다":"다르다";	// 가능
    String ex = changeA==changeB ? 1:2;	// 불가능  1:2 ===> "1":"2" 변경해주면 가능
    ```

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
        
		int A = Integer.parseInt(new StringBuilder(st.nextToken()).reverse().toString());
		int B = Integer.parseInt(new StringBuilder(st.nextToken()).reverse().toString());
		
		System.out.print(A > B ? A:B);
		
	}
}
```

- 수학적 연산을 통해 활용
- 상기 답안과 같은 블로그 기재 답안
- 48은 0의 아스키 코드값임

```java
import java.io.IOException;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
		int A = 0;
		int B = 0;
		
		A += System.in.read() - 48;
		A += (System.in.read() - 48)*10;
		A += (System.in.read() - 48)*100;
		
		System.in.read();	// 공백
		
		B += System.in.read() - 48;
		B += (System.in.read() - 48)*10;
		B += (System.in.read() - 48)*100;
		
		System.out.println(A > B ? A : B);
	}
}
```

> 5622번(https://www.acmicpc.net/problem/5622)

- 내 정답
- 굉장히 지저분하지만 풀렸음

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 숫자 1 : abc 2초
 * 숫자 2 : def 3초
 * 숫자 3 : ghi 4초
 * 
 * wa = 13초 ===> w = 9 => 10초 a = 2 => 3초
 * 
 */

public class Main {
  
  public static void main(String[] args) throws IOException{
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    String a = br.readLine();
    
    char [] arr = new char [a.length()];
    
    int sec = 0;
    
    for(int i =0; i<a.length();i++) {
      arr[i]=a.charAt(i);
    }
    
    for(int i =0; i<a.length();i++) {
      
      if(arr[i] == 'A' |arr[i] == 'B'|arr[i] == 'C') {
        sec += 3;
      }else if(arr[i] == 'D' |arr[i] == 'E'|arr[i] == 'F') {
        sec += 4;
      }else if(arr[i] == 'G' |arr[i] == 'H'|arr[i] == 'I') {
        sec += 5;
      }else if(arr[i] == 'J' |arr[i] == 'K'|arr[i] == 'L') {
        sec += 6;
      }else if(arr[i] == 'M' |arr[i] == 'N'|arr[i] == 'O') {
        sec += 7;
      }else if(arr[i] == 'P' |arr[i] == 'Q'|arr[i] == 'R'|arr[i] == 'S') {
        sec += 8;
      }else if(arr[i] == 'T' |arr[i] == 'U'|arr[i] == 'V') {
        sec += 9;
      }else if(arr[i] == 'W' |arr[i] == 'X'|arr[i] == 'Y'|arr[i] == 'Z') {
        sec += 10;
      }
        

    }
      
    System.out.println(sec);
    
  }
  
}
```

- switch 문을 사용한 정답
- 출처(https://st-lab.tistory.com/67)

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Main {
	public static void main(String[] args) throws IOException {
    
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		String s = br.readLine();
        
		int count=0;
		int k = s.length();
        
		for(int i = 0 ; i < k ; i++) {
        
			switch(s.charAt(i)) {
			case 'A' : case 'B': case 'C' : 
				count += 3; 
				break;
                
			case 'D' : case 'E': case 'F' : 
				count += 4; 
				break;
                
			case 'G' : case 'H': case 'I' : 
				count += 5; 
				break;
                
			case 'J' : case 'K': case 'L' : 
				count += 6; 
				break;
                
			case 'M' : case 'N': case 'O' : 
				count += 7; 
				break;
                
			case 'P' : case 'Q': case 'R' : case 'S' :
				count += 8; 
				break;
                
			case 'T' : case 'U': case 'V' : 
				count += 9; 
				break;
                
			case 'W' : case 'X': case 'Y' : case 'Z' : 
				count += 10; 
				break;
			}
		}		
		System.out.print(count);
	}
}
```

- 같은 블로그내 BufferedReader 없이 짠 코드
  - 'A'\~'B' 의 아스키 코드 : 65\~90 이용
  - 해당 블로거는 이방법을 추천하지 않았음
  - JAVA에서 구현하는 입출력 스트림사용을 추천함
  
- 정답 제출에는 문제가 없으나 콘솔창에 3이 계속 더해지는 오류가 있음

- `count-3` 을 할경우 오답처리됨

- 하기와 같이 조건문 변경에도 컴파일 에러가 남(콘솔에서는 잘됨)

  - `  if(value < 65) count = count;`
  - `  else if(value < 68) count += 3;`

- 원인 **추측**

  - `System.in.read()` 을 사용할경우 다음 코드를 실행하기 위해서는 `'\n'` 입력 또는 `System.out.println()` 와 같은 멈춤장치가 필요한것으로 보임

  - 백준채점은 우분투이기 때문에 '\n'으로 해도 문제가 되지 않음

  - `value` 를 출력해보면 입력한 문자와 상관없이  `13` , `10` 이 출력되는 것을 볼수 있음

  - 아스키 코드 : 

    - `13 = CR(Carriage Return, 첫번째로 돌아감, \r)` 
    - `10 = LF(Line Feed, 줄바꿈, \n)`

  - 리눅스 : `\n` 은 첫번째로 돌아감을 포함되어있음

  - 윈도우: `\n` 만 쓸경우 인식하지 못함, 반드시 `\r\n` 을 해주어야함

  - 자바가상머신(JVM)이 윈도우에서 `\n` 를 사용했을시 `\r` 도 자동으로 입력하는 것으로 보임

  - 자동으로 입력된 `\r` 이 저장되어 조건문속으로 들어가 실행되어 3이 입력되는것으로 추정됨

  - 참조사이트: 

    - [CR(\r), LF(\n) 개념](https://m.blog.naver.com/taeil34/221325864981 "CR(\r), LF(\n) 개념")
    
    - [System.in 자료1](https://blog.naver.com/jihogrammer/222281057830 "System.in 자료1")
    
    - [System.in 자료2](https://blog.naver.com/jihogrammer/222281999239 "System.in 자료2")
    
    - [System.in 자료3](https://blog.naver.com/jihogrammer/222314445259 "System.in 자료3")
    
    - [아스키코드표](https://hanburn.tistory.com/87 "아스키코드표")


```java
import java.io.IOException;
 
public class Main {
	public static void main(String[] args) throws IOException {
		
		int count = 0;
		int value;
		
		while(true) {
			
			value = System.in.read();
			
			if(value == '\n') {
				break;
			}
			
			if(value < 68) count += 3;
			else if(value < 71) count += 4;
			else if(value < 74) count += 5;
			else if(value < 77) count += 6;
			else if(value < 80) count += 7;
			else if(value < 84) count += 8;
			else if(value < 87) count += 9;
			else count += 10;
			
			
		}
		System.out.print(count);
	}
}
```

> 2941번(https://www.acmicpc.net/problem/2941)

- 런타임 에러나는 내 정답
- 답도출에는 문제가 없지만 백준 홈페이지에서 런타임 에러가 남

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

    
public class Main {
  
  public static void main(String[] args) throws IOException{
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    String word = br.readLine();
    
    char [] arr = new char[word.length()];
    
    int [] cnt = new int[word.length()];
    
    for(int i =0 ; i<word.length();i++) {
      arr[i] = word.charAt(i);
    }
    
    for(int i =0 ; i<word.length();i++) {
      cnt[i]= 0;
    }
    
    for(int i =0 ; i<word.length();i++) {
      
      if(arr[i] == 'c' && arr[i+1] == '=') {
        cnt[i]++;
        cnt[i+1]--;
        
        if(i+1 == word.length()-1 && arr[i+1] != '=') {
          cnt[i+1]++;
        }
        
      }else if(arr[i] == 'c' && arr[i+1] == '-') {
        cnt[i]++;
        cnt[i+1]--;
        
        if(i+1 == word.length()-1 && arr[i+1] != '-') {
          cnt[i+1]++;
        }
        
      }else if(arr[i] == 'd' && arr[i+1] == 'z' && arr[i+2] == '=') {
        cnt[i]++;
        cnt[i+2]--;
        
        if(i+2 == word.length()-1 && arr[i+2] != '=') {
          cnt[i+1]++;
          cnt[i+2]++;
        }
        
      }else if(arr[i] == 'd' && arr[i+1] == '-') {
        cnt[i]++;
        cnt[i+1]--;
        
        if(i+1 == word.length()-1 && arr[i+1] != '-') {
          cnt[i+1]++;
        }
        
      }else if(arr[i] == 'l' && arr[i+1] == 'j') {
        cnt[i]++;
        cnt[i+1]--;
        
        if(i+1 == word.length()-1) {
          cnt[i+1]++;
        }
        
      }else if(arr[i] == 'n' && arr[i+1] == 'j') {
        cnt[i]++;
        cnt[i+1]--;
        
        if(i+1 == word.length()-1) {
          cnt[i+1]++;
        }
        
      }else if(arr[i] == 's' && arr[i+1] == '=') {
        cnt[i]++;
        cnt[i+1]--;
        
        if(i+1 == word.length()-1 && arr[i+1] != '=') {
          cnt[i+1]++;
        }
        
      }else if(arr[i] == 'z' && arr[i+1] == '=') {
        cnt[i]++;
        cnt[i+1]--;
        
        if(i+1 == word.length()-1 && arr[i+1] != '=') {
          cnt[i+1]++;
        }
        
      }else {
        cnt[i]++;
      }
      
    }
    
    int cntA = 0;
    
    for(int i =0 ; i<word.length();i++) {
      cntA += cnt[i]; 

    }
    
    System.out.println(cntA);
  }
  
}
```

- 정답
- 출처(https://st-lab.tistory.com/68)
- 해당블로그의 필자는 배열을 사용하지 않았음
- 조건문을 좀 더 압축하여 코드길이를 줄임

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Main {
	public static void main(String[] args) throws IOException {
    
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		int len = str.length();
		int count = 0;
 
 //===========================================================       
        
		for (int i = 0; i < len; i++) {
 
			char ch = str.charAt(i);
 
			if(ch == 'c' && i < len - 1) {	
                
                // ex) 입력문자 : jjabqc , i = 5 , len = 6              
                // str.charAt(5) == 'c' && 5 < len 하게 되면 다음 조건에서 오류가 남
                // 조건 str.charAt(i + 1) == '=' 에 대입해보면
                // str.charAt(5 + 1) == '=' 
                // ===> c가 마지막 글자이므로 오류가남(charAt(6)는 존재하지 않기 때문)
                // 그러므로 len -1 로 처리해주어야함
                
				if(str.charAt(i + 1) == '=' || str.charAt(i + 1) == '-') {
                    
					i++;
                    
                    // 이경우 c= 가 입력 됬다면 c=을 한글자로 취급 하므로
                    // 한단계 건너 띄워 주어야 함
                    // 입력문자 : ac=b , 시행결과는 하기와 같음
                    // for문의 i = 1 일 경우 : charAt(1+1) == '=' 의 조건에 맞음
                    // 조건문이 실행 됬으므로 i 가 2인 상태로 루프 한개가 끝남
                    // 다음 루프는 i = 3 으로 실행됨
                    
				}
				
			} else if(ch == 'd' && i < len - 1) {
                
                // ch == 'd'인 경우는 2가지 경우로 나누어 봐야함
                // d- 와 dz= 경우
                
				if(str.charAt(i + 1) == '-') {	// d- 일 경우
                    
						i++;
                    
				} else if(str.charAt(i + 1) == 'z' && i < len - 2) {
					
					if(str.charAt(i + 2) == '=') {	// dz= 일 경우
						
                        i += 2;
                        
                        // 이 경우 두글자를 건너 띄어야 함
                        // i++이 아닌 i+=2 가 되어야함
                        
					}
				}
                
			} else if((ch == 'l' || ch == 'n') && i < len - 1) {
                
				if(str.charAt(i + 1) == 'j') {	// lj 또는 nj 일 경우
                    
					i++;
                    
				}
                
			} else if((ch == 's' || ch == 'z') && i < len - 1) {
                
				if(str.charAt(i + 1) == '=') {	// s= 또는z= 일 경우
                    
					i++;
                    
				}
			
		    }
            
 //=========================================================== 	
            
			count++;
 			// for문의 한 루프가 끝나면 count 에 +1을 해줌
            
		}//for end

 //===========================================================        
        
		System.out.println(count);
        
	}
}
```

> 1316번(https://www.acmicpc.net/problem/1316)

- 풀지 못했던 문제

- 정답
- 출처(https://st-lab.tistory.com/69)

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
 
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
	public static void main(String[] args) throws IOException{
 
		int count = 0;
		int N = Integer.parseInt(br.readLine());
 
		for (int i = 0; i < N; i++) {
			if (check() == true) {
				count++;
			}
		}
		System.out.println(count);
	}
 
	public static boolean check() throws IOException {
		boolean[] check = new boolean[26];
		int prev = 0;
		String str = br.readLine();
		
		for(int i = 0; i < str.length(); i++) {
			int now = str.charAt(i);	// i 번째 문자 저장 (현재 문자)
			
			
			// 앞선 문자와 i 번째 문자가 같지 않다면?
			if (prev != now) {		
				
				// 해당 문자가 처음 나오는 경우 (false 인 경우)
				if ( check[now - 'a'] == false ) {
					check[now - 'a'] = true;		// true 로 바꿔준다
					prev = now;					// 다음 턴을 위해 prev 도 바꿔준다 
				}
	 
				// 해당 문자가 이미 나온 적이 있는 경우 (그룹단어가 아니게 됨) 
				else {
					return false;	//함수 종료
				}
			}
	        
	        
			// 앞선 문자와 i 번째 문자가 같다면? (연속된 문자)
			// else 문은 없어도 됨
			else {
				continue;
			}
		}    
		return true;
	}
}
```

