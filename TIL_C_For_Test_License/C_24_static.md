여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 49강 static 변수

- 정적 변수, 공유하고 있는 변수

### static 변수

- 단 한번만 초기화하고, 그 이후에는 전역변수처럼 프로그램이 종료될 때까지 메모리 공간에 존재하는 변수
- 초기값이 지정이 안되면, 자동으로 0이 대입

### 지역변수 사용

```c 
void test(){
    int a = 10;
    a++;
    printf("%d", a);
}
void main(){
    test(); //11출력
    test(); //11출력
}
```

### static 변수 사용

```c
void test(){
    static int a = 10;
    a++;
    printf("%d", a);
}
void main(){
    test(); //11출력
    test(); //12출력
}
```

- 예제(2017 국가직 9급 컴퓨터일반)

```c
#include<stdio.h>
void funCount();
int main(void){
    int num;
    for(num=0; num<2; num++)
        funCount();
    return 0;
}

void funCount(){
    int num = 0;
    static int count;
    printf("num=%d, count=%d\n", ++num, count++);
}
//내정답
num=1, count=0
num=2, count=1 출력 => 정답
```

여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 50강 static 변수(2)

- 예제(2013 국가직 9급 컴퓨터일반)

```c
#include<stdio.h>
void main(){
    int i=0, sum=0;
    while(i<3){
        sum = sum + fo();
        i++;
    }
    printf("sum=%d\n", sum);
}
int fo(void){
    int var1 = 1;
    static int var2 = 1;
    return (var1++) + (var2++);
}
//내정답
sum=9 출력 => 정답
```

여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 51강 static 변수(3)

- 예제(2013 국가직 9급 컴퓨터 일반)

```c
#include<stdio.h>
void main(){
    int s1, s2;
    s1 = funcA(2);
    printf("F1 = %d,", s1);
    s1 = funcA(3);
    printf("F2 = %d,", s1);
    s2 = funcB(2);
    printf("F3 = %d,", s2);
    s2 = funcB(3);
    printf("F4 = %d", s2);
}

int funcA(int n){
    static int s =1;
    s *= n;
    return s;
}

int funcB(int n){
    int s = 1;
    s *= n;
    return s;
}
//내정답
F1 = 2, F2 = 6, F3 = 2, F4 = 3 출력 //정답
```

여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 52강 static 변수(4)

- 예제(2018 국가직 8급 컴퓨터 일반)

```c
#include<stdio.h>
int a = 10;
int b = 20;
int c = 30;
void main(){
    func();
    func();
    printf("a=%d, b=%d, c=%d\n", a, b, c);
}

void func(void){
    static int a = 100;
    int b = 200;
    a++;
    b++;
    c=a;
}
//내정답
a=10, b=20, c=102 출력
    
//전역변수는 함수안에 같은이름의 변수가 없을 때 사용됨
//이문제에서는 func()안에 a, b는 함수안에 선언되어있으므로 전역변수 a, b를 사용하지 않음
//c는 선언되어 있지않으므로 전역변수를 이용함
```

