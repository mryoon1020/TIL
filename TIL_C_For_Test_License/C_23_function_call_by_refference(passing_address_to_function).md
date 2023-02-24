여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 44강 함수에 주소전달(4)

```c
#include<stdio.h>
void main(void){
    int a=10, b=20, c=30, d=40;
    change(&a, &b, c, d);
    printf("a=%d, b=%d, c=%d, d=&d", a, b, c, d);
}

void change(int *px, int *py, int pc, int pd){
    *px = *py + pd;
    *py = pc + pd;
    pc = *px + pd;
    pd = *px + *py;
}
//내정답
a=60, b=70, c=30, d=40
```

여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 45강 함수에 주소전달(5)

- 예제(2019 계리직 컴퓨터일반)

```c
#include<stdio.h>
int main(){
    double f[] ={1, 2, 3, 4};
    printf("%3.1f\n", h(f, 4, 2));
    return 0;
}

double h(double *f, int d, double x){
    int i;
    double res = 0.0;
    for(i = d-1; i>=0; i--){
        res = res * x + f[i];
    }
    return res;
}
//내정답
49 출력 => 오답 => %3.1f로 인해서 49.0이 출력이 되야함
```

- `%3.1f` 
  - 3칸을 만들고 소수점 첫째자리까지 표현
  - `printf("%3.1f", 49.1);` 
    - 이경우 `.` 자리까지 4칸이 필요함
    - 조건에서는 3칸이므로 칸수가 모자람 => 이런경우 `.1f`만 처리하게됨 => `49.1` 출력됨
    - 즉, 정수부분은 칸이 남건 모자라건 무조건 다 출력됨
    - `printf("%3.1f", 149.53);` 
      - 이경우 역시 칸이 모자라지만 정수부분은 다 출력됨 => `149.5`로 출력

여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 46강 함수에 주소전달(6)

- 2014 국가직 9급 컴퓨터일반

```c
#include<stdio.h>
int main(){
    int a, b, c[1];
    a = 20;
    b = 20;
    c[0] = 20;
    func(&a, b, c);
    printf("a=%d b=%d c=%d", a, b, c[0]);
    return 0;
}

void func(int *a, int b, int *c){
    int x;
    x = *a;
    *a = x++;
    x = b;
    b=++x;
    --(*c);
}
//내정답
a=20 b=20 c=19 출력 => 정답
```

여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 47강 함수에 주소전달(7)

- 2018 지방직 9급 컴퓨터 일반

```c
#include<stdio.h>
#define SIZE 3
void func(int *m, int *a, int b);
int main(void){
    int num[SIZE]={1, 3, 6};
    int a = 10, b = 30;
    func(num, &a, b);
    printf("a=%d. b=%d\n", a, b);
    return 0;
}

void func(int *m, int*x, int y){
    int i = 0, n = 0;
    y=*x;
    n=*(m+1)+(*m+2);
    *x = ++n;
}
//내정답
a=7 b=30 => 정답
```

여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 48강 함수에 주소전달(8)

- 예제(2019 계리직 컴퓨터 일반)
- 3 2 1 4를 입력

```c
//---------------메인함수부분----------------
int n;
int *num;
printf("How many numbers?");
scanf("%d", &n);
num = (int*)malloc(sizeof(int)*n);
a(n, num);
b(n, num);
for(int i=0; i<n; i++)
    printf("%d", num[i]);
//-----------------------------------------
void a(int n, int *num){
    for(int i =0; i<n; i++)
        scanf("%d", &(num[i]));
}

void b(int *lt){
    int a, b;
    for(a=0; a<n-1; a++)
        for(b=a+1; b < n; b++)
            if(lt[a]>lt[b]) c(lt+a, lt+b);
}

void c(int *a, int *b)
    int t;
	t = *a; *a = *b; *b = t;

//정답
1 2 4 출력
```

- 풀이
  - 정렬 알고리즘이지만 한번 비틀어 놓은 문제


```c
//---------------메인함수부분----------------
int n;
int *num;
printf("How many numbers?");

scanf("%d", &n); 
//문제에서 3214를 입력 받는다고 하였으므로 n이라는 주소값에 3이라는 입력값을 받겠다는 뜻

num = (int*)malloc(sizeof(int)*n);
//malloc(sizeof(int)*n) => malloc 포인터로 3만큼 공간을 할당 받음

a(n, num);
//num[0] = 2, num[1] = 1, num[2] = 4

b(n, num);
//b(n, num) => b(3, num) => num[0] = 1, num[1] = 2, num[2] = 4

for(int i=0; i<n; i++)
    printf("%d", num[i]);
//1 2 4가 출력됨

//-----------------------------------------
void a(int n, int *num){
    for(int i =0; i<n; i++)
        scanf("%d", &(num[i]));
}

void b(int *lt){
    int a, b;
    for(a=0; a<n-1; a++)
        for(b=a+1; b < n; b++)
            if(lt[a]>lt[b]) c(lt+a, lt+b);
}

void c(int *a, int *b)
    int t;
	t = *a; *a = *b; *b = t;

//정답
1 2 4 출력
```
