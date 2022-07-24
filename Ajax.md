# 비동기통신 개념 다시잡기

### fetch의 이해와 활용

- promise: fetch를 사용했을 때 실행결과에 대한 상태를 반환해주는 약속된 객체
  - pending : 수행중
  - fullfilled: 성공적인 완료
  - rejected: 실패

- .then(): fetch 실행 결과를 .then() 괄호속 함수(callback함수)에 넣어줄게 라는 역할
- 콜백함수: 실행결과(성공의결과)를 받아줄수 있는 함수, 말그대로 호출햇을떄 결과를 그대로 돌려받는다
- status 200: 성공적으로 읽었다

> 코드 해석

```js
  let promise = fetch("hi.text").then(function(response){

		response.text().then(function(data){
    //response는 성공 실패에 대한 결과를 가지고 있는 객체
    //response 속 함수중 .text()라는함수 사용
    ///text받아온 데이터를 문자로 변형해주는 함수
    //문자로 변형된 함수를 then이라는 콜백함수를 받아 줄 수있는 함수에 넣어서 
    //그안에 있는 익명함수로 받아주는것
    //결과 값을 data라는 변수에 받아주는 것

    //fetch는 비동기 이므로 순서를 보장해주지 않음

    alert(data)
     
		})
  })
//실행시 알림창으로 hi.txt출력이됨
```

> hi.txt

```text
이것이 AJAX fetch 이다
```



- json형식
  - javascript객체 or 데이터를 주고받는 javascript의 표기법
  - `{"id": "kkk123", "name":"홍길동"}` 
  - 위와 같은 형태로 되어있음

> json 출력

```js
fetch("hi.json").then(function(response){

    return reponse.json()
    //return은 콜백함수에 대한 실행 결과임
    //fetch의 결과가 아님


}).then(function(data){
   return data.id
}).then(function(data){
    console.log(data)
})
//id만 출력이 됨
```

