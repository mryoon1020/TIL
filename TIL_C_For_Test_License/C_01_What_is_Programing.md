여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=Lj0EszeZo2A&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK

# 1강 프로그래밍이란?

### 프로그램 동작과정

- 선언 => 입력 => 처리 => 출력

```c
	int age1, age2, result
    
    printf("철수와 영희의 나이를 입력하세요:");

	scanf("%d%d", &age1, &age2);
	
	result = age1 + age2;

	printf("나이의 합은 %d살 입니다. \n", result);
```

- ex) 아침에 비가오면 우산을 가져가고 비가 오지않으면 자전거를 타고 간다.
  - 프로그램화 한다면 다음과 같다

```c
int 자전거, 우산;
if(날씨 == "비")
{
    printf("우산");
}
else
{
    printf("자전거");
}
```

