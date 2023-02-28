여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=TYeCnrI6vtk

# 02강 ++가 연속으로 있을 때 처리

```c
#include<stdio.h>
int main()
{
    int i = 5;
    int z = i++ + i++ + i++;
    printf("%d, %d", i, z);
}
//내정답
18, 8 출력 => 정답
```

```c
#include<stdio.h>
int main()
{
    int i = 5;
    int z = ++i + ++i + ++i;
    printf("%d, %d", i, z);
}

//풀이
++이 앞에 있다면 연산이 시작되는 시점의 i값을 가져옴
    ++i + ++i + ++i
현재 i 값 = 5
    첫번째 ++ 실행 => i = 6
    
    두번째 ++ 실행 => i = 7
    
    '+' 연산실행 => 현재 i 값 = 7 => 7 + 7
    
    세번째 ++ 실행 => i = 8
    
    '+' 연산실행 => 현재 i 값 = 8 => 7 + 7 + 8 => 22

//정답: 22, 8 출력
```

```c
#include<stdio.h>
int main()
{
    int i = 5;
    int z = ++i + i++ + ++i;
    printf("%d, %d", i, z);
}
//내정답
21, 8 출력 => 정답
```

