여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 37강 함수

### 함수

- 반복적인 수행을 정의해 놓은 작은 프로그램 단

- 구조

```c
반환타입 함수명(인자들...,){
    수행할 작업1
    수행할 작업2
 return
}

void 타입의 경우 반환(return)이 없음
```

- 함수 선언과 사용

```c
int sum(int a, int b)
{
    int c = a + b;
    return c;
}

int data = sum(10,20);

printf("%d",data);
```

- 예제(2012 지방직 9급 컴퓨터 일반)

```c
#include<stdio.h>
void swap(int a, int b){
    int temp;
    temp = a;
    a = b;
    b = temp;
}
//main 함수에서 swap함수를 인식하기 위해 위에 함수선언함
int main(void){
    int k, j;
    k = 3;
    j = 2;
    swap(k, j);
    printf("k = %d, j = %d", k, j);
    return 0;
}

//내 정답
k = 2, j = 3 출력 
//오답임 => 함수가 실행이 되었으나 아무런 반환값이 없음, 전역변수도 없음
//따라서 k값과 j값은 변화가 없음, 함수는 아무런 영향을 주지 못했음

//정답
k = 3, j = 2 출력 
```

여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 38강 함수와 반복문

- 예제(2016 국가직 9급 컴퓨터 일반)

```c
#include<stdio.h>
int func(int n);
int main(void){
    int num;
    printf("%d\n", func(5));
    return 0;
}
int func(int n){
    if(n < 2)
        return n;
    else{
        int i, tmp, current = 1, last = 0;
        for(i = 2; i <=n; i++)
            tmp = curent;
        	curent += last;
        last = tmp;
    }
    return current;
}
//내정답
5 출력
```

여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 39강 함수에 주소전달(1)

- call by value
  - 함수를 호출 할때 값만 호출하겠다
- call by reference
  - 주소값, 객체, 오브젝트, 배열등을 전달하겠다

### 포인터, 배열등을 함수에 전달

- 예제(2010 국가직 9급 컴퓨터 일반)

```c
#include<stdio.h>
int main(void){
    int x = 10, y = 20;
    printf("%d", f(&x, y));
    printf("%d %d\n", x, y);
}

int f(int *i, int j){
    *i += 5;
    return (2 * *i+ ++j);
}

//내 정답
51 10 20 출력 => 오답

//*i += 5;는 주소(포인터)의 값을 변형하므로 x값이 10에서 15로 변경됨
//정답
51 15 20 출력
```

- 가장 중요한 개념
  - 포인터를 함수에 넘겼을 때는 전역변수가 아니더라도 변수의 값을 변경시킬 수 있음

여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 40강 함수와 변수의 유효범위

- 예제(2009 국가직 9급 컴퓨터 일반)

```c
#include<stdio.h>
int a = 1, b = 2, c = 3;
int f(void);
int main(void){
    printf("%3d\n", f());
    printf("%3d%3d%3d\n", a, b, c);
    return 0;
}

int f(void){
    int b, c;
    a=b=c=4;
    return(a+b+c);
}
```

- 해설

```c
#include<stdio.h>

int a = 1, b = 2, c = 3; //전역 변수

int f(void);// main() 함수에서 밑에 f()라는 함수가 있다고 알려주는 것

int main(void){
    
    printf("%3d\n", f());//3개의 공간을 확보하여 오른쪽 끝부터 출력해라
    
    printf("%3d%3d%3d\n", a, b, c);
    
    return 0;
}

int f(void){
    int b, c;
    
    a=b=c=4;// 4를 a, b, c에 대입해라 라는 뜻, 여기서 a는 전역변수 a를 4로 변경
    
    return(a+b+c);
}
/*
12
4 2 3 출력됨
*/
```

