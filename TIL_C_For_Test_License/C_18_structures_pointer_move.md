여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 35강 구조체, 포인터 이동

### 구조체

- 여러변수들을 모아서, 하나의 객체를 구성할 때 사용하는 사용자 저의 타입 객체

- 구성

```c
struct 구조체명
{
    멤버변수 1;
    멤버변구 2;
}
```

- 예시

```c
struct person
{
    char *name;
    int age;
};
struct person user1;
user1.name = "h user";
printf("%s", user1.name);

//---------------------------------------------------

struct person
{
    char *name;
    int age;
}test;
test.name = "h user";
test.age = 40;
print("%s : %d", test.name, test.age);

//----------------------------------------------------

struct person
{
    char *name;
    int age;
};
struct person *p; 
p -> name = "test_2";
p -> age = 40;
printf("%s : %d", test -> name, test -> age);
```

- 예제(2013 국가직 9급 프로그래밍 언어론)

```c
#include<stdio.h>
int main(){
    struct list {
        int *fp;
    } data, *p;
    int x[] = { 100, 200, 300, 400 };
    p = &data;
    p -> fp = x + 1;
    printf("%d", *(++p -> fp));
    return 0;
}
```

| Data | *p   |
| ---- | ---- |
| fp   | fp   |
| 100  | 110  |

| x               | x[0] | x[1] | x[2] | x[3] |
| --------------- | ---- | ---- | ---- | ---- |
| **배열의 원소** | 100  | 200  | 300  | 400  |
| 200             | 200  | 201  | 202  | 203  |

- 구조체가 2개 생성되었지만 `p = &data;` 로 인해 다음과 같이 바뀜

| Data | *p      |
| ---- | ------- |
| fp   |         |
| 100  | **100** |

- `p -> fp = x + 1;`
  - p가 가리키는 공간 fp에 x+1 을 넣겠다는 뜻
  - x가 가리키는 주소값 = 100 => 100+1 => 101
- `printf("%d", *(++p -> fp));`
  - `p -> fp` 가 하나의 변수 이므로 fp를 ++해주어야함
  - `*(++p -> fp)` => `++p -> fp` 의 값 => `p -> fp` = 200 => ++ => 202 => 202의 값 => 300
  - 300 출력
- 정답 : 300 출력