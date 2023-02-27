여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 53강 재귀함수(1)

### 재귀함수

- 함수 내에서 자기의 함수를 다시 호출하는 함수
- 스택 내에 차곡차곡 쌓아두었다가 일괄로 처리를 한다

- 예제(2015 국가직 9급 컴퓨터일반)

```c
#include<stdio.h>
int func(int num){
    if(num == 1)
        return 1;
    else
        return num * func(num-1);
}

void main(){
    int i;
    for(i = 5; i >= 0; i--)
    {
        if(i % 2 == 1)
            printf("func(%d) : %d\n", i, func(i));
    }
}
```

- for 문

  - i = 5

    - if 문 실행
    - func(5)
      - num == 1 => 거짓 => else 문 실행
      - 하기 표는 스택을 표현한것임 => 맨 밑이 첫번째 실행결과
      - 재귀함수가 끝났을 때는 맨 위의 값부터 차례대로 계산이 수행됨

    | 실행순서 | num  | 재귀함수실행결과 | 실행순서 | 함수실행결과       |
    | -------- | ---- | ---------------- | -------- | ------------------ |
    | 5        | 1    | 1                | 1        | 1                  |
    | 4        | 2    | 2*func(1)        | 2        | 2*1                |
    | 3        | 3    | 3*func(2)        | 3        | 3*(2\*1)           |
    | 2        | 4    | 4*func(3)        | 4        | 4*(3\*(2\*1))      |
    | 1        | 5    | 5*func(4)        | 5        | 5*(4\*(3\*(2\*1))) |

    - 최종실행결과 => 120
    - `printf("func(%d) : %d\n", i, func(i));` = > func(5) : 120 출력

  - i = 4

    - if문 실행 하지 않음

  - i = 3

    - if문 실행
    - func(3)
    - num == 1 => 거짓 => else 문 실행

    | 실행순서 | num  | 재귀함수실행결과 | 실행순서 | 함수실행결과 |
    | -------- | ---- | ---------------- | -------- | ------------ |
    | 3        | 1    | 1                | 1        | 1            |
    | 2        | 2    | 2*func(1)        | 2        | 2*1          |
    | 1        | 3    | 3*func(2)        | 3        | 3*(2\*1)     |

    - 최종실행결과 => 6
    - `printf("func(%d) : %d\n", i, func(i));` = > func(3) : 6 출력

  - i = 2

    - if문 실행하지 않음

  - i = 1

    - func(1)
    - num == 1 =>  => 참 if문 실행
    - `printf("func(%d) : %d\n", i, func(i));` = > func(1) : 1 출력

- 최종 출력값

  120

  6

  1

여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 54강 재귀함수(2)

- 예제(2009 지방직 9급 프로그래밍 언어론)

```c
#include<stdio.h>
int f(int n){
    if(n>0)
        return n % 10 + f(n / 10);
    else
        return 0;
}

void main(){
    int result;
    result = f(123);
    printf("%d\n", result);
}
//내정답
6 출력 => 정답
```

- 계산한 값이 다음 번의 함수값에 들어감

| 실행순서 | n    | 재귀함수실행결과      | 실행순서 | 함수실행결과     |
| -------- | ---- | --------------------- | -------- | ---------------- |
| 4        | 0    | 0                     | 1        | 0                |
| 3        | 1    | 1%10 + func(1/10)     | 2        | 1 + 0            |
| 2        | 2    | 12%10 + func(12/10)   | 3        | 2 + (1+0)        |
| 1        | 123  | 123%10 + func(123/10) | 4        | 3 + (2 + (1+0))) |

여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 55강 재귀함수(3)

- 예제(2008 국가직 9급 컴퓨터 일반)

```c
#include<stdio.h>
int func(int n){
    if(n%2 == 1)
        n = n-1;
    if(n==0);
    	return 0;
    return(func(n-2)+n);
}

void main(){
    int result;
    result = func(19);
    printf("result=%d\n",result);
}
//내정답
result=90 출력 => 정답
/*    
func(19) 일때 다음 문장 실행됨
    if(n%2 == 1)
        n = n-1;
*/
```

여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 56강 재귀함수(4)

- 예제(2018 서울시 9급 컴퓨터 일반)

```c
#include<stdio.h>
int my(int i, int j){
    if(i<3) i=j=1;
    else{
        i = i-1;
        j = j-i;
        printf("%d, %d,", i, j);
        return my(i,j);
    }
}

void main(){
    my(5, 14);
    return 0;
}

//내정답
3, 7, 4, 10 출력 //오답
//if(i<3) => if(3<3) => 거짓이므로 else문 수행함
4, 10, 3, 7, 2, 5 출력함
//출력순서는 최초 실행값부터 출력
```

여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 57강 재귀함수(5)

- 예제 (2018 국회전산직 컴퓨터일반)

```c
#include<stdio.h>
int recursion(int n){
    if(n<5) return 1;
    else if(n % 5 == 1)
        return n + recursion(n-1);
    else recursion(n-1);
}

void main(){
    int n = recursion(16);
    printf("%d", n);
}
//내정답
34 출력 => 정답
    
/*
recursion(5) => 1
----------------------------
recursion(6) : 6 + recursion(5) => 7
recursion(11) : 11 + recursion(10) => 18
recursion(16) : 16 + recursion(15) => 34
상기 3개의 식처럼 변동값이 없다면 (그냥 다음 함수만 호출됨)
식이 특별히 없으면 스택에 안쌓아도됨
recursion(6) ~ recursion(10) => 7
recursion(11) ~ recursion(15) => 18
*/
```

여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 58강 중복 재귀함수(1)

- 피라미드 형태로 그려서 푸는것이 좋음
- 중복된 재귀함수들을 각각 계산하여 합산하면 됨

- 예제(2015 지방직 컴퓨터 일반)

```c
#include<stdio.h>
int recur(int a, int b){
    if(a<=1)
        return a*b;
    else
        return a*recur(a-1, b+1) + recur(a-1, b);
}

void main(){
    int a = 3, b = 2;
    printf("%d\n", recur(a, b));
}
```

```c
//풀이
recur(3, 2)
    else문 실행
    						3*recur(2, 3) + recur(2, 2)
    
2*recur(1, 4) + recur(1, 3)									2*recur(1, 3) + recur(1, 2)
    recur(1, 4) => 1 * 4 = 4									recur(1, 3) => 1* 3 = 3
    recur(1, 3) => 1* 3 = 3										recur(1, 2) => 1* 2 = 2
    2*4+3														2*3+2
    
    			    	3*recur(2, 3) + recur(2, 2) => 3*11+8 => 41
//정답
41 출력
```

여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 59강 중복 재귀함수(2)

- 예제(2014 계리직 컴퓨터일반)

```c
#include<stdio.h>
int sub (int n){
    if(n==0)return 0;
    if(n==1)return 1;
    return(sub(n-1)+sub(n-2));
}
void main(){
    int a = 0;
    a = sub(4);
    printf("%d", a)
}
//내정답
3 출력 => 정답
```

여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 60강 중복 재귀함수(3)

- 예제(2017 국회 전산직 컴퓨터 일반)

```c
//main함수를 실행할 때 fib()함수는 몇번 호출되는지 구하시오
#include<stdio.h>
int fib(int n){
    if(n==0)return 0;
    if(n==1)return 1;
    return(fib(n-1)+fib(n-2));
}
void main(){
    fib(5);
}
//15번 호출됨
```

```c
//풀이
											fib(5)
    									fib(4)+fib(3)
    			fib(3)+fib(2)									fib(2)+fib(1)
    fib(2)+fib(1)			fib(1)+fib(0)			fib(1)+fib(0)
fib(1)+fib(0)
                                                
//총 15번, main()에서 호출되는 것도 같이 세야된다는것을 잊지 말것
```

