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
//%s 는 주소값을 받음 => printf 에서 "bbb"의 주소값이 값으로 오지만 `%S`로 인해 bbb가 출력됨
```







