여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 21강 배열(1)

### 배열

- 정의 : 같은 자료형의 변수를 연속적으로 묶어 놓은 저장공간
- 선언 :

```c
자료형 배열명 [갯수]; => int a [5];
```

- 상기 배열은 다음과 같이 형성됨

| a[0] | a[1] | a[2] | a[3] | a[4] |
| ---- | ---- | ---- | ---- | ---- |
|      |      |      |      |      |

- 배열 예시

```c
int a [5];
a[0] = 10;
a[2] = 20;
a[6] = 60; => 오류 발생함 => a[6]은 크기가 5인 배열에서는 존재하지 않음
```

| a[0] | a[1] | a[2] | a[3] | a[4] |
| ---- | ---- | ---- | ---- | ---- |
| 10   |      | 20   |      |      |

### 이차원배열

- 같은 자료형의 변수를 행과 열의 연속적인 공간으로 묶어 놓은 것

- 선언:

```c
자료형 배열명 [행][열]; => int a [2][3];
```

- 상기배열은 다음과 같이 형성됨
  - 실제 저장은 1차원 배열 구조로 저장됨

| a\[0][0] | a\[0][1] | a\[0][2] |
| -------- | -------- | -------- |
| a\[1][0] | a\[1][1] | a\[1][2] |

- 예시(2019 국가직 9급 컴퓨터일반)

```c
void main(){
    char msg[50] = "Hello World! Good Luck!";
    int i = 2, number = 0;
    while(msg[i])!='!'){
        if( msg[i] == 'a'|| msg[i]=='e' || msg[i] == 'i'|| msg[i]=='u' ||
           msg[i] == 'o'|| msg[i]=='u' )
            number++;
        i++;
    }
    printf("%d", number);
    return 0;
}
```

| 0    | 1    | 2    | 3    | 4    | 5    | 6    | 7    | 8    | 9    | 10   | 11   | 12   | 13   | 14   | 15   | 16   | 17   | 18   | 19   | 20   | 21   | 22   | 50   |
| ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| H    | e    | l    | l    | o    |      | W    | o    | r    | l    | d    | !    |      | G    | o    | o    | d    |      | L    | u    | c    | k    | !    |      |

```c
while(msg[i])!='!') => ! != ! => false => while 종료 => 즉, !가 나올때 까지 while 수행
    
    => o가 2번 있으므로 number는 2번만 ++수행함, i = 11 일때 while문 정지
    => i = 11 number = 2
    => 2 출력
```

여기에 기록되는 모든 내용은 유튜브 흥달쌤 채널의 강의에서 개인 공부 목적으로 기록함

링크 : https://www.youtube.com/watch?v=-w6O5Bq8OMY&list=PLdaE6YENrbZA8sXCvVBUWjFwFI2zb4tlK&index=2

# 22강 배열(2)

- 예시(2015 서울시 9급 컴퓨터일반)

```c
void main(){
    int i;
    char ch;
    char str[7] = "nation";
    for(i=0; i<4; i++){
        ch = str[5-i];
        str[5-i] = str[i];
        str[i] = ch;
    }
    printf(:%s\n, str);
    return 0;
} 
```

- 풀이 - **Swap 알고리즘**
  - `char str[7] = "nation";` 은 다음 표와 같이 배정됨

|  0   |  1   |  2   |  3   |  4   |  5   |  6   |
| :--: | :--: | :--: | :--: | :--: | :--: | :--: |
|  n   |  a   |  t   |  i   |  o   |  n   |      |

```c
for문 시작
    
    i = 0 일때
    
    ch = str[5-i] 
    	=> ch = str[5] => ch = 'n'
    str[5-i] = str[i];
		=> str[5] = str[0] => str[5] = 'n'
    str[i] = ch
        => str[0] = ch => str[0] = 'n'
            
    i = 1 일때
    
    ch = str[5-i] 
    	=> ch = str[4] => ch = 'o'
    str[5-i] = str[i];
		=> str[4] = str[1] => str[4] = 'a'
    str[i] = ch
        => str[1] = ch => str[1] = 'o'
            
    i = 2 일때
    
    ch = str[5-i] 
    	=> ch = str[3] => ch = 'i'
    str[5-i] = str[i];
		=> str[3] = str[2] => str[3] = 't'
    str[i] = ch
        => str[2] = ch => str[2] = 'i'

    i = 3 일때

    ch = str[5-i] 
    	=> ch = str[2] => ch = 'i'
    str[5-i] = str[i];
		=> str[2] = str[3] => str[2] = 't'
    str[i] = ch
        => str[3] = ch => str[3] = 'i'

     i = 4 일때 for문 종료
```

- 최종값은 다음 표와 같음

|  0   |  1   |  2   |  3   |  4   |  5   |  6   |
| :--: | :--: | :--: | :--: | :--: | :--: | :--: |
|  n   |  o   |  t   |  i   |  a   |  n   |      |

- notian 출력함
