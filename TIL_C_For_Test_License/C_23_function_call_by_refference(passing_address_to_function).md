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