여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 9강 산술연산자

- 예제(2007 국가직 9급 프로그래밍 언어론)

```C
모든변수는 int 형,
y =  3 + 2 * (x = 7 / 2);
x, y ?
//내정답
x = 3 y = 9
//정답
x = 3 y = 9
```

- 예제2

```c
num = 6 + 14 % 5 / 2 - 2 * 2;
//내정답
num = 4
//정답
num = 4
```

- 예제3

```c
int a = 10, b = 20;
num = (a++ == 11) && (b++ == 20);

//내정답
num = 33
a = 12
b = 21
    
//정답
a = 11 b =20(앞괄호에서 0(거짓)이 나옴으로 뒷괄호를 연산하지 않았기 때문)
정답 : num = 0
    
//풀이
num = (a++ == 11) && (b++ == 20);
	연산자 우선순위로 인해 괄호 먼저 연산
        (a++ == 11)
    연산자 우선순위로 == 먼저 연산
        10 == 11 우선 연산 => 0(거짓)
//첫번째 연산의 결과가 거짓이므로 &&연산은 더 이상 연산하지않음
    
//뒷부분 괄호만 연산했을 때(실제로는 연산되지 않음)
(b++ == 20)
	b == 20 => 20 == 20 => 1(참)
    ++ 연산 => b = 21
```

- 예제3 - `ideone.com` 결과

```c
#include <stdio.h>

int main(void) {
	int a = 11, b = 20;
	int num = (a++ == 10) && (b++ == 20);
	printf("num : %d\n",num);
	printf("a : %d\n",a);
	printf("b : %d",b);
	return 0;
}
//결과값=====================================================
num : 0
a : 12
b : 20
```

- 예제4
  - && 연산 모두 참일때 결과값이 궁금해서 `ideone.com` 에서 예제3번을 변형해서 실행해보았음
  - && 연산자는 논리 연산자임을 잊지 말자 => 결과 값은 1(참), 0(거짓) 으로만 나옴
  - && 연산자 첫항에서 거짓이 나오면 뒷항들은 연산하지 않음

```C
#include <stdio.h>

int main(void) {
	int a = 11, b = 20;
	int num = (a++ == 11) && (b++ == 20);
	printf("num : %d\n",num);
	printf("a : %d\n",a);
	printf("b : %d",b);
	return 0;
}
//결과값=====================================================
num : 1
a : 12
b : 21
```

