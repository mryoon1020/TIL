# BufferedReader & BufferedWriter

### BufferedReader

- java.io의 클래스

- 메인함수들
  
  - close() : 입력 스트림을 닫고, 사용하던 자원 해제
  - readLine() : 한줄을 읽음(String 반환)

- Scanner와 유사한 기능

- Scanner보다 훨씬 속도가 빠름
  
  - Scanner와 달리 버퍼에 데이터를 담아 한번에 전송함

- 사용법
  
  - 한줄 단위로 데이터를 읽음(줄바꿈으로 구분)
  - BufferedReader를 통해 읽어진 결과값은 String으로 반환됨
  - 필요에 따라 가공처리가 필요함
  
  ```java
  import java.io.BufferedReader;
  import java.io.InputStreamReader;
  import java.io.Reader;
  
  public class Main {
  
    public static void main(String[] args) throws Exception {
  
      Reader r = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(r);
  //    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Reader 변수선언 없이 바로 넣어도 됨
      String str = br.readLine();
  
      System.out.println(str);
  
    }
  }
  ```

- StringTokenizer와 함께 쓰기
  
  - 입력된 값을 분리하여 출력하고 싶을때 사용
  - 입력된 값을 분리하여 출력
  
  ```java
  import java.io.BufferedReader;
  import java.io.InputStreamReader;
  import java.io.Reader;
  import java.util.StringTokenizer;
  
  public class Main {
  
    public static void main(String[] args) throws Exception {
  
      Reader r = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(r);
      String str = br.readLine();
  
      StringTokenizer st = new StringTokenizer(str,"/");
      int ct = st.countTokens();
  
      for(int i=0;i<ct;i++) {
        System.out.println(st.nextToken());
      }    
    }
  }
  ```
  
  - 입력된 값을 연산하여 출력
  
  ```java
  import java.io.BufferedReader;
  import java.io.InputStreamReader;
  import java.io.Reader;
  import java.util.StringTokenizer;
  
  public class Main {
  
    public static void main(String[] args) throws Exception {
  
      Reader r = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(r);
      String str = br.readLine();
  
      StringTokenizer st = new StringTokenizer(str,"/");
  
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
  
      int s = a+b+c;
  
      System.out.println("a : "+a+"\n"+"b : " + b+ "\n" + "c : " +c);
      System.out.println("a+b+c : "+s);
  
    }
  }
  ```

### BufferedWriter

- System.out.println() 과 유사

- 많은양의 데이터 처리시 System.out.println()보다 빠른성능을 보임

- 버퍼가 가득 찼을 때만 출력을 하기 때문에 마지막 자투리 데이터 부분이 버퍼에 남아잇을 수 있음
  
  - 사용후에는 항상  flush()를 호출하여 데이터를 모두 내보내줘야함

- 메인함수들
  
  - close() : 스트림을 닫고, 사용하던 자원 해제
  - flush() : 스트림을 비움
  - write() : 출력

- 사용법
  
  - 반드시 flush()를 통해 비워줄 것(버퍼가 가득차지 않으면 데이터가 출력되지 않음)
  
  ```java
  import java.io.BufferedReader;
  import java.io.BufferedWriter;
  import java.io.InputStreamReader;
  import java.io.OutputStreamWriter;
  import java.io.Reader;
  import java.io.Writer;
  import java.util.StringTokenizer;
  
  public class Main {
   
    public static void main(String[] args) throws Exception {
  
      Reader r = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(r);
      String str = br.readLine();
      
      StringTokenizer st = new StringTokenizer(str,"/");
      
      Writer w = new OutputStreamWriter(System.out);
      BufferedWriter bw = new BufferedWriter(w);
      
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      
      int s = a+b+c;
      
      bw.write("a : "+a+"\n");
      bw.write("b : "+b+"\n");
      bw.write("c : "+c+"\n");
      bw.write("a + b + c : " + s);    
      
      bw.flush();
    }
  }
  ```