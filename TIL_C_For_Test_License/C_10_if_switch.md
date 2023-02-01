여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 14강 if문, switch문(제어문)

### if문

- 조건에 따라서 다른 명령어들을 수행

- 만약에~~~로읽음
- 도식도

```
	+---- < 날씨 == 비 > -----+
	↓                        ↓
 자전거 타고               우산을 들고
	|            	         |
	+---→ |학교에 갑니다.| ←---+
```

### switch문

- 주어진 값에 맞는 명령어들을 수행
- break를 잘봐야함
  - break가 없다면 다음 명령어도 계속 수행됨
    - 하기 도식도 기준, break가 없다면, 설탕커피를 누른면 설탕커피, 믹스커피, 생수 다 나옴
- 도식도

```
< 자판기 버튼 > ---- |블랙| ---- |설탕커피| ---- |믹스커피| ---- |선택안함|
					 ↓            ↓             ↓              ↓
		    	  |커피2|		|커피2+설탕2|   |커2+프2+설2|		|생수|
				   	|				|			|				|
맛있게 먹자 ←---------+---------------+-----------+---------------+
```

### 예제

- 2018년 서울시 9급 컴퓨터 일반

```c
#include <stdio.h>
int main(){
    int a = 0, b =1;
    switch(a){
        case 0 : printf("%d\n", b++); break;
        case 1 : printf("%d\n", ++b); break;
        default : printf("%d\n", b); break;
    }
    return 0;
}
//해설
a = 0 이므로 다음 switch 문이 수행됨
case 0 : printf("%d\n", b++); break;
=> b++ 이므로 b를 먼저 출력한후에 ++ 수행
=> 1 출력후 b = 2

//번외 - 동일조건에서 모든 case에 break 없을 때 출력값
a = 0 이므로 다음 switch 문이 수행됨
case 0 : printf("%d\n", b++);
=> b++ 이므로 b를 먼저 출력한후에 ++ 수행
=> 1 출력후 b = 2
    
다음 case 수행
case 1 : printf("%d\n", ++b);
=> ++b 이므로 b를 증가 시킨후에 출력
=> b = 3, 3 출력
    
다음 case 수행
default : printf("%d\n", b);
=> b = 3, 3 출력
//최종출력값
2
3
3
(모든 printf에는 개행문자(\n)가 포함되어있으므로 줄 바꿔서 출력됨)
```