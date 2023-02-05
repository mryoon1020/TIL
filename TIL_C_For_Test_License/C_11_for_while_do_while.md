여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 15강 반복문, for문

### 반복문 종류

- for
  - 정해진 횟수만큼 반복
- while
  - 조건이 만족하는 동안 반복
  - while(조건){ }
- do ~ while
  - 무조건 한번 수행 후 조건이 만족하는 동안 반복
  - do { } while (조건) { } 구조

### for문

- 구조

```c
for(초기식; 조건식; 증감식)
{
    수행하는 작업들
}

//시행순서

for(0 초기식; Ⅰ 조건식; Ⅲ 증감식)
{
    Ⅱ 수행하는 작업들
}
```

- 예제(2008 국가직 9급 프로그래밍 언어론)

```c
#include <stdio.h>
int main(){
    int j;
    int sum = 0;
    for(j = 2; j <= 70; j += 5)
        sum = sum + 1;
    printf("%d", sum);
}
//내정답
14
//정답
sum = 14
j = 72
//j값 관련
j = 67일 때 sum = 14가 됨
해당 작업 수행후 다시 j 증감문이 수행됨
j = 72(j+=5 수행으로 증가됨)
조건문 수행
j <= 70 => 거짓 => for문 종료
```

여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 16강 while문

- while 구조

```c
while(조건)
{
    수행하는 작업들
}
```

- 예시

```c
int i = 0;
while(i<3)
{
    printf("i = : %d\n", i);
    i++;
}
printf("sum = : %d\n", i);

//출력결과
i = : 0
i = : 1
i = : 2
sum = : 3
```

- 예시(2009 지방직 9급 프로그래밍 언어론)

```c
#include <stdio.h>
int main(){
    int count = 2;
    int sum = 0;
    while(count <= 10){
        sum += count;
        count += 2;
    }
    print("%d", sum);
}

//내정답
sum = 30
//내풀이
최초실행 결과 count 4 sum = 2
2차 count = 6 sum = 6
3차 count = 8 sum = 12
4차 count = 10 sum = 20
5차 count = 12 sum = 30
6차 break
```

여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 17강 do~while, for, while문

- 구조
  - 구조상 일단 1회 수행후 반복시작

```c
do
{
    수행하는 작업들
}while(조건)
```

- 예시

```c
int i = 3;
do
{
    printf("i = %d\n", i);
    i++;
}while(i<3);
printf("sum = %d\n", i);

//출력값
i = 3
sum = 4

//while 문일때
    
int i = 3;
while(i<3)
{
    printf("i = %d\n", i);
    i++;
};
printf("sum = %d\n", i);

//출력값

sum = 4
=> printf("i = %d\n", i)이 수행되지 않음(i<3이 거짓이므로 while 수행안함)
```

- 예시(2010 국가직 9급 컴퓨터 일반)

```c
#include <stdio.h>
void main(){
    int a, b;
    a=2;
    while(a-- > 0){
        printf("a = %d\n", a);
    }
    for(b = 0; b < 2; b++){
        print("a = %d\n", a++);
    }
}

//내정답

a = 1
a = 2
a = 1
a = 2
    
//내풀이

1차 실행
while => a = 1 출력
for => b = 1, a = 2 출력
2차 실행
while => a = 1 출력
for => b = 2, a = 2 출력
3차 실행
    
//정답
    
a = 1
a = 0
a = -1
a = 0
    
//풀이
    
while문의 반복이 끝난후 for문이 수행됨

whlie
1차 실행
a = 1 출력
2차 실행
a = 0 출력
3차 실행
while 종료(단, a = -1 임)

for 시작
1차 실행
a = -1 출력, a = 0 , b = 1
2차 실행
a = 0 출력, a = 1 , b = 2
3차 실행
for 종료
```

여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 18강 while, if 문

- 예시(2016 계리직 컴퓨터 일반)

```c
#include <stdio.h>
int main(){
    int a = 120, b = 45;
    while(a != b){
        if(a > b) a = a - b;
        else b = b - a;
    }
    printf("%d", a);
}

//내정답
15 출력    
```

여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 19강 for 문, continue, break, if

### 반복문 옵션

- continue
  - 더이상 아래문장을 실행하지 않고, 반복문 처음으로 돌아감
  - 반복문에서만 사용가능
- break
  - 반복문을 빠져나감
- 예시(2018 계리직 컴퓨터 일반)

```c
#include <stdio.h>
int main(){
    int i, sum =0;
    for(i=1; i<=10; i+=2){
        if(i%2 && i%3) continue;
        sum += i;
    }
    printf("%d", sum);
    return 0;
}
//내 정답
sum = 3
//오답임 if가 시행안되도 for문은 계속 돌아감(조건 만족할때까지)
//&&연산은 값이 있거나 없거나로 참, 거짓이 판정됨, 둘다 값이 있을경우 if문이 수행됨
    
//정답
sum = 12
//해설
for 1차실행 결과
i = 3
sum = 0
    
for 2차실행 결과
i%2 && i%3 => 3%2 && 3%3 => 1 && 0 => if문 수행안됨
i = 5
sum = 3

for 3차실행 결과
i = 7
sum = 3

for 4차실행 결과
i = 9
sum = 3

for 5차실행 결과
i%2 && i%3 => 9%2 && 9%3 => 1 && 0 => if문 수행안됨
i = 11
sum = 12

for 6차실행 결과
for문 종료
```

여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 20강 다중 for 문, if문, continue, break

### 다중반복문

- 예시

```c
for(i=0; i<2; i++){
    for(j=0; j<2; j++){
        printf("i: %d, j: %d", i, j)
    }
}

//돌아가는 과정
i = 0
i < 2 => 참 => 내부 for문 실행
    
    j = 0
    j < 2 => 참 => printf("i: %d, j: %d", i, j) 실행
    => i: 0, j: 0 출력
    j + 1 => j = 1 변경
    
    j = 1
    j < 2 => 참 => printf("i: %d, j: %d", i, j) 실행
    => i: 0, j: 1 출력
    j + 1 => j = 2 변경
    
    j = 2
    j < 2 => 거짓 => 내부 for문 종료
        
i + 1  => i = 1

i = 1
i < 2 => 참 => 내부 for문 실행
    
    j = 0
    j < 2 => 참 => printf("i: %d, j: %d", i, j) 실행
    => i: 0, j: 0 출력
    j + 1 => j = 1 변경
    
    j = 1
    j < 2 => 참 => printf("i: %d, j: %d", i, j) 실행
    => i: 0, j: 1 출력
    j + 1 => j = 2 변경
    
    j = 2
    j < 2 => 거짓 => 내부 for문 종료
        
i + 1  => i = 2

i = 2
i < 2 => 거짓 => for문 종료

//출력결과
i: 0, j: 0
i: 0, j: 1
i: 1, j: 0
i: 1, j: 1
```

### continue

- 예시
  - continue의 경우 반복문의 처음으로 올라감
  - 즉, continue 실행시 continue이후의 코드는 실행되지 않음

```c
for(i=0; i<3; i++){
    if(i==1) continue;
    sum++;
}

//돌아가는 과정

i = 0
i < 3 => 참 => if문 실행
    if문 조건 => i==1 => 거짓 => if문 종료
    sum + 1 => sum = 1
i + 1 => i = 1
    
i = 1
i < 3 => 참 => if문 실행
    if문 조건 => i==1 => 참 => continue 실행
i + 1 => i = 2

i = 2
i < 3 => 참 => if문 실행
    if문 조건 => i==2 => 거짓 => if문 종료
    sum + 1 => sum = 2
i + 1 => i = 3

i = 3
i < 3 => 거짓 => for문 종료

//결과
i = 3
sum = 2
```

### break

- 예시
  - break문은 break 문이 있는 블록 자체를 완전히 빠져나오는 것
  - 즉, break가 있는 블록은 break이후로 아무코드도 실행되지 않음
  - 하기 for문의 경우 break 실행시 i++도 실행되지 않음

```c
for(i=0; i<3; i++){
    if(i==1) break;
    sum++;
}

//실행과정

i = 0
i < 3 => 참 => if문 실행
    if문 조건 => i==1 => 거짓 => if문 종료
    sum + 1 => sum = 1
i + 1 => i = 1
    
i = 1
i < 3 => 참 => if문 실행
    if문 조건 => i==1 => 참 => break 실행
    if문 종료
//결과
i = 1
sum = 1
```

- 예시(2009 지방직 9급 프로그래밍 언어론)

```c
#include <stdio.h>
void main(){
    int i, j, sum=0;
    for(i=1; i<10; i++){
        for(j=1; j<10; j++){
            if(j%3 == 0) continue;
            if(i%4 == 0) break;
            sum++;
        }
    }
    printf("%d", sum);
}

//내정답

i = 1
j = 1
sum = 1
2 2
3 2
4 3
5 4
6 4
7 5
8 6
9 6
10 6
    
i = 2
j = 1
sum = 7
2 8
3 8
4 9
5 10
6 10
7 11
8 12
9 12
10 12
    
i = 3
j = 1
sum = 13
2 14
3 14
4 15
5 16
6 16
7 17
8 18
9 18
10 18
    
i = 4
j = 1
break 실행

i => 1 증가할떄마다 sum 6증가(break 제외)
남은 break구간 i = 8
남은 i => 5 6 7 8 9
6 * 4(i = 8일 때 제외)=> 24
18 + 24 = 42
sum = 42
    
//정답
42 출력

//정확한 풀이
    
규칙을 빠르게 찾는 것이 중요
    
내부 for문 기준 한번 돌렸을 때 
1 2 3 4 5 6 7 8 9 에서 3, 6, 9 를 제외하고 sum 값은 1씩 증가함
=> 즉, 6씩 증가함

외부의 for문 기준
1 2 3 4 5 6 7 8 9 에서 4, 8 총 2회 break가 실행됨을 알수 있음
=> 즉, sum 값은 6씩 7번 증가
=> 0 + 6*7
=> sum = 42    
```

