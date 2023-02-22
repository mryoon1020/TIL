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
```