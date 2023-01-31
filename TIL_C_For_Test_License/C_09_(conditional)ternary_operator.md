여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 13강 삼항연산자(조건연산자)

### 삼항연산자

- 조건에 따라서 다른명령을 수행하는 연산자
- 구조 : 조건 ? 참 : 거짓

```C
int a = 10 > 5 ? 10 : 5;
printf("%d", a);
//10 이 출력됨
```

- 예제(2009 국가직 9급 프로그래밍 언어론)

```c
void main (void){
    int a, b;
    a = 20;
    b = (a > 10) ? a + a : a * a;
    printf("b = %d\n", b);
}

//내정답
b = 40
```



