여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=jxGsqCpLakA

# 01강 prinf 출력순서

```c
#include<stdio.h>
int main()
{
    int i =5;
    printf("%d, %d, %d, %d", i++, ++i, i++, ++i);
}
//내정답
5, 7, 7, 9 => 오답
    
//printf는 뒤에서부터 연산을 시작함
    ++, --가 앞에 있을 때는 최종값을 출력함 => / % + -와 같은 연산을 만나지 않았기 때문

    i++, ++i, i++, ++i 에서 뒤에서 부터 계산
    1	 2	  3 	4	

    4 => 3 => 2 => 1 순서로 계산
    
    4번 결과 => i = 6 하지만 5를 출력하지 않음 => 현재 상태 ? ? ? ?
    3번 결과 => 6을 출력 후 ++연산 => i = 7	=> 현재 상태 ? ? ? ?
    2번 결과 => i = 8 하지만 8을 출력하지 않음 => 현재 상태 ? ? 6 ?
    1번 결과 => 8을 출력 후 ++연산 => i = 9	=> 현재 상태 8 ? 6 ?    
    
    printf에 의해 출력하지 않았던 2번 4번에 최종 i값을 입력 => i = 9 => 8, 9, 6, 9
    
//정답 : 8, 9, 6, 9 출력
```

```c
#include<stdio.h>
int main()
{
    int i =5;
    printf("%d, %d, %d, %d", --i, i--, i--, i, --i);
}
//내정답
1, 3, 4, 1, 1 => 정답
```