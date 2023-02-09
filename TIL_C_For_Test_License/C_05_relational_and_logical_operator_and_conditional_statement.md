여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 7강 관계/논리연산자, 조건문

- 관계연산자 종류

| 연산자 |   예    | 결과 값  |
| :----: | :-----: | :------: |
|   ==   | 10 == 9 | 0 (거짓) |
|   !=   | 3 != 3  | 0 (거짓) |
|   >    |  5 > 5  | 0 (거짓) |
|   >=   | 5 >= 5  |  1 (참)  |
|   <    |  7 < 7  | 0 (거짓) |
|   <=   | 7 <= 7  |  1 (참)  |

- 논리연산자 종류
  - ! : 값이 있는것을 부정하면 거짓 => 0, 값이 있으면 참(1)
  - && : 양항이 둘다 참이어야 1임, 둘중하나라도 거짓이 있다면 0임
  - || : 둘중에 하나만 참이면 참(1)임

| 연산자 | 예                | 결과값   |
| ------ | ----------------- | -------- |
| !      | !9                | 0 (거짓) |
| &&     | 3 >= && 3 > 3     | 0 (거짓) |
| \|\|   | 5 > 5 \|\| 5 >= 5 | 1 (참)   |

- 조건문

  - 단순 if 문

  ```c
  if(조건) {
      조건이 만족할때의 처리;
  }
  ```

  - if ~ else 문

  ```c
  if(조건) {
      조건이 만족할때의 처리;
  }
  else {
      조건이 아닐때의 처리;
  }
  ```

  - 다중 if문

  ```c
  if(조건1) {
      조건1이 만족할때의 처리;
  }
  else if(조건2){
      조건2가 만족할때의 처리;
  }
  else{
      모든 조건이 아닐때의 처리;
  }
  ```

- 예제 (2017 국회사무처 9급 컴퓨터 일반)

```c
#include<stdio.h>
int main()
{
    int i = 3;
    int j =4;
    if((++i > j--) && (i++ < --j)) i = i-- + ++j;
    else j = i-- - --j;
    printf("%d\n", i);
}

//풀이

    if((++i > j--) && (i++ < --j)) i = i-- + ++j;	

	// i 앞에 붙은 연산자 ++ 우선수행 => i = 4
	// i > j 수행 한후 j뒤에 -- 수행
	// 조건문은 && 연산자로 이어져있으므로 i > j = 0 (거짓)
	// j뒤에 -- 수행 => j = 3

	// i = 4, j = 3 

	// 조건문 => 0 (거짓) => else 블록 수행

    else j = i-- - --j;

	// --j 연산 수행 => j = 2

	// i = 4, j = 2

	// i - --j 수행후 i뒤에 -- 수행 => i = 3

	// i = 3, j = 2

    printf("%d\n", i);

내정답 : 3
정답 : 3
```

# 8강 논리연산자, 증감연산

- 예제(2011 지방직 9급 프로그래밍 언어론)

```c
#include<stdio.h>
int main(){
    int a = 3+5, b=1, c;
    int ap, bp;
    ap = a++;
    bp = ++b;
    b = 3 * (ap == 8);
    c = 5 * (ap != 8);
    printf("%d %d %d %d %d", a, b, c, ap, bp);
}
//내정답
ap = 8 a = 9 bp =2 b =3 c = 0 => 9 3 0 8 2 출력
//정답 9 3 0 8 2
```

- ideone.com : C언어 , 자바 등 언어 컴파일 가능