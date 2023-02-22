여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 41강 함수에 주소전달(2)

- 예제(2009 국가직 9급 컴퓨터   일반)

```c
#include<stdio.h>
void main(){
    int a = 5;
    int b = 3;
    int c = 0;
    b= foo(a, &c);
    c= foo(b, &a);
    printf("a=%d b=%d c=%d", a, b, c);
}
int foo(int a, int *b){
    int c;
    *b = a+1;
    c = a-1;
    return c;
}
//내정답
a = 5 b = 4 c = 3 출력 //정답
```

여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 42강 함수에 주소전달(3)

- 예제(2012 국가직 9급 프로그래밍 언어론)

```c
#include<stdio.h>
void main(){
    int value = 3, list[4] = {1, 3, 5, 7};
    int i;
    swap(value, &list[0]);
    swap(list[2], &list[3]);
    swap(value, &list[value]);
    for(i=0; i<4; i++)
        printf("%d", list[i]);
}
void swap(int a, int *b){
    int temp;
    temp = a;
    a = *b;
    b* = temp;
}
//내 정답
3 3 5 3 출력 //정답
```