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

