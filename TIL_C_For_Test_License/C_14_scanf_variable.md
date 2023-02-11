여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 24강 scanf 와 변수

- 사용자의 키보드로 부터 입력받는 것
- 전처리기
  - #include<stdio.h>를 상단에 넣어주면됨(라이브러리임)

### scanf

- 사용자의 키보드로 입력받을 때 사용하는 함수
- 형식
  - `scanf("%d", 변수의 주소)`
- 사용법

```c
int a;
scanf("%d", &a);
printf("%d", a);
```

- 예제(2015 서울시 9급 컴퓨터 일반)

```c
//scanf()의 입력으로 90을 타이핑 하는것으로 가정
int main(){
    int i = 10;
    int j = 20;
    int *k = &i;
    
    scanf("%d", k);
    printf("%d, %d, %d\n", i, j, *k);
    return 0;
}
```

- 임의의 메모리 할당

| 100  | 101  | 102  | 103  |
| :--: | :--: | :--: | :--: |
|  i   |  j   |      |      |
|  10  |  20  |      |      |

|      k      |
| :---------: |
| 100(주소값) |

- scanf()를 통해 k의 주소값에 90 입력

- **정답:** 90, 20, 90 출력