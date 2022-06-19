# 실습과제

### 01. Spring boot 프로젝트를 사용하여 id에 a를, pw에 b를 입력하여 로그인 버튼을 눌렀을 때 다음과 같은 alert창이 뜨도록 test.js 작성

- `getElementByID("")` 를 사용하여 생각보다 간단하게 해결 되었음
- 입력값을 가져올때는  `.value` 를 잊지 말것

```js
$(function(){
	$("#login_btn").click(function(){

		let id = document.getElementById("id").value;
		let pw = document.getElementById("pw").value;
		
		alert(`${id}:${pw}`);
	});
});
```



