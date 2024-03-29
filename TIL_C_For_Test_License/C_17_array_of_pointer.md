여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 31강 포인터 배열(1)

### 포인터 배열

- 배열의 요소가 포인터(메모리주소)로 이루어진 것.

- 예시

  - 일반적 배열

    - `char *str = "Hello";`
    - 100은 임의의 주소값임

    | str  | 100  | H    | e    | l    | l    | o    |
    | ---- | ---- | ---- | ---- | ---- | ---- | ---- |

    - `printf("%s\n", str);` => Hello

  - 포인터 배열

    - `char *student[3] = {"aaa", "bbb", "ccc"};`
      - 해석하자면 크기가 3인 포인터 배열 student의 원소들(주소)의 값은 "aaa", "bbb", "ccc" 이다
      - student는 포인터 배열이므로 주소값을 원소로 갖는다
    
    
    | student | 100  | 20   | 30   | 40   |
    | ------- | ---- | ---- | ---- | ---- |
    - 각 숫자들은 주소값임
    - student는 임의의 주소값 100을 가짐
    - 포인터 배열에서는 값대신 주소값을 가지므로 "aaa", "bbb", "ccc"에 대한 주소값을 원소로 가짐
    

    | 20   | a    | a    | a    |
    | ---- | ---- | ---- | ---- |
    | 30   | b    | b    | b    |
    | 40   | c    | c    | c    |
    
    - 상기 표는 "aaa", "bbb", "ccc"에 할당된 임의의 메모리 주소 값을 표기한 것임
  
- 예제(2008 국가직 9급 프로그래밍 언어론)

```c
#include<stdio.h>
int main(){
    static char *c[] = {"aaa", "bbb", "ccc"};
    printf("%s", *(c+1));
}
//내정답
bbb 출력 => 정답
//%s 는 문자열과 주소값(포인터)를 받음 => printf 에서 "bbb"의 주소값이 값이 변수에 오지만 `%S`로 인해 bbb가 출력됨
```

여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 32강 포인터 배열(2)

- 예제(2016 서울시 9급 컴퓨터일반)

```c
#include<stdio.h>
int main(){
    char *arr1[2] = {"Good morning", "C language"};
    printf("%s\n", array1[0]+5);
    printf("%c\n", *(array1[1]+6));
}

// 내정답
m, u 출력 => 오답 //%s는 null이 나올때까지 전부 출력함 => morning, u 출력함
```

- 임의로 메모리 할당한다면 다음과 같음
  - 기록된 숫자는 임의의 주소값임

| array1 | array1[0] | array1[1] |
| :----: | :-------: | :-------: |
|  100   |    20     |    50     |

| array1[0]     | 20     | G     | o    | o     | d     |       | m     | o     | r     | n     | i     | n    | g    |
| ------------- | ------ | ----- | ---- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ---- | ---- |
| **array1[1]** | **50** | **C** |      | **l** | **a** | **n** | **g** | **u** | **a** | **g** | **e** |      |      |

- `printf("%s\n", array1[0]+5);`
  - array1[0]+5 => m
  - %s => null 이 나올때까지 문자열 출력 => morning 출력
- `printf("%c\n", *(array1[1]+6));`
  - array1[1]+6 => u
  - %c => 글자 한개(char)만 출력 => u 출력
- 정답: morining u 출력

여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 33강 포인터 배열(3)

- 예제(2007 국가직 9급 프로그래밍 언어론)

```c
void main(){
    int a[2][3] = {{-3, 14, 5}, {1, -10, 8}};
    int *b[] = {a[0], a[1]};
    int *p = b[1];
    printf("%d", *b[1]);
    printf("%d", *(++p));
    printf("%d", *(--p-2));
}

//내정답
1 -10 -3 => 오답 1 -10 14 출력 //--p-2 문제 똑바로 읽을 것
```

- 임의 메모리할당
  - 하기기록된 세자리 숫자는 모두 임의의 주소값임

|  a   |   a[0]   | 100  |  -3   |   14    |   5   |
| :--: | :------: | :--: | :---: | :-----: | :---: |
| 100  | **a[1]** | 103  | **1** | **-10** | **8** |

|  b   |   b[0]   |   a[0] => 100   |
| :--: | :------: | :-------------: |
| 200  | **b[1]** | **a[1] => 103** |

|  p   |
| :--: |
| 103  |

- `printf("%d", *b[1]);`
  - b[1] => a[1] => 103 => 1 출력
- `printf("%d", *(++p));`
  - p => 103 => ++ => 104 => -10출력
- `printf("%d", *(--p-2));`
  - p => 상위 printf에서의 연산으로 인해 현재 104임 => -- => 103 =>103 -2 => 101 => 14출력

- 정답: 1 -10 14 출력 

여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 34강 포인터 배열(4)

- 예제(2021 정보처리기사 3회 실기)

```c
#include<stdio.h>
int main(){
    int *arr[3];
    int a = 12, b = 24, c =36;
    arr[0] = &a;
    arr[1] = &b;
    arr[2] = &c;
    
    print("%d\n", *arr[1]+**arr+1);
}

//내정답
48 => 오답 => 정답: 37 // ()가 없다면 **이 우선임
```

- 임의의 메모리할당

|  배열  | arr  | arr[0] | arr[1] | arr[2] |
| :----: | :--: | :----: | :----: | :----: |
| 원소값 |      |        |        |        |
| 주소값 | 100  |  100   |  101   |  102   |

|  변수  |  a   |  b   |  c   |
| :----: | :--: | :--: | :--: |
|   값   |  12  |  24  |  36  |
| 주소값 | 200  | 300  | 400  |

- `arr[0] = &a;` , `arr[1] = &b;` , `arr[2] = &c;` 실행

|  배열  | arr  | arr[0] | arr[1] | arr[2] |
| :----: | :--: | :----: | :----: | :----: |
| 원소값 |      |  200   |  300   |  400   |
| 주소값 | 100  |  100   |  101   |  102   |

- `print("%d\n", *arr[1]+**arr+1);`
  - *arr[1] => arr[1] = 300 => 300의 값(\*) => 24
  - **arr+1 => arr의 주소  = 100 => 100의 값(\*) => 200 => 200의 값(\*) => 12 => 12+1 => 13
  - 24+13 = 37
- 정답: 37
